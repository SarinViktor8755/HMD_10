package com.mygdx.game.deathmatch.HUDAudio;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.deathmatch.MainGaming;
import com.mygdx.game.deathmatch.Service.OperationVector;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class AudioEngine {
    MainGaming mainGaming;
    ConcurrentHashMap<Integer, Float> stepCounter;
    private boolean keyFot;
    public MusicGame musicGame;

    public AudioEngine(MainGaming mainGaming) {
        this.mainGaming = mainGaming;
        stepCounter = new ConcurrentHashMap<Integer, Float>();
        musicGame = new MusicGame();
    }

    public void pleySoundKickStick(int xEvents, int yEvents) {
        float distanc = 1;
        int x = (int) mainGaming.getHero().getPosition().x;
        int y = (int) mainGaming.getHero().getPosition().y;
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/hit" + MathUtils.random(1, 2) + ".ogg", Sound.class);
        distanc = 1500 - OperationVector.getDistance(x, y, xEvents, yEvents);
        distanc = distanc / 1500;
        if (distanc < 0) return;
        long id = sound.play();
        sound.setPitch(id, MathUtils.random(.95f, 1.1f));
        sound.setVolume(id, distanc);
    }

    public void pleySoundKickStick() {
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/hit" + MathUtils.random(1, 2) + ".ogg", Sound.class);
        long id = sound.play();
        sound.setPitch(id, MathUtils.random(.95f, 1.2f));
    }

    //////////////
    public void pleySoundKickPistols(int xEvents, int yEvents) {
        float distanc = 1;
        int x = (int) mainGaming.getHero().getPosition().x;
        int y = (int) mainGaming.getHero().getPosition().y;
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/pistolShooting" + MathUtils.random(1, 2) + ".ogg", Sound.class);
        distanc = 1500 - OperationVector.getDistance(x, y, xEvents, yEvents);
        distanc = distanc / 1500;
        if (distanc < 0) return;
        long id = sound.play();
        sound.setPitch(id, MathUtils.random(.8f, 1.1f));
        sound.setVolume(id, distanc);
    }

    public void pleySoundKickPistols() {
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/pistolShooting" + MathUtils.random(1, 2) + ".ogg", Sound.class);
        long id = sound.play();
        sound.setPitch(id, MathUtils.random(.8f, 1.2f));
    }
    /////////////

    //////////////
    public void pleySoundKickShotgun(int xEvents, int yEvents) {
        float distanc = 1;
        int x = (int) mainGaming.getHero().getPosition().x;
        int y = (int) mainGaming.getHero().getPosition().y;
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/shotgun.ogg", Sound.class);
        distanc = 1500 - OperationVector.getDistance(x, y, xEvents, yEvents);
        distanc = distanc / 1500;
        if (distanc < 0) return;
        long id = sound.play();
        sound.setPitch(id, MathUtils.random(.8f, 1.1f));
        sound.setVolume(id, distanc);
    }

    public void pleySoundKickShotgun() {
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/shotgun.ogg", Sound.class);
        long id = sound.play();
        sound.setPitch(id, MathUtils.random(.8f, 1.2f));
    }
    /////////////

    public void pleyFight() {
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/f.ogg", Sound.class);
        long id = sound.play();
    }

    public void pleyYouWin(){
        musicGame.stopMusic();
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/win.ogg", Sound.class);
        long id = sound.play();
    }

    public void pleyYouLoose(){
        musicGame.stopMusic();
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/loose.ogg", Sound.class);
        long id = sound.play();
    }


    public void pleyLostLead() {
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/lostPrimuschestvo.ogg", Sound.class);
        sound.play();
    }

    public void pleyBestLead() {
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/best.ogg", Sound.class);
        sound.play();
    }
    public void pleyVoice() {
        if(getInVoise()) return;
        Sound sound = mainGaming.getAssetsManagerGame().get("voice/voice"+MathUtils.random(1,5)+".ogg", Sound.class);
        sound.play();
    }


    public void pleySoundTrampFot(int xEvents, int yEvents) {
        float distanc = 1;
        int x = (int) mainGaming.getHero().getPosition().x;
        int y = (int) mainGaming.getHero().getPosition().y;
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/death" + MathUtils.random(1, 2) + ".ogg", Sound.class);
        distanc = 1500 - OperationVector.getDistance(x, y, xEvents, yEvents);
        distanc = distanc / 1500;
        if (distanc < 0) return;
        long id = sound.play();
        sound.setPitch(id, MathUtils.random(.8f, 1.2f));
        sound.setVolume(id, distanc);
        // System.out.println(distanc);

    }

    public boolean getInVoise(){ // проеряет есть ли звук
        try {
       return mainGaming.getMainClient().getVoiceChatClient().isInVoise();
        } catch (NullPointerException e){
            return false;
        }
    }


    public void pleySoundBrainSmeared(int distance) {


    }

    public void pleySoundWinRound(int distance) {


    }

    public void pleySoundFailureRound(int distance) {


    }

    public void playTrampFeet(int xEvents, int yEvents) {  // звуктопот ног
        float distanc;
        int x = (int) mainGaming.getHero().getPosition().x;
        int y = (int) mainGaming.getHero().getPosition().y;
        Sound sound = mainGaming.getAssetsManagerGame().get("audio/top" + MathUtils.random(1, 3) + ".ogg", Sound.class);
        distanc = 1500 - OperationVector.getDistance(x, y, xEvents, yEvents);
        distanc = distanc / 3000;
        //System.out.println(distanc);
        if (distanc < 0) return;
        ;
        long id = sound.play();
        sound.setPitch(id, MathUtils.random(.8f, 1.2f));
        sound.setVolume(id, distanc);
    }


    //////////////////////////////////////////////////////////
    public void act(float dt) {
        musicGame.muteOut(getInVoise(),dt); // проверка и приглушения звука от голоса )) - надо провреить как работает
        Iterator<Map.Entry<Integer, Float>> entries = this.stepCounter.entrySet().iterator();
        cleanStepCounter(dt);
        //System.out.println(this.getStepCounter());
        while (entries.hasNext()) {
            try {
                Map.Entry<Integer, Float> entry = entries.next();
                // System.out.println("ID = " + entry.getKey() + " День недели = " + entry.getValue());
                if (entry.getValue() - dt >= 0)
                    this.stepCounter.put(entry.getKey(), entry.getValue() - dt);
                this.keyFot = false;
            } catch (ConcurrentModificationException e) {
                this.keyFot = false;
            }
        }

    }


    private void cleanStepCounter(float dt) {
        Iterator<Map.Entry<Integer, Float>> entries = this.stepCounter.entrySet().iterator();
        while (entries.hasNext()) {
            try {
                Map.Entry<Integer, Float> entry = entries.next();
                if (entry.getValue() - dt <= 0) this.stepCounter.remove(entry.getKey());

            } catch (ConcurrentModificationException e) {
                System.out.println("cleanStepCounter  ConcurrentModificationException");
            }
        }

    }

    public void addNewSoundStepToPleyerFromID(int id) { // добавление нового звука шага в массивж
        // System.out.println("popitka");
        if (keyFot) return;
        //System.out.println("1popitka YES");
        if (!stepCounter.containsKey(id)) {
            stepCounter.put(id, .35f);
            playTrampFeet(mainGaming.getHero().getOtherPlayers().getXplayToId(id), mainGaming.getHero().getOtherPlayers().getYplayToId(id));
        }
        this.keyFot = true;
    }

    public ConcurrentHashMap<Integer, Float> getStepCounter() {
        return stepCounter;
    }
}
