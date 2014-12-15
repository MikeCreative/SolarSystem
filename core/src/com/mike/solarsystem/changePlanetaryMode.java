package com.mike.solarsystem;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mike on 14/12/2014.
 */
public class changePlanetaryMode {

    public static void changeMode(){
        if (Globals.MODE == Globals.SIMULATION){    // If mode was changed to simulation mode
            for (int i = 0; i < Globals.NUMBER_OF_PLANETS; i++){
                // Save Velocity + Position Stats
                Vector2 Velocity = Planets.getPlanet(i).getLinearVelocity();
                Vector2 Position = Planets.getPlanet(i).getPosition();
                float mass = Planets.getPlanet(i).getMass();

                // Create New Planet
                Planets.ResizePlanets(i, Position, Velocity, mass, Globals.planetRadiusSimulation[i]/10);
            }
        } else {                                    // If mode was changed to Arcade mode
            for (int i = 0; i < Globals.NUMBER_OF_PLANETS; i++){
                // Save Velocity + Position Stats
                Vector2 Velocity = Planets.getPlanet(i).getLinearVelocity();
                Vector2 Position = Planets.getPlanet(i).getPosition();
                float mass = Planets.getPlanet(i).getMass();

                // Create New Planet
                Planets.ResizePlanets(i, Position, Velocity, mass, Globals.planetRadiusArcade[i]/10);
            }
        }
        //TODO: Get this working - Feels wrong
        Globals.CAMERA_MOVING = true;   // Reset the Zoom Level
    }

}
