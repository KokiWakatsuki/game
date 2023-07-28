package com.example.game.model;

import com.example.game.views.MainView;

public class MovingNeedle extends Trap{

    private Player player;
    private Slash slash;
    private Barrier barrier;
    private boolean damageFlag = false;
    private boolean deadFlag = false;

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setSlash(Slash slash) {
        this.slash = slash;
    }
    public void setBarrier(Barrier barrier) {
        this.barrier = barrier;
    }
    public void dead(){
        deadFlag = true;
        this.x = -this.xSize;
        this.y = -this.ySize;
        this.xSpeed = 0;
        this.ySpeed = 0;
    }
    public boolean isDead(){
        return deadFlag;
    }
    public MovingNeedle() {
        this.x = MainView.backGroundMax + (int)(Math.random() * 3000);
        this.y = 100 + (int)(Math.random() * 600);
        this.xSize = 150;
        this.ySize = 50;
        this.xSpeed = -5 -(int)(Math.random() * 10);
    }

    public void move(){
        x += xSpeed;

        if(x < -this.xSize){
            x = MainView.backGroundMax + (int)(Math.random() * 3000);
            y = 100 + (int)(Math.random() * 600);
        }

        // 無限ダメージを防ぐためにdamageFlagで制御する
        if(overlap(player) && !damageFlag){
            player.damage();
            damageFlag = true;
        } else if(!overlap(player)) {
            damageFlag = false;
        }

        if(overlap(slash) || overlap(barrier)){
            dead();
        }
    }
}
