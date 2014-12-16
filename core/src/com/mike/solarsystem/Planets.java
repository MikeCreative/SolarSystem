package com.mike.solarsystem;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import static com.mike.solarsystem.Globals.*;

/**
 * Created by Mike on 2/12/2014.
 *
 */
public class Planets extends InputAdapter {


    World current_world;
    private static Body[] planet = new Body[MAX_PLANETS];
    private static Fixture[] planetFixtures = new Fixture[MAX_PLANETS];
    private static Sprite[] planetSprites = new Sprite[MAX_PLANETS];
    private static World world;


    public Planets(World world, float x, float y, float radius, float density, String string) {
        current_world = world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = circleShape;
        fixDef.density = density;
        fixDef.restitution = .1f;
        fixDef.friction = .5f;

        bodyDef.position.set(x + NUMBER_OF_PLANETS, 0);
        planet[NUMBER_OF_PLANETS] = world.createBody(bodyDef);
        planetFixtures[NUMBER_OF_PLANETS] = planet[NUMBER_OF_PLANETS].createFixture(fixDef);
        System.out.println("Mass " + planet[NUMBER_OF_PLANETS].getMass());
        planet[NUMBER_OF_PLANETS].setUserData(string);

        // Sprite
        planetSprites[NUMBER_OF_PLANETS] = new Sprite(new Texture("planets/" + Globals.planetStrings[NUMBER_OF_PLANETS] + ".png"));
        planetSprites[NUMBER_OF_PLANETS].setSize(radius*2, radius*2);
        planetSprites[NUMBER_OF_PLANETS].setOrigin(planetSprites[NUMBER_OF_PLANETS].getWidth()/2, planetSprites[NUMBER_OF_PLANETS].getHeight()/2);
        planet[NUMBER_OF_PLANETS].setUserData(planetSprites[NUMBER_OF_PLANETS]);

        if(NUMBER_OF_PLANETS != 0) {
            double velocity = GravitationalForce.tangentalVelocity(planet[NUMBER_OF_PLANETS].getMass(), planet[0].getMass(), x + NUMBER_OF_PLANETS, NUMBER_OF_PLANETS);
            System.out.println("Velocity " + velocity);
            planet[NUMBER_OF_PLANETS].setLinearVelocity(0, (float) velocity);
        }

        NUMBER_OF_PLANETS++;    // New Planet
    }

    public static void Moon(World world, float x, float y, float radius, float density,  Body MotherPlanet){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = circleShape;
        fixDef.density = density;
        fixDef.restitution = .1f;
        fixDef.friction = .5f;

        bodyDef.position.set(MotherPlanet.getPosition().x + x, MotherPlanet.getPosition().y + 0);
        planet[NUMBER_OF_PLANETS] = world.createBody(bodyDef);
        Fixture planetFixture = planet[NUMBER_OF_PLANETS].createFixture(fixDef);
        planetFixture.setUserData("Planet" + NUMBER_OF_PLANETS);
        System.out.println("Planet Created: " + planet[NUMBER_OF_PLANETS].getMass());

        // Sprite
        planetSprites[NUMBER_OF_PLANETS] = new Sprite(new Texture("planets/" + Globals.planetStrings[NUMBER_OF_PLANETS] + ".png"));
        planetSprites[NUMBER_OF_PLANETS].setSize(radius*2, radius*2);
        planetSprites[NUMBER_OF_PLANETS].setOrigin(planetSprites[NUMBER_OF_PLANETS].getWidth()/2, planetSprites[NUMBER_OF_PLANETS].getHeight()/2);
        planet[NUMBER_OF_PLANETS].setUserData(planetSprites[NUMBER_OF_PLANETS]);

        if(NUMBER_OF_PLANETS != 0) {
            double velocity = GravitationalForce.tangentalVelocity(planet[NUMBER_OF_PLANETS].getMass(), MotherPlanet.getMass(), x + MotherPlanet.getPosition().x, NUMBER_OF_PLANETS);
            System.out.println("Velocity " + velocity);

            planet[NUMBER_OF_PLANETS].setLinearVelocity(0, (float) velocity + MotherPlanet.getLinearVelocity().y);
        }
        NUMBER_OF_PLANETS++;    // New Planet
    }

    public static Fixture getFixture(int i) {
        return planetFixtures[i];
    }

    public static World getWorld() {
        return world;
    }

    @Override
    public boolean keyDown(int keycode){
        switch (keycode){
            case Input.Keys.RIGHT:
//                System.out.println("Right");
                if (Globals.TIME_MULTIPLIER < 100){
                    Globals.TIME_MULTIPLIER += 10;
                }
                break;
            case Input.Keys.LEFT:
//                System.out.println("Left");
                if (Globals.TIME_MULTIPLIER > 1) {
                    Globals.TIME_MULTIPLIER -= 1;
                }
                break;
            case Input.Keys.E:
                System.out.println("E");
                break;
            default:
                return false;
        }
        System.out.println("Multipler " + Globals.TIME_MULTIPLIER);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        if ((amount > 0 ) && (Globals.CAMERA_ZOOM < Globals.MAX_ZOOM)){
            Globals.CAMERA_ZOOM += 0.01f;
        }
        if ((amount < 0 ) && (Globals.CAMERA_ZOOM > Globals.MIN_ZOOM)){
            Globals.CAMERA_ZOOM -= 0.01f;
        }

        return true;
    }

    public static Body getPlanet(int i) {
        return planet[i];
    }


    public static void ResizePlanets(int i, Vector2 position, Vector2 velocity, float mass, float radius) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = circleShape;
        float density = GravitationalForce.getNewDensity(mass, radius);
        System.out.println("Density " + density);
        fixDef.density = density;
        fixDef.restitution = .1f;
        fixDef.friction = .5f;

        bodyDef.position.set(position);
        planet[i].destroyFixture(planetFixtures[i]);
        Fixture planetFixture = planet[i].createFixture(fixDef);
        planetFixture.setUserData("Planet" + i);
        System.out.println("Planet MASS: " + planet[i].getMass());

        // Sprite
        planetSprites[i] = new Sprite(new Texture("planets/" + Globals.planetStrings[i] + ".png"));
        planetSprites[i].setSize(radius*2, radius*2);
        planetSprites[i].setOrigin(planetSprites[i].getWidth()/2, planetSprites[i].getHeight()/2);
        planet[i].setUserData(planetSprites[i]);

        planet[i].setLinearVelocity(velocity);
    }
}
