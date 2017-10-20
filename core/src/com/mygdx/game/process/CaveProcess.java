package com.mygdx.game.process;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Config.Tex;
import com.mygdx.game.Layout.GameLayout;
import com.mygdx.game.components.Button;
import com.mygdx.game.event.events.CaveEvent;
import com.mygdx.game.models.blocks.Cave;
import com.mygdx.game.models.map.BlockMap;
import com.mygdx.game.models.map.LandMap;
import com.mygdx.game.models.map.MapHelper;

/**
 * Created by artem on 10/11/17.
 */

public class CaveProcess implements IProcess {
    public LandMap landMap;
    private Button accept;
    private Button back;
    private Button backToMenu;
    private Button cave1active;
    private Button cave2active;
    private Button cave1;
    private Button cave2;
    private int cave = 0;
    private Cave caveObject;
    private int[][] avaliableMap;
    int[][] bannedSectors;


    public CaveProcess(LandMap map) {
        landMap = map;
        accept = new Button(Tex.acceptButton, new Vector2(715 * Tex.x, 5 * Tex.y));
        backToMenu = new Button(Tex.button_1_2, new Vector2(5 * Tex.x, 5 * Tex.y));
        back = new Button(Tex.backButton, new Vector2(110 * Tex.x, 5 * Tex.y));
        cave1 = new Button(Tex.cave3x5Button, new Vector2(215 * Tex.x, 5 * Tex.y));
        cave2 = new Button(Tex.cave3x7Button, new Vector2(320 * Tex.x, 5 * Tex.y));
        cave1active = new Button(Tex.cave3x5Button_active, new Vector2(215 * Tex.x, 5 * Tex.y));
        cave2active = new Button(Tex.cave3x7Button_active, new Vector2(320 * Tex.x, 5 * Tex.y));

        GameProcess.blockMap.generateAvaliableMap();
        avaliableMap = MapHelper.getAvaliableMapToTunnel();
        bannedSectors = MapHelper.getAlreadyBuldingCaves();
        int[][] alreadyTunnel = MapHelper.getAlreadyBuldingCells();
        for (int i = 0; i < BlockMap.sizeX; i++) {
            for (int j = BlockMap.sizeY - 1; j > 0; j--) {
                if (alreadyTunnel[i][j] == 5) {
                    avaliableMap[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < BlockMap.sizeX; i++) {
            for (int j = BlockMap.sizeY - 1; j > 0; j--) {
                if (bannedSectors[i][j] == 6) {
                    avaliableMap[i][j] = -3;
                }
                if (bannedSectors[i][j] == 5) {
                    avaliableMap[i][j] = -2;
                }
                if (bannedSectors[i][j] == 4) {
                    avaliableMap[i][j] = -4;
                }
            }
        }
    }

    @Override
    public void act(SpriteBatch batch) {
        landMap.act(batch);
        if (cave > 0) {
            caveObject.getAvaliableToBuild(avaliableMap);
            caveObject.renderMarker(batch);
        }
        for (int i = 0; i < BlockMap.sizeX; i++) {
            for (int j = BlockMap.sizeY - 1; j > 0; j--) {
                if (avaliableMap[i][j] == -1) {
                    batch.draw(Tex.marker_tonnel_4, i * Tex.x * 30, j * 30 * Tex.y, Tex.marker_tonnel_4.getWidth(), Tex.marker_tonnel_4.getHeight());
                } else if (avaliableMap[i][j] == -2) {
                    batch.draw(Tex.bannedSectorCave3x5, i * Tex.x * 30, j * 30 * Tex.y, Tex.bannedSectorCave3x5.getWidth(), Tex.bannedSectorCave3x5.getHeight());
                } else if (avaliableMap[i][j] == -4) {
                    batch.draw(Tex.bannedSectorCave3x7, i * Tex.x * 30, j * 30 * Tex.y, Tex.bannedSectorCave3x7.getWidth(), Tex.bannedSectorCave3x7.getHeight());
                }
            }
        }
        batch.end();
        GameProcess.menuBatch.begin();
        if (caveObject != null && caveObject.isAvaliableToBuild) {
            accept.act(GameProcess.menuBatch);
        }
        back.act(GameProcess.menuBatch);
        backToMenu.act(GameProcess.menuBatch);
        if (cave == 1) {
            cave1active.act(GameProcess.menuBatch);
        } else {
            cave1.act(GameProcess.menuBatch);
        }
        if (cave == 2) {
            cave2active.act(GameProcess.menuBatch);
        } else {
            cave2.act(GameProcess.menuBatch);
        }


        GameProcess.menuBatch.end();
        batch.begin();
    }

    @Override
    public void input() {
        if (backToMenu.input()) {
            accept.isActivated = false;
            GameLayout.removeProcess();
        }
        if (back.input()) {
            accept.isActivated = false;
            GameProcess.digPanelGameProcess.isShow = false;
            GameProcess.digPanelGameProcess.isClose = true;
            GameLayout.removeProcess();
        }
        if (cave == 1) {
            if (cave1active.input()) {
                cave = 0;
                caveObject = null;
                cave1active.isActivated = false;
                cave1active.setDelay(5);
            }
        } else {
            if (cave1.input()) {
                cave1.isActivated = false;
                cave1.setDelay(5);
                cave = 1;
                caveObject = new Cave(5, 3);
            }
        }
        if (cave == 2) {
            if (cave2active.input()) {
                cave = 0;
                caveObject = null;
                cave2active.isActivated = false;
                cave2active.setDelay(5);
            }
        } else {
            if (cave2.input()) {
                cave2.isActivated = false;
                cave2.setDelay(5);
                cave = 2;
                caveObject = new Cave(7, 3);
            }
        }
        if (caveObject != null && caveObject.isAvaliableToBuild && accept.input()) {
            accept.isActivated = false;
            if (caveObject.type == 1) {
                caveObject.setPosition(new Vector2(caveObject.markerPosition.x - 2, caveObject.markerPosition.y - 1));
            }
            if (caveObject.type == 2) {
                caveObject.setPosition(new Vector2(caveObject.markerPosition.x - 3, caveObject.markerPosition.y - 1));
            }
            CaveEvent caveEvent = new CaveEvent(caveObject);
            GameProcess.eventController.addEvent(caveEvent);
            GameLayout.removeProcess();
        }
        if (cave > 0) {
            Vector3 ar = GameLayout.camera.unproject(new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0));
            int x = (int) (ar.x / (30 * Tex.x));
            int y = (int) (ar.y / (30 * Tex.y)) + 1;
            caveObject.setMarkerPosition(new Vector2(x, y));
        }
    }
}
