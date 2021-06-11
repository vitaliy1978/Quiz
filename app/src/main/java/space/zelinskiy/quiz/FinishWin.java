package space.zelinskiy.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class FinishWin extends AppCompatActivity {

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
    MediaPlayer fanfary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        headfly = MediaPlayer.create(this,R.raw.fanfary);

        final String country = Locale.getDefault().getCountry();

        manager = ReviewManagerFactory.create(FinishWin.this);
        final Task<ReviewInfo> request = manager.requestReviewFlow();

    final TextView back_game = (TextView) findViewById(R.id.button_close);
    TextView textdescription = (TextView) findViewById(R.id.text_description_final);
    TextView textOptMark =(TextView)findViewById(R.id.textOpt_mark);
    TextView textOptLiders_end = findViewById(R.id.textOpt_liders_end);
    finishLayout = findViewById(R.id.finish_layout);
    ImageView main_cup = findViewById(R.id.main_cup);


        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        final int middleResult = save.getInt("middleResult", 0);
        final int level = save.getInt("Level", 1);
        final boolean muzof = save.getBoolean("muzof", false);  //берем данные о вкдюченности музыки

        final Animation a1 = AnimationUtils.loadAnimation(FinishWin.this,R.anim.alpha3);
        final Animation a2 = AnimationUtils.loadAnimation(FinishWin.this,R.anim.alpha3);
        final Animation a3 = AnimationUtils.loadAnimation(FinishWin.this,R.anim.alpha3);
        final Animation a4 = AnimationUtils.loadAnimation(FinishWin.this,R.anim.alpha3);
        final Animation a5 = AnimationUtils.loadAnimation(FinishWin.this,R.anim.alpha3);

        a1.setStartOffset(500);
        textOptMark.setVisibility(View.INVISIBLE);
        textOptMark.startAnimation(a1);
        textOptMark.setVisibility(View.VISIBLE);

        a2.setStartOffset(900);
        textOptLiders_end.setVisibility(View.INVISIBLE);
        textOptLiders_end.startAnimation(a2);
        textOptLiders_end.setVisibility(View.VISIBLE);

        a3.setStartOffset(1300);
        textdescription.setVisibility(View.INVISIBLE);
        textdescription.startAnimation(a3);
        textdescription.setVisibility(View.VISIBLE);

        a4.setStartOffset(1700);
        main_cup.setVisibility(View.INVISIBLE);
        main_cup.startAnimation(a4);
        main_cup.setVisibility(View.VISIBLE);

        a5.setStartOffset(2100);
        back_game.setVisibility(View.INVISIBLE);
        back_game.startAnimation(a5);
        back_game.setVisibility(View.VISIBLE);

        fanfary = MediaPlayer.create(this,R.raw.fanfary);
        if (muzof==false) {
            fanfary.start();
        }

        back_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fanfary.stop();
                try {
                    Intent intent = new Intent(FinishWin.this,GameLevels.class);
                    startActivity(intent);
                }catch (Exception e){

                }
            }
        });

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("users");

        textOptLiders_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fanfary.stop();
                startActivity(new Intent(FinishWin.this, userlist.class));
                finish();
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
                    Task<Void> flow = manager.launchReviewFlow(FinishWin.this,reviewInfo);

                    flow.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void result) {
                         //Toast.makeText(FinishWin.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    //временный Тоаст для тестирования
                   // Toast.makeText(FinishWin.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

            }
        });

       Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        text =getString(R.string.level_last1)+"\n"+getString(R.string.level_last3)+" "+getString(R.string.level_last4)+"\n"+getString(R.string.level_last5)+String.format("%d.%02d", middleResult / 100, (middleResult % 100))
                +"\n"+getString(R.string.level_last6);
        textdescription.setText(text);

        textdescription.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);
        textOptLiders_end.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);
        textOptMark.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);
        back_game.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);

    }

    //системная кнопка Назад - начало
    @Override
    public void onBackPressed() {
        fanfary.stop();
        Intent intent = new Intent(FinishWin.this,GameLevels.class);
        startActivity(intent);
    }
    //системная кнопка Назад - конец

    protected void onDestroy() {
        super.onDestroy();
        fanfary.stop();
     }

    @Override
    protected void onStop() {
        super.onStop();
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);   //выключаем гроскость при сворачивании
        am.setStreamMute(AudioManager.STREAM_MUSIC, true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);    //возвращаем гроскость при разворачивании
        am.setStreamMute(AudioManager.STREAM_MUSIC, false);
    }
}
