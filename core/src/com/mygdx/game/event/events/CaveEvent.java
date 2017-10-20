package com.mygdx.game.event.events;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Config.Tex;
import com.mygdx.game.event.eventHelpers.Distance;
import com.mygdx.game.event.eventHelpers.Movement;
import com.mygdx.game.models.blocks.Cave;
import com.mygdx.game.models.map.BlockMap;
import com.mygdx.game.models.map.MapHelper;
import com.mygdx.game.models.player.APlayer;
import com.mygdx.game.process.GameProcess;

import java.util.Vector;

/**
 * Created by User on 15.10.2017.
 */

public class CaveEvent implements IEvent {
    public Vector<Vector2> cells;
    public Vector<Vector2> standCells;
    public Vector<Vector3> foreground;
    public Vector<Vector2> foregroundBlocks;
    public Cave cave;
    public Vector2 standCell;
    private boolean isFinish = false;
    private boolean isDigged = false;
    public APlayer player;
    Movement movement;

    private final float blockHp = 1;
    float currentBlockHp = blockHp;

    public CaveEvent(Cave cave) {
        this.cave = cave;
        movement = new Movement();
        if (cave.type == 1) {
            if ((int) cave.position.y + 3 >= BlockMap.sizeY) {

            } else {
                for (int i = (int) cave.position.x -1 ; i <= (int) cave.position.x + 5; i ++) {
                    if (i >= 0 && i < BlockMap.sizeX) {
                        GameProcess.blockMap.fogMap[i][(int) cave.position.y + 3] = 1;
                    }
                }
            }
        }
        if (cave.type == 2) {
            if ((int) cave.position.y + 3 >= BlockMap.sizeY) {

            } else {
                for (int i = (int) cave.position.x -1 ; i <= (int) cave.position.x + 7; i ++) {
                    if (i >= 0 && i < BlockMap.sizeX) {
                        GameProcess.blockMap.fogMap[i][(int) cave.position.y + 3] = 1;
                    }
                }
            }
        }
        if (cave.type == 1) {
            setStandCell(new Vector2(cave.getPosition().x + 2, cave.getPosition().y));
            foreground = new Vector<Vector3>();
            foregroundBlocks = new Vector<Vector2>();

            foreground.add(new Vector3(cave.getPosition().x, cave.getPosition().y, 1));
            foreground.add(new Vector3(cave.getPosition().x + 1, cave.getPosition().y, 1));
            foreground.add(new Vector3(cave.getPosition().x + 2, cave.getPosition().y, 1));
            foreground.add(new Vector3(cave.getPosition().x + 3, cave.getPosition().y, 1));
            foreground.add(new Vector3(cave.getPosition().x + 4, cave.getPosition().y, 1));

            foreground.add(new Vector3(cave.getPosition().x, cave.getPosition().y + 1, 2));
            foreground.add(new Vector3(cave.getPosition().x + 1, cave.getPosition().y + 1, 2));
            foreground.add(new Vector3(cave.getPosition().x + 2, cave.getPosition().y + 1, 2));
            foreground.add(new Vector3(cave.getPosition().x + 3, cave.getPosition().y + 1, 2));
            foreground.add(new Vector3(cave.getPosition().x + 4, cave.getPosition().y + 1, 3));

            foreground.add(new Vector3(cave.getPosition().x, cave.getPosition().y + 2, 3));
            foreground.add(new Vector3(cave.getPosition().x + 1, cave.getPosition().y + 2, 3));
            foreground.add(new Vector3(cave.getPosition().x + 2, cave.getPosition().y + 2, 3));
            foreground.add(new Vector3(cave.getPosition().x + 3, cave.getPosition().y + 2, 3));
            foreground.add(new Vector3(cave.getPosition().x + 4, cave.getPosition().y + 2, 3));

            foregroundBlocks.add(new Vector2(cave.getPosition().x + 2, cave.getPosition().y));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 2, cave.getPosition().y + 1));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 1, cave.getPosition().y + 1));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 1, cave.getPosition().y));
            foregroundBlocks.add(new Vector2(cave.getPosition().x, cave.getPosition().y));
            foregroundBlocks.add(new Vector2(cave.getPosition().x, cave.getPosition().y + 1));

            foregroundBlocks.add(new Vector2(cave.getPosition().x, cave.getPosition().y + 2));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 1, cave.getPosition().y + 2));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 2, cave.getPosition().y + 2));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 3, cave.getPosition().y + 2));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 4, cave.getPosition().y + 2));

            foregroundBlocks.add(new Vector2(cave.getPosition().x + 4, cave.getPosition().y + 1));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 3, cave.getPosition().y + 1));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 3, cave.getPosition().y));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 4, cave.getPosition().y));
        }
        if (cave.type == 2) {
            setStandCell(new Vector2(cave.getPosition().x + 4, cave.getPosition().y));

            foreground = new Vector<Vector3>();
            foregroundBlocks = new Vector<Vector2>();

            foreground.add(new Vector3(cave.getPosition().x, cave.getPosition().y, 1));
            foreground.add(new Vector3(cave.getPosition().x + 1, cave.getPosition().y, 1));
            foreground.add(new Vector3(cave.getPosition().x + 2, cave.getPosition().y, 1));
            foreground.add(new Vector3(cave.getPosition().x + 3, cave.getPosition().y, 1));
            foreground.add(new Vector3(cave.getPosition().x + 4, cave.getPosition().y, 1));
            foreground.add(new Vector3(cave.getPosition().x + 5, cave.getPosition().y, 1));
            foreground.add(new Vector3(cave.getPosition().x + 6, cave.getPosition().y, 1));

            foreground.add(new Vector3(cave.getPosition().x, cave.getPosition().y + 1, 2));
            foreground.add(new Vector3(cave.getPosition().x + 1, cave.getPosition().y + 1, 2));
            foreground.add(new Vector3(cave.getPosition().x + 2, cave.getPosition().y + 1, 2));
            foreground.add(new Vector3(cave.getPosition().x + 3, cave.getPosition().y + 1, 2));
            foreground.add(new Vector3(cave.getPosition().x + 4, cave.getPosition().y + 1, 2));
            foreground.add(new Vector3(cave.getPosition().x + 5, cave.getPosition().y + 1, 2));
            foreground.add(new Vector3(cave.getPosition().x + 6, cave.getPosition().y + 1, 3));

            foreground.add(new Vector3(cave.getPosition().x, cave.getPosition().y + 2, 3));
            foreground.add(new Vector3(cave.getPosition().x + 1, cave.getPosition().y + 2, 3));
            foreground.add(new Vector3(cave.getPosition().x + 2, cave.getPosition().y + 2, 3));
            foreground.add(new Vector3(cave.getPosition().x + 3, cave.getPosition().y + 2, 3));
            foreground.add(new Vector3(cave.getPosition().x + 4, cave.getPosition().y + 2, 3));
            foreground.add(new Vector3(cave.getPosition().x + 5, cave.getPosition().y + 2, 3));
            foreground.add(new Vector3(cave.getPosition().x + 6, cave.getPosition().y + 2, 3));

            foregroundBlocks.add(new Vector2(cave.getPosition().x + 4, cave.getPosition().y));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 3, cave.getPosition().y));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 2, cave.getPosition().y));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 1, cave.getPosition().y));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 0, cave.getPosition().y));

            foregroundBlocks.add(new Vector2(cave.getPosition().x, cave.getPosition().y + 1));
            foregroundBlocks.add(new Vector2(cave.getPosition().x, cave.getPosition().y + 2));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 1, cave.getPosition().y + 2));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 1, cave.getPosition().y + 1));

            foregroundBlocks.add(new Vector2(cave.getPosition().x + 2, cave.getPosition().y + 1));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 2, cave.getPosition().y + 2));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 3, cave.getPosition().y + 2));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 3, cave.getPosition().y + 1));

            foregroundBlocks.add(new Vector2(cave.getPosition().x + 4, cave.getPosition().y + 1));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 4, cave.getPosition().y + 2));

            foregroundBlocks.add(new Vector2(cave.getPosition().x + 5, cave.getPosition().y + 2));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 6, cave.getPosition().y + 2));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 6, cave.getPosition().y + 1));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 5, cave.getPosition().y + 1));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 5, cave.getPosition().y));
            foregroundBlocks.add(new Vector2(cave.getPosition().x + 6, cave.getPosition().y));
        }
    }

    @Override
    public boolean isFinish() {
        return isFinish;
    }

    @Override
    public void setCells(Vector<Vector2> vector2s) {

    }

    @Override
    public void setStandCell(Vector2 vector2) {
        standCell = vector2;
        movement.isReady = false;
        movement.setStandPosition(vector2);
    }

    @Override
    public void setPlayer(APlayer player) {
        this.player = player;
        player.setEvent(this);
        movement.setPlayer(player);
    }

    @Override
    public APlayer getPlayer() {
        return player;
    }

    @Override
    public Vector2 getCellForDistance() {
        return standCell;
    }

    @Override
    public void act(SpriteBatch batch) {
        Vector2 playerXY = player.getXYPosition();
        cave.render(batch);
        if (foreground.size() != 0) {
            for (int i = 0; i < foreground.size(); i++) {
                if (foreground.get(i).z == 1) {
                    batch.draw(Tex.bg, foreground.get(i).x * 30 * Tex.x, foreground.get(i).y * 30 * Tex.y, Tex.bg.getWidth(), Tex.bg.getHeight());
                } else if (foreground.get(i).z == 2) {
                    batch.draw(Tex.groundBlock, foreground.get(i).x * 30 * Tex.x, foreground.get(i).y * 30 * Tex.y, Tex.groundBlock.getWidth(), Tex.groundBlock.getHeight());
                } else if (foreground.get(i).z == 3) {
                    batch.draw(Tex.groundBlock, foreground.get(i).x * 30 * Tex.x, foreground.get(i).y * 30 * Tex.y, Tex.groundBlock.getWidth(), Tex.groundBlock.getHeight());
                }
            }
        }
        if (player.isMoving()) {
            return;
        }

        if (standCell.x == playerXY.x && standCell.y == playerXY.y) {
            movement.isReady = true;
            if (player.canAtack()) {
                currentBlockHp -= player.damage;
            }

            if (foreground.size() == 0) {
                GameProcess.blockMap.blocks[(int) cave.position.x][(int) cave.position.y] = cave;
                isFinish = true;
                return;
            }
            if (this.currentBlockHp < 0) {
                currentBlockHp = blockHp;
                setStandCell(foregroundBlocks.get(0));
                for (int i = 0; i < foreground.size(); i ++) {
                    if (foreground.get(i).x == foregroundBlocks.get(0).x && foreground.get(i).y == foregroundBlocks.get(0).y) {
                        foreground.remove(i);
                        break;
                    }
                }
                foregroundBlocks.remove(0);
            }
        } else {
            if (!player.isMoving) {
                movement.isReady = false;
            }

            movement.act();
        }
    }

    @Override
    public boolean isAbleToWalk() {
        return true;
    }

    @Override
    public void dispose() {
        player.removeEvent();
    }
}
