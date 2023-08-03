package com.example.game.model;

public abstract class PlayerAbility extends GameCharacter{
    abstract public void move();
    abstract public void setPlayer(Player player);
    public void setBoss(Boss boss){

    }
}