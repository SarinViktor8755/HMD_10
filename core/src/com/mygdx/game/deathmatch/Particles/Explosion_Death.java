package com.mygdx.game.deathmatch.Particles;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Explosion_Death {
    private Vector2 position;
    private float time_life;
    private int nom_texture;
    private float kefm = 1;


    public Explosion_Death() {
        position = new Vector2(-150, -151);
        nom_texture = 1;
        kefm = 1;
    }

    public void setParameters(float x, float y) {
        this.position.set(x, y);
        nom_texture = 0;
        this.time_life = 1.5f;

    }

    public float getTime_life() {
        return time_life;
    }

    public void setParameters(float x, float y, float kZoom) {
        this.position.set(x, y);
        nom_texture = 0;
        this.time_life = 1.5f;
        this.kefm = kZoom;
    }

    public float getKefm() {
        return kefm;
    }

    public void setKefm(float kefm) {
        this.kefm = kefm;
    }

    public boolean isLife() {
        if (time_life > 0) return true;
        return false;
    }

    public void update(float deltaTime) {
        time_life -= deltaTime;
    }

    public Vector2 getPosition() {
        return position;
    }

//    public String getNameTextureRegion() {
//        int r = 1 + Math.abs((int) MathUtils.map(0, 1.5f, 29, 0, time_life));
//        if (r < 1) r = 1;
//        else if (r > 30) r = 30;
//        return String.valueOf(r);
//    }

    public void addPasricalDeath_little(float x, float y, float x1, float y1, float kefZoom) {
        if (!checkViseble(x, y,x1,y1)) return;
//        Explosion_Death a = this.explosion_Death_little.pollLast();
//        a.setParameters(x, y, kefM);
//        this.explosion_Death_little.offerFirst(a);
    }

    private boolean checkViseble(float x, float y, float x1, float y1) {
        if (!MathUtils.isEqual(x1, x, 500)) return false;
        if (!MathUtils.isEqual(y1, y, 500)) return false;
        return true;
    }


}
