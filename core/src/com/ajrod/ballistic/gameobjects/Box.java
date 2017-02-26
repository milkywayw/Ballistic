package com.ajrod.ballistic.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Box {

    protected float x, y, width, height;
    protected TextureRegion texture;

    public Box(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean contains(float x, float y) {
        return valuesWithinRange(this.x, x, xRadius()) && valuesWithinRange(this.y, y, yRadius());
    }

    private boolean valuesWithinRange(float a, float b, float radius) {
        return Math.abs(b - a) < radius;
    }

    public float leftEdge() {
        return x - xRadius();
    }

    public float bottomEdge() {
        return y - yRadius();
    }

    private float xRadius() {
        return width / 2;
    }

    private float yRadius() {
        return height / 2;
    }

    public void render(SpriteBatch sb) {

    }
}
