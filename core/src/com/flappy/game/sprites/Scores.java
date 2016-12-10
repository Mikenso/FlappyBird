package com.flappy.game.sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.flappy.game.Flappy;

/**
 * Created by Mike on 20.10.2016.
 */
public class Scores {


    private  Integer score;


public  String getScoreString () {
    return String.valueOf(score);
}
    String s;



    public Scores() {
        score = 0;
    }

    Preferences prefs = Gdx.app.getPreferences("preseren");



    public void incScore() {
        score+=1;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return prefs.getInteger("topScore");

    }

    public void setHighScore() {
        prefs.putInteger("topScore", score);
        prefs.flush();
    }

    public boolean recordNew() {
        return getScore() > getHighScore();
    }

}

