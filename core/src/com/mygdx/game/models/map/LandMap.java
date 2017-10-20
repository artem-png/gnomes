package com.mygdx.game.models.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Config.Tex;
import com.mygdx.game.Layout.GameLayout;
import com.mygdx.game.process.GameProcess;

import jdk.nashorn.internal.ir.Block;

/**
 * Created by User on 14.10.2017.
 */

public class LandMap {
    public BlockMap blockMap;
    public BgMap bgMap;

    public LandMap(BlockMap blockMap) {
        this.blockMap = blockMap;
        bgMap = new BgMap();
    }

    public void act(SpriteBatch batch) {
        Vector3 xy = GameLayout.camera.unproject(new Vector3(0, Gdx.graphics.getHeight(), 0));
        float x = xy.x - 30 * 10 * Tex.y;
        float y = xy.y - 30 * 5 * Tex.y;
        float w = GameLayout.camera.viewportWidth * GameLayout.camera.zoom + Gdx.graphics.getWidth() / GameLayout.camera.zoom / 2 + 30 * 20 * Tex.x;
        float h = GameLayout.camera.viewportHeight * GameLayout.camera.zoom + Gdx.graphics.getHeight() / GameLayout.camera.zoom / 2 + 30 * 10 * Tex.x;

        x = x / (30 * Tex.x);
        y = y / (30 * Tex.y);
        int actualX = (int) x;
        int actualY = (int) y;

        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        w = w / (30 * Tex.x / GameLayout.camera.zoom) + x;
        int actualW = (int) w;

        if (w + x >= BlockMap.sizeX) {
            w = BlockMap.sizeX - x;
            if (w + x >= BlockMap.sizeX) {
                w--;
            }
        }

        h = h / (30 * Tex.x / GameLayout.camera.zoom) + y;
        int actualH = (int) h;

        if (h + y >= BlockMap.sizeY) {
            h = BlockMap.sizeY - y;
            if (h + x >= BlockMap.sizeY) {
                h--;
            }
        }
        //System.out.println(x + " " + y + " " + w + " " + h);
        for (int i = (int) x; i < (int) (w + x); i++) {
            for (int j = (int) y; j < (int) (h + y); j++) {
                bgMap.act(batch, i, j);
            }
        }


        ////---------
        if (x == 0) {
            if (y == 0) {
                for (int i = -1; i < 0; i++) {
                    for (int j = -1; j < 0; j++) {
                        batch.draw(Tex.disallowBlock, i * 30 * Tex.x, j * 30 * Tex.y, Tex.disallowBlock.getWidth(), Tex.disallowBlock.getHeight());
                    }
                }
            }
            for (int i = -1; i < 0; i++) {
                for (int j = (int) y; j < (int) (h + y); j++) {
                    batch.draw(Tex.disallowBlock, i * 30 * Tex.x, j * 30 * Tex.y, Tex.disallowBlock.getWidth(), Tex.disallowBlock.getHeight());
                }
            }
            if (y + h == BlockMap.sizeY) {
                for (int i = -1; i < 0; i++) {
                    for (int j = BlockMap.sizeY; j < BlockMap.sizeY + 1; j++) {
                        batch.draw(Tex.disallowBlock, i * 30 * Tex.x, j * 30 * Tex.y, Tex.disallowBlock.getWidth(), Tex.disallowBlock.getHeight());
                    }
                }
            }
        }
        if (y == 0) {
            for (int i = (int) x; i < (int) (w + x); i++) {
                for (int j = -1; j < 0; j++) {
                    batch.draw(Tex.disallowBlock, i * 30 * Tex.x, j * 30 * Tex.y, Tex.disallowBlock.getWidth(), Tex.disallowBlock.getHeight());
                }
            }
        }
        /////---------


        for (int i = (int) x; i < (int) (x + w); i++) {
            for (int j = (int) y; j < (int) (h + y); j++) {
                blockMap.act(batch, i, j);
            }
        }

        for (int i = (int) x; i < (int) (x + w); i++) {
            for (int j = (int) y; j < (int) (h + y); j++) {
                blockMap.actCave(batch, i, j);
            }
        }

        //// ------------
        if (x + w == BlockMap.sizeX - 1) {
            if (y == 0) {
                for (int i = BlockMap.sizeX - 1; i < BlockMap.sizeX + 1; i++) {
                    for (int j = -1; j < 0; j++) {
                        batch.draw(Tex.disallowBlock, i * 30 * Tex.x, j * 30 * Tex.y, Tex.disallowBlock.getWidth(), Tex.disallowBlock.getHeight());
                    }
                }
            }
            for (int i = BlockMap.sizeX - 1; i < BlockMap.sizeX; i++) {
                for (int j = (int) y; j < (int) (h + y); j++) {

                    batch.draw(Tex.disallowBlock, i * 30 * Tex.x, j * 30 * Tex.y, Tex.disallowBlock.getWidth(), Tex.disallowBlock.getHeight());
                }
            }
        }
        if (y + h == BlockMap.sizeY) {
            for (int i = (int) x; i < (int) (w + x); i++) {
                for (int j = BlockMap.sizeY; j < BlockMap.sizeY + 1; j++) {
                    if (i == 20 && j == BlockMap.sizeY) {
                        continue;
                    }
                    batch.draw(Tex.disallowBlock, i * 30 * Tex.x, j * 30 * Tex.y, Tex.disallowBlock.getWidth(), Tex.disallowBlock.getHeight());
                }
            }
        }
        if (x + w == BlockMap.sizeX - 1) {
            if (y + h == BlockMap.sizeY) {
                for (int i = BlockMap.sizeX - 1; i < BlockMap.sizeX; i++) {
                    for (int j = BlockMap.sizeY; j < BlockMap.sizeY + 1; j++) {
                        if (i == 20 && j == BlockMap.sizeY) {
                            continue;
                        }
                        batch.draw(Tex.disallowBlock, i * 30 * Tex.x, j * 30 * Tex.y, Tex.disallowBlock.getWidth(), Tex.disallowBlock.getHeight());
                    }
                }
            }
        }
        ///// -----
        for (int i = actualX; i < (actualX + actualW); i++) {
            for (int j = actualY; j < (actualH + actualY); j++) {
                blockMap.actFog(batch, i, j);
            }
        }
        blockMap.act1(batch);
    }
}
