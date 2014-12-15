package com.mike.solarsystem;

/**
 * Created by Mike on 2/12/2014.
 *
 */
public class Globals {
    // Settings
    public static int SIMULATION = 0;
    public static int ARCADE = 1;
    public static int MODE = SIMULATION;

    //Planets + Physics
    public static String[] planetStrings = {"sun", "mercury", "venus", "moon", "mars"};
    public static float[] planetDensitiesSimulation = {1.408f, 5.427f, 5.243f, 5.514f, 3.93f};
    public static float[] planetRadiusSimulation = {696.34f, 2.5f, 6.05f, 6.37f, 3.4f};
    public static float[] planetRadiusArcade = {696.34f, 250f, 605f, 637f, 340f};
    public static float[] planetSemiMajorAxis = {1160, 2160, 2990, 4540, 15580};
    public static final int MAX_PLANETS = 20;
    public static int TIME_MULTIPLIER = 1;
    public static int MAX_TIME = 4000;
    public static int MIN_TIME = 1;
    public static double GRAVITATIONAL_CONSTANT = (6.67384*Math.pow(10, -1));
    public static int NUMBER_OF_PLANETS = 0;
    public static float[] forcesXArray = new float[MAX_PLANETS];
    public static float[] forcesYArray = new float[MAX_PLANETS];


    // Camera Settings
    public static float CAMERA_ZOOM = .1f;
    public static float MAX_ZOOM = 0.1f;
    public static float MIN_ZOOM = 0.0001f;
    public static float CAMERA_PAN = 20f;

    public static float CAMERA_X = 0;
    public static float CAMERA_Y = 0;

    public static int TRACKING_PLANET = 0;
    public static boolean TRACKING_STATE = true;    //Camera is tracking a planet. Will e false if user manually moves the camera
    public static boolean CAMERA_MOVING = false;    //New planet has been selected, move Camera to the new planet
    public static float[] PLANETZOOMLEVELSIMULATION = {0.01f, 0.0001f, 0.0001f, 0.0001f, 0.0001f, 0f};
    public static float[] PLANETZOOMLEVELARVADE = {0.07f, 0.005f, 0.015f, 0.015f, 0.01f, 0f};

    // Fling
    public static boolean FLING = false;
    public static float VelocityX;
    public static float VelocityY;


}
