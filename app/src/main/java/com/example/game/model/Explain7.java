package com.example.game.model;

public class Explain7 implements BossExplain, GameExplain, TrapExplain{
    public String getGameExplain(){
        return "説明はこれで終わり！タップしたらゲームが始まるよ！\nクリア目指して頑張って！";
    }
    public String getBossExplain(){
        return "aaaaa";
    }
    public String getTrapExplain(){
        return "ccccc";
    }
}

