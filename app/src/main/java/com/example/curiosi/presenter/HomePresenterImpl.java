package com.example.curiosi.presenter;

import androidx.annotation.NonNull;

import com.example.curiosi.activity.HomeActivity;
import com.example.curiosi.view.HomeView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class HomePresenterImpl2 implements HomePresenter2 {
    HomeView homeView;
    private DatabaseReference dBReference;
    private FirebaseDatabase dBInstance;
    FirebaseFirestore db;

    public HomePresenterImpl2(HomeView homeView){
        this.homeView = homeView;
    }

    @Override
    public void getDataFromFirebase(HomeActivity homeView) {
        setUp();
        getAllData();

    }

    @Override
    public void onUIReady(HomeActivity homeView) {

    }

    private void setUp() {
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
    }

    private void getAllData(){
        db.collection("testDB").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            task.getResult();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                    
                            }
                        }
                    }
                });
    }
}
