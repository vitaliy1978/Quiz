package space.zelinskiy.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OptionHelp extends AppCompatActivity {

    boolean muzof =false;
    boolean voiceof =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_help);

//        Window w = getWindow();
//        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        final Switch switchMuz = findViewById(R.id.switchMuz);
        final Switch switchVoice = findViewById(R.id.switchVoice);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        switchMuz.setChecked(save.getBoolean("statusMuz", false));
        switchVoice.setChecked(save.getBoolean("statusVoice", false));

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
                    muzof=false;
                }else{
                    switchMuz.setText(R.string.swithMusic2);
                    muzof=true;
                }
                SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                SharedPreferences.Editor editor = save.edit();
                editor.putBoolean("muzof", muzof);
                editor.putBoolean("statusMuz", switchMuz.isChecked());
                editor.commit();
            }
        });
        switchVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchVoice.isChecked()){
                    switchVoice.setText(R.string.swithVoice);
                    voiceof=false;
                }else{
                    switchVoice.setText(R.string.swithVoice2);
                    voiceof=true;
                }
                SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                SharedPreferences.Editor editor = save.edit();
                editor.putBoolean("statusVoice",switchVoice.isChecked());
                editor.putBoolean("voiceof", voiceof);
                editor.commit();
            }
        });

//        if (switchMuz != null) {
//            switchMuz.setOnCheckedChangeListener(this);
//        }


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
