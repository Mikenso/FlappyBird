package com.flappy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappy.game.Flappy;
import com.flappy.game.sprites.Bird;
import com.flappy.game.sprites.Scores;
import com.flappy.game.sprites.Tube;

/**
 * Created by Mike on 18.10.2016.
 */
public class PlayState extends State {



    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -60;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;

    private Scores score;

    private  int startScore;


    private Array<Tube> tubes;

    private  BitmapFont font;




    public PlayState(GameStateManger gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        cam.setToOrtho(false, Flappy.WIDTH /2, Flappy.HEIGHT /2 );
        bg = new Texture("bg3.png");
        ground= new Texture("ground.png");
        score = new Scores();

        groundPosition1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((cam.position.x - cam.viewportWidth/2) +ground.getWidth(),GROUND_Y_OFFSET);

        tubes = new Array<Tube>();
        startScore = 0;

        for (int i = 1; i < TUBE_COUNT ; i++) {
     tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
       handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size ; i++) {
           Tube tube = tubes.get(i);



             if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                     tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                 startScore +=1;
                 }

             if (tube.collides(bird.getBounds()))   {
                if (startScore > score.getHighScore()) score.setHighScore(startScore);
                 gsm.set(new PlayState(gsm));
                  }



        }
        if (bird.getPosition().y < ground.getHeight() + GROUND_Y_OFFSET ) {
            if (startScore > score.getHighScore())  score.setHighScore(startScore);
            gsm.set(new PlayState(gsm));
        }

        cam.update();
    }

    @Override
    public void redner(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        font = new BitmapFont();

        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes       ) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPosition1.x, groundPosition1.y);
        sb.draw(ground, groundPosition2.x, groundPosition2.y);
        font.draw(sb, String.valueOf(startScore), bird.getPosition().x, 352);
        sb.end();
    }

    @Override
    public void dispose() {
     bg.dispose();
     bird.dispose();
        font.dispose();
        ground.dispose();

        for (Tube tube : tubes   ) {
            tube.dispose();
        }
        System.out.println("Play State Disposed");
    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth /2 ) > (groundPosition1.x + ground.getWidth())) {
            groundPosition1.add(ground.getWidth() * 2, 0 );
        }
        if (cam.position.x - (cam.viewportWidth /2 ) > (groundPosition2.x + ground.getWidth())) {
            groundPosition2.add(ground.getWidth() * 2, 0 );
        }
    }
}
