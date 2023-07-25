package com.example.game.model;

public class Slash extends GameCharacter{
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }
    public Slash() {
        this.x = -120;
        this.y = -120;
        this.xSize = 150;
        this.ySize = 150;
    }

    public void move(){
        if(player.isSlash()){
            this.x = (player.getXSpeed() < 0) ? player.getX() - (this.xSize - player.getxSize()) : player.getX();
            this.y = player.getY();
        }else{
            this.x = -this.xSize;
            this.y = -this.ySize;
        }
    }
}
