package com.mike.solarsystem;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import jdk.nashorn.internal.objects.Global;

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


        if (Globals.VectorOverlayMode == Globals.NO_OVERLAY){
            // do nothing
        } else if(Globals.VectorOverlayMode == Globals.FORCE_OVERLAY){
            createForceOverlay(x, y, i);
        } else if (Globals.VectorOverlayMode == Globals.ACCEL_OVERLAY){
            createAccelOverlay(x, y, i);
        } else if (Globals.VectorOverlayMode == Globals.VELOCITY_OVERLAY){
            createVelocityOverlay(x, y, i);
        } else if (Globals.VectorOverlayMode == (Globals.FORCE_OVERLAY + Globals.ACCEL_OVERLAY)){
            createAccelOverlay(x, y, i);
            createForceOverlay(x, y, i);
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
        float acceleration = (float) GravitationalForce.computePlanetAccelerationVector(Planets.getPlanet(i).getMass(), distance, Planets.getPlanet(i).getLinearVelocity());
        float accelx = (float) (acceleration * Math.cos(rotation));
        float accely = (float) (acceleration * Math.sin(rotation));


        // TODO: Fix dependancy on Velocity /Math.sqrt(Globals.Time) etc
//        System.out.println("Acceleration " + accelx*1000000 + " " + accely*1000000 + " Rotation " + rotation);
        // Total
        shapeRenderer.line(x, y, x + accelx*10000000,  y + accely*10000000, Color.RED, Color.RED);
        // x Component
        shapeRenderer.line(x, y, x + accelx*10000000, y, Color.RED, Color.RED);
        // y Component
        shapeRenderer.line(x, y, x, y + accely*10000000, Color.RED, Color.RED);

    }

    public static void createVelocityOverlay(float x, float y, int i){
        float vx = (float) (Planets.getPlanet(i).getLinearVelocity().x/Math.sqrt(Globals.TIME_MULTIPLIER));
        float vy = (float) (Planets.getPlanet(i).getLinearVelocity().y/Math.sqrt(Globals.TIME_MULTIPLIER));
        shapeRenderer.line(x, y, x + vx*10, y + vy*10, Color.GREEN, Color.GREEN);

    }


}
