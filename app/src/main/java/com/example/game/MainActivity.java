package com.example.game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.game.helpers.BaseActivity;
import com.example.game.model.Explain;
import com.example.game.model.Player;
import com.example.game.model.World;
import com.example.game.views.MainView;


public class MainActivity extends BaseActivity {

    World world;
    MainView mainView;
    private static int explainCount = 0;

    public static int getExplainCount() {
        return explainCount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gravityEnabled = true;
        orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        world = new World();
        mainView = new MainView(this);

    }

    public void update() {
        if(mainView.isExplainFlag()){
            // 加速度センサーで左右に移動
            Player player = world.getPlayer();
            if (accelerationController.y > 2) {
                player.turnRight();
            } else if (accelerationController.y < -2) {
                player.turnLeft();
            } else {
                player.stop();
            }
            world.move();
        }

        mainView.draw(world);
    }

    public void retry(){
//        stopMusic();
//        stopSound();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();

//        startMusic();
    }

    //===========
    // イベントリスナー
    //===========
    public boolean onTouchEvent(MotionEvent event) {
        if(mainView.isExplainFlag()) {
            if (event.getX() > 700) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:    // 画面から指を離したら
                        world.getPlayer().stop();
                        break;
                    case MotionEvent.ACTION_DOWN: // 画面をタップしたら
                        if (!world.getPlayer().isDead()) {
                            world.getPlayer().jump();
                            break;
                        }
                }
            } else {
                if (world.getPlayer().getX() >= 15000) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_UP:    // 画面から指を離したら
                            world.getPlayer().stop();
                            break;
                        case MotionEvent.ACTION_DOWN: // 画面をタップしたら
                            if (!world.getPlayer().isDead()) {
                                world.getPlayer().movingSlash();
                                break;
                            }
                    }
                } else {
                    if (event.getY() < 350) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_UP:    // 画面から指を離したら
                                world.getPlayer().stop();
                                break;
                            case MotionEvent.ACTION_DOWN: // 画面をタップしたら
                                if (!world.getPlayer().isDead()) {
                                    world.getPlayer().barrier();
                                    break;
                                }
                        }
                    } else {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_UP:    // 画面から指を離したら
                                world.getPlayer().stop();
                                break;
                            case MotionEvent.ACTION_DOWN: // 画面をタップしたら
                                if (!world.getPlayer().isDead()) {
                                    world.getPlayer().slash();
                                    break;
                                }
                        }
                    }
                }
            }
        } else {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                explainCount++;
            }
        }

        return true;
    }
}