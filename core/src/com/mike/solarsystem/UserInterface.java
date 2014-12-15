package com.mike.solarsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Mike on 13/12/2014.
 */
public class UserInterface {

    private static Stage stage;

    private static TextureAtlas atlas;
    private static Skin skin;

    private static String[] testString = {"one", "two", "three", "four"};
    private static int SelectedIndex = 0;

    // UI Elements
    private static SelectBox selectBox;
    private static Label SelectedPlanet, CurrentZoom, CurrentTime;

    private static Slider zoomSlider, timeSlider;

    private static TextButton changeMode;

    public static void CreateInterface() {

        // Create the User interface
        atlas = new TextureAtlas("ui/atlas.pack");
        skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);

        stage = new Stage();

        // Select Planet Box
        selectBox = new SelectBox(skin);
        selectBox.setItems(Globals.planetStrings);
        selectBox.setBounds(0, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 15, Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 15);

        // Selected Planet Text
        SelectedPlanet = new Label("No Planet Currently Selected", skin);
        SelectedPlanet.setBounds(selectBox.getX() + selectBox.getWidth(), Gdx.graphics.getHeight() - SelectedPlanet.getHeight(), SelectedPlanet.getWidth(), SelectedPlanet.getHeight());

        // DISTANCE ITEMS
        zoomSlider = new Slider(Globals.MIN_ZOOM, Globals.MAX_ZOOM, Globals.MIN_ZOOM, false, skin);
        zoomSlider.setValue(Globals.CAMERA_ZOOM);
        zoomSlider.setBounds(50, 100, 600, 100);
        zoomSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Globals.CAMERA_ZOOM = zoomSlider.getValue();
            }
        });

        CurrentZoom = new Label("Zoom: ", skin);
        CurrentZoom.setBounds(50, 50, 100, 50);

        //Time Items
        timeSlider = new Slider(Globals.MIN_TIME, Globals.MAX_TIME, Globals.TIME_MULTIPLIER, false, skin);
        timeSlider.setValue(Globals.TIME_MULTIPLIER);
        timeSlider.setBounds(100 + zoomSlider.getWidth(), 100, 600, 100);
        timeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int currentTime = Globals.TIME_MULTIPLIER;
                int newTime = (int) timeSlider.getValue();
                Globals.TIME_MULTIPLIER = newTime;
                Physics.updatePlanetVelocity((float) currentTime, (float) newTime);
            }
        });

        CurrentTime = new Label("Current Time: 1x", skin);
        CurrentTime.setBounds(timeSlider.getX(), 100, CurrentTime.getWidth(), CurrentTime.getHeight());



        changeMode = new TextButton("Change Mode ", skin);
        changeMode.setBounds(0, 250, changeMode.getWidth(), changeMode.getHeight());
        changeMode.addListener(new ClickListener(){
        @Override
        public void clicked (InputEvent event,float x, float y) {
            if (Globals.MODE == Globals.SIMULATION) {
                Globals.MODE = Globals.ARCADE;

            } else {
                Globals.MODE = Globals.SIMULATION;
            }
        }
    });


        stage.addActor(selectBox);
        stage.addActor(SelectedPlanet);
        stage.addActor(CurrentZoom);
        stage.addActor(zoomSlider);
        stage.addActor(changeMode);
        stage.addActor(timeSlider);
        stage.addActor(CurrentTime);


        stage.addActor(InfoWindow.CreateInfoWindow());

    }

    public static void updateUI(){
        if (Globals.TRACKING_STATE) {
            if (Globals.CAMERA_MOVING) {
                SelectedPlanet.setText("Camera Moving...  " + Globals.planetStrings[Globals.TRACKING_PLANET]);
                InfoWindow.OpenInfoWindow();
            } else {
                SelectedPlanet.setText("Tracking: " + Globals.planetStrings[Globals.TRACKING_PLANET]);
            }
        } else {
            SelectedPlanet.setText("Currently Selected Planet: NONE");
            InfoWindow.CloseInfoWindow();
        }

        CurrentTime.setText("Current Time: " + Globals.TIME_MULTIPLIER + "x");
        timeSlider.setValue(Globals.TIME_MULTIPLIER);
        zoomSlider.setValue(Globals.CAMERA_ZOOM);

        CurrentZoom.setText("Zoom: " + Globals.CAMERA_ZOOM);
        if (selectBox.getSelectedIndex() != SelectedIndex){
            System.out.println("Box was changed");
            SelectedIndex = selectBox.getSelectedIndex();
            Globals.TRACKING_PLANET = SelectedIndex;
            Globals.TRACKING_STATE = true;
            Globals.CAMERA_MOVING = true;
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/60f));
        stage.draw();
    }



    //TODO;
    public static void dispose(){
        stage.dispose();

    }

    public static Stage getStage() {
        return stage;
    }

    public static Skin getSkin() {
        return skin;
    }
}
