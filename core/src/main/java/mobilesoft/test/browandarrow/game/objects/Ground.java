package mobilesoft.test.browandarrow.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import mobilesoft.test.browandarrow.game.Assets;
import mobilesoft.test.browandarrow.screen.AbstractGameScreen;

/**
 * Created by BruSD on 30.03.2014.
 */
public class Ground extends AbstractGameObject {
    private TextureRegion regWaterOverlay;
    private float length;

    public Ground (float length) {
        this.length = length;
        init();
    }


    private void init () {
        dimension.set(length * 10, 3);
        regWaterOverlay = Assets.instance.ground.ground;
        origin.x = -dimension.x / 2;
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = null;
        reg = regWaterOverlay;
        batch.draw(reg.getTexture(),
                position.x + origin.x, position.y + origin.y,
                origin.x, origin.y,
                dimension.x, dimension.y,
                scale.x, scale.y,
                rotation,
                reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(),
                false, false);
    }
}
