package com.example.tosscoin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;
    Random random;
    int side;
    private AdView mAdView;
    private RelativeLayout mainView;
    private RelativeLayout no_internet_layout;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.flip);
        mainView = (RelativeLayout) findViewById(R.id.toss_coin);
//        no_internet_layout = (RelativeLayout) findViewById(R.id.no_internet_layout);
        imageView = (ImageView)findViewById(R.id.coin);
        random = new Random();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                side = random.nextInt(2);

                if (side == 0){
                    imageView.setImageResource(R.drawable.head_real);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity. this, "HEADS !!!", Toast.LENGTH_SHORT).show();
                }else if (side == 1){
                    imageView.setImageResource(R.drawable.tail_real);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity. this, "TAILS !!!", Toast.LENGTH_SHORT).show();
                }

                RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(1000);
                imageView.startAnimation(rotateAnimation);
            }
        });


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

//        prepareAd();
//        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(new Runnable() {
//
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(mInterstitialAd.isLoaded()){
//                            mInterstitialAd.show();
//                        }
//                        else {
//
//                        }
//                        prepareAd();
//                    }
//                });
//            }
//
//        },5,5, TimeUnit.SECONDS );

    }

//    private void prepareAd() {
//
//        mInterstitialAd = new InterstitialAd(this);      // i have used a test id
//        mInterstitialAd.setAdUnitId("ca-app-pub-8080218238741505/5142437236");
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());
//    }

//    private void loadMainPage(){
//        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
//        //NetworkInfo networkInfo = cm.getActiveNetworkInfo();
//        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        NetworkInfo network = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//
//        assert wifi != null;
//        if(wifi.isConnected()){
//            no_internet_layout.setVisibility(View.GONE);
//            mainView.setVisibility(View.VISIBLE);
//        }
//        else {
//            assert network != null;
//            if (network.isConnected()){
//                no_internet_layout.setVisibility(View.GONE);
//                mainView.setVisibility(View.VISIBLE);
//            }
//            else {
//                no_internet_layout.setVisibility(View.VISIBLE);
//                mainView.setVisibility(View.GONE);
//                Toast.makeText(this, "You Don't have an Active Internet Connection", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    public void ReconnectWebsite(View view) {
//        loadMainPage();
//    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit ?")
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                    }
                }).show();
    }

}