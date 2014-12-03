package com.mike.solarsystem;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import static com.mike.solarsystem.Globals.*;

/**
 * Created by Mike on 2/12/2014.
 */
public class Planets {


    World current_world;
    private static Body[] planet = new Body[MAX_PLANETS];



    public Planets(World world, float x, float y, float radius) {
        current_world = world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = circleShape;
        fixDef.density = 1000;
        fixDef.restitution = .1f;
        fixDef.friction = .5f;

        bodyDef.position.set(x + NUMBER_OF_PLANETS*200, 0);
        planet[NUMBER_OF_PLANETS] = world.createBody(bodyDef);
        Fixture planetFixture = planet[NUMBER_OF_PLANETS].createFixture(fixDef);
        planetFixture.setUserData("Planet" + NUMBER_OF_PLANETS);
        System.out.println("Planet Created: " + NUMBER_OF_PLANETS);

        if(NUMBER_OF_PLANETS != 0) {
            float velocity = GravitationalForce.tangentalVelocity(planet[NUMBER_OF_PLANETS].getMass(), planet[0].getMass(), (float) x + NUMBER_OF_PLANETS * 200);
            System.out.println(velocity);
            planet[NUMBER_OF_PLANETS].setLinearVelocity(0, velocity);
        }
        NUMBER_OF_PLANETS++;    // New Planet Created

    }

    public static Body getPlanet(int i) {
        return planet[i];
    }



}
