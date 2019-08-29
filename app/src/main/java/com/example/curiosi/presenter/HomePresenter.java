package com.example.curiosi.presenter;

import com.example.curiosi.activity.HomeActivity;

public interface HomePresenter2 {
    void getDataFromFirebase(HomeActivity homeView);
    void onUIReady(HomeActivity homeView);
}
