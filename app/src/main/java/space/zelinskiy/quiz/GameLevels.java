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

     //  final int level = 21;

        array.rezult[0] = save.getInt("arrayRezult 0",0);
        array.rezult[1] = save.getInt("arrayRezult 1",0);
        array.rezult[2] = save.getInt("arrayRezult 2",0);
        array.rezult[3] = save.getInt("arrayRezult 3",0);
        array.rezult[4] = save.getInt("arrayRezult 4",0);
        array.rezult[5] = save.getInt("arrayRezult 5",0);
        array.rezult[6] = save.getInt("arrayRezult 6",0);
        array.rezult[7] = save.getInt("arrayRezult 7",0);
        array.rezult[8] = save.getInt("arrayRezult 8",0);
        array.rezult[9] = save.getInt("arrayRezult 9",0);
        array.rezult[10] = save.getInt("arrayRezult 10",0);
        array.rezult[11] = save.getInt("arrayRezult 11",0);
        array.rezult[12] = save.getInt("arrayRezult 12",0);
        array.rezult[13] = save.getInt("arrayRezult 13",0);
        array.rezult[14] = save.getInt("arrayRezult 14",0);
        array.rezult[15] = save.getInt("arrayRezult 15",0);
        array.rezult[16] = save.getInt("arrayRezult 16",0);
        array.rezult[17] = save.getInt("arrayRezult 17",0);
        array.rezult[18] = save.getInt("arrayRezult 18",0);
        array.rezult[19] = save.getInt("arrayRezult 19",0);
        array.rezult[20] = save.getInt("arrayRezult 20",0);
        array.rezult[21] = save.getInt("arrayRezult 21",0);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this,MainActivity.class);
                    intent.putExtra("wasTried",1);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 1-й уровень - Начало
        TextView textView1 = (TextView) findViewById(R.id.textView1);
       textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=1){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",1 );
                    startActivity(intent);
                    finish();
                    }else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехона 1-й уровень - Конец

        //кнопка для перехода 2-й уровень - Начало
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=2){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",2);
                    startActivity(intent);
                    finish();
                    }else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехона 2-й уровень - Конец

        //кнопка для перехода 3-й уровень - Начало
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=3){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",3);
                    startActivity(intent);
                    finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 3-й уровень - Конец

        //кнопка для перехода 4-й уровень - Начало
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=4){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",4);
                    startActivity(intent);
                    finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 4-й уровень - Конец

        //кнопка для перехода 5-й уровень - Начало
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
        //кнопка для перехода 5-й уровень - Конец

        //кнопка для перехода 6-й уровень - Начало
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=6){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",6);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 6-й уровень - Конец

        //кнопка для перехода 7-й уровень - Начало
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=7){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",7);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 7-й уровень - Конец

        //кнопка для перехода 8-й уровень - Начало
        TextView textView8 = (TextView) findViewById(R.id.textView8);
        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=8){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",8);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 8-й уровень - Конец
        //кнопка для перехода 9-й уровень - Начало
        TextView textView9 = (TextView) findViewById(R.id.textView9);
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=9){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",9);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 9-й уровень - Конец
        //кнопка для перехода 10-й уровень - Начало
        TextView textView10 = (TextView) findViewById(R.id.textView10);
        textView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=10){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",10);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 10-й уровень - Конец
        //кнопка для перехода 11-й уровень - Начало
        TextView textView11 = (TextView) findViewById(R.id.textView11);
        textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=11){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",11);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 11-й уровень - Конец
        //кнопка для перехода 12-й уровень - Начало
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=12){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",12);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 12-й уровень - Конец
        //кнопка для перехода 13-й уровень - Начало
        TextView textView13 = (TextView) findViewById(R.id.textView13);
        textView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=13){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",13);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 13-й уровень - Конец
        //кнопка для перехода 14-й уровень - Начало
        TextView textView14 = (TextView) findViewById(R.id.textView14);
        textView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=14){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",14);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 14-й уровень - Конец
        //кнопка для перехода 15-й уровень - Начало
        TextView textView15 = (TextView) findViewById(R.id.textView15);
        textView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=15){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",15);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 15-й уровень - Конец
        //кнопка для перехода 16-й уровень - Начало
        TextView textView16 = (TextView) findViewById(R.id.textView16);
        textView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=16){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",16);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 16-й уровень - Конец
        //кнопка для перехода 17-й уровень - Начало
        TextView textView17 = (TextView) findViewById(R.id.textView17);
        textView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=17){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",17);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 17-й уровень - Конец
        //кнопка для перехода 18-й уровень - Начало
        TextView textView18 = (TextView) findViewById(R.id.textView18);
        textView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=18){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",18);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 18-й уровень - Конец
        //кнопка для перехода 19-й уровень - Начало
        TextView textView19 = (TextView) findViewById(R.id.textView19);
        textView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=19){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",19);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 19-й уровень - Конец
        //кнопка для перехода 20-й уровень - Начало
        TextView textView20 = (TextView) findViewById(R.id.textView20);
        textView20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=20){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",20);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 20-й уровень - Конец
        //кнопка для перехода 21-й уровень - Начало
        TextView textView21 = (TextView) findViewById(R.id.textView21);
        textView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=21){
                        Intent intent = new Intent(GameLevels.this,Level5.class);
                        intent.putExtra("numlev",21);
                        startActivity(intent);
                        finish();}else {
                        //пусто
                    }
                }catch (Exception e){

                }
            }
        });
        //кнопка для перехода 21-й уровень - Конец


        final int[] count={R.id.textViewlock1,R.id.textViewlock2,R.id.textViewlock3,R.id.textViewlock4,R.id.textViewlock5,R.id.textViewlock6,
                R.id.textViewlock7,R.id.textViewlock8,R.id.textViewlock9,R.id.textViewlock10,R.id.textViewlock11,R.id.textViewlock12,R.id.textViewlock13,
                R.id.textViewlock14,R.id.textViewlock15,R.id.textViewlock16,R.id.textViewlock17,R.id.textViewlock18,R.id.textViewlock19,
                R.id.textViewlock20,R.id.textViewlock21};
        final int[] lock={R.id.imglock1,R.id.imglock2,R.id.imglock3,R.id.imglock4,R.id.imglock5,R.id.img_lock6,R.id.img_lock7,R.id.img_lock8,
                R.id.img_lock9,R.id.img_lock10,R.id.img_lock11,R.id.img_lock12,R.id.img_lock13,R.id.img_lock14,R.id.img_lock15,R.id.img_lock16,R.id.img_lock17,
                R.id.img_lock18,R.id.img_lock19,R.id.img_lock20,R.id.img_lock21};
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
    }

    //системная кнопка Назад - начало
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(GameLevels.this,MainActivity.class);
            intent.putExtra("wasTried",1);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
    //системная кнопка Назад - конец
}
