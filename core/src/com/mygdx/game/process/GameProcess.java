package com.mygdx.game.process;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.DigPanelGameProcess;
import com.mygdx.game.event.EventController;
import com.mygdx.game.models.map.BlockMap;
import com.mygdx.game.models.map.LandMap;
import com.mygdx.game.models.map.PlayerMap;
import com.mygdx.game.models.player.NormalPlayer;

/**
 * Created by artem on 10/10/17.
 */

public class GameProcess implements IProcess{
    public static LandMap landMap;
    public static BlockMap blockMap;
    public static PlayerMap playerMap;
    public static EventController eventController;
    public static SpriteBatch menuBatch;
    public static DigPanelGameProcess digPanelGameProcess;

    public GameProcess() {
        menuBatch = new SpriteBatch();
        playerMap = new PlayerMap();
        eventController = new EventController();

        playerMap.add(new NormalPlayer(new Vector2(13, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(13, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(13, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(14, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(14, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(14, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(15, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(15, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(15, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(16, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(16, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(16, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(17, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(17, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(17, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(18, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(18, BlockMap.sizeY - 3)));
        playerMap.add(new NormalPlayer(new Vector2(18, BlockMap.sizeY - 3)));


        blockMap = new BlockMap();
        landMap = new LandMap(blockMap);
        digPanelGameProcess = new DigPanelGameProcess();
    }

    public void act(SpriteBatch batch) {

        landMap.act(batch);

        eventController.act(batch);

        playerMap.act(batch);

        batch.end();
        menuBatch.begin();
        digPanelGameProcess.act(menuBatch);
        menuBatch.end();
        batch.begin();
    }

    public void input() {
        digPanelGameProcess.input();
    }

    public void dispose() {
        blockMap.dispose();
        playerMap.dispose();
        eventController.dispose();
    }
}
