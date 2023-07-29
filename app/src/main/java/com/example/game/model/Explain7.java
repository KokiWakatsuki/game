package com.example.game.model;

public class Explain7 implements BossExplain, GameExplain{
    public String getGameExplain(){
        return "説明はこれで終わり！タップしたらゲームが始まるよ！\nクリア目指して頑張って！";
    }
    public String getBossExplain(){
        return "話せるのはここまでみたい・・・\nタップしたらボスとの戦いが始まるよ！頑張って！";
    }
}

