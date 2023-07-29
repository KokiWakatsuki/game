package com.example.game.model;

public class Explain5 implements BossExplain, GameExplain{
    public String getGameExplain(){
        return "操作方法\n左上をタップするとバリアを張れるよ！\n3秒間ダメージを受けないけど、4回しか使えないから注意してね！";
    }
    public String getBossExplain(){
        return "ボスは瞬間移動をするから気を付けて！";
    }
}
