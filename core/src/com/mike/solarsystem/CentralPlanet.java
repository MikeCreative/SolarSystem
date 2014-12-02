package com.mike.solarsystem;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Mike on 2/12/2014.
 */
public class CentralPlanet {

    World current_world;
    private static Body planet;

    public CentralPlanet(World world, float x, float y, float radius) {
        current_world = world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = circleShape;
        fixDef.density = 100000;
        fixDef.restitution = .1f;
        fixDef.friction = .5f;

        planet = world.createBody(bodyDef);
        Fixture planetFixture = planet.createFixture(fixDef);
        planetFixture.setUserData("Planet");
        System.out.println("Sun Created" + x + " " + y);

    }


    public static float getMass() {
        return planet.getMass();
    }

    public static float getPositionX() {
        return planet.getPosition().x;
    }

    public static float getPositionY() {
        return planet.getPosition().y;
    }

    public static Body getBody() {
        return planet;
    }
}
