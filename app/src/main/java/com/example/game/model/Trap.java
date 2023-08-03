package com.example.game.model;

import com.example.game.model.pack.player_ability_pack.Barrier;
import com.example.game.model.pack.player_ability_pack.Slash;

public abstract class Trap extends GameCharacter{
    abstract public void move();
    abstract public void setPlayer(Player player);
    public void setSlash(Slash slash){

    }
    public void setBarrier(Barrier barrier){

    }
}
