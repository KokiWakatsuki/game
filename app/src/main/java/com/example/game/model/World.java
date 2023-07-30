package com.example.game.model;

import com.example.game.model.pack.boss_ability_pack.Beam;
import com.example.game.model.pack.boss_ability_pack.BossBarrier;
import com.example.game.model.pack.boss_ability_pack.Energy;
import com.example.game.model.pack.boss_pack.Boss1;
import com.example.game.model.pack.boss_pack.Boss2;
import com.example.game.model.pack.player_ability_pack.Barrier;
import com.example.game.model.pack.player_ability_pack.MovingSlash;
import com.example.game.model.pack.player_ability_pack.Slash;
import com.example.game.model.pack.trap_pack.MovingNeedle;
import com.example.game.model.pack.trap_pack.Needle;
import com.example.game.model.pack.trap_pack.Warp;

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
    List<BossAbility> bossAbilities;
    List<PlayerAbility> playerAbilities;
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

        bossAbilities = new LinkedList<BossAbility>();
        bossAbilities.add(bossBarrier);
        bossAbilities.add(beam);
        bossAbilities.add(energy);

        playerAbilities = new LinkedList<PlayerAbility>();
        playerAbilities.add(slash);
        playerAbilities.add(movingSlash);
        playerAbilities.add(barrier);


        // モデルの接続
        for(Ground ground : grounds){
            player.addLimitCharacter(ground);
        }
        traps.forEach(x -> x.setPlayer(player));
        traps.forEach(x -> x.setSlash(slash));
        traps.forEach(x -> x.setBarrier(barrier));
        boss1.setPlayer(player);
        boss2.setPlayer(player);
        bossAbilities.forEach(x -> x.setPlayer(player));
        bossAbilities.forEach(x -> x.setBoss(boss));
        bossAbilities.forEach(x -> x.setMovingSlash(movingSlash));
        playerAbilities.forEach(x -> x.setPlayer(player));
        playerAbilities.forEach(x -> x.setBoss(boss));
    }

    public void move() {
        // オブジェクトの更新
        player.move();
        boss.move();
        traps.forEach(x -> x.move());
        bossAbilities.forEach(x -> x.move());
        playerAbilities.forEach(x -> x.move());

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
