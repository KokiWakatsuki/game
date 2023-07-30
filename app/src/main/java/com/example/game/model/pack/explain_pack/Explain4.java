package com.example.game.model.pack.explain_pack;

public class Explain4 implements BossExplain, GameExplain{
    public String getGameExplain(){
        return "操作方法\n左下をタップすると剣で攻撃！\n障害物を消せるよ！";
    }
    public String getBossExplain(){
        return "プレイヤーの剣がパワーアップ！\n斬撃を飛ばせるようになったよ！";
    }
}
