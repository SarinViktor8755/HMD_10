package com.mygdx.game.deathmatch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.deathmatch.Assets.AssetsManagerGame;
import com.mygdx.game.deathmatch.Characters.MainCharacter;
import com.mygdx.game.deathmatch.ClientNetWork.MainClient;
import com.mygdx.game.deathmatch.HUDAudio.AudioEngine;
import com.mygdx.game.deathmatch.HUDAudio.Hud;
import com.mygdx.game.deathmatch.HUDAudio.SoundTrack;


import com.mygdx.game.deathmatch.Ip.AndroidInputProcessorGamePley;
import com.mygdx.game.deathmatch.Ip.DesktopInputProcessorGamePley;
import com.mygdx.game.deathmatch.Ip.InputProc;
import com.mygdx.game.deathmatch.LoadingScreen.StartScreen;
import com.mygdx.game.deathmatch.Particles.ParticleCustum;
import com.mygdx.game.deathmatch.RenderStartScreen;
import com.mygdx.game.deathmatch.Service.NikName;
import com.mygdx.game.deathmatch.SpaceMap.IndexMap;
import com.mygdx.game.deathmatch.Service.OperationVector;
import com.mygdx.game.deathmatch.ZombiKiller;


public class MainGaming implements Screen {
    private MainClient mainClient;
    private ZombiKiller zk;
    private OrthographicCamera camera;
    private World world;
    private SpriteBatch batch;
    private GameSpace gSpace;
    private MainCharacter hero;
    private Group gHero;


    private Hud hud;
    private IndexMap indexMap; // ??????????
    private AudioEngine audioEngine;
    private SoundTrack soundtrack;

    private TextureRegion textureAim;
    private FillViewport viewport;
    private RenderStartScreen renderStartScreen;
    private float timeInGame;
    Vector2 rot;
    private StartScreen startScreen;
    private InputProc apInput;
    InputMultiplexer inputMultiplexer;


    ParticleCustum particleCustum;

    public Hud getHud() {
        return hud;
    }

    public void setHud(Hud hud) {
        this.hud = hud;
    }

    public MainGaming setApInput(InputProc apInput) {
        this.apInput = apInput;
        return this;
    }


    public MainGaming(ZombiKiller zk) {

        //System.out.println("MainGaming !!!!!!!!!!!!!");
        this.zk = zk;
        mainClient = new MainClient(this);
        this.world = new World(new Vector2(0, 0), true);
        //mainClient.coonectToServer();
        mainClient.coonectToServer();


    }

    @Override
    public void show() {
        Gdx.app.log("access_audio_recording", String.valueOf(zk.isAccess_audio_recording()));
        System.out.println("show MainGaming");
       // System.out.println();
        rot = new Vector2();
        this.startScreen = new StartScreen(zk);

        setAssetsManagerGame(AssetsManagerGame.loadAllAsset(getAssetsManagerGame()));
        //  this.particleCustum = new ParticleCustum(getAssetsManagerGame().get("de/de.pack", TextureAtlas.class));


        this.audioEngine = new AudioEngine(this);
        this.gSpace = new GameSpace();

        this.indexMap = new IndexMap(this);
        this.hero = new MainCharacter(this);
        this.gHero = new Group();
        this.hero.setNikNameplayer(NikName.getNikName());
        gHero.addActor(hero);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FillViewport(zk.WHIDE_SCREEN, zk.HIDE_SCREEN, camera);

        inputMultiplexer = new InputMultiplexer();

        if (zk.isAndroid()) {
            apInput = new AndroidInputProcessorGamePley(this);
            // Gdx.input.setInputProcessor(inputMultiplexer);

        } else {
            apInput = new DesktopInputProcessorGamePley(this);

            // inputMultiplexer.setProcessors(apInput);


            // Gdx.input.setCursorCatched(true);
        }


        //zk.getMainGameScreen();
        hud = new Hud(this);

        inputMultiplexer.setProcessors(apInput);
        //inputMultiplexer.setProcessors(hud.getStageHUD());


        //inputMultiplexer.setProcessors(apInput);

        // Gdx.input.setInputProcessor(apInput);
        Gdx.input.setInputProcessor(inputMultiplexer);


        soundtrack = new SoundTrack(this);
        textureAim = getAssetsManagerGame().get("character/character", TextureAtlas.class).findRegion("aim");
        this.timeInGame = 0;
        renderStartScreen = new RenderStartScreen(zk, camera, viewport, getBatch());
        audioEngine.musicGame.pleyMusic();
        //this.world = new World(new Vector2(0,0),true);

//        Gdx.app.error("zk ::::", String.valueOf(zk.tip));
//        Gdx.app.error("zk ::::", String.valueOf(zk.isAccess_audio_recording()));

        //   Gdx.input.setInputProcessor(inputMultiplexer);

    }

    public FillViewport getViewport() {
        return viewport;
    }

    public float getTimeInGame() {
        return timeInGame;
    }

    @Override
    public void render(float delta) {

        if (!mainClient.isConnectToServer()) {
            renderStartScreen.render(delta);
            mainClient.coonectToServer();
            return;

        }

        updateVC();

        getHud().update();

        //   System.out.println("assets:: " + AssetsManagerGame.loadAsset(zk.assetsManagerGame));
//        System.out.println(getHero().getPosition());
//        System.out.println("camera " +getCamera().position);
//        getBatch().getTransformMatrix().scl(55);
//        getBatch().getProjectionMatrix().scl(55);
//        getBatch().getProjectionMatrix().inv();
//        viewport.
        // System.out.println(Gdx.graphics.getHeight());
        float dt = Gdx.graphics.getDeltaTime();
        apInput.act(delta);
        getMainClient().actionMainClient();
        gHero.act(dt);
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(.2f + (getHero().getGlobalAlpha() / 50), .2f - (getHero().getGlobalAlpha() / 80), .2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        getBatch().setProjectionMatrix(getBatch().getProjectionMatrix().scl(.2f));
//        getBatch().setProjectionMatrix(getBatch().getProjectionMatrix().scl(.3f));
        batch.begin();


        gHero.draw(batch, 1);

        try {
            rot.set(camera.up.x, camera.up.y);
            getHero().getLith().setConeTower(getHero().getPosition().x, getHero().getPosition().y, rot.angle());
            getHero().getLith().renderLights(camera); // ??????????????????
            getHero().getPoolBlood().renderAd(getBatch());
        } catch (Exception e) {
        }

        batch.end();

        //System.out.println(delta);
        hud.update(delta);
        cameraMove();
        soundtrack.ubdate(dt);
        hud.render(dt);
        this.timeInGame += delta;


        getBatch().begin();
        getHero().getPoolBlood().renderAd(getBatch());
        getBatch().end();
    }

//    public boolean isWrite_permission() {
//        return write_permission;
//    }
//
//    public void setWrite_permission(boolean write_permission) {
//        this.write_permission = write_permission;
//    }

    private void updateVC() {
        // This is some sort of update method that is called periodically in you app.
        // In LibGDX it is called render(). You know the drill.

        // This variable is the time, in seconds, between the calls to update().
        // LibGDX has done this for you!
        ;
        float deltaTime = Gdx.graphics.getDeltaTime();

        //Gdx.app.log("Voise", "isVoice " + apInput.isVoice() + "   isInVoise()"+ mainClient.getVoiceChatClient().isInVoise() + " acsees "+ zk.isAccess_audio_recording());
        // This would be replaced with some sort of user input, such as pressing a button.
        // System.out.println("isVoice " + apInput.isVoice() + "   isInVoise()"+ mainClient.getVoiceChatClient().isInVoise());
///////////////[
        //System.out.println(apInput.isVoice());

        getMainClient().getVoiceChatClient().updateTimerMinus();

        muteSound(deltaTime);


    }




/////////////////

    private void muteSound(float deltaTime){
        if (mainClient.getVoiceChatClient().isInVoise() || apInput.isVoice()){
            audioEngine.musicGame.muteOut(true);
        }
        else {
            audioEngine.musicGame.muteOut(false);
            getHero().setVoiseNomer(Integer.MIN_VALUE);
          //  getMainClient().getVoiceChatClient().nVoiseID = Integer.MIN_VALUE;
        }

        if (zk.isAccess_audio_recording() && apInput.isVoice() && !mainClient.getVoiceChatClient().isInVoise()) {
//            if (MathUtils.randomBoolean()) System.out.println("VOISE OUT> >");
//            else System.out.println("VOISE > OUT ");

            mainClient.getVoiceChatClient().sendVoice(mainClient.client, deltaTime);
        }

    }


    public void renderAim() { // ???????????????????? ????????????
//        System.out.println(hud.getAttacButton().getX()+"   "+hud.getAttacButton().getY());
//        System.out.println(hud.getAttacButton().getImageHeight()+"   "+hud.getAttacButton().getImageWidth());
//        System.out.println(hud.getAttacButton().getMinHeight()+"   "+hud.getAttacButton().getMinWidth());
//        System.out.println(hud.getAttacButton().getPrefHeight()+"   "+hud.getAttacButton().getPrefWidth());

        this.getViewport().setWorldSize(zk.WHIDE_SCREEN + timeInGame * 10, zk.HIDE_SCREEN + +timeInGame * 10);

        if (!getHero().isLive()) return;
        rot.set(camera.up.x, camera.up.y);
        int l = 0;
        if (getHero().getWeapons().getWeapon() != 1) l = 1000;
        else l = 300;
        for (int i = 250; i < l; i += 150) {
            getBatch().setColor(1, 1, 1, 1);
            getBatch().draw(textureAim,
                    (getHero().getPosition().x + rot.x * i) - textureAim.getRegionWidth() / 2,
                    (getHero().getPosition().y + rot.y * i) - textureAim.getRegionWidth() / 2
                    , textureAim.getRegionWidth() / 2, textureAim.getRegionWidth() / 2, textureAim.getRegionWidth(), textureAim.getRegionWidth(),
                    .8f, .8f,
                    rot.angle());
        }

//        for (int i = 0; i < 10; i++) {
//            for (int x = 0; x < 10; x++)
//                batch.draw(textureAim, i * 15, i * 15, 2, 2);
//        }

    }

    public ParticleCustum getParticleCustum() {
        return particleCustum;
    }

    private void cameraMove() {
        camera.up.set(getHero().getCookAngle(), 0);
        OperationVector.setTemp_vector(getHero().getPosition().cpy().add(getHero().getCookAngle().cpy().nor().scl(420)));
        camera.position.set(OperationVector.getTemp_vector().x, OperationVector.getTemp_vector().y, 0);
        camera.update();
        // System.out.println(getHero().getOtherPlayers().getSizeGamePlayer());
    }

    public void writeDead(int id) { // ???????????????? ?????? ??????????????
        getHero().getOtherPlayers().setLiveTiId(id, false);
    }

    public void writeLiving(int id) { // ???????????????? ?????? ??????????
        getHero().getOtherPlayers().setLiveTiId(id, true);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    public MainGaming setAssetsManagerGame(AssetManager assetsManagerGame) {
        this.zk.assetsManagerGame = assetsManagerGame;
        return this;
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        hud.dispose();
        batch.dispose();
        gSpace.dispose();
    }

//    public World getWorld() {
//        return world;
//    }

    public AudioEngine getAudioEngine() {
        return audioEngine;
    }

    public SoundTrack getSoundtrack() {
        return soundtrack;
    }

    public AssetManager getAssetsManagerGame() {
        return this.zk.assetsManagerGame;
    }

    public MainClient getMainClient() {
        return mainClient;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Camera getCamera() {
        return camera;
    }

    public MainCharacter getHero() {
        return hero;
    }

    public ZombiKiller getZk() {
        return zk;
    }

    public InputProc getApInput() {
        return apInput;
    }

    public IndexMap getIndexMap() {
        return indexMap;
    }


    public World getWorld() {
        return this.world;
    }
}
