package com.example.game.model;

public class Player extends GameCharacter{
    private int xSpeed = 0;
    private int ySpeed = 0;
    private int yAccel = -1;
    private double time = 0.00;
    private int hp = 1000;
    private int effectCount = 0;
    private boolean jumpFlag = false;
    private boolean deadFlag = false;
    private boolean clearFlag = false;
    private boolean damageFlag = false;
    private boolean damageEffect = false;
    private boolean slashFlag = false;
    private boolean movingSlashFlag = false;
    private boolean barrierFlag = false;
    private int barrierState = 0;
    private int slashCount = 0;
    private int movingSlashCount = 0;
    private int barrierCount = 0;

    public int MaxBarrier = 4;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.xSize = 96;
        this.ySize = 96;
        this.xSpeed = 0;
        this.ySpeed = 0;
    }

    public float getXSpeed() {
        return xSpeed;
    }

    public void turnRight() {
        xSpeed = 5;
    }

    public void turnLeft() {
        xSpeed = -5;
    }

    public void stop() {
        xSpeed = 0;
    }

    public void slash() {
        slashFlag = true;
    }

    public void movingSlash(){
        movingSlashFlag = true;
    }

    public void barrier() {
        barrierFlag = true;
    }

    public boolean isDead() {
        return deadFlag;
    }

    public boolean isSlash(){
        return slashFlag;
    }

    public boolean isMovingSlash(){
        return movingSlashFlag;
    }

    public boolean isBarrier(){
        return barrierFlag;
    }

    public int isBarrierState(){
        return barrierState;
    }

    public void dead() {
        deadFlag = true;
    }

    public void damage(){
        hp -= 10;
        damageFlag = true;
    }

    public int isDamage(){
        return hp;
    }

    public boolean isClear() {
        return clearFlag;
    }

    public double isTime(){
        return time;
    }

    public boolean isDamageEffect(){
        return damageEffect;
    }

    public void clear() {
        clearFlag = true;
    }

    public void jump() {
        if (jumpFlag == false) {
            ySpeed = 24;
            jumpFlag = true;
        }
    }

    public void move() {
        // 時間
        if (!isDead() && !isClear()){
            time += 0.01f;
        }

        // 移動
        if(!isDead()){
            x += xSpeed;
        }
        y += ySpeed;
        ySpeed += yAccel;

        // プレーヤーが画面からはみ出さないようにする
        if(x > 14900){
            if(x < 15000){
                x = 15000;
            }else if (x > World.WIDTH) {
                x = World.WIDTH;
            }
        }else{
            if (x < 0) {
                x = 0;
            }else if (x > 11160 - xSize) {
                x = 11160 - xSize;
            }
        }

        // Y座標が-20以下になると死亡
        if (y <= -50) {
            dead();
        }

        // どこかに着地したらjumpFlagをfalse
        if (y < yMin) {
            ySpeed = 0;
            jumpFlag = false;
        }

        if(hp == 0){
            dead();
        }

        if(damageFlag){
            effectCount++;
            if(effectCount % 5 == 0){
                damageEffect = damageEffect ? false : true;
            }
        }

        if(effectCount == 50){
            effectCount = 0;
            damageFlag = false;
            damageEffect = false;
        }

        if(isSlash()){
            slashCount++;
            if(slashCount == 10){
                slashFlag = false;
                slashCount = 0;
            }
        }

        if(isMovingSlash()){
            movingSlashCount++;
            if(movingSlashCount == 100){
                movingSlashFlag = false;
                movingSlashCount = 0;
            }
        }

        if(isBarrier() && barrierState < MaxBarrier){
            barrierCount++;
            if(barrierCount == 300 || this.x >= 15000){
                barrierFlag = false;
                barrierCount = 0;
                barrierState++;
            }
        }

        // 登録されたLimitCharacterに侵入しないように位置を調整
        correctPosition();
    }
}
