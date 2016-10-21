package com.flappy.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Mike on 18.10.2016.
 */
public class Bird {
    private static  final int GRAvITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private Texture texture;
    private Sound sound;

    private  Animation birdAnimation;

    private Rectangle bounds;

    public Bird(int x, int y) {
        this.position =new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture ("bird12.png");

         texture = new Texture("birdanimation1.png");

        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);

        bounds = new Rectangle(x, y, bird.getWidth() /3 , bird.getHeight());

        sound = Gdx.audio.newSound(Gdx.files.internal("steps.ogg"));

    }

    public void update(float dt) {
        birdAnimation.update(dt);
    if (position.y > 0)
        velocity.add(0, GRAvITY, 0);
      velocity.scl(dt);
        position.add(MOVEMENT*dt, velocity.y, 0);

        if (position.y < 0) {
            position.y =0;
        }

        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump() {
        velocity.y = 250;
        sound.setVolume(sound.play(), 0.03f);
       // sound.play();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        sound.dispose();
    }
}
