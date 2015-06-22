package com.mike.solarsystem.Physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mike.solarsystem.Globals;
import com.mike.solarsystem.Planets.Planets;

/**
 * Created by Mike on 21/12/2014.
 * Applying a vector based overlay to show how different forces affect the body.
 *
 */
public class PhysicsOverlay {

    private static ShapeRenderer shapeRenderer =  new ShapeRenderer();
    public static float distance;

    public static void CreateVectorOverlay(OrthographicCamera camera){
        int i = Globals.TRACKING_PLANET;    // Currently only do the overlay for the tracking planet
        float x = Planets.getPlanet(i).getPosition().x;
        float y = Planets.getPlanet(i).getPosition().y;

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        if ((Globals.VectorOverlayMode & (1)) != 0){
            createForceOverlay(x, y, i);
        }
        if ((Globals.VectorOverlayMode & (1 << 1)) != 0){
            createAccelOverlay(x, y, i);
        }
        if ((Globals.VectorOverlayMode & (1 << 2)) != 0){
            createVelocityOverlay(x, y, i);
        }

        shapeRenderer.end();
    }

    public static void createForceOverlay(float x, float y, int i){
        float forcex = Globals.forcesXArray[i];
        float forcey = Globals.forcesYArray[i];
//                float forcexy = GravitationalForce.pythagoras(forcex, forcey);
//                System.out.println("Force for Overlay: " + forcex + " " +forcey);
        // Total
        shapeRenderer.line(x, y, x - forcex*100000, y - forcey*100000, Color.BLUE, Color.BLUE);
        // x Component
        shapeRenderer.line(x, y, x - forcex*100000, y, Color.BLUE, Color.BLUE);
        // y Component
        shapeRenderer.line(x, y, x , y - forcey*100000, Color.BLUE, Color.BLUE);
    }

    public static void createAccelOverlay(float x, float y, int i){
        float orbitbodyx = Planets.getPlanet(0).getPosition().x;
        float orbitbodyy = Planets.getPlanet(0).getPosition().y;
        distance = (float) (Math.pow((orbitbodyx - x), 2) + Math.pow((orbitbodyy - y), 2));
        float rotation = (float) Math.atan((orbitbodyy - y) / (orbitbodyx - x));
        if ((x - orbitbodyx) <= 0) {
            rotation = (float) Math.PI + rotation;
        }
        float acceleration = (float) GravitationalForce.computePlanetAccelerationVector(Planets.getPlanet(i).getMass(), distance, Planets.getPlanet(i).getLinearVelocity())*500;
        float accelx = (float) (acceleration * Math.cos(rotation));
        float accely = (float) (acceleration * Math.sin(rotation));



//        System.out.println("Acceleration " + accelx + " " + accely + " Rotation " + rotation);
        // Total
        shapeRenderer.line(x, y, x + accelx*100000,  y + accely*100000, Color.RED, Color.RED);
        // x Component
        shapeRenderer.line(x, y, x + accelx*100000, y, Color.RED, Color.RED);
        // y Component
        shapeRenderer.line(x, y, x, y + accely*100000, Color.RED, Color.RED);

    }

    public static void createVelocityOverlay(float x, float y, int i){
        float vx = (float) (Planets.getPlanet(i).getLinearVelocity().x/Math.sqrt(Globals.TIME_MULTIPLIER));
        float vy = (float) (Planets.getPlanet(i).getLinearVelocity().y/Math.sqrt(Globals.TIME_MULTIPLIER));
        shapeRenderer.line(x, y, x + vx*10, y + vy*10, Color.GREEN, Color.GREEN);

    }


}
