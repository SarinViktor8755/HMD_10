package com.mygdx.game.deathmatch;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.deathmatch.Assets.AssetsManagerGame;
import com.mygdx.game.deathmatch.ClientNetWork.MainClient;
import com.mygdx.game.deathmatch.LoadingScreen.LoadingScreen;
import com.mygdx.game.deathmatch.LoadingScreen.StartScreen;
import com.mygdx.game.deathmatch.Menu.MenuScreen;
import com.mygdx.game.deathmatch.Pause.PauseScreen;
import com.mygdx.game.deathmatch.Service.NikName;
import com.mygdx.game.deathmatch.adMod.AdAds;


public class ZombiKiller extends Game {
	public AssetManager assetsManagerGame;

	public static String myNikName;

	static public final int WHIDE_SCREEN = 720;
	static public final int HIDE_SCREEN = 1520;
//    static public final int WHIDE_SCREEN = (int)(720 * .8);
//    static public final int HIDE_SCREEN = (int)(1520 * .8);

	public byte tip = 0;
	private AdAds adMod;


	private MainClient mainClient;
	private MainGaming mGaming;
	private PauseScreen pauseScreen;
	private LoadingScreen loadingScreen;
	private StartScreen startScreen;
	private MenuScreen menuScreen;

	public ZombiKiller(int tip, AdAds adMod) {
		this.adMod = adMod;
		this.tip = (byte) tip;
	}


	public ZombiKiller(int tip) {
		this.tip = (byte) tip;
		adMod = new AdAds() {
			@Override
			public void show() {

			}
		};
	}

	public boolean isAndroid() {
		if (tip == 1) return true;
		return false;
	}

	public void watchAds() {
		this.adMod.show();
	}

	@Override
	public void create() {
		this.startScreen = new StartScreen(this);
		this.assetsManagerGame = new AssetManager();
		AssetsManagerGame.loadAssetForMenu(assetsManagerGame);

		getMenu();
		//getMainGaming();

	}

	public void createGame(){
		this.mGaming = new MainGaming(this);
	}

	public void startGamePlay(){
		this.mGaming = new MainGaming(this);
		this.setScreen(this.mGaming);
		this.myNikName = NikName.getNikName();
	}


	public void setScreenToGame(){
		if(mGaming != null) {
			this.setScreen(mGaming);
		}else {
			this.mGaming = new MainGaming(this);
			this.setScreen(mGaming);
		}
	};

	public MainGaming getmGaming() {
		return mGaming;
	}



	public void getPauseScreen() {
		this.pauseScreen = new PauseScreen(this, true);
		this.setScreen(this.pauseScreen);
	}

	public void getPauseScreen(int PauseTime) {
		mGaming.getMainClient().client.stop();
		try {
			mGaming.getAudioEngine().musicGame.stopMusic();
		}catch (NullPointerException e){}
		boolean ad = true;
		if (mGaming.getTimeInGame() < 15) ad = false;
		this.pauseScreen = new PauseScreen(this, PauseTime, ad);
		this.setScreen(this.pauseScreen);
	}

	public void getLoadingScreen() {
		loadingScreen = new LoadingScreen(this);
		this.setScreen(loadingScreen);
	}

	public void loarToGame() {
		this.loadingScreen.dispose();
		this.mGaming = new MainGaming(this);

		// this.setScreen(this.mGaming);
	}

	public void getMenu(){
		this.menuScreen = new MenuScreen(this);
		this.setScreen(this.menuScreen);
	}

	public void getMainGaming() {
		this.mGaming = new MainGaming(this);
		this.setScreen(this.mGaming);

	}


	public void getMainGamingNewThred() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				getMainGaming(true);
			}
		});
		t.start();
	}

	public void getMainGaming(boolean setGame) {
		System.out.println("create New Game1");
		if (this.pauseScreen != null) this.pauseScreen.dispose();
		System.out.println("create New Game2");
		this.mGaming = new MainGaming(this);
		System.out.println("create New Game3");
	}


	public static String getMyNikName() {
		return myNikName;
	}

	public void getMainGameScreen() {
		this.setScreen(this.mGaming);
	}

	public Screen getMainGameScreen(boolean flag) {
		return this.mGaming;
	}

	public void render() {
		super.render(); // важно!
	}


}
