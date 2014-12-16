package com.mike.solarsystem;

/**
 * Created by Mike on 2/12/2014.
 *
 */
public class Globals {
    // Settings
    public static int SIMULATION = 0;
    public static int ARCADE = 1;
    public static int MODE = ARCADE;

    //Planets + Physics
    public static String[] planetStrings = {"sun", "mercury", "venus", "earth", "mars", "jupiter", "saturn", "uranus", "neptune", "moon"};
    public static String[] planetTitles = {"Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Moon"};
    public static float[] planetDensitiesSimulation = {1.408f, 5.427f, 5.243f, 5.514f, 3.93f, 1.326f, 0.687f, 1.27f, 1.638f, 3.34f};
    public static float[] planetRadiusSimulation = {69.634f, .25f, .605f, .637f, .34f, 6.91f, 5.82f, 2.536f, 2.4622f, 0.174f};
    public static float[] planetRadiusArcade = {69.634f, 25f, 60.5f, 63.7f, 34f, 69.11f*2, 58.2f*2, 253.6f, 246.22f, 1.7f};
    public static float[] planetMaxXDistance = {0f, 700, 1090, 1520, 2490, 8170, 15100, 30000, 45500, 3.6f};
    public static float[] planetStartingXDistance = {0f, 460, 1070,1470,2050,7410, 13500, 27500, 44500, 3.8f};
    public static float[] planetSemiMajorAxis = {(planetMaxXDistance[0] + planetStartingXDistance[0])/2, (planetMaxXDistance[1] + planetStartingXDistance[1])/2, (planetMaxXDistance[2] + planetStartingXDistance[2])/2, (planetMaxXDistance[3] + planetStartingXDistance[3])/2, (planetMaxXDistance[4] + planetStartingXDistance[4])/2, (planetMaxXDistance[5] + planetStartingXDistance[5])/2, (planetMaxXDistance[6] + planetStartingXDistance[6])/2, (planetMaxXDistance[7] + planetStartingXDistance[7])/2, (planetMaxXDistance[8] + planetStartingXDistance[8])/2, (planetMaxXDistance[9] + planetStartingXDistance[9])/2};
    public static float[] planetEccentricity = {0, 0.2056f, 0.0068f, 0.0167f, 0.0934f, 0.0484f, 0.0542f, 0.0472f, 0.0086f, 0.0549f};



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
    public static float MAX_ZOOM = 2f;
    public static float MIN_ZOOM = 0.0001f;
    public static float CAMERA_PAN = 20f;

    public static float CAMERA_X = 0;
    public static float CAMERA_Y = 0;

    public static int TRACKING_PLANET = 0;
    public static boolean TRACKING_STATE = true;    //Camera is tracking a planet. Will e false if user manually moves the camera
    public static boolean CAMERA_MOVING = false;    //New planet has been selected, move Camera to the new planet
    public static float[] PLANETZOOMLEVELSIMULATION = {0.5f, 0.0001f, 0.0001f, 0.0001f, 0.0001f, 0.001f, 0.001f, 0.001f, 0.001f};
    public static float[] PLANETZOOMLEVELARVADE = {0.5f, 0.005f, 0.015f, 0.015f, 0.01f, 0.01f, 0.2f, 0.2f, 0.2f};

    // Fling
    public static boolean FLING = false;
    public static float VelocityX;
    public static float VelocityY;


}
