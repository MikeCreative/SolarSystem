package com.mike.solarsystem;

import com.badlogic.gdx.Game;

public class SolarSystem extends Game {


    @Override
    public void create() {
        setScreen(new Play());
    }


    @Override
    public void dispose(){
        super.dispose();
//        AssetManagerClass.manager.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height){
        super.resize(width, height);
    }

    @Override
    public void pause(){
        super.pause();
    }

    @Override
    public void resume(){
        super.resume();
    }

}
