package com.mygdx.game.models.map.build;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Config.Tex;
import com.mygdx.game.models.map.BlockMap;
import com.mygdx.game.models.map.IMap;
import com.mygdx.game.models.map.MapHelper;
import com.mygdx.game.process.GameProcess;

import java.util.Vector;

/**
 * Created by artem on 10/11/17.
 */

public class TunnelMap implements IMap {
    int[][] sectors;
    int[][] choosenSectors;
    int[][] bannedSectors;
    int[][] alreadyBuild;

    int afterRemoveDelay = 0;

    Vector2 firstChoosen;
    Vector2 standCell;

    public TunnelMap() {
        sectors = new int[BlockMap.sizeX][BlockMap.sizeY];
        choosenSectors = new int[BlockMap.sizeX][BlockMap.sizeY];
        bannedSectors = MapHelper.getAlreadyBuldingCaves();
        alreadyBuild = MapHelper.getAlreadyBuldingCells();
        init();
    }


    @Override
    public void act(SpriteBatch batch) {
        for (int i = 0; i < BlockMap.sizeX; i++) {
            for (int j = 0; j < BlockMap.sizeY; j++) {
                if (firstChoosen != null) {
                    if (sectors[i][j] == 5) {
                        batch.draw(
                                Tex.marker_tonnel_1,
                                i * 30 * Tex.x,
                                j * 30 * Tex.y,
                                Tex.marker_tonnel_1.getWidth(),
                                Tex.marker_tonnel_1.getHeight()
                        );
                    } else if (choosenSectors[i][j] == 5) {
                        batch.draw(
                                Tex.marker_tonnel_2,
                                i * 30 * Tex.x,
                                j * 30 * Tex.y,
                                Tex.marker_tonnel_2.getWidth(),
                                Tex.marker_tonnel_2.getHeight()
                        );
                    } else if (alreadyBuild[i][j] == 5) {
                        batch.draw(
                                Tex.marker_tonnel_3,
                                i * 30 * Tex.x,
                                j * 30 * Tex.y,
                                Tex.marker_tonnel_3.getWidth(),
                                Tex.marker_tonnel_3.getHeight()
                        );
                    }
                } else {
                    if (alreadyBuild[i][j] == 5) {
                        batch.draw(
                                Tex.marker_tonnel_3,
                                i * 30 * Tex.x,
                                j * 30 * Tex.y,
                                Tex.marker_tonnel_3.getWidth(),
                                Tex.marker_tonnel_3.getHeight()
                        );
                    }
                    if (sectors[i][j] == 5) {
                        batch.draw(
                                Tex.marker_tonnel_1,
                                i * 30 * Tex.x,
                                j * 30 * Tex.y,
                                Tex.marker_tonnel_1.getWidth(),
                                Tex.marker_tonnel_1.getHeight()
                        );
                    }
                }
                if (bannedSectors[i][j] == 5) {
                    batch.draw(Tex.bannedSectorCave3x5, i * 30 * Tex.x, j * 30 * Tex.y, Tex.bannedSectorCave3x5.getWidth(), Tex.bannedSectorCave3x5.getHeight());
                }
                if (bannedSectors[i][j] == 4) {
                    batch.draw(Tex.bannedSectorCave3x7, i * 30 * Tex.x, j * 30 * Tex.y, Tex.bannedSectorCave3x7.getWidth(), Tex.bannedSectorCave3x7.getHeight());
                }
            }
        }
    }

    public void init() {
        GameProcess.blockMap.generateAvaliableMap();
        int[][] avaliableMap = MapHelper.getAvaliableMapToTunnel();
        for (int i = 0; i < BlockMap.sizeX; i++) {
            for (int j = 0; j < BlockMap.sizeY; j++) {
                if (avaliableMap[i][j] == 0) {
                    if (!nechetAndAvaliable(i - 1, j) && avaliableMap[i - 1][j] == -5 && bannedSectors[i - 1][j] == 0) {
                        sectors[i - 1][j] = 5;
                    }
                    if (!nechetAndAvaliable(i + 1, j) && avaliableMap[i + 1][j] == -5 && bannedSectors[i + 1][j] == 0) {
                        sectors[i + 1][j] = 5;
                    }
                    if (!nechetAndAvaliable(i, j + 1) && avaliableMap[i][j + 1] == -5 && bannedSectors[i][j + 1] == 0) {
                        sectors[i][j + 1] = 5;
                    }
                    if (!nechetAndAvaliable(i, j - 1) && avaliableMap[i][j - 1] == -5 && bannedSectors[i][j - 1] == 0) {
                        sectors[i][j - 1] = 5;
                    }
                }
            }
        }
        choosenSectors = new int[BlockMap.sizeX][BlockMap.sizeY];
    }

    public void refreshAfterAdd(Vector2 vector2) {
        int i = (int) vector2.x;
        int j = (int) vector2.y;
        int[][] avaliableMap = MapHelper.getAvaliableMapToTunnel();
        if (!nechetAndAvaliable(i - 1, j) && avaliableMap[i - 1][j] == -5 && choosenSectors[i - 1][j] == 0 && bannedSectors[i - 1][j] == 0) {
            sectors[i - 1][j] = 5;
        }
        if (!nechetAndAvaliable(i + 1, j) && avaliableMap[i + 1][j] == -5 && choosenSectors[i + 1][j] == 0 && bannedSectors[i + 1][j] == 0) {
            sectors[i + 1][j] = 5;
        }
        if (!nechetAndAvaliable(i, j + 1) && avaliableMap[i][j + 1] == -5 && choosenSectors[i][j + 1] == 0 && bannedSectors[i][j + 1] == 0) {
            sectors[i][j + 1] = 5;
        }
        if (!nechetAndAvaliable(i, j - 1) && avaliableMap[i][j - 1] == -5 && choosenSectors[i][j - 1] == 0 && bannedSectors[i][j - 1] == 0) {
            sectors[i][j - 1] = 5;
        }
    }

    public void refreshAfterDelete(Vector2 vector2) {
        if (choosenCount() == 0 || (vector2.x == firstChoosen.x && vector2.y == firstChoosen.y)) {
            init();
            firstChoosen = null;
            return;
        }
        int[][] newChoosenSectors = new int[BlockMap.sizeX][BlockMap.sizeY];
        boolean isAll = false;
        Vector2 cell = new Vector2(firstChoosen);
        newChoosenSectors[(int) cell.x][(int) cell.y] = 5;
        while (!isAll) {
            int b = 0;
            if (
                    !checkAvaliable((int) cell.x - 1, (int) cell.y)
                            && choosenSectors[(int) cell.x - 1][(int) cell.y] == 5
                            && newChoosenSectors[(int) cell.x - 1][(int) cell.y] != 5
                    ) {
                newChoosenSectors[(int) cell.x - 1][(int) cell.y] = 5;
                cell.x = cell.x - 1;
                b++;
            }
            if (
                    !checkAvaliable((int) cell.x + 1, (int) cell.y)
                            && choosenSectors[(int) cell.x + 1][(int) cell.y] == 5
                            && newChoosenSectors[(int) cell.x + 1][(int) cell.y] != 5
                    ) {

                newChoosenSectors[(int) cell.x + 1][(int) cell.y] = 5;
                cell.x = cell.x + 1;
                b++;
            }
            if (
                    !checkAvaliable((int) cell.x, (int) cell.y + 1)
                            && choosenSectors[(int) cell.x][(int) cell.y + 1] == 5
                            && newChoosenSectors[(int) cell.x][(int) cell.y + 1] != 5
                    ) {

                newChoosenSectors[(int) cell.x][(int) cell.y + 1] = 5;
                cell.y = cell.y + 1;
                b++;
            }
            if (
                    !checkAvaliable((int) cell.x, (int) cell.y - 1)
                            && choosenSectors[(int) cell.x][(int) cell.y - 1] == 5
                            && newChoosenSectors[(int) cell.x][(int) cell.y - 1] != 5
                    ) {

                newChoosenSectors[(int) cell.x][(int) cell.y - 1] = 5;
                cell.y = cell.y - 1;
                b++;
            }
            if (b == 0) {
                isAll = true;
            }
        }
        choosenSectors = newChoosenSectors;

        int i = (int) cell.x;
        int j = (int) cell.y;
        int[][] avaliableMap = MapHelper.getAvaliableMapToTunnel();
        if (!nechetAndAvaliable(i - 1, j) && avaliableMap[i - 1][j] == -5 && choosenSectors[i - 1][j] == 0 && bannedSectors[i][j - 1] == 0) {
            sectors[i - 1][j] = 5;
        }
        if (!nechetAndAvaliable(i + 1, j) && avaliableMap[i + 1][j] == -5 && choosenSectors[i + 1][j] == 0 && bannedSectors[i][j - 1] == 0) {
            sectors[i + 1][j] = 5;
        }
        if (!nechetAndAvaliable(i, j + 1) && avaliableMap[i][j + 1] == -5 && choosenSectors[i][j + 1] == 0 && bannedSectors[i][j - 1] == 0) {
            sectors[i][j + 1] = 5;
        }
        if (!nechetAndAvaliable(i, j - 1) && avaliableMap[i][j - 1] == -5 && choosenSectors[i][j - 1] == 0 && bannedSectors[i][j - 1] == 0) {
            sectors[i][j - 1] = 5;
        }
    }

    public Vector<Vector2> getStandCells() {
        int i = (int) firstChoosen.x;
        int j = (int) firstChoosen.y;
        Vector<Vector2> vector2s = new Vector<Vector2>();
        int[][] avaliableMap = MapHelper.getAvaliableMapToTunnel();
        if (i - 1 >= 0 && avaliableMap[i - 1][j] == 0) {
            vector2s.add(new Vector2(i - 1, j));
        }
        if (i + 1 < BlockMap.sizeX && avaliableMap[i + 1][j] == 0) {
            vector2s.add(new Vector2(i + 1, j));
        }
        if (j + 1 < BlockMap.sizeY && avaliableMap[i][j + 1] == 0) {
            vector2s.add(new Vector2(i, j + 1));
        }
        if (j - 1 >= 0 && avaliableMap[i][j - 1] == 0) {
            vector2s.add(new Vector2(i, j - 1));
        }

        return vector2s;
    }

    public Vector<Vector2> generateDataForEvent() {
        Vector<Vector2> vector2s = new Vector<Vector2>();
        if (firstChoosen == null) {
            return null;
        }
        int[][] newChoosenSectors = new int[BlockMap.sizeX][BlockMap.sizeY];
        boolean isAll = false;
        Vector2 cell = new Vector2(firstChoosen);
        vector2s.add(new Vector2(firstChoosen.x, firstChoosen.y));
        newChoosenSectors[(int) cell.x][(int) cell.y] = 5;
        while (!isAll) {
            int b = 0;
            if (
                    !checkAvaliable((int) cell.x - 1, (int) cell.y)
                            && choosenSectors[(int) cell.x - 1][(int) cell.y] == 5
                            && newChoosenSectors[(int) cell.x - 1][(int) cell.y] != 5
                    ) {
                vector2s.add(new Vector2(cell.x - 1, cell.y));
                newChoosenSectors[(int) cell.x - 1][(int) cell.y] = 5;
                cell.x = cell.x - 1;
                b++;
            }
            if (
                    !checkAvaliable((int) cell.x + 1, (int) cell.y)
                            && choosenSectors[(int) cell.x + 1][(int) cell.y] == 5
                            && newChoosenSectors[(int) cell.x + 1][(int) cell.y] != 5
                    ) {
                vector2s.add(new Vector2(cell.x + 1, cell.y));
                newChoosenSectors[(int) cell.x + 1][(int) cell.y] = 5;
                cell.x = cell.x + 1;
                b++;
            }
            if (
                    !checkAvaliable((int) cell.x, (int) cell.y + 1)
                            && choosenSectors[(int) cell.x][(int) cell.y + 1] == 5
                            && newChoosenSectors[(int) cell.x][(int) cell.y + 1] != 5
                    ) {
                vector2s.add(new Vector2(cell.x, cell.y + 1));
                newChoosenSectors[(int) cell.x][(int) cell.y + 1] = 5;
                cell.y = cell.y + 1;
                b++;
            }
            if (
                    !checkAvaliable((int) cell.x, (int) cell.y - 1)
                            && choosenSectors[(int) cell.x][(int) cell.y - 1] == 5
                            && newChoosenSectors[(int) cell.x][(int) cell.y - 1] != 5
                    ) {
                vector2s.add(new Vector2(cell.x, cell.y - 1));
                newChoosenSectors[(int) cell.x][(int) cell.y - 1] = 5;
                cell.y = cell.y - 1;
                b++;
            }
            if (b == 0) {
                isAll = true;
            }
        }

        return vector2s;
    }

    @Override
    public boolean add(Object object) {
        Vector2 vector2 = (Vector2) object;
        if (sectors[(int) vector2.x][(int) vector2.y] == 5) {
            if (afterRemoveDelay > 0) {
                afterRemoveDelay--;
                return false;
            }
            choosenSectors[(int) vector2.x][(int) vector2.y] = 5;
            if (choosenCount() == 1) {
                firstChoosen = vector2;
            }
            sectors = new int[BlockMap.sizeX][BlockMap.sizeY];
            refreshAfterAdd(vector2);
            return true;

        } else if (choosenSectors[(int) vector2.x][(int) vector2.y] == 5 && Gdx.input.justTouched()) {
            afterRemoveDelay = 5;
            remove(vector2);
            refreshAfterDelete(vector2);
            return true;
        } else {
            return false;
        }
    }

    public void remove(Object object) {
        Vector2 vector2 = (Vector2) object;
        if (choosenSectors[(int) vector2.x][(int) vector2.y] == 5) {
            choosenSectors[(int) vector2.x][(int) vector2.y] = 0;
            sectors = new int[BlockMap.sizeX][BlockMap.sizeY];
        }
    }

    public boolean nechetAndAvaliable(int x, int y) {
        if (checkAvaliable(x, y)) {
            return true;
        }
        if (x % 2 == 1 && y % 2 == 1) {
            return true;
        }

        return false;
    }

    public boolean checkAvaliable(int x, int y) {
        if (x < 0 || y < 0 || x >= BlockMap.sizeX || y >= BlockMap.sizeY) {
            return true;
        } else {
            return false;
        }
    }

    public int choosenCount() {
        int a = 0;
        for (int i = 0; i < BlockMap.sizeX; i++) {
            for (int j = 0; j < BlockMap.sizeY; j++) {
                if (choosenSectors[i][j] == 5) {
                    a++;
                }
            }
        }

        return a;
    }

    @Override
    public void dispose() {

    }
}
