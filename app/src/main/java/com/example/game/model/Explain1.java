package com.example.game.model;

public class Explain1 implements BossExplain, GameExplain, TrapExplain{
    public String getGameExplain(){
        return "画面をタップして説明を読んでね！";
    }
    public String getBossExplain(){
        return "aaa";
    }
    public String getTrapExplain(){
        return "ccc";
    }

}
