package com.example.game.model.pack.explain_pack;

public class Explain6 implements BossExplain, GameExplain{
    public String getGameExplain(){
        return "危険ポイント\nHPが0になるとGameOverだよ！気を付けて！\n煙が立っているところには落とし穴があるよ！ジャンプして飛び越えて！";
    }
    public String getBossExplain(){
        return "ボスの攻撃は近づくだけでもダメージを受けそうな迫力だ！\n近づきすぎないように注意して！";
    }
}
