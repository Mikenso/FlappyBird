package com.flappy.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Mike on 18.10.2016.
 */
public class GameStateManger {

    private Stack<State> states;

    public GameStateManger() {
        this.states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    public void update (float dt) {
        states.peek().update(dt);
    }

    public void render (SpriteBatch sb) {
        states.peek().redner(sb);
    }
}
