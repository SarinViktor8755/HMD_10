package com.mygdx.game.deathmatch.HUDAudio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;

public class MusicGame {
    private Music music;
    private int id_music;
    private int counVice;

    public MusicGame() {
        if (Gdx.files.internal("audio/music.ogg").exists())
            music = Gdx.audio.newMusic(Gdx.files.internal("audio/music.ogg"));
        counVice = 50;
    }

    public void pleyMusic() {
        this.music.play();
        this.music.setVolume(1);
        this.music.setLooping(true);
    }

    public void stopMusic() {
        this.music.stop();
        this.music.setVolume(0);
    }

    public void muteOut(boolean down) { // приглушаем музыку для воис чата
        if (down) {
            music.stop();
            counVice = 0;

        } else {

            if (counVice > 50) music.play();
            counVice++;
            System.out.println(counVice);
        }
        music.setVolume(MathUtils.clamp(music.getVolume(), 20, 100));
    }

    public void setVolme(float vol) {
        this.music.setVolume(vol);
    }

    public void musicStopPley() {
        this.music.stop();
    }

    public void dispose() {
        this.music.dispose();
    }
}
