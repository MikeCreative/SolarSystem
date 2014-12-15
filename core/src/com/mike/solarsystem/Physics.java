package com.mike.solarsystem;

import java.util.Currency;

/**
 * Created by Mike on 3/12/2014.
 *
 */
public class Physics {


    public static void planetUpdate() {

        for (int i = 0; i < Globals.NUMBER_OF_PLANETS; i++) {

            float x = Planets.getPlanet(i).getPosition().x;
            float y = Planets.getPlanet(i).getPosition().y;
            float xForce = 0, yForce = 0;       // Reset Forces
            float distance = 0;
            float rotation = 0;
            float gravity = 0;

            for (int j = 0; j < Globals.NUMBER_OF_PLANETS; j++){
                if (i != j) {   // For all except the same planet
                    float planetX = Planets.getPlanet(j).getPosition().x;
                    float planetY = Planets.getPlanet(j).getPosition().y;

                    distance = (float) (Math.pow((planetX - x), 2) + Math.pow((planetY - y), 2));
//                    System.out.println("Distance " + distance);

                    rotation = (float) Math.atan((planetY - y) / (planetX - x));
                    if ((x - planetX) <= 0) {
                        rotation = (float) Math.PI + rotation;
                    }
                    gravity = GravitationalForce.ComputeGravity(Planets.getPlanet(i).getMass(), Planets.getPlanet(j).getMass(), (float) Math.sqrt(distance));
                    xForce += (float) (gravity * Math.cos(rotation));
                    yForce += (float) (gravity * Math.sin(rotation));
                }
            }
            if ( i == 1) {
//                System.out.println("Force Out " + gravity*Globals.TIME_MULTIPLIER + " Current Speed " + Planets.getPlanet(i).getLinearVelocity() + " Acceleration Force " + GravitationalForce.computePlanetAccelerationVector(Planets.getPlanet(i).getMass(),(float) Math.sqrt(distance), Planets.getPlanet(i).getLinearVelocity())*Planets.getPlanet(i).getMass());
            }
            Planets.getPlanet(i).applyForceToCenter((float) (-xForce*Globals.TIME_MULTIPLIER),(float) (-yForce*Globals.TIME_MULTIPLIER), true);
        }
    }

    public static void updatePlanetVelocity(double currentTime, double newTime){
        //TODO: Issue here
        for (int i = 0; i < Globals.NUMBER_OF_PLANETS; i++){
            double currentVelocityX = Planets.getPlanet(i).getLinearVelocity().x;
            double currentVelocityY = Planets.getPlanet(i).getLinearVelocity().y;
            double newVelocityX;
            double newVelocityY;
            System.out.println("Divisor " + newTime/currentTime);
            newVelocityX = currentVelocityX * Math.sqrt(newTime/currentTime);
            newVelocityY = currentVelocityY * Math.sqrt(newTime/currentTime);
            Planets.getPlanet(i).setLinearVelocity((float) newVelocityX,(float) newVelocityY);
        }
    }
}
