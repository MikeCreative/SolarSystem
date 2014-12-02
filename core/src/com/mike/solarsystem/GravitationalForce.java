package com.mike.solarsystem;

/**
 * Created by Mike on 2/12/2014.
 */
public class GravitationalForce {

    public static float ComputeGravity(float mass1, float mass2, float distance){
        float forceGravity;

        forceGravity = (float) ((Globals.GRAVITATIONAL_CONSTANT*mass1*mass2)/(distance*distance));

        return forceGravity;
    }

    public static float tangentalVelocity(float mass1, float mass2, float distance){
        float velocity;

        velocity = (float) (Globals.GRAVITATIONAL_CONSTANT*(mass1 + mass2)/distance);

        return (float) Math.sqrt(velocity);
    }

}
