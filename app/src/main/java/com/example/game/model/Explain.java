package com.example.game.model;

public class Explain {
    private BossExplain bossExplain;
    private ControlExsplain controlExplain;
    private TrapExplain trapExplain;

    public String getBossExplain() {
        return bossExplain.getBossExplain();
    }

    public void setBossExplain(BossExplain bossExplain) {
        this.bossExplain = bossExplain;
    }

    public String getControlExplain() {
        return controlExplain.getControlExplain();
    }

    public void setControlExplain(ControlExsplain controlExsplain) {
        this.controlExplain = controlExsplain;
    }

    public String getTrapExplain() {
        return trapExplain.getTrapExplain();
    }

    public void setTrapExplain(TrapExplain trapExplain) {
        this.trapExplain = trapExplain;
    }
}
