package com.mike.solarsystem.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mike.solarsystem.Globals;
import com.mike.solarsystem.Planets.Planets;

/**
 * Created by USER on 7/12/2014.
 */
public class CameraHandler {

    // Camera Move
    private static float cameraX, cameraY, currentX, currentY, differenceX, differenceY;
    // Camera Zoom
    private static float currentZoom, desiredZoom, Zoomdifference;


    // TODO: Play with the values - Issue here at max speed
    public static void CameraHandler(OrthographicCamera camera){

        camera.zoom = Globals.CAMERA_ZOOM;
        cameraX = camera.position.x;
        cameraY = camera.position.y;

        if (Globals.TRACKING_STATE){    // Camera is tracking planet

            if (Globals.CAMERA_MOVING) {
                //ZOOM
                currentZoom = Globals.CAMERA_ZOOM;
                if (Globals.MODE == Globals.SIMULATION){
                    desiredZoom = Globals.PLANETZOOMLEVELSIMULATION[Globals.TRACKING_PLANET];
                } else {
                    desiredZoom = Globals.PLANETZOOMLEVELARVADE[Globals.TRACKING_PLANET];
                }
                Zoomdifference = currentZoom - desiredZoom;

                // ZOOM
                currentX = Planets.getPlanet(Globals.TRACKING_PLANET).getPosition().x;
                currentY = Planets.getPlanet(Globals.TRACKING_PLANET).getPosition().y;

                differenceX = cameraX - currentX;
                differenceY = cameraY - currentY;
                Globals.CAMERA_ZOOM = currentZoom - Zoomdifference/10;

                if ((Math.abs(differenceX) < 0.02) && (Math.abs(differenceY) < 0.02) && (Math.abs(Zoomdifference) < 0.01) ){
                    System.out.println("Camera Move Successful");
                    Globals.CAMERA_MOVING = false;
                }
//                System.out.println("Distance Camera Planet " + differenceX + " " + differenceY);


                // TODO: Different Levels of Camera Movement
                if ((Math.abs(differenceX) < 0.2) && (Math.abs(differenceY) < 0.2) && (Math.abs(Zoomdifference) < 0.01) ) {
                    Globals.CAMERA_X = cameraX - differenceX;
                    Globals.CAMERA_Y = cameraY - differenceY;
                } else {
                    Globals.CAMERA_X = cameraX - differenceX/5;
                    Globals.CAMERA_Y = cameraY - differenceY/5;
                }
            } else {
             Globals.CAMERA_X = Planets.getPlanet(Globals.TRACKING_PLANET).getPosition().x;
                Globals.CAMERA_Y = Planets.getPlanet(Globals.TRACKING_PLANET).getPosition().y;
            }
            camera.position.set(Globals.CAMERA_X, Globals.CAMERA_Y, 0);
        } else {

            // If flinging
            if (Globals.FLING){
                Globals.VelocityX *= 0.98f;
                Globals.VelocityY *= 0.98f;

                Globals.CAMERA_X += -Globals.VelocityX * Gdx.graphics.getDeltaTime();
                Globals.CAMERA_Y += Globals.VelocityY * Gdx.graphics.getDeltaTime();

                camera.position.set(Globals.CAMERA_X, Globals.CAMERA_Y, 0);
                if (Math.abs(Globals.VelocityX) < 0.01f) {
                    Globals.FLING = false;
                    Globals.VelocityX = 0;
                }
                if (Math.abs(Globals.VelocityY) < 0.01f) {
                    Globals.FLING = false;
                    Globals.VelocityY = 0;
                }
            }
            camera.position.set(Globals.CAMERA_X, Globals.CAMERA_Y, 0);
        }

        camera.update();




    }




}
