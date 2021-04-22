package space.zelinskiy.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    Array array = new Array(); //Создали новый оъект из класса Array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE); //Указываем сохраненные данные по количеству пройденных уровней
        final int level = save.getInt("Level", 1);

        array.rezult[0] = save.getInt("array.rezult[0]",0);
        array.rezult[1] = save.getInt("array.rezult[1]",0);
        array.rezult[2] = save.getInt("array.rezult[2]",0);
        array.rezult[3] = save.getInt("arrayRezult 3",0);
        array.rezult[4] = save.getInt("arrayRezult 4",0);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехона 1-й уровень - Начало
        TextView textView1 = (TextView) findViewById(R.id.textView1);
       textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=1){
                    Intent intent = new Intent(GameLevels.this,Level1.class);
                    startActivity(intent);
                    finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехона 1-й уровень - Конец

        //кнопка для перехона 2-й уровень - Начало
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=2){
                    Intent intent = new Intent(GameLevels.this,Level2.class);
                    startActivity(intent);
                    finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехона 2-й уровень - Конец

        //кнопка для перехона 3-й уровень - Начало
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=3){
                    Intent intent = new Intent(GameLevels.this,Level3.class);
                    startActivity(intent);
                    finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехона 3-й уровень - Конец

        //кнопка для перехона 4-й уровень - Начало
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=4){
                    Intent intent = new Intent(GameLevels.this,Level4.class);
                        intent.putExtra("numlev",4);
                    startActivity(intent);
                    finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехона 4-й уровень - Конец

        //кнопка для перехона 5-й уровень - Начало
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=5){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",5);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехона 5-й уровень - Конец

        final int[] count={R.id.textViewlock1,R.id.textViewlock2,R.id.textViewlock3,R.id.textViewlock4,R.id.textViewlock5,R.id.textViewlock6};
        final int[] lock={R.id.imglock1,R.id.imglock2,R.id.imglock3,R.id.imglock4,R.id.imglock5,R.id.img_lock6};
        for (int i=0;i<level;i++){
            TextView tv = findViewById(count[i]);
            ImageView im = findViewById(lock[i]);
           // tv.setText(""+(i+1));
            tv.setVisibility(View.VISIBLE);
           // tv.setText(sek[i]);
            if (array.rezult[i]<=0){
                tv.setText("");
            }else {
                tv.setText(String.format("%d.%02d", array.rezult[i] / 100, (array.rezult[i] % 100)));
            }
            im.setVisibility(View.GONE);
        }
      //  TextView textViewlock1 =findViewById(R.id.textViewlock1);
      //  textViewlock1.setText(String.format("%d.%02d", array.rezult[0] / 100, (array.rezult[0] % 100)));
    }
    //системная кнопка Назад - начало
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(GameLevels.this,MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
    //системная кнопка Назад - конец
}
