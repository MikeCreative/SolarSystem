package com.mike.solarsystem;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by USER on 7/12/2014.
 */
public class CameraHandler {

    private static float cameraX, cameraY, currentX, currentY;

    public static void CameraHandler(OrthographicCamera camera){

        camera.zoom = Globals.CAMERA_ZOOM;
        cameraX = camera.position.x;
        cameraY = camera.position.y;

        if (Globals.TRACKING_STATE){    // Camera is tracking planet

            currentX = Planets.getPlanet(Globals.TRACKING_PLANET).getPosition().x;
            currentY = Planets.getPlanet(Globals.TRACKING_PLANET).getPosition().y;
//            System.out.println("Camera X"  + cameraX + " Currentx " + currentX);
//            if (cameraX != currentX){
//                //Must animate Camera
//                cameraX += (cameraX - currentX*0.001f);
//                camera.position.x = cameraX;
//            } else {
//                Globals.CAMERA_X = currentX;
//                camera.position.x = Globals.CAMERA_X;
//            }
//
//            if (cameraY != currentY){
//                //Must animate Camera
//                cameraY += (cameraY - currentY*0.001f);
//                camera.position.y = cameraY;
//            } else {
//                Globals.CAMERA_Y = currentY;
//                camera.position.y = Globals.CAMERA_Y;
//            }
            Globals.CAMERA_X = Planets.getPlanet(Globals.TRACKING_PLANET).getPosition().x;
            Globals.CAMERA_Y = Planets.getPlanet(Globals.TRACKING_PLANET).getPosition().y;

            //TODO: Animate Camera Movement

            // Check to see if camera is currently on point



            camera.position.set(Planets.getPlanet(Globals.TRACKING_PLANET).getPosition().x, Planets.getPlanet(Globals.TRACKING_PLANET).getPosition().y, 0);
            // Keep updating camera positions incase user moves off planet

        } else {
            camera.position.set(Globals.CAMERA_X, Globals.CAMERA_Y, 0);
        }

        camera.update();




    }




}
