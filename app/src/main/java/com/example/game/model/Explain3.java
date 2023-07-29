package com.example.game.model;

public class Explain3 implements BossExplain, GameExplain, TrapExplain{
    public String getGameExplain(){
        return "操作方法\n画面を傾けるとプレーヤーが動くよ！\n右側をタップするとプレイヤーがジャンプ！障害物をよけられるよ！";
    }
    public String getBossExplain(){
        return "aaaaa";
    }
    public String getTrapExplain(){
        return "ccccc";
    }
}

