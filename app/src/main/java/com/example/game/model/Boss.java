package com.example.game.model;

public abstract class Boss extends GameCharacter{

    abstract public void setPlayer(Player player);

    abstract public void dead();

    abstract public void damage();

    abstract public boolean isBeamFlag();

    abstract public boolean isEnergyFlag();

    abstract public void falseBeamFlag();

    abstract public void falseEnergyFlag();

    abstract public void move();

    abstract public int getBossHp();

    abstract public void setBossHp(int bossHp);

    abstract public void setAppearFlag(boolean appearFlag);

    abstract public void setCount(int count);

    abstract public void setDamageFlag(boolean damageFlag);

    abstract public boolean isBossStateFlag();
}
