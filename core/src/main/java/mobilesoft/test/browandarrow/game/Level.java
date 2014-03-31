package mobilesoft.test.browandarrow.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;

/**
 * Created by BruSD on 30.03.2014.
 */
public class Level {
    public static final String TAG = Level.class.getName();
    private TiledMap map;
    private TiledMapTile tiledMapTile;
    public MapRenderer mapRenderer;
    public TiledMapTileLayer backgroundLayer;
    public MapLayer roadLayer;

    private int objectID;

    public Level (String filename ) {
        init(filename);
    }

    private void init (String filename) {
        //map = new TmxMapLoader().load(filename);

    }

    public void render (SpriteBatch batch) {

    }

    public void update (float deltaTime) {


    }
}
