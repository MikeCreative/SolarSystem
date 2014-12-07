package com.mike.solarsystem;

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

            for (int j = 0; j < Globals.NUMBER_OF_PLANETS; j++){
                if (i != j) {   // For all except the same planet
                    float planetX = Planets.getPlanet(j).getPosition().x;
                    float planetY = Planets.getPlanet(j).getPosition().y;

                    distance = (float) (Math.pow((planetX - x), 2) + Math.pow((planetY - y), 2));

//                    System.out.println("distance " + distance);
                    rotation = (float) Math.atan((planetY - y) / (planetX - x));
                    if ((x - planetX) <= 0) {
                        rotation = (float) Math.PI + rotation;
                    }

                    float gravity = GravitationalForce.ComputeGravity(Planets.getPlanet(i).getMass(), Planets.getPlanet(j).getMass(), (float) Math.sqrt(distance));
                    xForce += (float) (gravity * Math.cos(rotation));
                    yForce += (float) (gravity * Math.sin(rotation));

//                    System.out.println("Force x " + xForce + " Force y " + yForce);
//                    System.out.println("Distance" + distance);
                }
            }
//                Planets.getPlanet(i).setLinearVelocity(xVelocity, yVelocity);
//            System.out.println("Force" + xForce + " " + yForce);
            Planets.getPlanet(i).applyForceToCenter(-xForce, -yForce, true);


        }
    }


}

//for (int i = 0; i < Globals.NUMBER_OF_PLANETS; i++) {
//        float x = CentralPlanet.getPositionX();
//        float y = CentralPlanet.getPositionY();
//        float planetX = Planets.getPlanet(i).getPosition().x;
//        float planetY = Planets.getPlanet(i).getPosition().y;
//        float distance = (float) (Math.pow((planetX - x), 2) + Math.pow((planetY - y), 2));
//
//        float rotation = (float) Math.atan((planetY - y) / (planetX - x));
//        if ((x - planetX) <= 0) {
//        rotation = (float) Math.PI + rotation;
//        }
//
//        float gravity = GravitationalForce.ComputeGravity(Planets.getPlanet(i).getMass(), CentralPlanet.getMass(), (float) Math.sqrt(distance));
//
//        float xForce = (float) (gravity * Math.cos(rotation));
//        float yForce = (float) (gravity * Math.sin(rotation));
//        Planets.getPlanet(i).applyForceToCenter(xForce / 2, yForce / 2, true);
//
//        CentralPlanet.getBody().applyForceToCenter(-xForce / 2, -yForce / 2, true);
//
//        float velocity = GravitationalForce.tangentalVelocity(Planets.getPlanet(i).getMass(), CentralPlanet.getMass(), (float) Math.sqrt(distance));
//
//        float xVelocity = (float) (velocity * Math.cos(rotation + Math.PI / 2));
//        float yVelocity = (float) (velocity * Math.sin(rotation + Math.PI / 2));
//
//        Planets.getPlanet(i).setLinearVelocity(xVelocity, yVelocity);
//        }
//        }