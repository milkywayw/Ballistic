package com.ajrod.ballistic.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TexturedBox extends Box {

    protected TextureRegion currentTexture;

    public TexturedBox(TextureRegion region, float x, float y, float w, float h) {
        super(x, y, w, h);
        currentTexture = region;
    }

    public void render(SpriteBatch sb) {
        sb.draw(currentTexture, leftEdge(), bottomEdge(), width, height);
    }
}
