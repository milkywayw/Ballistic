package com.ajrod.ballistic.gameobjects;

import com.ajrod.ballistic.Ballistic;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

public class SecondBoss extends Box {

    private static Animation ani;
    private static TextureRegion health, healthBar, barEnd;

    static {
        healthBar = Ballistic.res.getAtlas("pack").findRegion("HealthBar");
        barEnd = Ballistic.res.getAtlas("pack").findRegion("HealthEnd");
        health = Ballistic.res.getAtlas("pack").findRegion("Health");

        TextureRegion[] missileFrames = new TextureRegion[12];
        TextureRegion[][] tmp = Ballistic.res.getAtlas("pack").findRegion("sharkMissile_v2").split(75, 50);

        int k = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 3; j++)
                missileFrames[k++] = tmp[i][j];

        ani = new Animation(0.1f, missileFrames);
        ani.setPlayMode(PlayMode.LOOP_PINGPONG);
    }

    private SecondBossP2 p2;
    private int hp;
    private boolean defeated, goingRight, invincible, toggle;
    private float stateTime;
    private Explosion explosion;
    private boolean goingDown, hit;
    private int cycles;
    private float closeness;

    public SecondBoss() {
        super(-50, Ballistic.HEIGHT - 100, 150, 100);

        defeated = false;
        invincible = false;
        goingRight = true;
        goingDown = true;
        toggle = true;
        hit = false;

        closeness = 19;
        cycles = 1;
        hp = 100;

        explosion = new Explosion(x, y);

        p2 = new SecondBossP2(hp);

        stateTime = 0f;
    }

    public void onClick() {
        if (cycles == 3) {
            p2.onClick();
            if (p2.isDefeated())
                reset();
        } else {
            if (!invincible) {
                if (Ballistic.soundOn)
                    Ballistic.sounds.hit.play(1.0f);
                hp--;
            }
            hit = true;
            if (hp == 0) {
                if (Ballistic.soundOn)
                    Ballistic.sounds.explosion.play(1.0f);
                reset();
            }
        }
    }

    public void update(float dt) {
        explosion.update(dt);
        stateTime += dt;
        if (stateTime > 0.29f) stateTime = 0;
        if (cycles == 3) {
            if (toggle) {
                p2.setHP(hp);
                invincible = true;
                width = 0;
                toggle = false;
            }
            if (!defeated) {
                p2.update(dt, 1);
            } else {
                p2.update(dt, 0);
            }
        } else {
            if (goingRight) {
                x += dt * 240;
                if (x >= Ballistic.WIDTH + 50) {
                    goingRight = false;
                    if (goingDown) {
                        y -= 100;
                        if (y < 250) {
                            goingDown = false;
                        }
                    } else {
                        y += 100;
                        if (y > 650) {
                            goingDown = true;
                            cycles++;
                        }
                    }
                }
            } else {
                x -= dt * 240;
                if (x <= -50) {
                    goingRight = true;
                    if (goingDown) {
                        y -= 100;
                        if (y < 150) {
                            goingDown = false;
                        }
                    } else {
                        y += 100;
                        if (y > 650) {
                            goingDown = true;
                            cycles++;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (cycles == 3) {
            p2.render(sb);
            return;
        }

        explosion.render(sb);
        renderSelf(sb);

        if (hp > 0) {
            sb.draw(healthBar, 32, Ballistic.HEIGHT - 29, 416, 20);
            sb.draw(barEnd, 40 + hp * 4, Ballistic.HEIGHT - 25, 4, 12);
            sb.draw(health, 40, Ballistic.HEIGHT - 25, hp * 4, 12);
        }
    }

    private void renderSelf(SpriteBatch sb) {
        float shift = goingRight ? 0f : 0.6f;

        if(hit) {
            shift += 0.3f;
            hit = false;
        }

        texture = ani.getKeyFrame(stateTime + shift, true);
        super.render(sb);
    }

    public void reset() {
        if (cycles == 3) {
            p2.reset();
            defeated = true;
        } else {
            explosion.setXY(x, y);
            explosion.setWH(width);
            width = 0;
            height = 0;
            defeated = true;
        }
    }

    public boolean killedU() {
        return p2.getRadius() >= p2.getMaxRadius();
    }

    public boolean isDefeated() {
        return defeated || p2.isDefeated();
    }

    @Override
    public boolean contains(float x, float y) {
        if (cycles == 3)
            return p2.contains(x, y);
        else
            return this.contains(x, y);
    }

    public float getCloseness() {
        return closeness;
    }

    public double getRadius() {
        if (cycles == 3) return p2.getRadius();
        else return 75;
    }

    public float getX() {
        if (cycles == 3) return Ballistic.WIDTH / 2;
        else return x;
    }

    public float getY() {
        if (cycles == 3) return Ballistic.HEIGHT / 2;
        else return y;
    }

}