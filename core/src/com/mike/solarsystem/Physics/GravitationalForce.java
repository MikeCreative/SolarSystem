package com.mike.solarsystem.Physics;

import com.badlogic.gdx.math.Vector2;
import com.mike.solarsystem.Globals;
import com.mike.solarsystem.Planets.Planets;

/**
 * Created by Mike on 2/12/2014.
 * Various Equations used for different aspects of this application
 */
public class GravitationalForce {

    public static float ComputeGravity(float mass1, float mass2, float distance){
        float forceGravity;

        forceGravity = (float) ((Globals.GRAVITATIONAL_CONSTANT*mass1*mass2)/(distance*distance));

        return forceGravity;
    }

    public static double tangentalVelocity(float mass1, float mass2, float distance, int i){
        double velocity;

        // Circular
//        velocity = (double) (Globals.GRAVITATIONAL_CONSTANT*(mass2)/(distance));
        // Elliptic orbit
        velocity = (float) (Globals.GRAVITATIONAL_CONSTANT*(mass2 + mass1)*((2/distance) - (1f/Globals.planetSemiMajorAxis[i])));

        return (double) Math.sqrt(velocity);
    }


    public static double tangentalVelocityMoon(float mass1, float mass2, float distance, int i){
        double velocity;

        // Circular
//        velocity = (double) (Globals.GRAVITATIONAL_CONSTANT*(mass2)/(distance));
        // Elliptic orbit
        velocity = (float) (Globals.GRAVITATIONAL_CONSTANT*(mass2 + mass1)*((2/distance) - (1f/Globals.planetSemiMajorAxis[i])));

        return (double) Math.sqrt(velocity);
    }


    public static float getNewDensity(float mass, float radius){
        float density;

        density = (float) (mass/(Math.PI * radius * radius));

        return density;
    }

    public static double computePlanetAccelerationVector(float mass, float distance, Vector2 velocity){
        double acceleration = 0;

        double velcx = velocity.x/Math.sqrt(Globals.TIME_MULTIPLIER);
        double velcy = velocity.y/Math.sqrt(Globals.TIME_MULTIPLIER);
        acceleration = (float) ((velcx*velcx + velcy*velcy))/distance;

        return acceleration;
    }

    public static float getDistance(int planet1, int planet2){
        float distance = 0;

        distance = Planets.getPlanet(planet1).getPosition().x*Planets.getPlanet(planet1).getPosition().x + Planets.getPlanet(planet1).getPosition().y*Planets.getPlanet(planet1).getPosition().y;

        return (float) Math.sqrt(distance);
    }

    public static float pythagoras(float a, float b){
        float c = 0;
        c = a*a + b*b;

        return (float) Math.sqrt(c);
    }

    public static float semiMinorAxis(float semiMajorAxis, float eccentricity){
        float minorAxis = 0;

        minorAxis = (float) (semiMajorAxis * Math.sqrt(1 - Math.pow(eccentricity, 2)));

        return minorAxis;
    }

}
