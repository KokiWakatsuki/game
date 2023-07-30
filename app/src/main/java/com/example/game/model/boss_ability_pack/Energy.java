package com.example.game.model.boss_ability_pack;

import com.example.game.model.Boss;
import com.example.game.model.BossAbility;
import com.example.game.model.Player;

public class Energy extends BossAbility {

    private Boss boss;
    private Player player;
    private int energyCount = 0;
    private boolean damageFlag = false;
    private boolean energy = false;
    private float fyAccel = -0.1f;
    private float fySpeed = 12.0f;
    private float fy = 0.0f;
    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public float getFy() {
        return fy;
    }
    public Energy() {
        this.x = -300;
        fy = -300.0f;
        this.xSize = 300;
        this.ySize = 300;
    }
    public void initialize(){
        energyCount = 0;
        boss.falseEnergyFlag();
        this.x = -300;
        fy = -300.0f;
        fySpeed = 12.0f;
        energy = false;
    }

    public void move(){
        this.y = (int)fy;

        if(boss.isEnergyFlag()){
            if(!energy){
                this.x = (player.getX() > boss.getX()) ? boss.getX() + boss.getxSize() : boss.getX();
                fy = (float) boss.getY();
                this.xSpeed =  (player.getX() > boss.getX()) ? 3 : -3;
                energy = true;
            }
            this.x += xSpeed;
            fySpeed += fyAccel;
            fy += fySpeed;

            energyCount++;
        }

        if(energyCount > 400){
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
