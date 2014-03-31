package mobilesoft.test.browandarrow.screen.transitions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by BruSD on 30.03.2014.
 */
public interface ScreenTransition {

    public float getDuration ();

    public void render (SpriteBatch batch,
                        Texture currScreen,
                        Texture nextScreen,
                        float alpha);

}
