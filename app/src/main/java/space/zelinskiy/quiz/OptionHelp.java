package space.zelinskiy.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final Switch switchMuz = findViewById(R.id.switchMuz);
        final Switch switchVoice = findViewById(R.id.switchVoice);

        TextView text_opt_head = findViewById(R.id.text_opt_head);
        TextView text_opt_description = findViewById(R.id.text_opt_description);
        TextView text_opt_description2 = findViewById(R.id.text_opt_description2);
        TextView text_opt_description3 = findViewById(R.id.text_opt_description3);
        final TextView textOptliders = (TextView) findViewById(R.id.textOpt_liders);
        final TextView textOptMark = (TextView) findViewById(R.id.textOpt_mark);
        final TextView textOptShare = (TextView) findViewById(R.id.textOpt_share);
        text_opt_head.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*12);
        text_opt_description.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);
        text_opt_description2.setTextSize(TypedValue.COMPLEX_UNIT_PX, MainActivity.he*9);
        text_opt_description3.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);
        textOptliders.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);
        textOptMark.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);
        textOptShare.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);
        switchMuz.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);
        switchVoice.setTextSize(TypedValue.COMPLEX_UNIT_PX,MainActivity.he*9);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        final int level = save.getInt("Level", 1);

        switchMuz.setChecked(save.getBoolean("statusMuz", true));   //берем данные о положении выключателя
        switchVoice.setChecked(save.getBoolean("statusVoice", true));  //берем данные о положении выключателя
        muzofStr = save.getString("muzofStr", muzofStr);
        voiceofStr = save.getString("voiceofStr", voiceofStr);
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

        // Кнопка которая закрывает диалоговое окно - Начало
        TextView button_close = (TextView) findViewById(R.id.button_close_from_option);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(OptionHelp.this,MainActivity.class);
                    intent.putExtra("wasTried",1);
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
                                    //Toast.makeText(FinishWin.this,"Error",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{

                        }
                    }
                });

            }
        });

        if (level<=5){
            textOptMark.setVisibility(View.INVISIBLE);
        }

        if (level>5){
            textOptMark.setVisibility(View.VISIBLE);
        }

        textOptliders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionHelp.this, userlist.class));
                finish();
            }
        });
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
            intent.putExtra("wasTried",1);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
    //системная кнопка Назад - конец
}
