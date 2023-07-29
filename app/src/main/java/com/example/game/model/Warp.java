package com.example.game.model;

import com.example.game.MainActivity;
import com.example.game.views.MainView;

public class Warp extends Trap{
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }
    public Warp() {
        this.x = 10000;
        this.y = 50;
        this.xSize = 300;
        this.ySize = 300;
    }

    public void move(){
        // fullOverlapだけではワープホールに入っている感じがしないため、中心あたりまで進んだらワープするようにする
        if(fullOverlap(player) && player.getX() > (this.x + this.xSize / 3)){
            player.setX(15000);
            player.setY(10);
            MainActivity.setExplainCount(0);
            MainView.setExplainFlag(false);
        }
    }
}
