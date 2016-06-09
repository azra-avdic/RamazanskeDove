package com.coderock.azra.ramazanskedove;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity  extends Activity {

    @BindView(R.id.tvAppName) TextView tvAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ButterKnife.bind(this);
        Typeface fontHelveticaExtNormal = Typeface.createFromAsset(this.getAssets(), "fonts/HelveticaExt-Normal.ttf");

        tvAppName.setText(Html.fromHtml(getString(R.string.app_name_tm)));
        tvAppName.setTypeface(fontHelveticaExtNormal);


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
