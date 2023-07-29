package com.example.game.model;

public class Explain2 implements BossExplain, GameExplain, TrapExplain{
    public String getGameExplain(){
        return "このゲームは森を抜けてボスを倒すゲームだよ！\n道中には障害物や落とし穴があるから気を付けて！";
    }
    public String getBossExplain(){
        return "aaaaa";
    }
    public String getTrapExplain(){
        return "ccccc";
    }
}
