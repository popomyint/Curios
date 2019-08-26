package com.example.curiosi.presenter;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.curiosi.R;
import com.example.curiosi.activity.MainActivity;
import com.example.curiosi.view.MainView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainPresenterImpl implements MainPresenter, GoogleApiClient.OnConnectionFailedListener {

    private MainView mainView;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    // Google client to communicate with Google
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN_G = 2016;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void createGoogleClient(MainActivity mainView) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(mainView.getResources().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        this.mainView.specifyGoogleSignIn(gso);
        mGoogleApiClient = new GoogleApiClient.Builder(mainView)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
    }

    @Override
    public void signIn(MainActivity mainView) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mainView.startActivityForResult(signInIntent, RC_SIGN_IN_G);
    }

    @Override
    public void onActivityResult(MainActivity mainView, int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN_G) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result, mainView);
        }
    }

    private void handleSignInResult(GoogleSignInResult result, MainActivity mainView) {
        //Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            authWithGoogle(acct);
//            String personName = acct.getDisplayName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
//            String token = acct.getIdToken();
//            loginView.startProfileActivity();

        } else {
            mainView.startProfileActivity();
            GoogleSignInAccount acct = result.getSignInAccount();
            authWithGoogle(acct);
        }
    }

    private void authWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mainView.startProfileActivity();
                } else {
                    //Toast.makeText(getApplicationContext(), "Auth Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mainView.startProfileActivity();
    }
}
