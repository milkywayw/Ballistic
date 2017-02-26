package com.ajrod.ballistic.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Graphic extends TexturedBox {

    // TODO: this class can probably be removed, but will consult first. It's basically just a TexturedBox
    public Graphic(TextureRegion image, float x, float y) {
        super(image, x, y, image.getRegionWidth() + 200, image.getRegionHeight() + 200);
    }
}