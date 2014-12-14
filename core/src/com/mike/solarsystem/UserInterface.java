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

import javafx.scene.shape.MoveTo;

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
    private static Label SelectedPlanet, CurrentZoom;

    private static Slider slider;
    private static Window window;

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
        slider = new Slider(Globals.MIN_ZOOM, Globals.MAX_ZOOM, Globals.MIN_ZOOM, false, skin);
        slider.setValue(Globals.CAMERA_ZOOM);
        slider.setBounds(50, 100, 600, 100);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Globals.CAMERA_ZOOM = slider.getValue();
            }
        });

        CurrentZoom = new Label("Zoom: ", skin);
        CurrentZoom.setBounds(50, 50, 100, 50);

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
        stage.addActor(slider);
        stage.addActor(changeMode);

        //TODO Implement Information Window
        window = new Window("Planet Info", skin);
        window.setBounds(Gdx.graphics.getWidth() + window.getWidth(), 0, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight());
        window.setKeepWithinStage(false);


        stage.addActor(window);

    }

    public static void updateUI(){
        if (Globals.TRACKING_STATE) {
            if (Globals.CAMERA_MOVING) {
                SelectedPlanet.setText("Camera Moving...  " + Globals.planetStrings[Globals.TRACKING_PLANET]);
                OpenInfoWindow();
            } else {
                SelectedPlanet.setText("Tracking: " + Globals.planetStrings[Globals.TRACKING_PLANET]);
            }
        } else {
            SelectedPlanet.setText("Currently Selected Planet: NONE");
            CloseInfoWindow();
        }

        slider.setValue(Globals.CAMERA_ZOOM);

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

    private static void CloseInfoWindow() {
        MoveToAction action = new MoveToAction();
        action.setPosition(window.getWidth() + Gdx.graphics.getWidth(), 0);
        action.setDuration(2f);
        window.addAction(action);
    }

    private static void OpenInfoWindow() {
        MoveToAction action = new MoveToAction();
        action.setPosition(Gdx.graphics.getWidth() - window.getWidth(), 0);
        action.setDuration(0.5f  );
        window.addAction(action);

    }

    //TODO;
    public static void dispose(){
        stage.dispose();

    }

    public static Stage getStage() {
        return stage;
    }
}
