package com.example.game.model.pack.player_ability_pack;

import com.example.game.model.pack.boss_pack.Boss;
import com.example.game.model.Player;

public class MovingSlash extends PlayerAbility {
    private Player player;
    private Boss boss;
    private boolean bossDamageFlag;
    private int bossDamageCount;

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setBoss(Boss boss) {
        this.boss = boss;
    }
    public float getXSpeed() {
        return xSpeed;
    }
    public boolean isBossDamageFlag(){
        return bossDamageFlag;
    }
    private boolean slashFlag = false;
    public MovingSlash() {
        this.x = -1000;
        this.y = -120;
        this.xSize = 150;
        this.ySize = 150;
        this.xSpeed = 20;
    }

    public void move(){
        this.x += xSpeed;

        if(player.isMovingSlash()){
            if(!slashFlag) {
                this.x = (player.getXSpeed() < 0) ? player.getX() - (this.xSize - player.getxSize()) : player.getX();
                this.y = player.getY();
                this.xSpeed = (player.getXSpeed() < 0) ? -20 : 20;
                slashFlag = true;
            }
        }else{
            this.x = -this.xSize;
            this.y = -this.ySize;
            slashFlag = false;
        }

        if(overlap(boss) && !bossDamageFlag){
            boss.damage();
            bossDamageFlag = true;
        }

        if(bossDamageFlag){
            bossDamageCount++;
        }

        if(bossDamageCount == 200){
            bossDamageFlag = false;
            bossDamageCount = 0;
        }

    }
}
