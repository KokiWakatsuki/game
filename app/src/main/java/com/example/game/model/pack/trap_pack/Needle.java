package com.example.game.model.pack.trap_pack;

import com.example.game.model.pack.player_ability_pack.Barrier;
import com.example.game.model.pack.player_ability_pack.Slash;
import com.example.game.model.Player;
import com.example.game.model.Trap;

public class Needle extends Trap {

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
    public Needle(int x, int y) {
        this.x = x + (int) (Math.random() * 200 - 100);
        this.y = y + (int) (Math.random() * 800 - 400);
        this.xSize = 70;
        this.ySize = 300;
        this.ySpeed = 15;
    }

    public void move(){
        y += ySpeed;

        if(y > 15){
            ySpeed = -2;
        } else if(y < -500) {
            ySpeed = 10;
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
