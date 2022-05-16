package com.mygdx.game.deathmatch.Lighting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;

import com.mygdx.game.deathmatch.MainGaming;

import java.util.ArrayList;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class B2lights {
    private World world;

    private PointLight pointLightHero;
    private ObFromLight object;
    private RayHandler rayHandlerHero;

    private ArrayList<PointLight> pointLightsList = new ArrayList<PointLight>();

    private ConeLight coneLightHero;
    private ConeLight coneLightTower;

    private BuletFlash buletFlash;
    private boolean lasetOn;

    private float laserLith;

    public B2lights(MainGaming mg) {
        lasetOn = true;
        //Gdx.app.log("Gdx version", com.badlogic.gdx.Version.VERSION);
        this.world = mg.getWorld();
        pointLightsList = new ArrayList<>();
        RayHandler.useDiffuseLight(true);
        this.rayHandlerHero = new RayHandler(this.world);
        object = new ObFromLight(this.world); // припятсвия
        object.crearBodys(mg.getIndexMap().getTopQualityMap_Box());
        PointLight pl;


        // поле
        for (int i = 0; i < 5000; i += 500) {
            for (int j = 0; j < 5000; j += 500) {
                pl = new PointLight(rayHandlerHero, 10, getColorFromPoint(), 1300, j, i);
                pl.setIgnoreAttachedBody(false);
                pointLightsList.add(pl);
            }
        }
        coneLightTower = new ConeLight(rayHandlerHero, 3, Color.RED, 1800, 0, 0, 45, 4); // лазер
        pointLightHero = new PointLight(rayHandlerHero, 4, Color.WHITE, 300, 0, 0); /// свитильник героя
        coneLightHero = new ConeLight(rayHandlerHero, 65, Color.ROYAL, 1500, 0, 0, 90, 60);
//

        buletFlash = new BuletFlash(rayHandlerHero);

        //на машинах
        for (Body cars : object.getBodyList()) {
            pl = new PointLight(rayHandlerHero, 4, getColorFromPoint(), 800, cars.getPosition().x, cars.getPosition().y);
            pl.attachToBody(cars);
        }

        rayHandlerHero.setAmbientLight(.5f);

        // rayHandlerHero.setShadows();
    }


    public void setConeTower(float x, float y, float align) {
        this.coneLightTower.setPosition(x, y);
        this.coneLightTower.setDirection(align);
    }

    Color getColorFromPoint() {
        Color colorPoint;
        if (MathUtils.randomBoolean(.1f)) colorPoint = Color.CHARTREUSE;
        else if ((MathUtils.randomBoolean(.1f))) colorPoint = Color.RED;
        else if ((MathUtils.randomBoolean(.1f))) colorPoint = Color.NAVY;
        else if ((MathUtils.randomBoolean(.1f))) colorPoint = Color.BLUE;
        else if ((MathUtils.randomBoolean(.2f))) colorPoint = Color.OLIVE;
        else if ((MathUtils.randomBoolean(.3f))) colorPoint = Color.YELLOW;
        else colorPoint = Color.BROWN;
        return colorPoint;
    }


    public void upDateLights(float xHero, float yHero, float align, float timer) {
        world.step(1 / 30f, 1, 1);
        coneLightHero.setPosition(xHero, yHero);
        pointLightHero.setPosition(xHero, yHero);
        coneLightHero.setDirection(align);
        buletFlash.upDate();

        /////////////////////
        laserLith = MathUtils.clamp(laserLith, 300, 1800);
        coneLightTower.setDistance(laserLith);
        if (lasetOn) laserLith = laserLith + Gdx.graphics.getDeltaTime() * 550;
        else laserLith = laserLith - Gdx.graphics.getDeltaTime() * 550;
        coneLightTower.setDistance(laserLith);
        attenuation(timer); // затухание локации

    }

    private  void attenuation(float timer){
        float TIMER_END = 40_000;
        if (timer < TIMER_END) {
            for (int i = 0; i < pointLightsList.size(); i++) {
                pointLightsList.get(i).setDistance(MathUtils.map(0, TIMER_END, 400, 1300, timer));
            }
            pointLightHero.setDistance(MathUtils.map(0, TIMER_END, 0, 300, timer));
        }
    }

    public void renderLights(OrthographicCamera camera) {
        rayHandlerHero.setCombinedMatrix(camera);
        rayHandlerHero.updateAndRender();
    }

    public boolean isLasetOn() {
        return lasetOn;
    }

    public void setLasetOn(boolean lasetOn) {
        this.lasetOn = lasetOn;
    }

    public boolean isAtShadow(float x, float y) {
        return rayHandlerHero.pointAtShadow(x, y);
    }

    public Texture getTexture() {
        return rayHandlerHero.getLightMapTexture();
    }


    public void startBulletFlash(float x, float y) {
        buletFlash.newFlesh(x, y);
    }

}
