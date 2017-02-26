package com.ajrod.ballistic.gameobjects;

import com.ajrod.ballistic.Ballistic;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SoundButton extends TexturedBox {

    private static TextureRegion[] region;

    static {
        region = new TextureRegion[2];
        TextureRegion[][] tmp = Ballistic.res.getAtlas("pack").findRegion("soundButton").split(50, 50);
        System.arraycopy(tmp[0], 0, region, 0, region.length);
    }

    public SoundButton(float x, float y, float w, float h, boolean soundOn) {
        super(region[0], x, y, w, h);
        setState(soundOn);
    }

    public void setState(boolean soundOn) {
        texture = soundOn ? region[0] : region[1];
    }
}
