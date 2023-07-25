package com.example.game.model;

public class BossBarrier extends GameCharacter{
    private boolean bossState = false;
    public BossBarrier() {
        this.x = -100;
        this.y = -100;
        this.xSize = 0;
        this.ySize = 0;
    }
    private Boss boss;
    private MovingSlash movingSlash;
    public void setBoss(Boss boss) {
        this.boss = boss;
    }
    public void setMovingSlash(MovingSlash movingSlash) {
        this.movingSlash = movingSlash;
    }
    public boolean isBossState(){
        return bossState;
    }

    public void move(){
        this.xSize = boss.getxSize() + 100;
        this.ySize = boss.getxSize() + 100;
        this.x = boss.getX() - ((this.xSize - boss.getxSize()) / 2);
        this.y = boss.getY() - ((this.ySize - boss.getySize()) / 2);

        bossState = movingSlash.isBossDamageFlag();
    }
}
