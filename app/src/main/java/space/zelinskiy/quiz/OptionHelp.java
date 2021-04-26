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
    String muzofStr ="";
    String voiceofStr ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_help);

//        Window w = getWindow();
//        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        final Switch switchMuz = findViewById(R.id.switchMuz);
        final Switch switchVoice = findViewById(R.id.switchVoice);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        switchMuz.setChecked(save.getBoolean("statusMuz", false));   //берем данные о положении выключателя
        switchVoice.setChecked(save.getBoolean("statusVoice", false));  //берем данные о положении выключателя
        muzofStr = save.getString("muzofStr", muzofStr);
        voiceofStr = save.getString("voiceofStr", voiceofStr);
        switchMuz.setText(muzofStr.toString());
        switchVoice.setText(voiceofStr.toString());

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
