package com.example.curiosi.view;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public interface MainView extends BaseView {
    void specifyGoogleSignIn(GoogleSignInOptions gos);
    void startProfileActivity();
}
