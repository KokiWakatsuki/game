package com.example.game.model;

import java.util.LinkedList;
import java.util.List;

public class GameCharacter {
    // 属性
    protected int x = 0;
    protected int y = 0;
    protected int xSize = 0;
    protected int ySize = 0;
    protected int xSpeed = 0;
    protected float ySpeed = 0;
    protected boolean activeFlag = true;

    protected List<GameCharacter> xMaxLimits=new LinkedList<GameCharacter>();
    protected List<GameCharacter> xMinLimits=new LinkedList<GameCharacter>();
    protected List<GameCharacter> yMaxLimits=new LinkedList<GameCharacter>();
    protected List<GameCharacter> yMinLimits=new LinkedList<GameCharacter>();
    protected int xMax;
    protected int xMin;
    protected int yMax;
    protected int yMin;

    // メソッド
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public boolean isActive() {
        return activeFlag;
    }

    // キャラクターの右端のｘ座標を取得する
    public int getRight() {
        return x + xSize - 1;
    }

    // キャラクターの左端のｘ座標を取得する
    public int getLeft() {
        return x;
    }

    // キャラクターの上端のｙ座標を取得する
    public int getTop() {
        return y + ySize - 1;
    }

    // キャラクターの下端のｙ座標を取得する
    public int getBottom() {
        return y;
    }

    public boolean isOnLeft(GameCharacter target) {
        return target.getRight() < getLeft();
    }

    public boolean isOnRight(GameCharacter target) {
        return getRight() < target.getLeft();

    }

    public boolean isOverOnTop(GameCharacter target) {
        return getBottom() < target.getBottom();
    }

    public boolean isOverOnBottom(GameCharacter target) {
        return target.getTop() < getTop();
    }

    public boolean isOverOnLeft(GameCharacter target) {
        return target.getRight() < getRight();
    }

    public boolean isOverOnRight(GameCharacter target) {
        return getLeft() < target.getLeft();

    }

    public boolean isOnTop(GameCharacter target) {
        return getTop() < target.getBottom();
    }

    public boolean isOnBottom(GameCharacter target) {
        return target.getTop() < getBottom();
    }

    // 横方向で重なっていないか判定する
    public boolean overlapX(GameCharacter target) {
        return !isOnRight(target) && !isOnLeft(target);
    }

    // 縦方向で重なっていないか判定する
    public boolean overlapY(GameCharacter target) {
        return !isOnTop(target) && !isOnBottom(target);
    }

    // 衝突していないか判定する
    public boolean overlap(GameCharacter target) {
        return overlapX(target) && overlapY(target);
    }

    public boolean fullOverlap(GameCharacter target){
        return ( getBottom() <= target.getBottom() && target.getTop()<getTop()
        && getLeft() <= target.getLeft() && target.getRight() <= getRight());
    }

    private List<GameCharacter> targets = new LinkedList<GameCharacter>();

    public void resetLimit(){
        checkLimitList();
        checkMoveArea();
        correctPositions();
    }
    public void addLimitCharacter(GameCharacter target) {
        targets.add(target);
    }

    private void checkLimitList() {
        xMaxLimits.clear();
        xMinLimits.clear();
        yMaxLimits.clear();
        yMinLimits.clear();
        for(GameCharacter target:targets){
            if(!target.isActive()) continue;
            if(overlapY(target)){
                if(isOnRight(target)) {
                    xMaxLimits.add(target);
                }
                if(isOnLeft(target)) {
                    xMinLimits.add(target);
                }
            }
            if(overlapX(target)){
                if(isOnTop(target)) {
                    yMaxLimits.add(target);
                }
                if(isOnBottom(target)) {
                    yMinLimits.add(target);
                }
            }
        }
    }

    private void checkMoveArea() {
        xMax=99999;
        xMin=-99999;
        yMax=99999;
        yMin=-99999;
        for(GameCharacter target: xMaxLimits){
            xMax=Math.min(xMax,target.getLeft()-xSize);
        }
        for(GameCharacter target: xMinLimits){
            xMin=Math.max(xMin,target.getRight()+1);
        }
        for(GameCharacter target: yMaxLimits){
            yMax=Math.min(yMax,target.getBottom()-ySize);
        }
        for(GameCharacter target: yMinLimits){
            yMin=Math.max(yMin,target.getTop()+1);
        }
     }

    private void correctPositions() {
        if (xMax < x) {
            x = xMax;
            xSpeed = 0;
        }
        if (x < xMin) {
            x = xMin;
            xSpeed = 0;
        }
        if (yMax < y) {
            y = yMax;
            ySpeed = 0;
        }
        if (y < yMin) {
            y = yMin;
            ySpeed = 0;
        }
    }

    public void correctPosition(){
        checkMoveArea();
        correctPositions();
        checkLimitList();
    }
    public void move() {

    }

}
