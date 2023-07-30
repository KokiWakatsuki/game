package com.example.game.model.explain_pack;

public class Explain2 implements BossExplain, GameExplain{
    public String getGameExplain(){
        return "このゲームは森を抜けてボスを倒すゲームだよ！\n道中には障害物や落とし穴があるから気を付けて！";
    }
    public String getBossExplain(){
        return "ここはボスの部屋かもしれない・・・";
    }
}
