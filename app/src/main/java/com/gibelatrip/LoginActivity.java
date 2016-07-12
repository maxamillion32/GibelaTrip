package com.gibelatrip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.gibelatrip.view.GibelaTripAuthButton;

public class LoginActivity extends Activity {

    GibelaTripAuthButton phoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpDigitsButton();
    }

    private void setUpDigitsButton() {
        phoneButton = (GibelaTripAuthButton) findViewById(R.id.phone_button);
        phoneButton.setAuthTheme(R.style.AppTheme);
        phoneButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession digitsSession, String phoneNumber) {
                getFragmentManager().beginTransaction().replace(R.id.container, CardInformationSaveFragment.newInstance(digitsSession.getAuthToken().toString(), phoneNumber)).commit();

            }

            @Override
            public void failure(DigitsException e) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
