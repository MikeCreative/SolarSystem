package com.mike.solarsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import java.text.DecimalFormat;


/**
 * Created by Mike on 15/12/2014.
 */
public class InfoWindow {


    private static Window window;

    private static Label planet, velocity, distance, force;




    public static Window CreateInfoWindow(){

        window = new Window("Information", UserInterface.getSkin());
        window.setBounds(Gdx.graphics.getWidth() + window.getWidth(), 0, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight());
        window.setKeepWithinStage(false);
        window.padTop(100);

        planet = new Label("Planet ", UserInterface.getSkin());
        velocity = new Label("Velocity ", UserInterface.getSkin());
        distance = new Label("Distance ", UserInterface.getSkin());
        force = new Label("Force " , UserInterface.getSkin());


        window.row();
        window.add(planet).left();
        window.row();
        window.add(velocity).left();
        window.row();
        window.add(distance).left();
        window.row();
        window.add(force).left();
        window.row();

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
