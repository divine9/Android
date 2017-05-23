package com.fixpocket.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.fixpocket.Fragment.Home;
import com.fixpocket.Model.LoginModel.Loginmodel;
import com.fixpocket.Model.RegistrationModel.RegistrationModel;
import com.fixpocket.Network.ConnectivityReceiver;
import com.fixpocket.R;
import com.fixpocket.Custome.TermsOfServices;
import com.fixpocket.Utils.Constant;
import com.fixpocket.Webservice.FixpocketAPI;
import com.fixpocket.Webservice.WebUtil.BaseURL;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.facebook.FacebookSdk;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener,
        ConnectivityReceiver.ConnectivityReceiverListener {

    private EditText signUpEmail, signUpUsername, signUpPassword, signUpCouponCode;
    String EmailId, Username, Password, Coupon;
    private ImageView signUpWithGoogle, signUpWithLinkdIN, signUpWithFacebook;
    private TextView signin, termsOfServices;
    private Button apply, signUp;
    GoogleApiClient mGoogleApiClient;
    public static final int RC_SIGN_IN = 9001;
    CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    //    private LoginButton ;
    LoginManager loginManager;

    MKLoader mkLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mkLoader = (MKLoader) findViewById(R.id.mlloader);

        init();

        signUpWithGoogle.setOnClickListener(this);
        signUpWithLinkdIN.setOnClickListener(this);
        signUp.setOnClickListener(this);
        signin.setOnClickListener(this);
        termsOfServices.setOnClickListener(this);
        apply.setOnClickListener(this);

// /demo google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //demo facebook
        //Login with facebook

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        loginManager.getInstance().registerCallback(callbackManager, callback);
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };
        accessTokenTracker.startTracking();
        signUpWithFacebook.setOnClickListener(this);

        checkConnection();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpWithFacebook:
                loginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
                break;
            case R.id.signUpWithGmail:
                signUpWithgoogle();
                break;
            case R.id.signUpWithLinkdin:
                // do stuff;
                break;

            case R.id.signUp:

                EmailId = signUpEmail.getText().toString();
                Username = signUpUsername.getText().toString();
                Password = signUpPassword.getText().toString();
                Coupon = signUpCouponCode.getText().toString();

                boolean isConnected = ConnectivityReceiver.isConnected();
                if ((isConnected)) {
                    if (EmailId.isEmpty()) {
                        Message("Enter EmailAddress.");
                    } else if (Username.isEmpty()) {
                        Message("Enter Username.");
                    } else if (Password.isEmpty()) {
                        Message("Enter Password.");
                    } else {
                        mkLoader.setVisibility(View.VISIBLE);
                        SignUpAPICALLl();
                    }
                } else {
                    showSnack(isConnected);
                }

                break;
            case R.id.signIn:

                Intent login = new Intent(Signup.this, Login.class);
                startActivity(login);
                break;
            case R.id.signUpTerms:

                Intent intent = new Intent(Signup.this, TermsOfServices.class);
                startActivity(intent);

                break;
            case R.id.promoCodeApply:
                // do stuff;
                break;


        }
    }

    public void init() {

        signUpEmail = (EditText) findViewById(R.id.signUp_emailid);
        signUpUsername = (EditText) findViewById(R.id.signUp_username);
        signUpPassword = (EditText) findViewById(R.id.signUp_password);
        signUpCouponCode = (EditText) findViewById(R.id.signUp_coupon_code);

        signUpWithFacebook = (ImageView) findViewById(R.id.signUpWithFacebook);
        signUpWithGoogle = (ImageView) findViewById(R.id.signUpWithGmail);
        signUpWithLinkdIN = (ImageView) findViewById(R.id.signUpWithLinkdin);

        signin = (TextView) findViewById(R.id.signIn);
        termsOfServices = (TextView) findViewById(R.id.signUpTerms);

        apply = (Button) findViewById(R.id.promoCodeApply);
        signUp = (Button) findViewById(R.id.signUp);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(signUpEmail.getWindowToken(), 0);
    }

    // sign in for gmail

    public void signUpWithgoogle() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // on activity result for gmail and facbook

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // for gmail

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

        //for facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);


    }
    // handle sign in result for gmail

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("asd", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Intent intent = new Intent(Signup.this, Home.class);
            startActivity(intent);


        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //Call back for facbook

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Intent intent = new Intent(Signup.this, Home.class);
            startActivity(intent);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    private void SignUpAPICALLl() {
        FixpocketAPI bookNPlayAPI = BaseURL.getFixpocketAPI();
        Call<RegistrationModel> call = bookNPlayAPI.signup(Constant.API_AUTH,
                Username, Password, EmailId, Coupon);
        call.enqueue(new Callback<RegistrationModel>() {
            @Override
            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                mkLoader.setVisibility(View.GONE);
                if (response.code() == 200) {
                    if (response.body().getStatus() == 1) {

                        Intent login = new Intent(Signup.this, Login.class);
                        startActivity(login);
                        finish();

                    } else {
                        Message("SignUp Failed.");
                    }
                }
            }

            @Override
            public void onFailure(Call<RegistrationModel> call, Throwable t) {
                mkLoader.setVisibility(View.GONE);
                Message("" + t.getMessage());
            }
        });
    }

    public void Message(String message) {
        int color = Color.WHITE;
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }
}
