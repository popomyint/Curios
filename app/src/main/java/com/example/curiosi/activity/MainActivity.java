package com.example.curiosi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.curiosi.R;
import com.example.curiosi.view.MainView;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.skipbtn)
    Button skipbtn;

    @BindView(R.id.signin)
    Button signIn;

    @BindView(R.id.signup)
    Button signUp;

    @BindView(R.id.google)
    ImageButton googleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void specifyGoogleSignIn(GoogleSignInOptions gos) {

    }

    @Override
    public void startProfileActivity() {

    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }
}
