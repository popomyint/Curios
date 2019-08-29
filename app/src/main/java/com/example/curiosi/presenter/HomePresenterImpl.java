package com.example.curiosi.presenter;

import com.example.curiosi.view.HomeView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomePresenterImpl implements HomePresenter {
    HomeView homeView;
    private DatabaseReference dBReference;
    private FirebaseDatabase dBInstance;
    FirebaseFirestore db;

    public HomePresenterImpl(HomeView homeView){
        this.homeView = homeView;
    }
}
