package com.mygdx.game.deathmatch;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration ();
		config.setWindowedMode(ZombiKiller.WHIDE_SCREEN/2,ZombiKiller.HIDE_SCREEN/2);
		config.setTitle("HOTLINE DEATHMATCH") ;

		new Lwjgl3Application(new ZombiKiller(3), config);
	}
}
