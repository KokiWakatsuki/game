package com.example.game.model;

public class Explain {
    private GameExplain gameExplain;
    private BossExplain bossExplain;
    private TrapExplain trapExplain;

    // セット
    public void setGameExplain(GameExplain gameExplain) {
        this.gameExplain = gameExplain;
    }
    public void setTrapExplain(TrapExplain trapExplain) {
        this.trapExplain = trapExplain;
    }
    public void setBossExplain(BossExplain bossExplain) {
        this.bossExplain = bossExplain;
    }

    // ゲット
    public String getGameExplain() {
        return gameExplain.getGameExplain();
    }
    public String getTrapExplain() {
        return trapExplain.getTrapExplain();
    }
    public String getBossExplain() {
        return bossExplain.getBossExplain();
    }


}
