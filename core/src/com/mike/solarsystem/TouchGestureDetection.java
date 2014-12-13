package com.mike.solarsystem;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;

/**
 * Created by USER on 7/12/2014.
 */
public class TouchGestureDetection implements GestureDetector.GestureListener {

    public static float initalScale;
    public static float initialPositionX, initialPositionY;

    protected Body hitBody = null;
    Vector3 testPoint = new Vector3();
    QueryCallback callback = new QueryCallback() {
        @Override public boolean reportFixture (Fixture fixture) {
            // if the hit point is inside the fixture of the body
            // we report it
            if (fixture.testPoint(testPoint.x, testPoint.y)) {
                hitBody = fixture.getBody();
                return false;
            } else
                return true;
        }
    };

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        Globals.FLING = false;

        // For Body Touch
//        Play.getCamera().unproject(testPoint.set(x,y,0));
//
//        hitBody = null;
//        //TODO Get this working, perhaps make test poitns bigger + x + y, I dunno, does this mean venter of screen? Put it in tap?
//        Play.getWorld().QueryAABB(callback, testPoint.x - 1000f, testPoint.y - 1000f, testPoint.x + 1000f, testPoint.y + 1000f);
//
//        if (hitBody != null) {
//            System.out.println("I touched: " + hitBody.getUserData());
//            for (int i = 0; i < 5; i++){
//                if (hitBody.getUserData().equals(Globals.planetStrings[i])){
//                    System.out.println("Planet " + i);
//                    Globals.TRACKING_PLANET = i;    // Start Tracking Touched Planet
//                    Globals.TRACKING_STATE = true;
//                }
//            }
//        }

        // For Zoom
        initalScale = Globals.CAMERA_ZOOM;
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        System.out.println("tap");
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        System.out.println("long Press");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        Globals.FLING = true;
        Globals.VelocityX = Globals.CAMERA_ZOOM * velocityX * Globals.CAMERA_PAN;
        Globals.VelocityY = Globals.CAMERA_ZOOM * velocityY * Globals.CAMERA_PAN;
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Globals.FLING = false;
        Globals.TRACKING_STATE = false; // User is moving the camera around, so stop tracking the current planet
        Globals.CAMERA_X += -deltaX*Globals.CAMERA_ZOOM*Globals.CAMERA_PAN;
        Globals.CAMERA_Y += deltaY*Globals.CAMERA_ZOOM*Globals.CAMERA_PAN;
        System.out.println("pan X: " + x + " " + y + " " + deltaX + " " + deltaY);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        System.out.println("panStop");
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        float ratio = initialDistance / distance;

        Globals.CAMERA_ZOOM = MathUtils.clamp(initalScale * ratio, Globals.MIN_ZOOM, Globals.MAX_ZOOM);
        System.out.println("zoom");


        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
