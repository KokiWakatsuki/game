package com.example.game.model.pack.boss_ability_pack;

import com.example.game.model.Boss;
import com.example.game.model.BossAbility;
import com.example.game.model.pack.player_ability_pack.MovingSlash;

public class BossBarrier extends BossAbility {
    private boolean bossState = false;
    public BossBarrier() {
        this.x = -100;
        this.y = -100;
        this.xSize = 519;
        this.ySize = 519;
    }
    private Boss boss;
    private MovingSlash movingSlash;
    public void setBoss(Boss boss) {
        this.boss = boss;
    }
    public void setMovingSlash(MovingSlash movingSlash) {
        this.movingSlash = movingSlash;
    }
    public boolean isBossState(){
        return bossState;
    }

    public void move(){
        this.x = boss.getX() - ((this.xSize - boss.getxSize()) / 2);
        this.y = boss.getY() - ((this.ySize - boss.getySize()) / 2);

        bossState = movingSlash.isBossDamageFlag();
    }
}
