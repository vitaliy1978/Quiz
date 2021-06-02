package space.zelinskiy.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    MediaPlayer headfly;

    TextView buttonOption;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    LoginButton loginFacebook;
    CallbackManager callbackManager;
    private static final String TAG = "FacebookAuthetification";
    private AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

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

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE); //Указываем сохраненные данные
        final boolean voiceof = save.getBoolean("voiceof", false);  //берем данные о включенности звуков

        final ImageView mainImg = (ImageView)findViewById(R.id.main_img);
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
        mainImg.startAnimation(a);
        final Animation a3 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove2);
        a3.setStartOffset(6970);
        head2.startAnimation(a3);
        headfly = MediaPlayer.create(this,R.raw.headfly);
        if (voiceof==false) {
            headfly.start();
        }

        buttonOption = findViewById(R.id.buttonOption);

        loginFacebook = findViewById(R.id.login_button);
        loginFacebook.setReadPermissions("email","public_profile");
        //проверка активности приложения в фейсбуке - начало
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        //проверка активности приложения в фейсбуке - конец
        callbackManager = CallbackManager.Factory.create();
        auth = FirebaseAuth.getInstance();
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"OnSucces"+loginResult);
                backToast = Toast.makeText(getBaseContext(),"OnSucces",Toast.LENGTH_SHORT);
                backToast.show();
                handleFaceBookToken(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
                Log.d(TAG,"OnCancel");
                backToast = Toast.makeText(getBaseContext(),"OnCancel",Toast.LENGTH_SHORT);
                backToast.show();
            }

            @Override
            public void onError(FacebookException error) {
                backToast = Toast.makeText(getBaseContext(),"OnError",Toast.LENGTH_SHORT);
                backToast.show();
                Log.d(TAG,"OnError"+error);
            }
        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser();
                if (user !=null){
                    updateUI(user);
                } else{
                    updateUI(null);
                }
            }
        };
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                currentAccessToken = AccessToken.getCurrentAccessToken();
                if (currentAccessToken==null){
                    auth.signOut();
//                    new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
//                            .Callback() {
//                        @Override
//                        public void onCompleted(GraphResponse graphResponse) {
//                            LoginManager.getInstance().logOut();
//
//                        }
//                    }).executeAsync();
                }

            }
        };



        Button buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                try {
                    headfly.stop();
                    Intent intent = new Intent(MainActivity.this,OptionHelp.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
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
                if (task.isSuccessful()){
                    Log.d(TAG,"sing in with credential is succesful");
                    FirebaseUser user = auth.getCurrentUser();
                    updateUI(user);
                }else{
                    Log.d(TAG,"sing in with credential is failure", task.getException());
                    backToast = Toast.makeText(getBaseContext(), "autentification failed", Toast.LENGTH_SHORT);
                    backToast.show();
                    updateUI(null);
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
            buttonOption.setText(user.getDisplayName());
        } else{
            buttonOption.setText("");
        }
    }


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

    protected void onDestroy() {
        super.onDestroy();
        headfly.stop();
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
