package com.flappy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappy.game.Flappy;
import com.flappy.game.sprites.Scores;

/**
 * Created by Mike on 18.10.2016.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playButton;
    private  BitmapFont font;


    public MenuState(GameStateManger gsm) {
        super(gsm);
        cam.setToOrtho(false, Flappy.WIDTH / 2, Flappy.HEIGHT / 2);
        this.background = new Texture("bg.png");
        this.playButton = new Texture("playButton.png");


    }

    @Override
    public void handleInput() {
      if (Gdx.input.justTouched()) {
     gsm.set(new PlayState(gsm));
       }
    }

    @Override
    public void update(float dt) {
     handleInput();
    }

    @Override
    public void redner(SpriteBatch sb) {
        font = new BitmapFont();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playButton,cam.position.x - playButton.getWidth() /2 , cam.position.y /2 -52);
        font.draw(sb, String.valueOf(new Scores().getHighScore()), cam.position.x /2, cam.position.y *2  -52);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        font.dispose();
        System.out.println("Menu State Disposed");
    }
}
