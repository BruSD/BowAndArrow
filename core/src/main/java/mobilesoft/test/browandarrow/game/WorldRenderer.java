package mobilesoft.test.browandarrow.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;
import mobilesoft.test.browandarrow.util.Constants;

/**
 * Created by BruSD on 30.03.2014.
 */
public class WorldRenderer  implements Disposable {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;

    private OrthographicCamera cameraGUI;


    private static final boolean DEBUG_DRAW_BOX2D_WORLD = true;
    private Box2DDebugRenderer b2debugRenderer;

    public WorldRenderer (WorldController worldController) {
        this.worldController = worldController;
        init();
    }
    private void init () {
        batch = new SpriteBatch();

        //World view Camera
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
                Constants.VIEWPORT_HEIGHT);

        camera.update();


        //GUI Camera
        cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH,
                Constants.VIEWPORT_GUI_HEIGHT);
        //cameraGUI.position.set(0 ,0, 0);
        cameraGUI.setToOrtho(true); // flip y-axis
        cameraGUI.update();


        b2debugRenderer = new Box2DDebugRenderer();
    }

    public void render () {
        renderWorld(batch);
        renderGui(batch);
    }

    public void resize (int width, int height) {
        //World view Camera
        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        camera.update();

        //GUI Camera
        cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
        cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT
                / (float)height) * (float)width;
        cameraGUI.position.set(cameraGUI.viewportWidth / 2,
                cameraGUI.viewportHeight / 2, 0);
        cameraGUI.update();
    }

    private void renderWorld(SpriteBatch batch) {
        worldController.cameraHelper.applyTo(camera);
        batch.setProjectionMatrix(camera.combined);
        //worldController.b2world.step( 1/60.0f, 6, 2 );
        batch.begin();

        worldController.level.render(batch);
        batch.end();



        if (DEBUG_DRAW_BOX2D_WORLD) {
            b2debugRenderer.render(worldController.b2world,
                    camera.combined);
        }
    }

    private void renderGui(SpriteBatch batch) {
        batch.setProjectionMatrix(cameraGUI.combined);
        batch.begin();

        // draw collected gold coins icon + text
        // (anchored to top left edge)
        renderGuiScore(batch);
        renderPowerShot(batch);

        renderGuiFpsCounter(batch);
        batch.end();
    }


    private void renderGuiScore (SpriteBatch batch) {
        float x = -15;
        float y = -15;

        Assets.instance.fonts.defaultBig.draw(batch,
                "" + (int)worldController.scoreVisual,
                x + 50, y + 37);
    }

    private void renderGuiFpsCounter(SpriteBatch batch) {
        float x = cameraGUI.viewportWidth - 55;
        float y = cameraGUI.viewportHeight - 15;
        int fps = Gdx.graphics.getFramesPerSecond();
        BitmapFont fpsFont = Assets.instance.fonts.defaultNormal;
        if (fps >= 45) {
            // 45 or more FPS show up in green
            fpsFont.setColor(0, 1, 0, 1);
        } else if (fps >= 30) {
            // 30 or more FPS show up in yellow
            fpsFont.setColor(1, 1, 0, 1);
        } else {
            // less than 30 FPS show up in red
            fpsFont.setColor(1, 0, 0, 1);
        }
        fpsFont.draw(batch, "FPS: " + fps, x, y);
        fpsFont.setColor(0, 0, 0, 1); // white
    }

    private void renderPowerShot(SpriteBatch batch){
        float x = 15;
        float y = cameraGUI.viewportHeight - 15;
        int power = (int)worldController.powerOfShot;

        BitmapFont  powerFont = Assets.instance.fonts.defaultNormal;
        if (power >= 70) {
            // 70 or more Power show up in green
            powerFont.setColor(0, 1, 0, 1);
        } else if (power >= 30) {
            // 30 or more Power show up in yellow
            powerFont.setColor(1, 1, 0, 1);
        } else {
            // less than 30 Power show up in red
            powerFont.setColor(1, 0, 0, 1);
        }
        powerFont.draw(batch, "Power: " + power, x, y);
        powerFont.setColor(0, 0, 0, 1); // white
    }


    @Override
    public void dispose() {

    }


}
