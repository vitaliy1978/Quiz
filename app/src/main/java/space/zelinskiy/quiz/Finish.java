package space.zelinskiy.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Locale;

public class Finish extends AppCompatActivity {

    ReviewManager manager;
    ReviewInfo reviewInfo;
    private long backPressedTime;
    private Toast backToast;
    MediaPlayer headfly;
    String text, login;
    Toast liderToast;
    final int middleResult=0, level=1;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    RelativeLayout finishLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        final String country = Locale.getDefault().getCountry();

        manager = ReviewManagerFactory.create(Finish.this);
        final Task<ReviewInfo> request = manager.requestReviewFlow();

    final TextView back_game = (TextView) findViewById(R.id.button_close);
    TextView textdescription = (TextView) findViewById(R.id.text_description_final);
    TextView textOptMark =(TextView)findViewById(R.id.textOpt_mark);
    TextView textOptLiders = findViewById(R.id.textOpt_liders);
    finishLayout = findViewById(R.id.finish_layout);
    ImageView image = findViewById(R.id.main_cup);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        final int middleResult = save.getInt("middleResult", 0);
        final int level = save.getInt("Level", 1);

        back_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Finish.this,GameLevels.class);
                    startActivity(intent);
                }catch (Exception e){

                }
            }
        });

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("users");

        textOptLiders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindows();
               // startActivity(new Intent(Finish.this, MapActivity.class));
              //  finish();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignInWindows();
            }
        });

        textOptMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull Task<ReviewInfo> task) {
                if (task.isSuccessful()){
                    reviewInfo = task.getResult();
                    Task<Void> flow = manager.launchReviewFlow(Finish.this,reviewInfo);

                    flow.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void result) {
                         //Toast.makeText(Finish.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    //временный Тоаст для тестирования
                   // Toast.makeText(Finish.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

            }
        });

       Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        final ClickableSpan clickableSpan1 = new ClickableSpan() {
//            @Override
//            public void onClick(@NonNull View widget) {
//                liderToast = Toast.makeText(getBaseContext(),getString(R.string.level_last4),Toast.LENGTH_SHORT);
//                liderToast.show();
//            }
//
//            @Override
//            public void updateDrawState(@NonNull TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setColor(Color.BLUE);
//            }
//        };
//
        text =getString(R.string.level_last1)+"\n"+getString(R.string.level_last3)+" "+getString(R.string.level_last4)+"\n"+getString(R.string.level_last5)+String.format("%d.%02d", middleResult / 100, (middleResult % 100))
                +"\n"+getString(R.string.level_last6);
        textdescription.setText(text);
//        SpannableString boldtext = new SpannableString(text);
//        if (country=="RU"){
//            boldtext.setSpan(clickableSpan1, 32, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }else{
//            boldtext.setSpan(clickableSpan1, 28, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
//        textdescription.setText(boldtext);
//        textdescription.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void showSignInWindows() {
        AlertDialog.Builder dialod = new AlertDialog.Builder(this);
        dialod.setTitle(R.string.title_registration);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View sign_in_windows = layoutInflater.inflate(R.layout.sign_in_windows, null);
        dialod.setView(sign_in_windows);
        MaterialEditText email = sign_in_windows.findViewById(R.id.emailField);
        MaterialEditText password = sign_in_windows.findViewById(R.id.passField);
        dialod.setNegativeButton(R.string.back_registration, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialod.setPositiveButton(R.string.ok_registration, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(finishLayout,R.string.name_registration, Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length()<5){
                    Snackbar.make(finishLayout,R.string.password_registration, Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email.getText().toString()+"@mail.ru", password.getText().toString())
                        .addOnSuccessListener(new com.google.android.gms.tasks.OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(Finish.this, MapActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(finishLayout,R.string.error_registration+e.getMessage(),Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });

        dialod.show();
    }

    public void showRegisterWindows() {
        AlertDialog.Builder dialod = new AlertDialog.Builder(this);
        dialod.setTitle(R.string.title_registration);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View register_windows = layoutInflater.inflate(R.layout.register_windows, null);
        dialod.setView(register_windows);
        MaterialEditText email = register_windows.findViewById(R.id.emailField);
        MaterialEditText password = register_windows.findViewById(R.id.passField);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        final int middleResult = save.getInt("middleResult", 0);
        final int level = save.getInt("Level", 1);

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
                    Snackbar.make(finishLayout, R.string.name_registration, Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() < 6) {
                    Snackbar.make(finishLayout, R.string.password_registration, Snackbar.LENGTH_SHORT).show();
                    return;
                }

                //регистрация пользователя
                auth.createUserWithEmailAndPassword(email.getText().toString() + "@mail.ru", password.getText().toString())
                        .addOnSuccessListener(new com.google.android.gms.tasks.OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setName(email.getText().toString() + "@mail.ru");
                                user.setPass(password.getText().toString());
                                user.setLevel(level);
                                user.setMiddleResult(middleResult);
                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new com.google.android.gms.tasks.OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                             //   Snackbar.make(finishLayout, R.string.add_registration, Snackbar.LENGTH_SHORT).show();
                                                backToast = Toast.makeText(getBaseContext(),getString(R.string.add_registration),Toast.LENGTH_SHORT);
                                                backToast.show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(finishLayout,R.string.error_registration+e.getMessage(),Snackbar.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
            }
        });

        dialod.show();
    }


    //системная кнопка Назад - начало
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Finish.this,GameLevels.class);
        startActivity(intent);
    }
    //системная кнопка Назад - конец

    protected void onDestroy() {
        super.onDestroy();

     }
}
