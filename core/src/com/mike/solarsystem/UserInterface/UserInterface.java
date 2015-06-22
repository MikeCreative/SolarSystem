package com.mike.solarsystem.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mike.solarsystem.Globals;
import com.mike.solarsystem.Physics.Physics;

/**
 * Created by Mike on 13/12/2014.
 *
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

    private static TextButton changeMode, openMenu;

    public static void CreateInterface() {

        // Create the User interface
        atlas = new TextureAtlas("ui/atlas.pack");
        skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);

        stage = new Stage();

        // Select Planet Box
        selectBox = new SelectBox(skin);
        selectBox.setItems(Globals.planetTitles);
        selectBox.setBounds(0, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 15, Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 15);
        selectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("I was changed " + selectBox.getSelectedIndex());
                Globals.TRACKING_PLANET = selectBox.getSelectedIndex();
//                selectBox.setSelectedIndex(0);
                Globals.TRACKING_STATE = true;
                Globals.CAMERA_MOVING = true;
            }
        });

        // Selected Planet Text
        SelectedPlanet = new Label("No Planet Currently Selected", skin);
        SelectedPlanet.setBounds(selectBox.getX() + selectBox.getWidth(), Gdx.graphics.getHeight() - SelectedPlanet.getHeight() - selectBox.getHeight()/4, SelectedPlanet.getWidth(), SelectedPlanet.getHeight());

        //Time Items
        timeSlider = new Slider(Globals.MIN_TIME, Globals.MAX_TIME, Globals.TIME_MULTIPLIER, false, skin);
        timeSlider.setValue(Globals.TIME_MULTIPLIER);
        timeSlider.setBounds(Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/20, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/15);
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
        CurrentTime.setBounds(timeSlider.getX(), timeSlider.getY() - Gdx.graphics.getHeight()/20, CurrentTime.getWidth(), CurrentTime.getHeight());

        openMenu = new TextButton("Info", skin, "information");
        openMenu.setBounds(Gdx.graphics.getWidth() - openMenu.getWidth(), 0, openMenu.getWidth(), openMenu.getHeight());
        openMenu.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event,float x, float y) {
                System.out.println("Clicked");
                InfoWindow.OpenInfoWindow();
            }
        });


        stage.addActor(selectBox);
        stage.addActor(SelectedPlanet);
        stage.addActor(timeSlider);
        stage.addActor(CurrentTime);
//        stage.addActor(openMenu);


        stage.addActor(InfoWindow.CreateInfoWindow());
        stage.addActor(moreInfo.createMoreInfoWindow());


    }

    public static void updateUI(){
        if (Globals.TRACKING_STATE) {
            if (Globals.CAMERA_MOVING) {
                SelectedPlanet.setText("Camera Moving...  " + Globals.planetTitles[Globals.TRACKING_PLANET]);
                InfoWindow.OpenInfoWindow();
            } else {
                SelectedPlanet.setText("Tracking: " + Globals.planetTitles[Globals.TRACKING_PLANET]);
            }
        } else {
            SelectedPlanet.setText("Select Planet");
            InfoWindow.CloseInfoWindow();
        }

        CurrentTime.setText("Current Time: " + Globals.TIME_MULTIPLIER + "x");
        timeSlider.setValue(Globals.TIME_MULTIPLIER);
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

    public static SelectBox getComboBox() {
        return selectBox;
    }
}
