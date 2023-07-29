package com.example.game.model;

public class Explain5 implements BossExplain, GameExplain, TrapExplain{
    public String getGameExplain(){
        return "操作方法\n左上をタップするとバリアを張れるよ！\n3秒間ダメージを受けないけど、4回しか使えないから注意してね！";
    }
    public String getBossExplain(){
        return "aaaaa";
    }
    public String getTrapExplain(){
        return "ccccc";
    }
}
