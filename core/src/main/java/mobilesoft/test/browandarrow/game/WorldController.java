package mobilesoft.test.browandarrow.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.utils.Disposable;
import mobilesoft.test.browandarrow.screen.DirectedGame;
import mobilesoft.test.browandarrow.util.CameraHelper;

/**
 * Created by BruSD on 30.03.2014.
 */
public class WorldController  extends InputAdapter implements Disposable {

    private static final String TAG = WorldController.class.getName();
    public CameraHelper cameraHelper;
    public boolean isPlateOpenForPutTower = true;
    private DirectedGame game;

    public Level level;
    public int score;
    public float scoreVisual;

    public float powerOfShot;



    private String levelID = "levels/level-1.tmx";

    public World b2world;

    /** a hit body **/
    protected Body hitBody = null;
    /** our mouse joint **/
    protected MouseJoint mouseJoint = null;

    /** ground body to connect the mouse joint to **/
    protected Body groundBody;


    public WorldController (DirectedGame game) {
        this.game = game;
        init();
    }
    //region LifeCycle
    private void init() {
        cameraHelper = new CameraHelper();
        initLevel();
    }

    private void initLevel () {
        score = 0;
        scoreVisual = score;
        powerOfShot = 0;
        level = new Level(levelID);


        initPhysics();
    }

    @Override
    public void dispose() {
        mouseJoint = null;
    }

    private void initPhysics () {
        if (b2world != null) b2world.dispose();
        b2world = new World(new Vector2(0, -9.81f), true);
        // Rocks
        Vector2 origin = new Vector2();
//        for (Rock rock : level.rocks) {
//            BodyDef bodyDef = new BodyDef();
//            bodyDef.type = BodyDef.BodyType.KinematicBody;
//            bodyDef.position.set(rock.position);
//            Body body = b2world.createBody(bodyDef);
//            rock.body = body;
//            PolygonShape polygonShape = new PolygonShape();
//            origin.x = rock.bounds.width / 2.0f;
//            origin.y = rock.bounds.height / 2.0f;
//            polygonShape.setAsBox(rock.bounds.width / 2.0f,
//                    rock.bounds.height / 2.0f, origin, 0);
//            FixtureDef fixtureDef = new FixtureDef();
//            fixtureDef.shape = polygonShape;
//            body.createFixture(fixtureDef);
//            polygonShape.dispose();
//        }
    }

    //endregion
    public void update (float deltaTime) {
        handleInput(deltaTime);


        b2world.step(deltaTime, 6, 2);

        level.update(deltaTime);

    }

    private void handleInput (float deltaTime) {

    }
    private void moveCamera (float x, float y) {
        x += cameraHelper.getPosition().x;
        y += cameraHelper.getPosition().y;
        cameraHelper.setPosition(x, y);

        Gdx.app.debug(TAG, "Camera position: " + x +" " + y);

    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {

        powerOfShot += 1;
        return false;
    }


    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        powerOfShot += 5;

        return false;
    }
}
