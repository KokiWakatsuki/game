package com.example.game.model;

public class Explain4 implements BossExplain, GameExplain, TrapExplain{
    public String getGameExplain(){
        return "操作方法\n左下をタップすると剣で攻撃！\n障害物を消せるよ！";
    }
    public String getBossExplain(){
        return "aaaaa";
    }
    public String getTrapExplain(){
        return "ccccc";
    }
}
