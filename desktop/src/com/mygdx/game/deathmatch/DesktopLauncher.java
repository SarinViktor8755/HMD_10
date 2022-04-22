package com.mygdx.game.deathmatch;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration ();
		config.setWindowedMode(ZombiKiller.WHIDE_SCREEN/4,ZombiKiller.HIDE_SCREEN/4);
		config.setTitle("HOTLINE DEATHMATCH") ;
//		config.width = ZombiKiller.WHIDE_SCREEN / 2;
//		config.height = ZombiKiller.HIDE_SCREEN / 2;

		//  config.setWindowedMode(ZombiKiller.WHIDE_SCREEN / 2,ZombiKiller.HIDE_SCREEN / 2);
		new Lwjgl3Application(new ZombiKiller(3), config);
	}
}
