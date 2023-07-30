package com.example.game.model;

import com.example.game.model.pack.explain_pack.BossExplain;
import com.example.game.model.pack.explain_pack.GameExplain;

public class Explain {
    private GameExplain gameExplain;
    private BossExplain bossExplain;

    // セット
    public void setGameExplain(GameExplain gameExplain) {
        this.gameExplain = gameExplain;
    }
    public void setBossExplain(BossExplain bossExplain) {
        this.bossExplain = bossExplain;
    }

    // ゲット
    public String getGameExplain() {
        return gameExplain.getGameExplain();
    }
    public String getBossExplain() {
        return bossExplain.getBossExplain();
    }


}
