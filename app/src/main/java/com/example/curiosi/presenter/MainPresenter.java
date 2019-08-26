package com.example.curiosi.presenter;

import android.content.Intent;

import com.example.curiosi.activity.MainActivity;

public interface MainPresenter {
    void createGoogleClient (MainActivity mainView);
    void onStart();
    void signIn(MainActivity mainView);
    void onActivityResult (MainActivity mainView,int requestCode, int resultCode, Intent data);
    void onStop ();
    void onDestroy();
}
