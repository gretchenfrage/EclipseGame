package old.com.phoenixkahlo.roomgame.networking.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class StreamUtils {

	public static void writeByteArray(byte[] array, OutputStream out) throws IOException {
		out.write(intToBytes(array.length));
		out.write(array);
	}

	public static byte[] readByteArray(InputStream in) throws IOException {
		byte[] head = new byte[4];
		in.read(head);
		byte[] body = new byte[bytesToInt(head)];
		in.read(body);
		return body;
	}

	public static void writeString(String string, OutputStream out) throws IOException {
		writeByteArray(stringToBytes(string), out);
	}

	public static String readString(InputStream in) throws IOException {
		return bytesToString(readByteArray(in));
	}

	public static void writeInt(int n, OutputStream out) throws IOException {
		out.write(intToBytes(n));
	}

	public static int readInt(InputStream in) throws IOException {
		byte[] bytes = new byte[4];
		in.read(bytes);
		return bytesToInt(bytes);
	}

	public static void writeLong(long n, OutputStream out) throws IOException {
		out.write(longToBytes(n));
	}

	public static long readLong(InputStream in) throws IOException {
		byte[] bytes = new byte[8];
		in.read(bytes);
		return bytesToLong(bytes);
	}

	public static void writeDouble(double n, OutputStream out) throws IOException {
		out.write(doubleToBytes(n));
	}

	public static double readDouble(InputStream in) throws IOException {
		byte[] bytes = new byte[8];
		in.read(bytes);
		return bytesToDouble(bytes);
	}

	public static void writeFloat(float n, OutputStream out) throws IOException {
		out.write(floatToBytes(n));
	}

	public static float readFloat(InputStream in) throws IOException {
		byte[] bytes = new byte[4];
		in.read(bytes);
		return bytesToFloat(bytes);
	}

	public static void writeShort(short n, OutputStream out) throws IOException {
		out.write(shortToBytes(n));
	}

	public static short readShort(InputStream in) throws IOException {
		byte[] bytes = new byte[2];
		in.read(bytes);
		return bytesToShort(bytes);
	}

	public static void writeChar(char c, OutputStream out) throws IOException {
		out.write(charToBytes(c));
	}

	public static char readChar(InputStream in) throws IOException {
		byte[] bytes = new byte[2];
		in.read(bytes);
		return bytesToChar(bytes);
	}

	public static void writeBoolean(boolean b, OutputStream out) throws IOException {
		out.write(b ? 1 : 0);
	}

	public static boolean readBoolean(InputStream in) throws IOException {
		return in.read() != 0;
	}

	public static byte[] intToBytes(int n) {
		return ByteBuffer.allocate(4).putInt(n).array();
	}

	public static int bytesToInt(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt();
	}

	public static byte[] longToBytes(long n) {
		return ByteBuffer.allocate(4).putDouble(n).array();
	}

	public static long bytesToLong(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getLong();
	}

	public static byte[] doubleToBytes(double n) {
		return ByteBuffer.allocate(8).putDouble(n).array();
	}

	public static double bytesToDouble(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getDouble();
	}

	public static byte[] floatToBytes(float n) {
		return ByteBuffer.allocate(4).putFloat(n).array();
	}

	public static float bytesToFloat(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getFloat();
	}

	public static byte[] shortToBytes(short n) {
		return ByteBuffer.allocate(2).putShort(n).array();
	}

	public static short bytesToShort(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getShort();
	}

	public static byte[] charToBytes(char c) {
		return ByteBuffer.allocate(2).putChar(c).array();
	}

	public static char bytesToChar(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getChar();
	}

	public static byte[] stringToBytes(String string) {
		return string.getBytes(StandardCharsets.UTF_8);
	}

	public static String bytesToString(byte[] bytes) {
		return new String(bytes, StandardCharsets.UTF_8);
	}

}
