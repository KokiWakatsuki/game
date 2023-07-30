package com.example.game.model.pack.explain_pack;

public class Explain3 implements BossExplain, GameExplain{
    public String getGameExplain(){
        return "操作方法\n画面を傾けるとプレーヤーが動くよ！\n右側をタップするとプレイヤーがジャンプ！障害物をよけられるよ！";
    }
    public String getBossExplain(){
        return "ここではバリアが使えないみたい！注意して！";
    }
}

