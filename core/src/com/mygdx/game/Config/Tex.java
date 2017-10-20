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
    public static Sprite digButton;
    public static Sprite marker_tonnel_1;
    public static Sprite bannedSectorCave3x5;
    public static Sprite bannedSectorCave3x7;
    public static Sprite marker_tonnel_2;
    public static Sprite marker_tonnel_3;
    public static Sprite marker_tonnel_4;
    public static Sprite marker_cave_3x5_1;
    public static Sprite marker_cave_3x7_1;
    public static Sprite marker_cave_3x5_2;
    public static Sprite marker_cave_3x7_2;
    public static Sprite dug_tonnel_1;
    public static Sprite acceptButton;
    public static Sprite bg;
    public static Sprite button_1_1;
    public static Sprite button_1_2;
    public static Sprite caveButton;
    public static Sprite backButton;
    public static Sprite cave3x5;
    public static Sprite cave3x7;
    public static Sprite cave3x5Button;
    public static Sprite cave3x7Button;
    public static Sprite cave3x5Button_active;
    public static Sprite cave3x7Button_active;
    public static Sprite disallowBlock;
    public static Sprite fog;

    public Tex() {
        if (y > x) {
            x = y;
        }
        if (x > y) {
            y = x;
        }
        groundBlock = generateSprite("blockGround.png", 38f * Tex.x, 38f * Tex.y);
        normalPlayer = generateSprite("normalPlayer.png", 30 * Tex.x, 30 * Tex.y);
        digButton = generateSprite("button_1_tonnel.png", 80 * Tex.x, 80 * Tex.y);
        acceptButton = generateSprite("button_1_ok.png", 80 * Tex.x, 80 * Tex.y);
        marker_tonnel_1 = generateSprite("marker_tonnel_2.png", 30 * Tex.x, 30 * Tex.y);
        marker_tonnel_2 = generateSprite("marker_tonnel_1.png", 30 * Tex.x, 30 * Tex.y);
        marker_tonnel_3 = generateSprite("marker_tonnel_3.png", 30 * Tex.x, 30 * Tex.y);
        marker_tonnel_4 = generateSprite("marker_tonnel_4.png", 30 * Tex.x, 30 * Tex.y);
        bannedSectorCave3x5 = generateSprite("marker_cave_3x5_3.png", 30 * 5 *  Tex.x, 30 * 3 * Tex.y);
        bannedSectorCave3x7 = generateSprite("marker_cave_3x7_3.png", 30 * 7 *  Tex.x, 30 * 3 * Tex.y);
        marker_cave_3x5_1 = generateSprite("marker_cave_3x5_1.png", 30 * 5 * Tex.x, 30 * 3 * Tex.y);
        marker_cave_3x7_1 = generateSprite("marker_cave_3x7_1.png", 30 * 7 * Tex.x, 30 * 3 * Tex.y);
        marker_cave_3x5_2 = generateSprite("marker_cave_3x5_2.png", 30 * 5 * Tex.x, 30 * 3 * Tex.y);
        marker_cave_3x7_2 = generateSprite("marker_cave_3x7_2.png", 30 * 7 * Tex.x, 30 * 3 * Tex.y);
        dug_tonnel_1 = generateSprite("dug_tonnel_1.png", 30 * Tex.x, 30 * Tex.y);
        bg = generateSprite("background_64х64_cave_1.png", 30 * Tex.x, 30 * Tex.y);
        button_1_1 = generateSprite("button_1_1.png", 80 * Tex.x, 80 * Tex.y);
        button_1_2 = generateSprite("button_1_2.png", 80 * Tex.x, 80 * Tex.y);
        caveButton = generateSprite("button_1_cave.png", 80 * Tex.x, 80 * Tex.y);
        backButton = generateSprite("button_1_back.png", 80 * Tex.x, 80 * Tex.y);
        cave3x5Button = generateSprite("button_1_cave_3x5.png", 80 * Tex.x, 80 * Tex.y);
        cave3x7Button = generateSprite("button_1_cave_3x7.png", 80 * Tex.x, 80 * Tex.y);
        cave3x5Button_active = generateSprite("button_2_cave_3x5.png", 80 * Tex.x, 80 * Tex.y);
        cave3x7Button_active = generateSprite("button_2_cave_3x7.png", 80 * Tex.x, 80 * Tex.y);
        cave3x5 = generateSprite("cave_3x5_1.png", 30 * 5 * Tex.x, 30 * 3 * Tex.y - 6 * Tex.y);
        cave3x7 = generateSprite("cave_3x7_1.png", 30 * 7 * Tex.x, 30 * 3 * Tex.y - 7 * Tex.y);
        disallowBlock = generateSprite("earth_stone_1.png", 38f * Tex.x, 38f * Tex.y);
        fog = generateSprite("fog_1.png", 30f * Tex.x, 30f * Tex.y);
    }

    public Sprite generateSprite(String name, float w, float h) {
        Sprite sprite = new Sprite(new Texture(name));
        sprite.setSize(w, h);
        sprite.setOrigin(w / 2, h / 2);
        return sprite;
    }
}
