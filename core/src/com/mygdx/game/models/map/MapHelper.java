package com.mygdx.game.models.map;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.event.events.CaveEvent;
import com.mygdx.game.event.events.IEvent;
import com.mygdx.game.event.events.TonnelEvent;
import com.mygdx.game.models.blocks.Cave;
import com.mygdx.game.process.GameProcess;

import java.util.Vector;

/**
 * Created by artem on 10/11/17.
 */

public class MapHelper {
    public static int[][] getAvaliableMapToWalk() {
        return GameProcess.blockMap.getAvaliableMap();
    }

    public static int[][] getAvaliableMapToTunnel() {
        return GameProcess.blockMap.getAvaliableMap();
    }

    public static int[][] getAlreadyBuldingCaves() {
        int[][] map = new int[BlockMap.sizeX][BlockMap.sizeY];
        Vector<IEvent> all = GameProcess.eventController.getAllEvents();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i) instanceof CaveEvent) {
                Cave cave = ((CaveEvent) all.get(i)).cave;
                Vector2 cavePosition = cave.position;
                if (cave.type == 1) {
                    if (cavePosition.y - 1 >= 0) {
                        map[(int) cavePosition.x][(int) cavePosition.y - 1] = 6;
                        map[(int) cavePosition.x + 1][(int) cavePosition.y - 1] = 6;
                        map[(int) cavePosition.x + 2][(int) cavePosition.y - 1] = 6;
                        map[(int) cavePosition.x + 3][(int) cavePosition.y - 1] = 6;
                        map[(int) cavePosition.x + 4][(int) cavePosition.y - 1] = 6;
                    }

                    map[(int) cavePosition.x][(int) cavePosition.y + 1] = 6;
                    map[(int) cavePosition.x + 1][(int) cavePosition.y + 1] = 6;
                    map[(int) cavePosition.x + 2][(int) cavePosition.y + 1] = 6;
                    map[(int) cavePosition.x + 3][(int) cavePosition.y + 1] = 6;
                    map[(int) cavePosition.x + 4][(int) cavePosition.y + 1] = 6;

                    map[(int) cavePosition.x][(int) cavePosition.y + 2] = 6;
                    map[(int) cavePosition.x + 1][(int) cavePosition.y + 2] = 6;
                    map[(int) cavePosition.x + 2][(int) cavePosition.y + 2] = 6;
                    map[(int) cavePosition.x + 3][(int) cavePosition.y + 2] = 6;
                    map[(int) cavePosition.x + 4][(int) cavePosition.y + 2] = 6;

                    map[(int) cavePosition.x][(int) cavePosition.y] = 5;
                }
                if (cave.type == 2) {
                    if (cavePosition.y - 1 >= 0) {
                        map[(int) cavePosition.x][(int) cavePosition.y - 1] = 6;
                        map[(int) cavePosition.x + 1][(int) cavePosition.y - 1] = 6;
                        map[(int) cavePosition.x + 2][(int) cavePosition.y - 1] = 6;
                        map[(int) cavePosition.x + 3][(int) cavePosition.y - 1] = 6;
                        map[(int) cavePosition.x + 4][(int) cavePosition.y - 1] = 6;
                        map[(int) cavePosition.x + 5][(int) cavePosition.y - 1] = 6;
                        map[(int) cavePosition.x + 6][(int) cavePosition.y - 1] = 6;
                    }

                    map[(int) cavePosition.x][(int) cavePosition.y + 1] = 6;
                    map[(int) cavePosition.x + 1][(int) cavePosition.y + 1] = 6;
                    map[(int) cavePosition.x + 2][(int) cavePosition.y + 1] = 6;
                    map[(int) cavePosition.x + 3][(int) cavePosition.y + 1] = 6;
                    map[(int) cavePosition.x + 4][(int) cavePosition.y + 1] = 6;
                    map[(int) cavePosition.x + 5][(int) cavePosition.y + 1] = 6;
                    map[(int) cavePosition.x + 6][(int) cavePosition.y + 1] = 6;

                    map[(int) cavePosition.x][(int) cavePosition.y + 2] = 6;
                    map[(int) cavePosition.x + 1][(int) cavePosition.y + 2] = 6;
                    map[(int) cavePosition.x + 2][(int) cavePosition.y + 2] = 6;
                    map[(int) cavePosition.x + 3][(int) cavePosition.y + 2] = 6;
                    map[(int) cavePosition.x + 4][(int) cavePosition.y + 2] = 6;
                    map[(int) cavePosition.x + 5][(int) cavePosition.y + 2] = 6;
                    map[(int) cavePosition.x + 6][(int) cavePosition.y + 2] = 6;

                    map[(int) cavePosition.x][(int) cavePosition.y] = 4;
                }
            }
        }
        return map;
    }

    public static int[][] getAlreadyBuldingCells() {
        int[][] map = new int[BlockMap.sizeX][BlockMap.sizeY];
        Vector<IEvent> all = GameProcess.eventController.getAllEvents();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i) instanceof TonnelEvent) {
                Vector<Vector2> cells = ((TonnelEvent) all.get(i)).cells;
                for (int j = 0; j < cells.size(); j++) {
                    map[(int) cells.get(j).x][(int) cells.get(j).y] = 5;
                }
            }
        }
        return map;
    }

    public static void printMap(int[][] map) {
        for (int i = BlockMap.sizeX - 1; i > 0; i--) {
            for (int j = 0; j < BlockMap.sizeY; j++) {
                System.out.print(map[j][i] + " ");
            }
            System.out.println();

        }
    }
}
