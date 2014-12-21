package com.mike.solarsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

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

    private Planets planet;
    FPSLogger fpsLogger;
    private int mode = Globals.SIMULATION;
//    private RayHandler rayHandler;


    private Array<Body> tmpBodies = new Array<Body>();


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);   // Green background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        planetUpdate();
        OrbitalInformation.trajectory(camera);

        // Check Mode
        if (Globals.MODE != mode){
            mode = Globals.MODE;
            changePlanetaryMode.changeMode();
        }



        // Images
        // TODO: add depth so that the right objects are drawn first etc
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        world.getBodies(tmpBodies);
        for (Body body : tmpBodies){
            if(body.getUserData() instanceof Sprite){
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x - sprite.getWidth()/2, body.getPosition().y - sprite.getHeight()/2);
//                sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
                sprite.draw(batch);
            }

        }
        batch.end();


        // UI Elements
        UserInterface.updateUI();
        InfoWindow.UpdateInformation();
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



        for (int i = 0; i < 9; i++) {
            planet = new Planets(world, Globals.planetStartingXDistance[i], 0, Globals.planetRadiusSimulation[i], Globals.planetDensitiesSimulation[i]/10, Globals.planetStrings[i]);
        }

        //TODO: Get the Moon Working
        //Moon
        Planets.Moon(world, Globals.planetStartingXDistance[9], 0, Globals.planetRadiusSimulation[9], Globals.planetDensitiesSimulation[9]/10, Planets.getPlanet(3));

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
