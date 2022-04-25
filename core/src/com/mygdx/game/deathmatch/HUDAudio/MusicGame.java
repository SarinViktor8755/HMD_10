package com.mygdx.game.deathmatch.HUDAudio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;

public class MusicGame {
    private Music music;
    private int id_music;

    public MusicGame() {
        if (Gdx.files.internal("audio/music.ogg").exists())
            music = Gdx.audio.newMusic(Gdx.files.internal("audio/music.ogg"));
    }

    public void pleyMusic() {
        this.music.play();
        this.music.setVolume(100);
        this.music.setLooping(true);
    }

    public void stopMusic() {
        this.music.stop();
        this.music.setVolume(0);
    }

    public void muteOut(boolean down, float dTime) { // приглушаем музыку для воис чата
        if (down) {
            music.setVolume(music.getVolume() - (dTime * 1000));
        } else {
            music.setVolume(music.getVolume() + (dTime * 1000));
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
