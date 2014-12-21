package com.mike.solarsystem;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Mike on 5/12/2014.
 *
 */
public class OrbitalInformation {

        private static ShapeRenderer shapeRenderer = new ShapeRenderer();


    // What is the speed etc
    // What is the current Position etc

    // Display Trajectory
    public static void trajectory(OrthographicCamera camera){

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //Circle
//        shapeRenderer.circle(Planets.getPlanet(0).getPosition().x, Planets.getPlanet(0).getPosition().y, 460);
//        shapeRenderer.circle(Planets.getPlanet(0).getPosition().x, Planets.getPlanet(0).getPosition().y, 1070);
//        shapeRenderer.circle(Planets.getPlanet(0).getPosition().x, Planets.getPlanet(0).getPosition().y, 1470);
//        shapeRenderer.circle(Planets.getPlanet(0).getPosition().x, Planets.getPlanet(0).getPosition().y, 2050);

        // Actual
        for (int i = 1; i < Globals.NUMBER_OF_PLANETS - Globals.NUMBER_OF_MOONS; i++) {
            float CircleSize = Globals.planetMaxXDistance[i] + Globals.planetStartingXDistance[i];
            float semiMinorAxis = GravitationalForce.semiMinorAxis(CircleSize, Globals.planetEccentricity[i]);
            // Eclipse
            shapeRenderer.ellipse(Planets.getPlanet(0).getPosition().x + Globals.planetStartingXDistance[i] - CircleSize, Planets.getPlanet(0).getPosition().y - semiMinorAxis / 2, CircleSize, semiMinorAxis);
        }

        for (int i = Globals.NUMBER_OF_PLANETS - Globals.NUMBER_OF_MOONS; i < Globals.NUMBER_OF_PLANETS; i++){
            float CircleSize = Globals.planetMaxXDistance[i] + Globals.planetStartingXDistance[i];
            float semiMinorAxis = GravitationalForce.semiMinorAxis(CircleSize, Globals.planetEccentricity[i]);
            // Eclipse
            shapeRenderer.ellipse(Planets.getPlanet(3).getPosition().x + Globals.planetStartingXDistance[i] - CircleSize, Planets.getPlanet(3).getPosition().y - semiMinorAxis / 2, CircleSize, semiMinorAxis);
        }

        shapeRenderer.end();

//        http://space.stackexchange.com/questions/1904/how-to-programmatically-calculate-orbital-elements-using-position-velocity-vecto

//        http://www.physics.buffalo.edu/phy410-505/2011/topic2/app1/index.html
        // Attempt first planet
//        Vector2 velocity = Planets.getPlanet(1).getLinearVelocity();
//        Vector2 position = Planets.getPlanet(1).getPosition();


    }

}
