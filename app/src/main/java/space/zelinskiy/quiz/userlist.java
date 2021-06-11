package space.zelinskiy.quiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Collections;


public class userlist extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<User> list;
    private Toast backToast;
    FirebaseAuth auth;
   // FirebaseAuth.AuthStateListener authStateListener;
    FirebaseDatabase db;
    Button buttonReg;
    TextView textTop, button_close_from_userlist, tvlevel, tvaverage;
    int alreadyre;
    String uid, username;
    MediaPlayer topleaders;
    RelativeLayout userlistLayout;
    MaterialEditText emailfield, pasfield;
    private static final String TAG = "FacebookAuthetification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);

        final int middleResult = save.getInt("middleResult", 0);
        final int level = save.getInt("Level", 1);
        final int alreadyReg = save.getInt("alreadyReg", 0);
        final int facebookLoged = save.getInt("facebookLoged", 0);
        String uid = save.getString("uid", "");
        final boolean muzof = save.getBoolean("muzof", false);  //берем данные о вкдюченности музыки
        topleaders = MediaPlayer.create(this,R.raw.topliders);

//        backToast = Toast.makeText(getBaseContext(), facebookLoged+"", Toast.LENGTH_SHORT);
//        backToast.show();

        if (muzof==false) {
            topleaders.start();
        }

        alreadyre=0;

        textTop = findViewById(R.id.textTop);
        tvlevel = findViewById(R.id.tvlevel);
        tvaverage = findViewById(R.id.tvaverage);
        button_close_from_userlist = findViewById(R.id.button_close_from_userlist);
        buttonReg = findViewById(R.id.buttonReg);
        recyclerView = findViewById(R.id.userList);
        userlistLayout = findViewById(R.id.userlist_layout);
        emailfield = findViewById(R.id.emailField);
        pasfield = findViewById(R.id.passField);
//        loginFacebook = findViewById(R.id.login_button);
//        loginFacebook.setReadPermissions("email","public_profile");

        final Animation a = AnimationUtils.loadAnimation(userlist.this,R.anim.alpha3);
        final Animation a1 = AnimationUtils.loadAnimation(userlist.this,R.anim.alpha5);
        //a.setStartOffset(1300);
        textTop.setVisibility(View.INVISIBLE);
        textTop.startAnimation(a1);
        textTop.setVisibility(View.VISIBLE);

        tvaverage.setVisibility(View.INVISIBLE);
        tvaverage.startAnimation(a1);
        tvaverage.setVisibility(View.VISIBLE);

        tvlevel.setVisibility(View.INVISIBLE);
        tvlevel.startAnimation(a1);
        tvlevel.setVisibility(View.VISIBLE);

        button_close_from_userlist.setVisibility(View.INVISIBLE);
        button_close_from_userlist.startAnimation(a1);
        button_close_from_userlist.setVisibility(View.VISIBLE);

//        loginFacebook.setVisibility(View.INVISIBLE);
//        loginFacebook.startAnimation(a1);
//        loginFacebook.setVisibility(View.VISIBLE);

        a.setStartOffset(1400);
        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView.startAnimation(a);
        recyclerView.setVisibility(View.VISIBLE);

        database = FirebaseDatabase.getInstance().getReference("users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();


        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);
                    //  myAdapter.notifyDataSetChanged();

                    Collections.sort(list,User.levelSort);
                    //    Collections.sort(list,User.AverageSort);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        if (alreadyReg==0 && middleResult>1) {
            if (isOnlineInternet(this)) {
                buttonReg.setVisibility(View.INVISIBLE);
                buttonReg.startAnimation(a1);
                buttonReg.setVisibility(View.VISIBLE);
            } else{
                backToast = Toast.makeText(getBaseContext(), getString(R.string.not_internet), Toast.LENGTH_SHORT);
                backToast.show();
            }
            //определяем UID текущего юзера
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                uid = user.getUid();
                SharedPreferences.Editor editor = save.edit();
                editor.putString("uid", String.valueOf(uid));
                editor.commit();
            }
            //определяем UID текущего юзера
        }

        textTop.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*14);
        tvaverage.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);
        tvlevel.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);
        buttonReg.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (alreadyReg==0 && alreadyre==0){
                    showRegisterWindows();
                }
//                Collections.sort(list,User.levelSort);
                myAdapter.notifyDataSetChanged();
            }
        });

        button_close_from_userlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topleaders.stop();
                try {
                    Intent intent = new Intent(userlist.this,MainActivity.class);
                    intent.putExtra("wasTried",1);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });

    }

    public void showRegisterWindows() {
        AlertDialog.Builder dialod = new AlertDialog.Builder(this);
        dialod.setTitle(R.string.title_registration);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View register_windows = layoutInflater.inflate(R.layout.register_windows, null);
        dialod.setView(register_windows);
        MaterialEditText email = register_windows.findViewById(R.id.emailField);
        MaterialEditText password = register_windows.findViewById(R.id.passField);
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int middleResult = save.getInt("middleResult", 0);
        final int level = save.getInt("Level", 1);

        //фильтр символов в поле имя- Начало
        InputFilter customFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start,
                                       int end, Spanned dest, int dstart, int dend) {
                if (source.equals("")) { // for backspace
                    return source;
                }
                if (source.toString().matches("[a-zA-Zа-яА-Я0-9]+")) {
                    return source;
                }
                return "";
            }
        };

        InputFilter lenthFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start,
                                       int end, Spanned dest, int dstart, int dend) {
                if (source.length() <= 15) {
                    return source;
                }
                return "";
            }
        };
        email.setFilters(new InputFilter[]{customFilter, new InputFilter.LengthFilter(15)});
        //фильтр символов в поле имя - Конец

        //фильтр символов в поле пароль - Начало
        InputFilter customFilterPas = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start,
                                       int end, Spanned dest, int dstart, int dend) {
                if (source.equals("")) { // for backspace
                    return source;
                }
                if (source.toString().matches("[a-zA-Zа-яА-Я0-9.]+")) {
                    return source;
                }
                return "";
            }
        };

        InputFilter lenthFilterPas = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start,
                                       int end, Spanned dest, int dstart, int dend) {
                if (source.length() <= 15) {
                    return source;
                }
                return "";
            }
        };
        password.setFilters(new InputFilter[]{customFilterPas, new InputFilter.LengthFilter(15)});
        //фильтр символов в поле пароль - Конец



        dialod.setNegativeButton(R.string.back_registration, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialod.setPositiveButton(R.string.ok_registration, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                if (TextUtils.isEmpty(email.getText().toString())) {
                    //  Snackbar.make(finishLayout, R.string.name_registration, Snackbar.LENGTH_SHORT).show();
                    backToast = Toast.makeText(getBaseContext(), getString(R.string.name_registration), Toast.LENGTH_SHORT);
                    backToast.show();
                    return;
                }
                if (password.getText().toString().length() < 6) {
                    //   Snackbar.make(finishLayout, R.string.password_registration, Snackbar.LENGTH_SHORT).show();
                    backToast = Toast.makeText(getBaseContext(), getString(R.string.password_registration), Toast.LENGTH_LONG);
                    backToast.show();
                    return;
                }

                final int facebookLoged = save.getInt("facebookLoged", 0);

                if (facebookLoged==1){ //если юзер залогинен через фейсбук
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = save.getString("uid", "");
                    if (user != null) {
                        uid = user.getUid();
                        SharedPreferences.Editor editor = save.edit();
                        editor.putString("uid", String.valueOf(uid));
                        editor.commit();
                    }
                    database = FirebaseDatabase.getInstance().getReference("users");
                    Query query = database
                            .orderByChild("name")
                            .equalTo(email.getText().toString() + "@mail.ru");
                    String finalUid = uid;
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getChildrenCount()>0) {
                                //username found
                                backToast = Toast.makeText(getBaseContext(), getString(R.string.error_registration), Toast.LENGTH_LONG);
                                backToast.show();
                            }else{
                                // username not found
                                database.child(finalUid).child("name").setValue(email.getText().toString() + "@mail.ru");
                                database.child(finalUid).child("middleResult").setValue(middleResult);
                                database.child(finalUid).child("level").setValue(level);
                                database.child(finalUid).child("pass").setValue(password.getText().toString());
                                database.child(finalUid).child("key").setValue(0);
                                database.child(finalUid).child("number").setValue(0);
                                SharedPreferences.Editor editor = save.edit();
                                editor.putInt("alreadyReg", 1);
                                editor.commit();
                                alreadyre = 1;
                                buttonReg.setVisibility(View.INVISIBLE);
                                backToast = Toast.makeText(getBaseContext(), getString(R.string.add_registration), Toast.LENGTH_LONG);
                                backToast.show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                    if (user != null) {
                        uid = user.getUid();
                        SharedPreferences.Editor editor = save.edit();
                        editor.putString("uid", String.valueOf(uid));
                        editor.commit();
                    }
                }

                if (facebookLoged==0) {   //если юзер не залогинен через фейсбук
                    if (!TextUtils.isEmpty(email.getText().toString())) {
                        //регистрация пользователя - начало
                        auth.createUserWithEmailAndPassword(email.getText().toString() + "@mail.ru", password.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        database = FirebaseDatabase.getInstance().getReference("users");
                                        Query query = database
                                                .orderByChild("name")
                                                .equalTo(email.getText().toString() + "@mail.ru");
                                        String finalUid = uid;
                                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.getChildrenCount()>0) {
                                                    //username found
                                                    backToast = Toast.makeText(getBaseContext(), getString(R.string.error_registration), Toast.LENGTH_LONG);
                                                    backToast.show();
                                                }else{
                                                    // username not found

                                                    if (task.isSuccessful()) {
                                                        User user = new User();
                                                        user.setName(email.getText().toString() + "@mail.ru");
                                                        user.setPass(password.getText().toString());
                                                        user.setLevel(level);
                                                        user.setMiddleResult(middleResult);

                                                        database.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                .setValue(user)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        SharedPreferences.Editor editor = save.edit();
                                                                        editor.putInt("alreadyReg", 1);
                                                                        editor.commit();
                                                                        alreadyre = 1;
                                                                        buttonReg.setVisibility(View.INVISIBLE);
                                                                        backToast = Toast.makeText(getBaseContext(), getString(R.string.add_registration), Toast.LENGTH_LONG);
                                                                        backToast.show();
                                                                    }
                                                                });
                                                    } else {
                                                        backToast = Toast.makeText(getBaseContext(), getString(R.string.error_registration), Toast.LENGTH_LONG);
                                                        backToast.show();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                            }
                                        });


                                    }
                                });
                        //регистрация пользователя - конец
                    }
                }
            }
        });
        dialod.show();
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
    public void onBackPressed(){
        topleaders.stop();
        try {
            Intent intent = new Intent(userlist.this,MainActivity.class);
            intent.putExtra("wasTried",1);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
    //системная кнопка Назад - конец

    protected void onDestroy() {
        super.onDestroy();
        topleaders.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);   //выключаем гроскость при сворачивании
        am.setStreamMute(AudioManager.STREAM_MUSIC, true);
//        if (authStateListener !=null){
//            auth.addAuthStateListener(authStateListener);
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);    //возвращаем гроскость при разворачивании
//        am.setStreamMute(AudioManager.STREAM_MUSIC, false);
//        auth.addAuthStateListener(authStateListener);
    }
}
