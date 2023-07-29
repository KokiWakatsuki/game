package com.example.game.model;

public class Explain6 implements BossExplain, GameExplain, TrapExplain{
    public String getGameExplain(){
        return "危険ポイント\nHPが0になるとGameOverだよ！気を付けて！\n煙が立っているところには落とし穴があるよ！ジャンプして飛び越えて！";
    }
    public String getBossExplain(){
        return "aaaaa";
    }
    public String getTrapExplain(){
        return "ccccc";
    }
}
