package com.example.game.model;

import java.util.LinkedList;
import java.util.List;

public class World {
    // 定数
    public static final int WIDTH = 16500;

    // モデル
    Player player;
    Boss boss;
    Boss1 boss1;
    Boss2 boss2;
    Beam beam;
    Energy energy;
    Slash slash;
    MovingSlash movingSlash;
    Barrier barrier;
    BossBarrier bossBarrier;
    Warp warp;
    Explain explain;
    List<Trap> traps;
    List<Ground> grounds;
    List<Needle> needles;
    List<MovingNeedle> movingNeedles;
    public World() {
        // モデルの取得
        player = new Player(0, 100);
        slash = new Slash();
        movingSlash = new MovingSlash();
        barrier = new Barrier();
        bossBarrier = new BossBarrier();
        warp = new Warp();
        boss1 = new Boss1();
        boss2 = new Boss2();
        boss = boss1;
        beam = new Beam();
        energy = new Energy();
        explain = new Explain();

        grounds = new LinkedList<Ground>();
        for(int i = 0; i < 75; i++){
            if(i % 10 != 0 || i == 0){
                grounds.add(new Ground(i * 150, 0));
            }
        }

        for(int i = 0; i < 11; i++){
            grounds.add(new Ground(15000 + i * 150, -80));
        }

        needles = new LinkedList<Needle>();
        for(int i = 0; i < 30; i++){
            needles.add(new Needle((i + 1) * 300, -500));
        }

        movingNeedles = new LinkedList<MovingNeedle>();
        for(int i = 0; i < 20; i++){
            movingNeedles.add(new MovingNeedle());
        }

        traps = new LinkedList<Trap>();
        traps.add(warp);
        traps.addAll(needles);
        traps.addAll(movingNeedles);


        // モデルの接続
        for(Ground ground : grounds){
            player.addLimitCharacter(ground);
        }
        traps.forEach(x -> x.setPlayer(player));
        traps.forEach(x -> x.setSlash(slash));
        traps.forEach(x -> x.setBarrier(barrier));
        slash.setPlayer(player);
        movingSlash.setPlayer(player);
        movingSlash.setBoss(boss);
        barrier.setPlayer(player);
        boss1.setPlayer(player);
        boss2.setPlayer(player);
        bossBarrier.setBoss(boss);
        bossBarrier.setMovingSlash(movingSlash);
        beam.setBoss(boss);
        beam.setPlayer(player);
        energy.setBoss(boss);
        energy.setPlayer(player);

    }

    public void move() {
        // オブジェクトの更新
        player.move();
        slash.move();
        movingSlash.move();
        barrier.move();
        boss.move();
        bossBarrier.move();
        traps.forEach(x -> x.move());
        beam.move();
        energy.move();

        if(boss1.getBossHp() < 6){
            beam.initialize();
            energy.initialize();
            boss2.setX(boss1.getX());
            boss2.setY(boss1.getY());
            boss2.falseEnergyFlag();
            boss2.falseBeamFlag();
            boss2.setBossHp(boss1.getBossHp());
            boss2.setAppearFlag(true);
            boss2.setCount(0);
            boss2.setDamageFlag(false);
            boss = boss2;
            bossBarrier.setBoss(boss);
            beam.setBoss(boss);
            energy.setBoss(boss);
            movingSlash.setBoss(boss);

            // 条件を満たさないようにする
            boss1.setBossHp(10);
        }
    }

    //Getter

    public Player getPlayer() {
        return player;
    }

    public List<Ground> getGrounds() {
        return grounds;
    }

    public List<Needle> getNeedles() {
        return needles;
    }

    public List<MovingNeedle> getMovingNeedles() {
        return movingNeedles;
    }

    public Slash getSlash() {
        return slash;
    }

    public Barrier getBarrier() {
        return barrier;
    }

    public Warp getWarp() {
        return warp;
    }

    public Boss getBoss() {
        return boss;
    }

    public MovingSlash getMovingSlash() {
        return movingSlash;
    }

    public BossBarrier getBossBarrier() {
        return bossBarrier;
    }

    public Beam getBeam() {
        return beam;
    }

    public Energy getEnergy() {
        return energy;
    }

    public Explain getExplain() {
        return explain;
    }
}
