package space.zelinskiy.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
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
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;

import java.util.Locale;

public class Finish extends AppCompatActivity {

    ReviewManager manager;
    ReviewInfo reviewInfo;
    private long backPressedTime;
    private Toast backToast;
    MediaPlayer headfly;
    String text;
    Toast liderToast;
    final int middleResult=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        final String country = Locale.getDefault().getCountry();

        manager = ReviewManagerFactory.create(Finish.this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull Task<ReviewInfo> task) {
                if (task.isSuccessful()){
                    reviewInfo = task.getResult();
                    Task<Void> flow = manager.launchReviewFlow(Finish.this,reviewInfo);

                    flow.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void result) {

                        }
                    });
                }else{
                    //временный Тоаст для тестирования
                    Toast.makeText(Finish.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    TextView back_game = (TextView) findViewById(R.id.button_close);
    TextView textdescription = (TextView) findViewById(R.id.text_description_final);

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

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                liderToast = Toast.makeText(getBaseContext(),getString(R.string.level_last4),Toast.LENGTH_SHORT);
                liderToast.show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }
        };

        text =getString(R.string.level_last1)+"\n"+getString(R.string.level_last3)+"\n"+getString(R.string.level_last4)+"\n"+getString(R.string.level_last5)+String.format("%d.%02d", middleResult / 100, (middleResult % 100))
                +"\n"+getString(R.string.level_last6);
        SpannableString boldtext = new SpannableString(text);
        if (country=="RU"){
            boldtext.setSpan(clickableSpan1, 32, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else{
            boldtext.setSpan(clickableSpan1, 28, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textdescription.setText(boldtext);
        textdescription.setMovementMethod(LinkMovementMethod.getInstance());

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
