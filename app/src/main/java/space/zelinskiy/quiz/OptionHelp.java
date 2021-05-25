package space.zelinskiy.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.logging.Level;

public class OptionHelp extends AppCompatActivity {

    boolean muzof =false;
    boolean voiceof =false;
    String muzofStr ="";
    String voiceofStr ="";
    ReviewManager manager;
    ReviewInfo reviewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_help);

//        Window w = getWindow();
//        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        final Switch switchMuz = findViewById(R.id.switchMuz);
        final Switch switchVoice = findViewById(R.id.switchVoice);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        final int level = save.getInt("Level", 1);
        switchMuz.setChecked(save.getBoolean("statusMuz", true));   //берем данные о положении выключателя
        switchVoice.setChecked(save.getBoolean("statusVoice", true));  //берем данные о положении выключателя
        muzofStr = save.getString("muzofStr", muzofStr);
        voiceofStr = save.getString("voiceofStr", voiceofStr);
       // switchMuz.setText(muzofStr.toString());
      //  switchVoice.setText(voiceofStr.toString());
        if (switchMuz.isChecked()){
            switchMuz.setText(R.string.swithMusic);
        }else{
            switchMuz.setText(R.string.swithMusic2);
        }
        if (switchVoice.isChecked()){
            switchVoice.setText(R.string.swithVoice);
        }else{
            switchVoice.setText(R.string.swithVoice2);
        }

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Кнопка которая закрывает диалоговое окно - Начало
        TextView button_close = (TextView) findViewById(R.id.button_close_from_option);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(OptionHelp.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
        // Кнопка которая закрывает диалоговое окно - Конец

        switchMuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchMuz.isChecked()){
                    switchMuz.setText(R.string.swithMusic);
                    muzofStr = switchMuz.getText().toString();
                    muzof=false;
                }else{
                    switchMuz.setText(R.string.swithMusic2);
                    muzofStr = switchMuz.getText().toString();
                    muzof=true;
                }
                SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                SharedPreferences.Editor editor = save.edit();
                editor.putBoolean("muzof", muzof);
                editor.putBoolean("statusMuz", switchMuz.isChecked());
                editor.putString("muzofStr",muzofStr);
                editor.commit();
            }
        });
        switchVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchVoice.isChecked()){
                    switchVoice.setText(R.string.swithVoice);
                    voiceofStr = switchVoice.getText().toString();
                    voiceof=false;
                }else{
                    switchVoice.setText(R.string.swithVoice2);
                    voiceofStr = switchVoice.getText().toString();
                    voiceof=true;
                }
                SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                SharedPreferences.Editor editor = save.edit();
                editor.putBoolean("statusVoice",switchVoice.isChecked());
                editor.putBoolean("voiceof", voiceof);
                editor.putString("voiceofStr", voiceofStr);
                editor.commit();
            }
        });

        final TextView textOptliders = (TextView) findViewById(R.id.textOpt_liders);
        final TextView textOptMark = (TextView) findViewById(R.id.textOpt_mark);
        final TextView textOptShare = (TextView) findViewById(R.id.textOpt_share);

        manager = ReviewManagerFactory.create(OptionHelp.this);
        final Task<ReviewInfo> request = manager.requestReviewFlow();



        textOptMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
                    @Override
                    public void onComplete(@NonNull Task<ReviewInfo> task) {
                        if (task.isSuccessful()){
                            reviewInfo = task.getResult();
                            Task<Void> flow = manager.launchReviewFlow(OptionHelp.this,reviewInfo);

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


       // texOptliders.setVisibility(View.INVISIBLE);
       // texOptShare.setVisibility(View.INVISIBLE);

        if (level<=2){
            textOptMark.setVisibility(View.INVISIBLE);
        }

        if (level>2){
            textOptMark.setVisibility(View.VISIBLE);
        }

        textOptliders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionHelp.this, MapActivity.class));
                finish();
            }
        });
//        textOptliders.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction()==MotionEvent.ACTION_DOWN){
//                    textOptliders.setElevation(3);
//                }
//                if(event.getAction()==MotionEvent.ACTION_UP){
//                    textOptliders.setElevation(12);
//                }
//                return true;
//            }
//        });

//            textOptShare.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if (event.getAction()==MotionEvent.ACTION_DOWN){
//                        textOptShare.setElevation(3);
//                    }
//                    if(event.getAction()==MotionEvent.ACTION_UP){
//                        textOptShare.setElevation(50);
//                    }
//                    return false;
//                    }
//            });

            textOptShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textOptShare.setElevation(2);
                String message = getString(R.string.link_game);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,message);
                Intent chosenIntent = Intent.createChooser(intent,getString(R.string.chuserTitle));
                startActivity(chosenIntent);
            }
        });


    }
    //системная кнопка Назад - начало
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(OptionHelp.this,MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
    //системная кнопка Назад - конец
}
