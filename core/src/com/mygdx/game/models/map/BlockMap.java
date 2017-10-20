package com.mygdx.game.models.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Config.Tex;
import com.mygdx.game.models.blocks.ABlock;
import com.mygdx.game.models.blocks.Cave;
import com.mygdx.game.models.blocks.GroundBlock;
import com.mygdx.game.process.GameProcess;

/**
 * Created by artem on 10/9/17.
 */

public class BlockMap implements IMap {
    public ABlock[][] blocks;
    public static int sizeX = 200;
    public static int sizeY = 200;

    float deltaX;
    float deltaY;

    public int[][] avaliableMap;
    public int[][] fogMap;

    public BlockMap() {
        blocks = new ABlock[sizeX][sizeY];
        avaliableMap = new int[sizeX][sizeY];
        fogMap = new int[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (j == sizeY - 1 || j == sizeY - 2 || j == sizeY - 3) {
                    if (i > 19 && i < 21) {
                        fillFogAround(new Vector2(i * 30 * Tex.x, j * 30 * Tex.y));
                        continue;
                    }
                }
                if (j == sizeY - 3) {
                    if (i > 11 && i < 21) {
                        fillFogAround(new Vector2(i * 30 * Tex.x, j * 30 * Tex.y));
                        continue;
                    }
                }

                GroundBlock groundBlock = new GroundBlock();
                groundBlock.setHp(3);
                groundBlock.setPosition(new Vector2(i * 30 * Tex.x, j * 30 * Tex.y));
                blocks[i][j] = groundBlock;
                avaliableMap[i][j] = -5;
            }
        }
        generateAvaliableMap();

        deltaX = (Gdx.graphics.getWidth() / (2f / (float) Math.sqrt((double) Tex.x)));
        deltaY = (Gdx.graphics.getHeight() / (2f / (float) Math.sqrt((double) Tex.y)));
    }

    public void act(SpriteBatch batch, int i, int j) {
        if (i < 0 || j < 0) {
            return;
        }
        if (blocks[i][j] == null) {

        } else if (blocks[i][j].getHp() < 0) {
            fillFogAround(blocks[i][j].getPosition());
            blocks[i][j] = null;
            generateAvaliableMap();
        } else {
            Vector2 position = blocks[i][j].getPosition();
            if (blocks[i][j] instanceof Cave) {

            } else {
                blocks[i][j].render(batch);
            }
        }
    }

    public void actFog(SpriteBatch batch, int i, int j) {
        if (j >= sizeY && j <= sizeY + 3 && i > 9 && i < 23) {
            return;
        }
        if (i < 0 || j < 0 || i >= sizeX || j >= sizeY) {
            batch.draw(Tex.fog, i * 30 * Tex.x, j * 30 * Tex.y, 30 * Tex.x, 30 * Tex.y);
        } else if (fogMap[i][j] == 0) {
            batch.draw(Tex.fog, i * 30 * Tex.x, j * 30 * Tex.y, 30 * Tex.x, 30 * Tex.y);
        }
    }

    public void actCave(SpriteBatch batch, int i, int j) {
        if (i < 0 || j < 0) {
            return;
        }
        if (blocks[i][j] == null) {

        } else if (blocks[i][j].getHp() < 0) {
            blocks[i][j] = null;
            generateAvaliableMap();
        } else {
            Vector2 position = blocks[i][j].getPosition();
            if (blocks[i][j] instanceof Cave) {
                blocks[i][j].render(batch);

            }
        }
    }

    public void act1(SpriteBatch batch) {
        for (int i = 0; i < BlockMap.sizeX; i ++) {
            for (int j = 0; j < BlockMap.sizeY; j ++) {
                if (blocks[i][j] != null) {
                    blocks[i][j].act(batch);
                }
            }
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

    public ABlock getBlock(int x, int y) {
        if (this.blocks[x][y] == null) {
            return new GroundBlock();
        }
        return this.blocks[x][y];
    }

    public void generateAvaliableMap() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (this.blocks[i][j] == null) {
                    avaliableMap[i][j] = 0;
                } else {
                    avaliableMap[i][j] = -5;
                }
            }
        }
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (this.blocks[i][j] instanceof Cave) {
                    Cave cave = (Cave) this.blocks[i][j];
                    if (cave.type == 1) {
                        avaliableMap[i][j] = 0;
                        avaliableMap[i + 1][j] = 0;
                        avaliableMap[i + 2][j] = 0;
                        avaliableMap[i + 3][j] = 0;
                        avaliableMap[i + 4][j] = 0;

                        avaliableMap[i][j + 1] = -99;
                        avaliableMap[i + 1][j + 1] = -99;
                        avaliableMap[i + 2][j + 1] = -99;
                        avaliableMap[i + 3][j + 1] = -99;
                        avaliableMap[i + 4][j + 1] = -99;

                        avaliableMap[i][j + 2] = -99;
                        avaliableMap[i + 1][j + 2] = -99;
                        avaliableMap[i + 2][j + 2] = -99;
                        avaliableMap[i + 3][j + 2] = -99;
                        avaliableMap[i + 4][j + 2] = -99;

                        avaliableMap[i][j - 1] = -99;
                        avaliableMap[i + 1][j - 1] = -99;
                        avaliableMap[i + 2][j - 1] = -99;
                        avaliableMap[i + 3][j - 1] = -99;
                        avaliableMap[i + 4][j - 1] = -99;
                    }
                    if (cave.type == 2) {
                        avaliableMap[i][j] = 0;
                        avaliableMap[i + 1][j] = 0;
                        avaliableMap[i + 2][j] = 0;
                        avaliableMap[i + 3][j] = 0;
                        avaliableMap[i + 4][j] = 0;
                        avaliableMap[i + 5][j] = 0;
                        avaliableMap[i + 6][j] = 0;

                        avaliableMap[i][j + 1] = -99;
                        avaliableMap[i + 1][j + 1] = -99;
                        avaliableMap[i + 2][j + 1] = -99;
                        avaliableMap[i + 3][j + 1] = -99;
                        avaliableMap[i + 4][j + 1] = -99;
                        avaliableMap[i + 5][j + 1] = -99;
                        avaliableMap[i + 6][j + 1] = -99;

                        avaliableMap[i][j + 2] = -99;
                        avaliableMap[i + 1][j + 2] = -99;
                        avaliableMap[i + 2][j + 2] = -99;
                        avaliableMap[i + 3][j + 2] = -99;
                        avaliableMap[i + 4][j + 2] = -99;
                        avaliableMap[i + 5][j + 2] = -99;
                        avaliableMap[i + 6][j + 2] = -99;

                        avaliableMap[i][j - 1] = -99;
                        avaliableMap[i + 1][j - 1] = -99;
                        avaliableMap[i + 2][j - 1] = -99;
                        avaliableMap[i + 3][j - 1] = -99;
                        avaliableMap[i + 4][j - 1] = -99;
                        avaliableMap[i + 5][j - 1] = -99;
                        avaliableMap[i + 6][j - 1] = -99;
                    }
                }
            }
        }
    }

    public void fillFogAround(Vector2 position) {
        int x = (int) (position.x / (30 * Tex.x));
        int y = (int) (position.y / (30 * Tex.y));
        for (int i = x - 2; i <= x + 2; i ++) {
            for (int j = y - 2; j <= y + 2; j++) {
                if (i < 0 || j < 0 || i >= sizeX || j >= sizeY) {
                    continue;
                }
                fogMap[i][j] = 1;
            }
        }
    }

    public int[][] getAvaliableMap() {
        return avaliableMap;
    }
}
