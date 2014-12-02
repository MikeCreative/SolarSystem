package com.mike.solarsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import static com.mike.solarsystem.Planets.planetUpdate;

/**
 * Created by Mike on 2/12/2014.
 */
public class Play implements Screen {

    // World Variables
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;

    private SpriteBatch batch;

    float timestep = 1 / 60f;

    private CentralPlanet centralPlanet;
    private Planets planet;

    private RayHandler rayHandler;



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);   // Green background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        planetUpdate();
        camera.update();
//        rayHandler.setCombinedMatrix(camera.combined);
        rayHandler.updateAndRender();

        debugRenderer.render(world, camera.combined);

        world.step(timestep, 8, 3);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void show() {

        world = new World(new Vector2(), true);
        camera = new OrthographicCamera();
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        centralPlanet = new CentralPlanet(world, 0, 0, 20);
        planet = new Planets(world, 100, 0, 3);

        rayHandler = new RayHandler(world);
        PointLight pointLight = new PointLight(rayHandler, 10, new Color(1,1,1,1), 1, 10, 10);
        pointLight.attachToBody(CentralPlanet.getBody(), 0, 0);




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
        rayHandler.dispose();
    }
}
