package com.mike.solarsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.text.DecimalFormat;


/**
 * Created by Mike on 15/12/2014.
 */
public class InfoWindow {


    private static Window window;

    private static Label planet, velocity, distance, force;

    private static Slider massSlider;
    private static TextButton forceButton, accButton, velButton;




    public static Window CreateInfoWindow(){

        window = new Window("Information", UserInterface.getSkin());
        window.setBounds(Gdx.graphics.getWidth() + window.getWidth(), 0, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight());
        window.setKeepWithinStage(false);
        window.padTop(100);

        planet = new Label("Planet ", UserInterface.getSkin());
        velocity = new Label("Velocity ", UserInterface.getSkin());
        distance = new Label("Distance ", UserInterface.getSkin());
        force = new Label("Force " , UserInterface.getSkin());

        // Mass Slider Idea
//        massSlider = new Slider(0, 100, 1, false, UserInterface.getSkin());

        // Force Overlay
        forceButton = new TextButton("Forces", UserInterface.getSkin());
        forceButton.addListener(new ClickListener(){
        @Override
        public void clicked (InputEvent event,float x, float y) {
            System.out.println("Add Force Overlay");
             }
        });
        accButton = new TextButton("Accel", UserInterface.getSkin());
        accButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event,float x, float y) {
                System.out.println("Add Acc Overlay");
            }
        });
        velButton = new TextButton("Velocity", UserInterface.getSkin());
        velButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event,float x, float y) {
                System.out.println("Add Velocity Overlay");
            }
        });


        window.row();
        window.add(planet).left();
        window.row();
        window.add(velocity).left();
        window.row();
        window.add(distance).left();
        window.row();
        window.add(force).left();
//        window.row();
//        window.add(massSlider);
        window.row();
        window.add(forceButton);
        window.add(accButton);
        window.row();
        window.add(velButton);

        return window;

    }


    public static void UpdateInformation(){
        int currentPlanet = Globals.TRACKING_PLANET;
        DecimalFormat df = new DecimalFormat("#.00");
        planet.setText("Planet: " + Globals.planetStrings[currentPlanet]);
        velocity.setText("v = " + df.format(GravitationalForce.pythagoras(Planets.getPlanet(currentPlanet).getLinearVelocity().x, Planets.getPlanet(currentPlanet).getLinearVelocity().y)/Math.sqrt(Globals.TIME_MULTIPLIER)) + "m/s");
        distance.setText("d = " + df.format(GravitationalForce.getDistance(currentPlanet, 0)) + "m");
        force.setText("F = " +  df.format(GravitationalForce.pythagoras(Globals.forcesXArray[currentPlanet], Globals.forcesYArray[currentPlanet])));


    }

    public static void CloseInfoWindow() {
        MoveToAction action = new MoveToAction();
        action.setPosition(window.getWidth() + Gdx.graphics.getWidth(), 0);
        action.setDuration(2f);
        window.addAction(action);
    }

    public static void OpenInfoWindow() {
        MoveToAction action = new MoveToAction();
        action.setPosition(Gdx.graphics.getWidth() - window.getWidth(), 0);
        action.setDuration(0.5f  );
        window.addAction(action);

    }
}
