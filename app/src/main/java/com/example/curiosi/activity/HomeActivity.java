package com.example.curiosi.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.curiosi.R;
import com.example.curiosi.adapter.FireStoreAdapter;
import com.example.curiosi.model.DataModel;
import com.example.curiosi.presenter.HomePresenterImpl;
import com.example.curiosi.view.HomeView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements HomeView {
    private FireStoreAdapter fireStoreAdapter;
    private List<DataModel> dataList = new ArrayList<>();
    private static final String TAG = HomeActivity.class.getSimpleName();
    FirebaseFirestore db;

    HomePresenterImpl homePresenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_home);

        fireStoreAdapter = new FireStoreAdapter(dataList);
        RecyclerView recyclerView = findViewById(R.id.customRecyclerView);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(fireStoreAdapter);
        //StudentDataPrepare();
        //homePresenter = new HomePresenterImpl(this);
        //homePresenter.getDataFromFirebase(this);
        FireStoreDataPrepare();
    }

    private void FireStoreDataPrepare() {
        db = FirebaseFirestore.getInstance();
     /*   FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);*/

//        DocumentReference dd = db.collection("testDB").document("post1");
//        dd.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                        DataModel data = document.toObject(DataModel.class);
//                        dataList.add(data);
//                    }
//                }
//            }
//        });

        db.collection("testDB").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //task.getResult();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DataModel data = document.toObject(DataModel.class);
                                dataList.add(data);
                            }
                        }
                    }
                });
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

//    private void StudentDataPrepare() {
//        DataModel data = new DataModel(1, "title", "description1");
//        dataList.add(data);
//        data = new DataModel(4, "title aaaa sai","des fd sdf asf saf sdf ");
//        dataList.add(data);
//        data = new DataModel(5, "title ddd sai","des studentDataPrepare df as");
//        dataList.add(data);
//        data = new DataModel(6, "title asee sai","des tudentData Prepare tudentDataPrepare");
//        dataList.add(data);
//        data = new DataModel(7, "title yyy sai","des tudentDat aPr eparetude ntDat aPrepare2");
//        dataList.add(data);
//    }


}
