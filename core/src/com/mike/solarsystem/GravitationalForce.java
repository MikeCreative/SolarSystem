package com.mike.solarsystem;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mike on 2/12/2014.
 */
public class GravitationalForce {

    public static float ComputeGravity(float mass1, float mass2, float distance){
        float forceGravity;

        forceGravity = (float) ((Globals.GRAVITATIONAL_CONSTANT*mass1*mass2)/(distance*distance));

        return forceGravity;
    }

    public static double tangentalVelocity(float mass1, float mass2, float distance, int i){
        double velocity;

        velocity = (double) (Globals.GRAVITATIONAL_CONSTANT*(mass2)/(distance));

        // Elliptic orbit
//        velocity = (float) (Globals.GRAVITATIONAL_CONSTANT*(mass2)*((2/distance) - (1/Globals.planetSemiMajorAxis[i])));
//

        return (double) Math.sqrt(velocity);
    }

    public static float getNewDensity(float mass, float radius){
        float density;

        density = (float) (mass/(Math.PI * radius * radius));

        return density;
    }

    public static double computePlanetAccelerationVector(float mass, float distance, Vector2 velocity){
        double acceleration = 0;

        acceleration = (float) ((velocity.x*velocity.x + velocity.y*velocity.y))/distance;

        return acceleration;
    }

}
