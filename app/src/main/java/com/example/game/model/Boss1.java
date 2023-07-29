package com.example.game.model;

public class Boss1 extends Boss{
    private boolean appearFlag = false;
    private boolean damageFlag;
    private int count = 0;
    private int bossSpeed = 3;
    private int bossLoop = 20;
    private boolean beamFlag = false;
    private boolean energyFlag = false;
    private int bossHp = 10;
    private int bossDamageCount = 0;
    private boolean bossDeadFlag = false;
    private boolean bossDamageFlag = false;
    private boolean bossDamageEffect = false;

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Boss1() {
        this.x=15800;
        this.y=1000;
        this.xSize=419;
        this.ySize=297;
        this.xSpeed = 0;
        this.ySpeed = -10;
    }

    public void dead() {
        bossDeadFlag = true;
    }

    public void damage(){
        bossHp -= 1;
        bossDamageFlag = true;
    }

    public boolean isBeamFlag(){
        return beamFlag;
    }

    public boolean isEnergyFlag(){
        return energyFlag;
    }

    public void falseBeamFlag(){
        beamFlag = false;
    }

    public void falseEnergyFlag(){
        energyFlag = false;
    }

    private void xmove(){
        this.xSpeed = 0;
        this.ySpeed = 0;
        count++;
        if(count > bossLoop * 4 && count < bossLoop * 5){
            beamFlag = true;
        }else if(count > bossLoop * 7 && count < bossLoop * 8){
            energyFlag = true;
        }else if(count == bossLoop * 9){
            count = 0;
            this.x = 15000 + (int)(Math.random() * 1000);
            this.y = (int)(Math.random() * 200) + 100;
        }
    }

    public void move(){
        if(bossDeadFlag){
            ySpeed = -10;
            y += ySpeed;
        }else{
            if(!appearFlag && player.getX() >= 15000){
                y += ySpeed;
            }

            if(this.y == 100){
                appearFlag = true;
            }

            if(appearFlag){
                xmove();
                x += xSpeed;
                y += ySpeed;

                if(overlap(player) && !damageFlag){
                    player.damage();
                    damageFlag = true;
                }

                if(!overlap(player)){
                    damageFlag = false;
                }
            }
        }

        if(this.y < -this.ySize){
            player.clear();
        }

        if(bossHp == 0){
            dead();
        }

        if(bossDamageFlag){
            bossDamageFlag = false;
        }
    }

    public int getBossHp() {
        return bossHp;
    }

    public void setBossHp(int bossHp) {
        this.bossHp = bossHp;
    }

    public void setAppearFlag(boolean appearFlag) {
        this.appearFlag = appearFlag;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDamageFlag(boolean damageFlag) {
        this.damageFlag = damageFlag;
    }
}
