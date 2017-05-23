package com.fixpocket.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import com.facebook.FacebookSdk;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.fixpocket.Model.LoginModel.Loginmodel;
import com.fixpocket.Network.ConnectivityReceiver;
import com.fixpocket.R;
import com.fixpocket.Fragment.Home;
import com.fixpocket.Webservice.WebUtil.BaseURL;
import com.fixpocket.Webservice.FixpocketAPI;
import com.fixpocket.Utils.Constant;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener,
        ConnectivityReceiver.ConnectivityReceiverListener {

    private EditText logInUsername, logInPassword;
    private TextView loginForgotPassword;
    private ImageView logInWithFacebook, logInWithGoogle, logInWithLinkdIN;
    private Button login;
    GoogleApiClient mGoogleApiClient;
    public static final int RC_SIGN_IN = 9001;
    CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    //    private LoginButton ;
    LoginManager loginManager;

    String username, password;
    MKLoader mkLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mkLoader = (MKLoader) findViewById(R.id.mlloader);

        init();

        logInUsername.setText("nisarg");
        logInPassword.setText("nisarg2106");

        logInWithFacebook.setOnClickListener(this);
        logInWithGoogle.setOnClickListener(this);
        logInWithLinkdIN.setOnClickListener(this);
        login.setOnClickListener(this);
        loginForgotPassword.setOnClickListener(this);
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

        checkConnection();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginWithFacebook:
                loginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
                break;
            case R.id.loginWithGmail:
                signInWithgoogle();
                break;
            case R.id.loginWithLinkdin:
                // do stuff;
                break;

            case R.id.signIn:

                username = logInUsername.getText().toString();
                password = logInPassword.getText().toString();

                boolean isConnected = ConnectivityReceiver.isConnected();
                if ((isConnected)) {
                    if (username.isEmpty()) {
                        Message("Enter Email or UserName");
                    } else if (password.isEmpty()) {
                        Message("Enter Password.");
                    } else {
                        mkLoader.setVisibility(View.VISIBLE);
                        prepareRetrofitCall();
                    }
                } else {
                    showSnack(isConnected);
                }

                break;
            case R.id.login_forgot_password:
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
                break;
        }
    }

    public void init() {

        logInUsername = (EditText) findViewById(R.id.login_emailid);
        logInPassword = (EditText) findViewById(R.id.login_password);
        logInWithFacebook = (ImageView) findViewById(R.id.loginWithFacebook);
        logInWithGoogle = (ImageView) findViewById(R.id.loginWithGmail);
        logInWithLinkdIN = (ImageView) findViewById(R.id.loginWithLinkdin);
        login = (Button) findViewById(R.id.signIn);
        loginForgotPassword = (TextView) findViewById(R.id.login_forgot_password);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(logInUsername.getWindowToken(), 0);

    }
    // sign in for gmail

    public void signInWithgoogle() {

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
            Intent intent = new Intent(Login.this, Home.class);
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
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };


    private void prepareRetrofitCall() {
        FixpocketAPI bookNPlayAPI = BaseURL.getFixpocketAPI();
        Call<Loginmodel> call = bookNPlayAPI.Login(Constant.API_AUTH,
                username, password);
        call.enqueue(new Callback<Loginmodel>() {
            @Override
            public void onResponse(Call<Loginmodel> call, Response<Loginmodel> response) {
                mkLoader.setVisibility(View.GONE);
                if (response.code() == 200) {
                    if (response.body().getStatus() == 1) {

                        Constant.AUTH_TOKEN = response.body().getData().getBasicInfo().getAuthToken();
                        Log.e("Constant.AUTH_TOKEN ", "" + Constant.AUTH_TOKEN);
                        
                        Intent signin = new Intent(Login.this, DashBoard.class);
                        startActivity(signin);
                        finish();
                    } else {
                        Message("Login Failed.");
                    }
                }
            }

            @Override
            public void onFailure(Call<Loginmodel> call, Throwable t) {
                mkLoader.setVisibility(View.GONE);
                Toast.makeText(Login.this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
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
}
