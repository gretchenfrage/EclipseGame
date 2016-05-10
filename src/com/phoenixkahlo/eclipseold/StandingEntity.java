package com.phoenixkahlo.eclipseold;

/**
 * An entity that can stand on Platforms.
 */
public interface StandingEntity extends Entity {

	/**
	 * Only expected to work correctly when called after StandingEntity.pre/postUpdate() in a given update cycle.
	 */
	boolean onPlatform();

	/**
	 * Is expected to return whatever platform was standing on during the call to preUpdate(), and thus can only
	 * be expected to acurately return what platform is on if onPlatform() == true.
	 */
	Platform getPlatform();

	/**
	 * Has same limitations as getPlatform.
	 */
	double getPlatformAngle();
	
}
