package com.example.game.model.pack.player_ability_pack;

import com.example.game.model.Player;

public class Barrier extends PlayerAbility {
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }
    public Barrier() {
        this.x = -180;
        this.y = -180;
        this.xSize = 150;
        this.ySize = 150;
    }

    public void move(){
        if(player.isBarrier() && player.isBarrierState() < player.MaxBarrier){
            this.x = player.getX() - ((this.xSize - player.getxSize()) / 2);
            this.y = player.getY() - ((this.ySize - player.getySize()) / 2);
        }else{
            this.x = -this.xSize;
            this.y = -this.ySize;
        }
    }
}
