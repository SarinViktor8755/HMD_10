package com.mygdx.game.deathmatch.Service;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class NikName {
    public static String getNikName() {
        Preferences prefs = Gdx.app.getPreferences("NikName");
        return prefs.getString("nik", "Name_" + MathUtils.random(2048));
    }

    public static void setNikName(String nik) {
        Preferences prefs = Gdx.app.getPreferences("NikName");
        if (nik.length() < 1) nik = "Ananimus";
        prefs.putString("nik", nik);
        prefs.flush();
    }

    public static String getTokken() {
        String token;
        String tokken = "tokken";
        Preferences prefs = Gdx.app.getPreferences("NikName");
        if (!prefs.contains(tokken)) token = genirateTokk();
        else token = prefs.getString(tokken);
        prefs.putString(tokken, token);
        prefs.flush();
        //System.out.println(prefs.getString(tokken));
        return token;
    }


    private static String genirateTokk() {
        long longToken = Math.abs(random.nextLong());
        return Long.toString(longToken, 16);
    }
}
