package com.mike.solarsystem.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mike.solarsystem.Globals;
import com.mike.solarsystem.Physics.GravitationalForce;
import com.mike.solarsystem.Planets.Planets;

import java.text.DecimalFormat;

import static com.mike.solarsystem.UserInterface.UserInterface.getSkin;


/**
 * Created by Mike on 15/12/2014.
 */
public class InfoWindow {


    private static Window window;

    private static Label planet, velocity, distance, force, eccentricity;

    private static Slider massSlider;
    private static TextButton forceButton, accButton, velButton, pinButton, moreInfoButton;
    private static boolean[] buttonStates = {false, false, false};




    public static Window CreateInfoWindow(){

        window = new Window("", getSkin());
        window.setBounds(Gdx.graphics.getWidth() + window.getWidth(), 0, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight());
        window.setKeepWithinStage(false);
        window.padTop(100);


        planet = new Label("Planet ", getSkin());
        planet.setFontScale(1f);
        velocity = new Label("Velocity ", getSkin());
        distance = new Label("Distance ", getSkin());
        force = new Label("Force " , getSkin());
        eccentricity = new Label("e ", getSkin());

//        Mass Slider Idea
        massSlider = new Slider(0, 100, 1, false, UserInterface.getSkin());

        // Force Overlay
        forceButton = new TextButton("Forces", getSkin());
        forceButton.addListener(new ClickListener(){
        @Override
        public void clicked (InputEvent event,float x, float y) {
            if (buttonStates[0]){   // Button is active
                Globals.VectorOverlayMode -= Globals.FORCE_OVERLAY;
                buttonStates[0] = false;
            } else {
                Globals.VectorOverlayMode += Globals.FORCE_OVERLAY;
                buttonStates[0] = true;
            }
        }
        });
        accButton = new TextButton("Accel", getSkin());
        accButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event,float x, float y) {
            if (buttonStates[1]){   // Button is active
                Globals.VectorOverlayMode -= Globals.ACCEL_OVERLAY;
                buttonStates[1] = false;
            } else {
                Globals.VectorOverlayMode += Globals.ACCEL_OVERLAY;
                buttonStates[1] = true;
            }
            }
        });
        velButton = new TextButton("Velocity", getSkin());
        velButton.getStyle().checked = velButton.getStyle().down;
        velButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event,float x, float y) {
            if (buttonStates[2]){   // Button is active
                Globals.VectorOverlayMode -= Globals.VELOCITY_OVERLAY;
                buttonStates[2] = false;
            } else {
                Globals.VectorOverlayMode += Globals.VELOCITY_OVERLAY;
                buttonStates[2] = true;
            }
            }
        });

        // Pin the info window to the frame
        pinButton = new TextButton("Pin", getSkin());
        pinButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event,float x, float y) {
                Globals.pinned = !Globals.pinned;
            }
        });

        // More Information button
        moreInfoButton = new TextButton("More Information", getSkin(), "information");
        moreInfoButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event,float x, float y) {
                if (Globals.INFO){
                    moreInfo.CloseWindow();
                    Globals.INFO = false;
                } else {
                    moreInfo.OpenWindow();
                    Globals.INFO = true;
                }
                moreInfo.OpenWindow();
                System.out.println("Info button was clicked");
            }
        });


        window.row();
        window.add(planet).left().width(window.getWidth() / 1.2f);
        window.row();
        window.add(" ");
        window.row();
        window.add(velocity).left();
        window.row();
        window.add(distance).left();
        window.row();
        window.add(force).left();
        window.row();
        window.add(eccentricity).left();
        window.row();
        window.add(" ");

        window.row();
        window.add(forceButton).fillX().pad(5).height(100);
        window.row();
        window.add(accButton).fillX().pad(5).height(100);
        window.row();
        window.add(velButton).fillX().pad(5).height(100);
        window.row();
        // TODO: Layout
        window.add(moreInfoButton).fillX().pad(5).height(100);
        window.row();
        window.add(pinButton).fillX().pad(5).height(100);
        window.row();
//        window.add("");
//        window.row();
//        window.add(massSlider).fillX().pad(5);


        window.debug();
        window.top();

        return window;

    }


    public static void UpdateInformation(){
        int currentPlanet = Globals.TRACKING_PLANET;
        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat df4 = new DecimalFormat("#.0000");
        planet.setText("Planet: " + Globals.planetTitles[currentPlanet]);
        velocity.setText("v = " + df.format(GravitationalForce.pythagoras(Planets.getPlanet(currentPlanet).getLinearVelocity().x, Planets.getPlanet(currentPlanet).getLinearVelocity().y)/Math.sqrt(Globals.TIME_MULTIPLIER)) + "m/s");
        distance.setText("d = " + df.format(GravitationalForce.getDistance(currentPlanet, 0)) + "m");
        force.setText("F = " +  df.format(GravitationalForce.pythagoras(Globals.forcesXArray[currentPlanet], Globals.forcesYArray[currentPlanet])));
        eccentricity.setText("e = " + df4.format(Globals.planetEccentricity[currentPlanet]));

    }

    public static void CloseInfoWindow() {
        MoveToAction action = new MoveToAction();
        action.setPosition(window.getWidth() + Gdx.graphics.getWidth(), 0);
        action.setDuration(2f);
        if (!Globals.pinned) {
            window.addAction(action);
        }
    }

    public static void OpenInfoWindow() {
        MoveToAction action = new MoveToAction();
        action.setPosition(Gdx.graphics.getWidth() - window.getWidth(), 0);
        action.setDuration(0.5f);
        window.addAction(action);
    }
}
