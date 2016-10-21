package com.flappy.game.sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Mike on 20.10.2016.
 */
public class Scores {
   private int score;



    Preferences prefs = Gdx.app.getPreferences("preseren");



    public void incScore() {
        score+=1;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHighScore() {
        return prefs.getInteger("topScore");

    }

    public void setHighScore(int score) {
        prefs.putInteger("topScore", score);
        prefs.flush();
    }




    public static void main(String[] args) {

    }
}

