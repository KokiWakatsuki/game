package com.example.game.model;

public class Beam extends GameCharacter {
    private Boss boss;
    private Player player;
    private int beamSize = 0;
    private int beamCount = 0;
    private boolean beamState = false;
    private boolean beamFlag = false;
    private boolean damageFlag = false;
    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public boolean isBeamState() {
        return beamState;
    }

    public Beam() {
        this.x = -100;
        this.y = -100;
        this.xSize = 0;
        this.ySize = 100;
    }
    public void initialize(){
        beamCount = 0;
        boss.falseBeamFlag();
        this.x = -100;
        this.y = -100;
        beamSize = 0;
    }

    public void move(){
        if (boss.isBeamFlag()) {
            if(!beamFlag){
                beamState = (player.getX() > boss.getX()) ? true : false;
                beamFlag = true;
            }
            this.x = beamState ? boss.getX() + boss.getxSize() : boss.getX() - this.xSize;
            beamCount++;
            beamSize += 50;

            this.y = boss.getY() + (boss.getySize() / 2);

            this.xSize = beamSize;
        }else{
            beamFlag = false;
        }

        if(beamCount > 50){
            initialize();
        }

        if(overlap(player) && !damageFlag){
            player.damage();
            damageFlag = true;
        } else if(!overlap(player)) {
            damageFlag = false;
        }
    }
}
