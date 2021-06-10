package space.zelinskiy.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.appodeal.ads.Appodeal;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    MediaPlayer headfly;
    public int wasTried=0;

    TextView buttonOption, buttonTop;
    Button buttonStart;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    LoginButton loginFacebook;
    CallbackManager callbackManager;
    private static final String TAG = "FacebookAuthetification";
    private AccessTokenTracker accessTokenTracker;
    private static final String CONSENT = "consent";
    boolean consent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        Appodeal.disableLocationPermissionCheck();
        Appodeal.disableWriteExternalStoragePermissionCheck();

        Appodeal.initialize(this, "a974c5ba4cff40feeb011cd509020d30098be772998f97fc", Appodeal.INTERSTITIAL, true);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "{space.zelinskiy.quiz}",                  //Insert your own package name.
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        Intent intent =getIntent();  //получить intent
        wasTried  = intent.getIntExtra("wasTried",0);  //метод getStringExtra читает была ли попытка подключения к Facebook

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE); //Указываем сохраненные данные
        final boolean voiceof = save.getBoolean("voiceof", false);  //берем данные о включенности звуков
        final int facebookLoged = save.getInt("facebookLoged", 0);

        final int level = save.getInt("Level", 1);
        final int alreadyReg = save.getInt("alreadyReg", 0);

        final ImageView mainImg = (ImageView)findViewById(R.id.main_img);
        final ImageView mainImg2 = (ImageView)findViewById(R.id.main_img2);
        TextView buttonTop = (TextView) findViewById(R.id.buttonTop);

        if (level<2){
            buttonTop.setVisibility(View.INVISIBLE);
            mainImg2.setVisibility(View.GONE);
            mainImg.setVisibility(View.VISIBLE);
        }
        if (level>=2 && level % 2==0){
            buttonTop.setVisibility(View.VISIBLE);
            mainImg.setVisibility(View.GONE);
            mainImg2.setVisibility(View.VISIBLE);
        }
        if (level>=2 && level % 2==1){
          //  buttonTop.setVisibility(View.VISIBLE);
            mainImg2.setVisibility(View.GONE);
            mainImg.setVisibility(View.VISIBLE);
        }

        final ImageView head2 = (ImageView)findViewById(R.id.imageHead2);
        final ImageView head1 = (ImageView)findViewById(R.id.imageHead1);
        final ImageView head1light = (ImageView)findViewById(R.id.imageHead1light);
        final ImageView imageIntro1 = (ImageView)findViewById(R.id.imageIntro1);
        final ImageView imageIntro2 = (ImageView)findViewById(R.id.imageIntro2);
        final ImageView imageIntro3 = (ImageView)findViewById(R.id.imageIntro3);
        final ImageView imageIntro4 = (ImageView)findViewById(R.id.imageIntro4);
        final ImageView imageIntro5 = (ImageView)findViewById(R.id.imageIntro5);
        final ImageView imageIntro6 = (ImageView)findViewById(R.id.imageIntro6);
        final ImageView imageIntro7 = (ImageView)findViewById(R.id.imageIntro7);
        final ImageView imageIntro8 = (ImageView)findViewById(R.id.imageIntro8);
        final ImageView imageIntro9 = (ImageView)findViewById(R.id.imageIntro9);
        final ImageView imageIntro10 = (ImageView)findViewById(R.id.imageIntro10);
        final ImageView imageIntro11 = (ImageView)findViewById(R.id.imageIntro11);
        final ImageView imageIntro12 = (ImageView)findViewById(R.id.imageIntro12);

        final Animation a = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha3);
        final Animation a5 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha4);
        final Animation a2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove1);
        final Animation a4 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove3);
        final Animation aIntro1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro1);
        final Animation aIntro2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro2);
        final Animation aIntro3 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro3);
        final Animation aIntro4 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro4);
        final Animation aIntro5 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro5);
        final Animation aIntro6 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro6);
        final Animation aIntro7 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro7);
        final Animation aIntro8 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro8);
        final Animation aIntro9 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro9);
        final Animation aIntro10 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro10);
        final Animation aIntro11 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro11);
        final Animation aIntro12 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro12);

        head1light.setVisibility(View.INVISIBLE);


        imageIntro1.setVisibility(View.VISIBLE);
        imageIntro1.startAnimation(aIntro1);
        imageIntro1.setVisibility(View.INVISIBLE);

        aIntro2.setStartOffset(1300);
        imageIntro2.setVisibility(View.VISIBLE);
        imageIntro2.startAnimation(aIntro2);
        imageIntro2.setVisibility(View.INVISIBLE);

        aIntro3.setStartOffset(2350);
        imageIntro3.setVisibility(View.VISIBLE);
        imageIntro3.startAnimation(aIntro3);
        imageIntro3.setVisibility(View.INVISIBLE);

        aIntro4.setStartOffset(3350);
        imageIntro4.setVisibility(View.VISIBLE);
        imageIntro4.startAnimation(aIntro4);
        imageIntro4.setVisibility(View.INVISIBLE);

        aIntro5.setStartOffset(4150);
        imageIntro5.setVisibility(View.VISIBLE);
        imageIntro5.startAnimation(aIntro5);
        imageIntro5.setVisibility(View.INVISIBLE);

        aIntro6.setStartOffset(4750);
        imageIntro6.setVisibility(View.VISIBLE);
        imageIntro6.startAnimation(aIntro6);
        imageIntro6.setVisibility(View.INVISIBLE);

        aIntro7.setStartOffset(5250);
        imageIntro7.setVisibility(View.VISIBLE);
        imageIntro7.startAnimation(aIntro7);
        imageIntro7.setVisibility(View.INVISIBLE);

        aIntro8.setStartOffset(5650);
        imageIntro8.setVisibility(View.VISIBLE);
        imageIntro8.startAnimation(aIntro8);
        imageIntro8.setVisibility(View.INVISIBLE);

        aIntro9.setStartOffset(5950);
        imageIntro9.setVisibility(View.VISIBLE);
        imageIntro9.startAnimation(aIntro9);
        imageIntro9.setVisibility(View.INVISIBLE);

        aIntro10.setStartOffset(6150);
        imageIntro10.setVisibility(View.VISIBLE);
        imageIntro10.startAnimation(aIntro10);
        imageIntro10.setVisibility(View.INVISIBLE);

        aIntro11.setStartOffset(6350);
        imageIntro11.setVisibility(View.VISIBLE);
        imageIntro11.startAnimation(aIntro11);
        imageIntro11.setVisibility(View.INVISIBLE);

        aIntro12.setStartOffset(6550);
        imageIntro12.setVisibility(View.VISIBLE);
        imageIntro12.startAnimation(aIntro12);
        imageIntro12.setVisibility(View.INVISIBLE);

        a2.setStartOffset(6550);
        a4.setStartOffset(6550);
        head1.startAnimation(a2);
        head1light.startAnimation(a4);
        head1light.setVisibility(View.INVISIBLE);
       // mainImg.startAnimation(a);
        final Animation a3 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove2);
        a3.setStartOffset(6970);
        head2.startAnimation(a3);
        headfly = MediaPlayer.create(this,R.raw.headfly);
        if (voiceof==false) {
            headfly.start();
        }

        buttonOption = findViewById(R.id.buttonOption);
        buttonStart = findViewById(R.id.buttonStart);

        loginFacebook = findViewById(R.id.login_button);
        loginFacebook.setReadPermissions("email","public_profile");
        //проверка активности приложения в фейсбуке - начало
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        //проверка активности приложения в фейсбуке - конец
        callbackManager = CallbackManager.Factory.create();
        auth = FirebaseAuth.getInstance();

        if (facebookLoged==0 && isOnlineInternet(this) && isAppInstalled() && wasTried==0){
            loginFacebook.performClick();
        }

        if (isOnlineInternet(this) && isAppInstalled() && wasTried==0) {
            loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.d(TAG, "OnSucces" + loginResult);
//                    backToast = Toast.makeText(getBaseContext(), "OnSucces", Toast.LENGTH_SHORT);
//                    backToast.show();
                    handleFaceBookToken(loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {
                    Log.d(TAG, "OnCancel");
//                    backToast = Toast.makeText(getBaseContext(), "OnCancel", Toast.LENGTH_SHORT);
//                    backToast.show();
                    wasTried=1;
                }

                @Override
                public void onError(FacebookException error) {
//                    backToast = Toast.makeText(getBaseContext(), "OnError", Toast.LENGTH_SHORT);
//                    backToast.show();
                    Log.d(TAG, "OnError" + error);
                    wasTried=1;
                }
            });
        }else{
//            backToast = Toast.makeText(getBaseContext(), "Нет соединения с интернетом", Toast.LENGTH_SHORT);
//            backToast.show();
            loginFacebook.setVisibility(View.INVISIBLE);
        }

            authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        updateUI(user);
                    } else {
                        updateUI(null);
                    }
                }
            };

            accessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    currentAccessToken = AccessToken.getCurrentAccessToken();
                    if (currentAccessToken == null) {
                        auth.signOut();
                        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                                .Callback() {
                            @Override
                            public void onCompleted(GraphResponse graphResponse) {

                                LoginManager.getInstance().logOut();

                            }
                        }).executeAsync();
                    }
                }
            };


        Button buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wasTried=1;
                try {
                    headfly.stop();
                    Intent intent = new Intent(MainActivity.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });

         TextView buttonOption = (TextView) findViewById(R.id.buttonOption);
        buttonOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wasTried=1;
                try {
                    headfly.stop();
                    Intent intent = new Intent(MainActivity.this,OptionHelp.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });

        buttonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wasTried=1;
                startActivity(new Intent(MainActivity.this, userlist.class));
                finish();
            }
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }


    private void handleFaceBookToken(AccessToken accessToken) {
        Log.d(TAG,"handleFacebookToken"+accessToken);
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                if (task.isSuccessful()){
                    Log.d(TAG,"sing in with credential is succesful");
//                      backToast = Toast.makeText(getBaseContext(), "autentification succes", Toast.LENGTH_SHORT);
//                    backToast.show();
                    FirebaseUser user = auth.getCurrentUser();
                    updateUI(user);
                    SharedPreferences.Editor editor = save.edit();
                    editor.putInt("facebookLoged", 1);
                    editor.commit();
                }else{
                    Log.d(TAG,"sing in with credential is failure", task.getException());
//                    backToast = Toast.makeText(getBaseContext(), "autentification failed", Toast.LENGTH_SHORT);
//                    backToast.show();
                    updateUI(null);
                    SharedPreferences.Editor editor = save.edit();
                    editor.putInt("facebookLoged", 0);
                    editor.commit();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI(FirebaseUser user) {
        if (user !=null){
//            buttonOption.setText(user.getDisplayName());
//            buttonStart.setText(user.getEmail());
        } else{
//            buttonOption.setText("");
        }
    }

    public boolean isAppInstalled() {
        try {
            getApplicationContext().getPackageManager().getApplicationInfo("com.facebook.katana", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    //есть ли соединение с интернетом - Начало
    public static boolean isOnlineInternet(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }
     //есть ли соединение с интернетом - Конец

    //системная кнопка Назад - начало
    @Override
    public void onBackPressed() {
        if (backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            headfly.stop();
            super.onBackPressed();
            return;
        } else{
            backToast = Toast.makeText(getBaseContext(),getString(R.string.btnBacktwice),Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime=System.currentTimeMillis();
    }
    //системная кнопка Назад - конец

    @Override
    protected void onDestroy() {
        super.onDestroy();
        headfly.stop();
      // accessTokenTracker.stopTracking();
     }
    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener !=null){
            auth.addAuthStateListener(authStateListener);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
       auth.addAuthStateListener(authStateListener);
    }
}
