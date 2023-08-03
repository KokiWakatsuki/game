package com.example.game.model;

import com.example.game.model.pack.player_ability_pack.MovingSlash;

public abstract class BossAbility extends GameCharacter{
    abstract public void move();
    abstract public void setBoss(Boss boss);
    public void setPlayer(Player player){

    }
    public void setMovingSlash(MovingSlash movingSlash){

    }
}
