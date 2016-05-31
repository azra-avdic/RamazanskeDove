package com.example.azra.ramazanskedove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Azra on 30.5.2016.
 */
public class SplashActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash_screen);

    Thread timerThread = new Thread(){
        public void run(){
            try{
                sleep(2000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }finally{
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };
    timerThread.start();
    }

        @Override
        protected void onPause() {
            super.onPause();
            finish();
        }

}
