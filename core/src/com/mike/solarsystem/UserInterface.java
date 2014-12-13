package com.mike.solarsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

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

    public static void CreateInterface(){

        // Create the User interface
        atlas = new TextureAtlas("ui/atlas.pack");
        skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);

        stage = new Stage();

        // Select Planet Box
        selectBox = new SelectBox(skin);
        selectBox.setItems(Globals.planetStrings);
        selectBox.setBounds(10, Gdx.graphics.getHeight() - 50, 300, 70);

        // Selected Planet Text
        SelectedPlanet = new Label("No Planet Currently Selected", skin);
        SelectedPlanet.setBounds(300 + 50, Gdx.graphics.getHeight() - SelectedPlanet.getHeight(), SelectedPlanet.getWidth(), SelectedPlanet.getHeight());

        // DISTANCE ITEMS
        slider = new Slider(0, 100, 1, false, skin);
        slider.setBounds(Gdx.graphics.getWidth() - 650, 50, 600, 100);

        CurrentZoom = new Label("Zoom: " , skin);
        CurrentZoom.setBounds(50, 50, 100, 50);


        stage.addActor(selectBox);
        stage.addActor(SelectedPlanet);
        stage.addActor(CurrentZoom);
//        stage.addActor(slider);

        //TODO Implement Information Window
        window = new Window("Planet Info", skin);
        window.setBounds(Gdx.graphics.getWidth() + window.getWidth(), 0, window.getWidth(), Gdx.graphics.getHeight());
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
        action.setDuration(0.5f);
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
