package com.example.ahmedkhaled.buzzels;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.ahmedkhaled.buzzels.Login.LoginActivity;

import me.wangyuwei.particleview.ParticleView;

public class SplashActivity extends AppCompatActivity {

    ParticleView particleView;
    Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        particleView = (ParticleView) findViewById(R.id.animation);
        particleView.startAnim();
        particleView.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                CheckConnection();
            }
        });
    }

    private void CheckConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable()) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            NoConnection();
        }


    }

    private void NoConnection() {
        snackbar = Snackbar.make(findViewById(R.id.parentView), R.string.snakeBarsplash
                , Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckConnection();
            }
        });
        final Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
        layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewGroup.LayoutParams lp = layout.getLayoutParams();
                if (lp instanceof CoordinatorLayout.LayoutParams) {
                    ((CoordinatorLayout.LayoutParams) lp).setBehavior(new
                            DisableSwipeBehavior());
                    layout.setLayoutParams(lp);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    //noinspection deprecation
                    layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

    }

    //onSwipeDismiss method
    private class DisableSwipeBehavior extends SwipeDismissBehavior<Snackbar.SnackbarLayout> {
        @Override
        public boolean canSwipeDismissView(View view) {
            return false;
        }
    }

}
