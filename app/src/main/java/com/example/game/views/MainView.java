package com.example.game.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.game.MainActivity;
import com.example.game.R;
import com.example.game.helpers.BaseView;
import com.example.game.model.pack.player_ability_pack.Barrier;
import com.example.game.model.pack.boss_ability_pack.Beam;
import com.example.game.model.Boss;
import com.example.game.model.pack.boss_ability_pack.BossBarrier;
import com.example.game.model.pack.boss_ability_pack.Energy;
import com.example.game.model.Explain;
import com.example.game.model.pack.explain_pack.Explain1;
import com.example.game.model.pack.explain_pack.Explain2;
import com.example.game.model.pack.explain_pack.Explain3;
import com.example.game.model.pack.explain_pack.Explain4;
import com.example.game.model.pack.explain_pack.Explain5;
import com.example.game.model.pack.explain_pack.Explain6;
import com.example.game.model.pack.explain_pack.Explain7;
import com.example.game.model.GameCharacter;
import com.example.game.model.pack.trap_pack.MovingNeedle;
import com.example.game.model.pack.player_ability_pack.MovingSlash;
import com.example.game.model.pack.trap_pack.Needle;
import com.example.game.model.Player;
import com.example.game.model.pack.player_ability_pack.Slash;
import com.example.game.model.World;

import java.util.ArrayList;
import java.util.List;


public class MainView extends BaseView {

    MainActivity mainActivity;
    ConstraintLayout constraintLayout;
    Context context;

    // 定数
    boolean deadState = false;
    public static int backGroundMax = 11160;
    private float time = 1.00f;
    private static boolean explainFlag = false;

    public static boolean isExplainFlag() {
        return explainFlag;
    }
    public static void setExplainFlag(boolean explainFlag) {
        MainView.explainFlag = explainFlag;
    }

    // 画像用変数
    Bitmap backGroundImage;
    Bitmap bossBackGroundImage;
    Bitmap playerRightImage;
    Bitmap playerLeftImage;
    Bitmap playerRightDeadImage;
    Bitmap playerLeftDeadImage;
    Bitmap groundImage;
    Bitmap leafImage;
    Bitmap smokeImage;
    Bitmap needleImage;
    Bitmap hpImage;
    Bitmap hpGageImage;
    Bitmap playerDeadImage;
    Bitmap movingNeedleImage;
    Bitmap slashRightImage;
    Bitmap slashLeftImage;
    Bitmap barrierImage;
    Bitmap warpImage;
    Bitmap bossImage;
    Bitmap boss2Image;
    Bitmap bossBarrierImage;
    Bitmap beamLeftImage;
    Bitmap beamRightImage;
    Bitmap energyImage;

    // テキスト用変数
    TextView gameClearTextView;
    TextView gameOverTextView;
    TextView timeTextView;
    TextView barrierStateTextView;
    TextView explainTextView;


    // ビュー用変数
    ImageViewBuilder imageViewBuilder;
    Button retryButton;

    public MainView(Context context) {
        super(context);
        this.context = context;
        this.mainActivity = (MainActivity) context;

        // 画像の読み込み
        backGroundImage = loadImage(R.drawable.field);
        bossBackGroundImage = loadImage(R.drawable.boss_background);
        bossBarrierImage = loadImage(R.drawable.boss_barrier);
        playerRightImage = loadImage(R.drawable.player_right);
        playerLeftImage = loadImage(R.drawable.player_left);
        playerRightDeadImage = loadImage(R.drawable.player_right_dead);
        playerLeftDeadImage = loadImage(R.drawable.player_left_dead);
        groundImage = loadImage(R.drawable.ground);
        leafImage = loadImage(R.drawable.leaf);
        smokeImage = loadImage(R.drawable.smoke);
        needleImage = loadImage(R.drawable.needle);
        hpImage = loadImage(R.drawable.hp_green);
        hpGageImage = loadImage(R.drawable.hp_bar);
        movingNeedleImage = loadImage(R.drawable.moving_needle);
        slashRightImage = loadImage(R.drawable.slash_right);
        slashLeftImage = loadImage(R.drawable.slash_left);
        barrierImage = loadImage(R.drawable.barrier);
        warpImage = loadImage(R.drawable.warp);
        bossImage = loadImage(R.drawable.boss);
        boss2Image = loadImage(R.drawable.boss2);
        beamLeftImage = loadImage(R.drawable.beam_left);
        beamRightImage = loadImage(R.drawable.beam_right);
        energyImage = loadImage(R.drawable.energy);


        // ビューの生成・登録
        constraintLayout = new ConstraintLayout(context);
        baseActivity.setContentView(constraintLayout);
        imageViewBuilder = new ImageViewBuilder(constraintLayout, context);

        retryButton = new Button(context);
        constraintLayout.addView(retryButton);
        retryButton.setVisibility(GONE);
        retryButton.setOnClickListener(new RetryButtonOnClickListener(){
            @Override
            public void onClick(View view){
                mainActivity.retry();
            }
        });
    }

    public void draw(World world) {
        // スクロール
        Explain explain = world.getExplain();
        Player player = world.getPlayer();
        Boss boss = world.getBoss();
        Slash slash = world.getSlash();
        MovingSlash movingSlash = world.getMovingSlash();
        Barrier barrier = world.getBarrier();
        if (player.getX() < 700) {
            canvasBaseX = 0;
        } else if (player.getX() < 10240) {
            canvasBaseX = player.getX() - 700;
        } else if (player.getX() > 12900) {
            canvasBaseX = 15000;
        }

        // ImageViewBuilderリセット
        imageViewBuilder.reset();

        // 背景を表示
        ImageView imageView = imageViewBuilder.getImageView();
        drawImage(0, 0, backGroundMax, 1050, backGroundImage, imageView);

        // ボス部屋背景を表示
        ImageView imageView2 = imageViewBuilder.getImageView();
        drawImage(15000, 0, 1550, 800, bossBackGroundImage, imageView2);

        // 地面を表示
        world.getGrounds().forEach(x -> drawCharacter(x, groundImage));

        // トゲを表示
        world.getNeedles().forEach(x -> drawNeedle(x));

        // 動くトゲを表示
        world.getMovingNeedles().forEach(x -> drawMovingNeedle(x));

        // ワープを表示
        drawCharacter(world.getWarp(), warpImage);

        // ボスを表示
        drawBoss(world.getBoss());

        // バリアを表示
        drawBarrier(barrier, player);

        // プレーヤを表示
        drawPlayer(player);

        // 斬撃を表示
        drawSlash(slash, player);

        // 飛ぶ斬撃を表示
        drawMovingSlash(movingSlash, player);

        // ボスのバリアを表示
        drawBossBarrier(world.getBossBarrier());

        // ビームを表示
        drawBeam(world.getBeam(), player, boss);

        // エネルギーを表示
        drawEnergy(world.getEnergy());

        // スモーク表示
        List<ImageView> smokes = new ArrayList<ImageView>();
        // 落とし穴と合わせる
        for(int i = 0, j = 0; i < 75; i++){
            if(i % 10 == 0 && i != 0){
                smokes.add(imageViewBuilder.getImageView());
                drawImage(i * 150, 0, 150, 800, smokeImage, smokes.get(j));
                j++;
            }
        }

        // HPを表示
        ImageView hp = imageViewBuilder.getImageView();
        drawImage(canvasBaseX + 270 - 1, 610, player.isDamage() + 1, 30, hpImage, hp);

        // HPゲージを表示
        ImageView hpGage = imageViewBuilder.getImageView();
        drawImage(canvasBaseX + 260, 600, 1020, 50, hpGageImage, hpGage);

        // 草むら表示
        List<ImageView> leafs = new ArrayList<ImageView>();
        for(int i = 0; i < 9; i++){
            leafs.add(imageViewBuilder.getImageView());
            drawImage(i * 1300, 0, 1360, 200, leafImage, leafs.get(i));
        }

        // 経過時間を表示
        drawTime(player.isTime());

        // バリア回数を表示
        drawBarrierState(player.isBarrierState());

        // ゲームオーバーとゲームクリア
        if(player.isDead() && !player.isClear()){
            drawGameOver();
            drawRetryButton();
        } else if(player.isClear() && !player.isDead()){
            drawGameClear();
            drawRetryButton();
        }

        if(player.getX() < 15000) {
            drawGameExplain(explain);
        } else {
            drawBossExplain(explain);
        }
    }

    //======================
    // リトライボタン表示用の関数
    //======================
    public void drawRetryButton(){
        retryButton.setVisibility(VISIBLE);
        retryButton.setText("もう一回");
        drawViewCenter(canvasBaseX + 200, 500, retryButton);
    }

    //======================
    // テキストビュー表示用の関数
    //======================
    public void drawGameClear() {
        if (gameClearTextView == null) {
            gameClearTextView = new TextView(context);
            constraintLayout.addView(gameClearTextView);
        }
        gameClearTextView.setTextSize(32);
        gameClearTextView.setTextColor(Color.WHITE);
        gameClearTextView.setText("Game Clear !!");
        drawTextViewCenter(canvasBaseX + 750, 350, gameClearTextView);
    }

    public void drawGameOver() {
        if (gameOverTextView == null) {
            gameOverTextView = new TextView(context);
            constraintLayout.addView(gameOverTextView);
        }
        gameOverTextView.setTextSize(32);
        gameOverTextView.setTextColor(Color.RED);
        gameOverTextView.setText("Game Over !!");
        drawTextViewCenter(canvasBaseX + 750, 350, gameOverTextView);
    }

    public void drawTime(double time){
        if (timeTextView == null) {
            timeTextView = new TextView(context);
            constraintLayout.addView(timeTextView);
        }
        timeTextView.setTextSize(40);
        timeTextView.setTextColor(Color.WHITE);
        timeTextView.setText(String.format("%.2f", time));
        drawTextViewRight(canvasBaseX + 1500, 590, timeTextView);
    }

    public void drawBarrierState(int maxBarrier){
        if (barrierStateTextView == null) {
            barrierStateTextView = new TextView(context);
            constraintLayout.addView(barrierStateTextView);
        }
        barrierStateTextView.setTextSize(40);
        barrierStateTextView.setTextColor(Color.WHITE);
        barrierStateTextView.setText("" + maxBarrier);
        drawTextViewRight(canvasBaseX + 100, 590, barrierStateTextView);
    }

    public void drawGameExplain(Explain explain){
        Explain1 explain1 = new Explain1();
        Explain2 explain2 = new Explain2();
        Explain3 explain3 = new Explain3();
        Explain4 explain4 = new Explain4();
        Explain5 explain5 = new Explain5();
        Explain6 explain6 = new Explain6();
        Explain7 explain7 = new Explain7();
        if(explainTextView == null){
            explainTextView = new TextView(context);
            constraintLayout.addView(explainTextView);
            explainTextView.setVisibility(View.VISIBLE);
            explainTextView.setText("");
            explainTextView.setTextSize(24);
            explainTextView.setTextColor(Color.WHITE);
            explainTextView.setHeight(300);
            explainTextView.setWidth(1400);
            explainTextView.setGravity(Gravity.CENTER);
            explainTextView.setBackgroundColor(Color.BLACK);
        }
        switch (MainActivity.getExplainCount()){
            case 0:
                explain.setGameExplain(explain1);
                break;
            case 1:
                explain.setGameExplain(explain2);
                break;
            case 2:
                explain.setGameExplain(explain3);
                break;
            case 3:
                explain.setGameExplain(explain4);
                break;
            case 4:
                explain.setGameExplain(explain5);
                break;
            case 5:
                explain.setGameExplain(explain6);
                break;
            case 6:
                explain.setGameExplain(explain7);
                break;
            default:
                // 何も設定しないとエラーになるため、設定しておく
                explain.setGameExplain(explain1);
                explainFlag = true;
                explainTextView.setVisibility(View.GONE);
                break;
        }
        drawTextViewCenter(canvasBaseX + 750, 200, explainTextView);
        explainTextView.setText(explain.getGameExplain());
    }

    public void drawBossExplain(Explain explain){
        Explain1 explain1 = new Explain1();
        Explain2 explain2 = new Explain2();
        Explain3 explain3 = new Explain3();
        Explain4 explain4 = new Explain4();
        Explain5 explain5 = new Explain5();
        Explain6 explain6 = new Explain6();
        Explain7 explain7 = new Explain7();
        switch (MainActivity.getExplainCount()){
            case 0:
                explainTextView.setVisibility(View.VISIBLE);
                explain.setBossExplain(explain1);
                break;
            case 1:
                explain.setBossExplain(explain2);
                break;
            case 2:
                explain.setBossExplain(explain3);
                break;
            case 3:
                explain.setBossExplain(explain4);
                break;
            case 4:
                explain.setBossExplain(explain5);
                break;
            case 5:
                explain.setBossExplain(explain6);
                break;
            case 6:
                explain.setBossExplain(explain7);
                break;
            default:
                explainFlag = true;
                explainTextView.setVisibility(View.GONE);
                break;
        }
        drawTextViewCenter(canvasBaseX + 750, 200, explainTextView);
        explainTextView.setText(explain.getBossExplain());
    }

    //======================
    // キャラクター表示用の関数
    //======================
    private void drawPlayer(Player player) {
        boolean effect = player.isDamageEffect();
        boolean dead = player.isDead();
        ImageView imageView = imageViewBuilder.getImageView();
        Bitmap playerImage = (player.getXSpeed() < 0) ? playerLeftImage : playerRightImage;
        if(dead){
            if(!deadState){
                playerDeadImage = (player.getXSpeed() < 0) ? playerLeftDeadImage : playerRightDeadImage;
            }
            deadState = true;
            drawCharacter(player, playerDeadImage);
        }else{
            if(effect){
                imageView.setVisibility(imageView.GONE);
            }else{
                drawCharacter(player, playerImage);
            }
        }
    }

    private void drawCharacter(GameCharacter c, Bitmap image) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            drawImage(x, y, xSize, ySize, image, imageView);
        }
    }

    private void drawBoss(Boss c) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            if(!c.isBossStateFlag()){
                drawImage(x, y, xSize, ySize, bossImage, imageView);
            } else {
                drawImage(x, y, xSize, ySize, boss2Image, imageView);
            }
        }
    }

    private void drawEnergy(Energy c) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            int x = c.getX();
            int y = (int)c.getFy();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            drawImage(x, y, xSize, ySize, energyImage, imageView);
        }
    }

    private void drawBeam(Beam c, Player player, Boss boss) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            Bitmap beamImage = (c.isBeamState()) ? beamRightImage : beamLeftImage;
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            drawImage(x, y, xSize, ySize, beamImage, imageView);
        }
    }

    private void drawNeedle(Needle c) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            boolean dead = c.isDead();
            if(dead){
                imageView.setVisibility(imageView.GONE);
            }else{
                drawImage(x, y, xSize, ySize, needleImage, imageView);
            }
        }
    }

    private void drawMovingNeedle(MovingNeedle c) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            boolean dead = c.isDead();
            if(dead){
                imageView.setVisibility(imageView.GONE);
            }else{
                drawImage(x, y, xSize, ySize, movingNeedleImage, imageView);
            }
        }
    }

    private void drawSlash(Slash c, Player player) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            boolean slash = player.isSlash();
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            Bitmap slashImage = (player.getXSpeed() < 0) ? slashLeftImage : slashRightImage;
            if(slash){
                drawImage(x, y, xSize, ySize, slashImage, imageView);
            }else{
                imageView.setVisibility(imageView.GONE);
            }
        }
    }

    private void drawMovingSlash(MovingSlash c, Player player) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            boolean slash = player.isMovingSlash();
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            Bitmap slashImage = (c.getXSpeed() < 0) ? slashLeftImage : slashRightImage;
            if(slash){
                drawImage(x, y, xSize, ySize, slashImage, imageView);
            }else{
                imageView.setVisibility(imageView.GONE);
            }

        }
    }

    private void drawBarrier(Barrier c, Player player) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            boolean barrier = player.isBarrier();
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            if(barrier){
                drawImage(x, y, xSize, ySize, barrierImage, imageView);
            }else{
                imageView.setVisibility(imageView.GONE);
            }

        }
    }

    private void drawBossBarrier(BossBarrier c) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            boolean state = c.isBossState();
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            if(state){
                drawImage(x, y, xSize, ySize, bossBarrierImage, imageView);
            }else{
                imageView.setVisibility(imageView.GONE);
            }

        }
    }
}


