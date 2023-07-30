package com.example.game.model.pack.explain_pack;

public class Explain1 implements BossExplain, GameExplain{
    public String getGameExplain(){
        return "画面をタップして説明を読んでね！";
    }
    public String getBossExplain(){
        return "怪しい雰囲気が漂っている・・・";
    }
}
