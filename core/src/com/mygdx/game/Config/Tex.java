package com.mygdx.game.Config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by artem on 10/6/17.
 */

public class Tex {
    public static float x = (float) Gdx.graphics.getWidth() / 800;
    public static float y = (float) Gdx.graphics.getHeight() / 450;

    public static Sprite groundBlock;
    public static Sprite normalPlayer;

    public Tex() {
        if (y > x) {
            x = y;
        }
        if (x > y) {
            y = x;
        }
        groundBlock = generateSprite("blockGround.png", 30 * Tex.x, 30 * Tex.y);
        normalPlayer = generateSprite("normalPlayer.png", 25 * Tex.x, 25 * Tex.y);
    }

    public Sprite generateSprite(String name, float w, float h) {
        Sprite sprite = new Sprite(new Texture(name));
        sprite.setSize(w, h);
        sprite.setOrigin(w/2, h/2);
        return sprite;
    }
}
