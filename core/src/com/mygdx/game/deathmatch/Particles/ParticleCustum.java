package com.mygdx.game.deathmatch.Particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayDeque;

public class ParticleCustum {

    ArrayDeque<Explosion_Death> explosion_Death_little;

    public ParticleCustum() {
        this.explosion_Death_little = new ArrayDeque<Explosion_Death>();
        for (int i = 0; i < 10; i++) {
            Explosion_Death ed = new Explosion_Death();
            this.explosion_Death_little.add(ed);
        }


    }
    public void render(SpriteBatch spriteBatch,float deltaTime){
        this.renderExplosion_Death(spriteBatch,deltaTime);
    }

    public void renderExplosion_Death(SpriteBatch sb,float deltaTime) {

        for (Explosion_Death ed : explosion_Death_little) {
            if (!ed.isLife()) continue;
            ed.update(deltaTime);

//            /////////////////
//            TextureAtlas.AtlasRegion tex =  textureAtlasDeathExplosion.findRegion(ed.getNameTextureRegion());
//            float xw = MathUtils.map(100,0,100,0,tex.getRegionWidth());
//            float yw = MathUtils.map(100,0,100,0,tex.getRegionHeight());
            /////////////////
//            sb.draw(
//                    tex,
//                    ed.getPosition().x - (tex.getRegionWidth() / 2/ed.getKefm()*ed.getTime_life()), ed.getPosition().y - (tex.getRegionHeight() / 2/ed.getKefm()*ed.getTime_life()),
//                    xw/ed.getKefm()*ed.getTime_life(), yw/ed.getKefm()*ed.getTime_life()
//            );
        }



    }

    public void addPasricalDeath_little(float x, float y,float kefM) {
       // if (!checkViseble(x, y)) return;
        Explosion_Death a = this.explosion_Death_little.pollLast();
        a.setParameters(x, y,kefM);
        this.explosion_Death_little.offerFirst(a);
    }




}
