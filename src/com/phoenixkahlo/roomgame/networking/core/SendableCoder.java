package com.phoenixkahlo.roomgame.networking.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SendableCoder {

	public static interface SendableCoderEntry {

		boolean isType(Sendable sendable);
		
		Sendable create(InputStream in) throws IOException, BadDataException;
		
		void write(OutputStream out, Sendable sendable) throws IOException;
		
	}
	
	private Map<Integer, SendableCoderEntry> entries = new HashMap<Integer, SendableCoderEntry>();
	
	public void register(int id, SendableCoderEntry entry) throws RuntimeException {
		if (entries.containsKey(id) || id < 0)
			throw new RuntimeException("Duplicate sendable headers");
		else
			entries.put(id, entry);
	}
	
	public Sendable read(InputStream in) throws IOException, BadDataException {
		int id = StreamUtils.readInt(in);
		if (entries.containsKey(id)) {
			return entries.get(id).create(in);
		} else {
			throw new IOException("Header " + id + " not registered");
		}
	}
	
	public void write(OutputStream out, Sendable sendable) throws IOException, RuntimeException {
		for (Map.Entry<Integer, SendableCoderEntry> entry : entries.entrySet()) {
			if (entry.getValue().isType(sendable)) {
				StreamUtils.writeInt(entry.getKey(), out);
				entry.getValue().write(out, sendable);
				return;
			}
		}
		throw new RuntimeException("Sendable " + sendable + " not registered");
	}

	public void register(int id, Class<? extends Sendable> clazz) throws RuntimeException {
		try {
			Constructor<? extends Sendable> constructor = clazz.getConstructor(InputStream.class);
			register(id, new SendableCoderEntry() {
				
				@Override
				public boolean isType(Sendable sendable) {
					return sendable.getClass() == clazz;
				}
				
				@Override
				public Sendable create(InputStream in) throws IOException {
					try {
						return constructor.newInstance(in);
					} catch (InvocationTargetException e) {
						throw (IOException) e.getTargetException();
					} catch (InstantiationException | IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
						throw new RuntimeException("Sendable class invalidly designed for reflection SendableCoderEntry");
					}
				}

				@Override
				public void write(OutputStream out, Sendable sendable) throws IOException {
					try {
						clazz.getMethod("write", OutputStream.class).invoke(sendable, out);
					} catch (InvocationTargetException e) {
						throw (IOException) e.getTargetException();
					} catch (IllegalAccessException | IllegalArgumentException | SecurityException
							| NoSuchMethodException e) {
						e.printStackTrace();
						throw new RuntimeException("Sendable class invalidly designed for reflection SendableCoderEntry");
					}
				}
				
			});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new RuntimeException("Sendable class invalidly designed for reflection SendableCoderEntry");
		}
	}
	
	public void register(int id, Sendable singleton) throws RuntimeException {
		register(id, new SendableCoderEntry() {
			
			@Override
			public boolean isType(Sendable sendable) {
				return sendable == singleton;
			}
			
			@Override
			public Sendable create(InputStream in) {
				return singleton;
			}

			@Override
			public void write(OutputStream out, Sendable sendable) throws IOException {}
			
		});
	}
	
	public static interface SendableTypeChecker {
		
		boolean isType(Sendable sendable);
		
	}
	
	public static interface SendableFactory {
		
		Sendable create(InputStream in) throws IOException;
		
	}
	
	public static interface SendableWriter {
		void write(OutputStream out, Sendable sendable);
	}
	
	public void register(int id, SendableTypeChecker typeChecker, SendableFactory factory,
			SendableWriter writer) throws RuntimeException {
		register(id, new SendableCoderEntry() {
			
			@Override
			public boolean isType(Sendable sendable) {
				return typeChecker.isType(sendable);
			}
			
			@Override
			public Sendable create(InputStream in) throws IOException {
				return factory.create(in);
			}

			@Override
			public void write(OutputStream out, Sendable sendable) throws IOException {
				writer.write(out, sendable);
			}
			
		});
	}
	
}
