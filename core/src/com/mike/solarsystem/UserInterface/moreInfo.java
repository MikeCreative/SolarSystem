package com.mike.solarsystem.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mike.solarsystem.Globals;

import static com.mike.solarsystem.UserInterface.UserInterface.*;

/**
 * Created by Mike on 8/01/2015.
 */
public class moreInfo {

    private static Window moreInfoWindow;
    private static TextButton exitButton;
    private static Texture imageTexture1, imageTexture2, imageTexture3, imageTexture4;
    private static Image image;

    private static ScrollPane scrollPane;

    public static Window createMoreInfoWindow(){
        moreInfoWindow = new Window(" ", getSkin());
        moreInfoWindow.setBounds(50, - Gdx.graphics.getHeight(), Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
        moreInfoWindow.setKeepWithinStage(false);

        exitButton = new TextButton("Close", getSkin(), "information");
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event,float x, float y) {
                CloseWindow();
                Globals.INFO = false;
            }
        });


        // Image
        imageTexture1 = new Texture("info/Overlay.png");
        imageTexture2 = new Texture("info/GravitationalForces.png");
        imageTexture3  = new Texture("info/test2.png");
        imageTexture4 = new Texture("info/test3.png");
        image = new Image(imageTexture1);
        image.setBounds(moreInfoWindow.getX(), moreInfoWindow.getY(), moreInfoWindow.getWidth(), moreInfoWindow.getHeight());


        // Scroll Pane
        String[] strings = new String[] {"Gravity", "Forces", "Circular Orbit", "Elliptical Orbit", "Planetary Rotation", "Component Forces"};

        final List list = new List<String>(getSkin());
        list.setItems(strings);

        scrollPane = new ScrollPane(list, getSkin());
        scrollPane.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switch (list.getSelectedIndex()) {
                    case 0:
                        image.setDrawable(new SpriteDrawable(new Sprite(imageTexture1)));
                        break;
                    case 1:
                        image.setDrawable(new SpriteDrawable(new Sprite(imageTexture2)));
                        break;
                    case 2:
                        image.setDrawable(new SpriteDrawable(new Sprite(imageTexture3)));
                        break;
                    case 3:
                        image.setDrawable(new SpriteDrawable(new Sprite(imageTexture4)));
                        break;
                    default:
                        break;
                }
            }
        });

        moreInfoWindow.add(image).fill();
        moreInfoWindow.add(scrollPane).top();
        moreInfoWindow.row();
        moreInfoWindow.add(" ");
        moreInfoWindow.add(exitButton).right();
        moreInfoWindow.debug();
        return moreInfoWindow;
    }

    public static void CloseWindow() {
        MoveToAction action = new MoveToAction();
        action.setPosition(moreInfoWindow.getX(), -moreInfoWindow.getHeight());
        action.setDuration(0.5f);
        moreInfoWindow.addAction(action);
    }

    public static void OpenWindow() {
        MoveToAction action = new MoveToAction();
        action.setPosition(moreInfoWindow.getX(), 0);
        action.setDuration(0.5f);
        moreInfoWindow.addAction(action);
        moreInfoWindow.toFront();
    }
}
