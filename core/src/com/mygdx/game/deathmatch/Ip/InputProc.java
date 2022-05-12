package com.mygdx.game.deathmatch.Ip;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by 1 on 05.09.2019.
 */

public interface InputProc extends InputProcessor {
    boolean scrolled(int amount);

    boolean isMove();
    public void act(float deltTime);
    boolean isTuach();
    boolean isVoice();
}



