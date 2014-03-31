
package mobilesoft.test.browandarrow;

import com.badlogic.gdx.Application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

import mobilesoft.test.browandarrow.game.Assets;
import mobilesoft.test.browandarrow.screen.DirectedGame;
import mobilesoft.test.browandarrow.screen.GameScreen;
import mobilesoft.test.browandarrow.screen.transitions.ScreenTransition;
import mobilesoft.test.browandarrow.screen.transitions.ScreenTransitionFade;


public class BowAndArrow extends DirectedGame {

    private static final String TAG = BowAndArrow.class.getName();

    @Override
    public void create () {
        // Set Libgdx log level
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        // Load assets
        Assets.instance.init(new AssetManager());

        // Load preferences for audio settings and start playing music
        //GamePreferences.instance.load();


        // Start game at menu screen
        ScreenTransition transition = ScreenTransitionFade.init(0.0f);
        setScreen(new GameScreen(this), transition);
    }
}
