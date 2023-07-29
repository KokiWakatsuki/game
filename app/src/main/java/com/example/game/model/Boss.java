package com.example.game.model;

public class Boss extends GameCharacter{

    private boolean appearFlag = false;
    private boolean damageFlag;
    private int count = 0;
    private int bossSpeed = 3;
    private int bossLoop = 20;
    private boolean beamFlag = false;
    private boolean energyFlag = false;
    private int bossHp = 10;
    private int bossDamageCount = 0;
    private boolean bossDeadFlag = false;
    private boolean bossDamageFlag = false;
    private boolean bossDamageEffect = false;

    private Player player;

    public void setPlayer(Player player) {
    }

    public void dead() {
    }

    public void damage(){
    }

    public boolean isBeamFlag(){
        return false;
    }

    public boolean isEnergyFlag(){
        return false;
    }

    public void falseBeamFlag(){
    }

    public void falseEnergyFlag(){
    }

    public void move(){
    }

    public int getBossHp() {
        return 0;
    }

    public void setBossHp(int bossHp) {
    }

    public void setAppearFlag(boolean appearFlag) {
    }

    public void setCount(int count) {
    }

    public void setDamageFlag(boolean damageFlag) {
    }
}
