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
public class Planets {


    World current_world;
    private static Body planet;

    public Planets(World world, float x, float y, float radius) {
        current_world = world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = circleShape;
        fixDef.density = 100;
        fixDef.restitution = .1f;
        fixDef.friction = .5f;


        planet = world.createBody(bodyDef);
        Fixture planetFixture = planet.createFixture(fixDef);
        planetFixture.setUserData("Planet");
        System.out.println("Planet Created" + x + " " + y);

    }

    public static void planetUpdate(){
        float x = CentralPlanet.getPositionX();
        float y = CentralPlanet.getPositionY();
        float planetX = planet.getPosition().x;
        float planetY = planet.getPosition().y;
        float distance = (float) (Math.pow((planetX-x),2) + Math.pow((planetY-y), 2));

        float rotation = (float) Math.atan((planetY - y) / (planetX - x));
        if ((x - planetX) <= 0){
            rotation = (float) Math.PI + rotation;
        }

//        System.out.println("Distance " + planet.getMass() + " " + CentralPlanet.getMass() + " " + Math.sqrt(distance));
        float gravity = GravitationalForce.ComputeGravity(planet.getMass(), CentralPlanet.getMass(), (float) Math.sqrt(distance));

        float xForce = (float) (gravity*Math.cos(rotation));
        float yForce = (float) (gravity*Math.sin(rotation));
//        System.out.println(xForce + " " +  yForce);
        planet.applyForceToCenter(xForce, yForce, true);
//        System.out.println(gravity);

        float velocity = GravitationalForce.tangentalVelocity(planet.getMass(), CentralPlanet.getMass(), (float) Math.sqrt(distance));

        float xVelocity = (float) (velocity*Math.cos(rotation + 90));
        float yVelocity = (float) (velocity*Math.sin(rotation + 90));

        planet.setLinearVelocity(xVelocity, yVelocity);

//
    }
}
