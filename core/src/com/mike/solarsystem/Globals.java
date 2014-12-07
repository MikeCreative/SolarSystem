package com.mike.solarsystem;

/**
 * Created by Mike on 2/12/2014.
 *
 */
public class Globals {

    public static String[] planetStrings = {"SUN", "MERCURY", "VENUS", "EARTH", "MARS"};

    public static final int MAX_PLANETS = 20;

    public static int TIME_MULTIPLIER = 1;

    public static double GRAVITATIONAL_CONSTANT = (6.67384*Math.pow(10, -1));

    public static int NUMBER_OF_PLANETS = 0;

    // Camera Settings
    public static float CAMERA_ZOOM = 1f;
    public static float MAX_ZOOM = 2;
    public static float MIN_ZOOM = 0.001f;

    public static float CAMERA_X = 0;
    public static float CAMERA_Y = 0;

    public static int TRACKING_PLANET = 0;
    public static boolean TRACKING_STATE = true;  //Camera is tracking a planet. Will e false if user manually moves the camera


}
