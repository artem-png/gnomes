package com.mygdx.game.models.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Config.Tex;
import com.mygdx.game.models.blocks.ABlock;
import com.mygdx.game.models.blocks.Cave;
import com.mygdx.game.models.blocks.GroundBlock;
import com.mygdx.game.models.map.IMap;
import com.mygdx.game.process.GameProcess;

/**
 * Created by artem on 10/9/17.
 */

public class BgMap implements IMap {

    public void act(SpriteBatch batch, int i, int j) {
        if (i < 0 || j < 0) {
            return;
        }
        if (GameProcess.blockMap.blocks[i][j] == null || GameProcess.blockMap.blocks[i][j] instanceof Cave) {
            batch.draw(Tex.bg, i * 30 * Tex.x, j * 30 * Tex.y, 30 * Tex.x, 30 * Tex.y);
        }
    }

    @Override
    public void act(SpriteBatch batch) {

    }

    @Override
    public boolean add(Object object) {
        return true;
    }

    @Override
    public void dispose() {

    }
}
