package com.mike.solarsystem;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mike on 5/12/2014.
 *
 */
public class OrbitalInformation {

        private static ShapeRenderer shapeRenderer = new ShapeRenderer();


    // What is the speed etc
    // What is the current Position etc

    // Display Trajectory
    public static void trajectory(){

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.circle(0, 0, 100);
//        shapeRenderer.end();

//        http://space.stackexchange.com/questions/1904/how-to-programmatically-calculate-orbital-elements-using-position-velocity-vecto

//        http://www.physics.buffalo.edu/phy410-505/2011/topic2/app1/index.html
        // Attempt first planet
        Vector2 velocity = Planets.getPlanet(1).getLinearVelocity();
        Vector2 position = Planets.getPlanet(1).getPosition();


    }

}
