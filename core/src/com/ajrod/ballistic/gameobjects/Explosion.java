package com.ajrod.ballistic.gameobjects;

import com.ajrod.ballistic.Ballistic;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion extends TexturedBox {

    private static Animation ani;
    private static TextureRegion[] missileFrames;

    static {
        missileFrames = new TextureRegion[5];
        TextureRegion[][] tmp = Ballistic.res.getAtlas("pack").findRegion("explosion").split(25, 25);

        ani = new Animation(0.05f, missileFrames);
    }

    private float stateTime, timer;

    public Explosion(float x, float y) {
        super(missileFrames[0], x, y, 0, 0);

        stateTime = 0f;
        timer = 1;
    }

    private static void initRegion() {
    }

    public void update(float dt) {
        if (width != 0) {
            stateTime += dt;
            timer -= dt * 4;
        }
        if (timer <= 0) reset();
    }

    @Override
    public void render(SpriteBatch sb) {
        if (width == 0)
            return;

        currentTexture = ani.getKeyFrame(stateTime);
        super.render(sb);
    }

    public void setWH(float radius) {
        width = radius + 10;
        height = radius + 10;
    }

    private void reset() {
        stateTime = 0f;
        timer = 1;
        width = 0;
        height = 0;
    }

    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
    }
}