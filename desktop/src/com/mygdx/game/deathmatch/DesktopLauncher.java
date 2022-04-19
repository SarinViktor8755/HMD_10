package com.mygdx.game.deathmatch;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "HOTLINE DEATHMATCH";
		config.width = ZombiKiller.WHIDE_SCREEN / 2;
		config.height = ZombiKiller.HIDE_SCREEN / 2;

		//  config.setWindowedMode(ZombiKiller.WHIDE_SCREEN / 2,ZombiKiller.HIDE_SCREEN / 2);
		new LwjglApplication(new ZombiKiller(3), config);
	}
}
