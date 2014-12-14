package com.mike.solarsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import static com.mike.solarsystem.Globals.*;
import static com.mike.solarsystem.Physics.planetUpdate;

/**
 * Created by Mike on 2/12/2014.
 */
public class Play implements Screen {

    // World Variables
    private static World world;
    private Box2DDebugRenderer debugRenderer;
    private static OrthographicCamera camera;

    private SpriteBatch batch;

    float timestep = 1 / 60f;

    private CentralPlanet centralPlanet;
    private Planets planet;
    FPSLogger fpsLogger;
    private int mode = Globals.SIMULATION;
//    private RayHandler rayHandler;



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);   // Green background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);


        planetUpdate();
        OrbitalInformation.trajectory();

        // Check Mode
        if (Globals.MODE != mode){
            mode = Globals.MODE;
            changePlanetaryMode.changeMode();
        }

        // UI Elements
        UserInterface.updateUI();

        CameraHandler.CameraHandler(camera);



        fpsLogger.log();
//        rayHandler.setCombinedMatrix(camera.combined);
//        rayHandler.updateAndRender();

        debugRenderer.render(world, camera.combined);

        world.step(timestep, 8, 3);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = Gdx.graphics.getWidth()*30;
        camera.viewportHeight = Gdx.graphics.getHeight()*30;
    }

    @Override
    public void show() {
        fpsLogger = new FPSLogger();

        world = new World(new Vector2(), true);
        camera = new OrthographicCamera();
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

//        centralPlanet = new CentralPlanet(world, 0, 0, 20);

        // Central Planet - SUN
        planet = new Planets(world, 0, 0, Globals.planetRadiusSimulation[0], Globals.planetDensitiesSimulation[0], "SUN");
        // Mercury
        planet = new Planets(world, 5700, 0, Globals.planetRadiusSimulation[1], Globals.planetDensitiesSimulation[1], "MERCURY");
        // Venus
        planet = new Planets(world, 10800, 0, Globals.planetRadiusSimulation[2], Globals.planetDensitiesSimulation[2], "VENUS");
        // Earth
        planet = new Planets(world, 15000, 0, Globals.planetRadiusSimulation[3], Globals.planetDensitiesSimulation[3], "EARTH");
        // Mars
        planet = new Planets(world, 22800, 0, Globals.planetRadiusSimulation[4], Globals.planetDensitiesSimulation[4], "MARS");
//        planet = new Planets(world, 350, 0, 4);

//        Planets.Moon(world, 10, 0, 0.5f, Planets.getPlanet(3));
//        Planets.Moon(world, -10, 0, 0.5f, Planets.getPlanet(3));

        // UI Elements
        UserInterface.CreateInterface();

        // Touch Detection
        TouchGestureDetection touchGestureDetection = new TouchGestureDetection();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(planet);
        inputMultiplexer.addProcessor(UserInterface.getStage());
        inputMultiplexer.addProcessor(new GestureDetector(touchGestureDetection));

        Gdx.input.setInputProcessor(inputMultiplexer);

//        rayHandler = new RayHandler(world);
//        PointLight pointLight = new PointLight(rayHandler, 10, new Color(1,1,1,1), 1, 10, 10);
//        pointLight.attachToBody(CentralPlanet.getBody(), 0, 0);


    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
        batch.dispose();
//        rayHandler.dispose();
    }

    public static OrthographicCamera getCamera() {
        return camera;
    }

    public static World getWorld() {
        return world;
    }
}
