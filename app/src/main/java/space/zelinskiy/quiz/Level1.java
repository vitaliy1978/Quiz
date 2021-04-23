package space.zelinskiy.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;  //Переменная для левой картинки + текст
    public int numRight;  //Переменная для правой картинки + текст
    Array array = new Array(); //Создали новый оъект из класса Array
    Random random = new Random(); //для генерации случайных чисел
    public int count =0;  //Счетчик правильных ответов
    MediaPlayer musicfon, musicotschet;
    public int sek=0, sekost=0;  //подсчет секунд и подсчет секунд для остановки после превышения

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        final TextView text_levels = (TextView)findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_left.setClipToOutline(true);//код, который скругляет углы
        img_right.setClipToOutline(true);//код, который скругляет углы

        final TextView text_left = findViewById(R.id.text_left);  //Путь к левой TextView
        final TextView text_right = findViewById(R.id.text_right);  //Путь к правой TextView
        final TextView text_otschet = findViewById(R.id.text_otschet); //Путь к индикатору отсчета перед игрой
        final TextView text_time = findViewById(R.id.text_time); //Путь к индикатору секунд в игре
        final Button button_back = (Button)findViewById(R.id.button_back);  //Путь к кнопке Назад
        musicfon = MediaPlayer.create(this, R.raw.musicfon);
        musicotschet = MediaPlayer.create(this,R.raw.musicotschet);

        img_left.setEnabled(false);
        img_right.setEnabled(false);
        button_back.setEnabled(false);

        //Развернуть игру на весь экран - Начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Развернуть игру на весь экран - Конец

        dialog = new Dialog(this); //Создам новое диалоговое окно в начале игры
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  //Скрываем заголовок у диалогового окна
        dialog.setContentView(R.layout.preview_dialog); //Путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //Прозрачный фон диалогового окна
        dialog.setCancelable(false);  //окно нельзя открыть кнопкой назад

        //Устанавливаем картинку в диалоговое окно - Начало
        ImageView preview_img =(ImageView)dialog.findViewById(R.id.preview_img);
        preview_img.setImageResource(R.drawable.preview_img_one);
        //Устанавливаем картинку в диалоговое окно - Конец

        //Устанавливаем описание задания - Начало
        TextView text_description = (TextView)dialog.findViewById(R.id.text_description);
        TextView text_description2 = (TextView)dialog.findViewById(R.id.text_description2);
        text_description.setText(R.string.levelone);
        text_description2.setText(R.string.levelone2);
        //Устанавливаем описание задания - Конец

        // Кнопка которая закрывает диалоговое окно - Начало
        TextView button_close = (TextView) dialog.findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialog.dismiss();  //закрываем диалоговое окно
            }
        });
        // Кнопка которая закрывает диалоговое окно - Конец

        //Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level1.this,R.anim.alpha);
        final Animation a2 = AnimationUtils.loadAnimation(Level1.this,R.anim.alpha2);
        //Подключаем анимацию - конец

        //__________________________
        dialogEnd = new Dialog(this); //Создам новое диалоговое окно в конце игры
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);  //Скрываем заголовок у диалогового окна
        dialogEnd.setContentView(R.layout.dialog_end); //Путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //Прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);  //окно нельзя открыть кнопкой назад

        final TextView textdescribtionEnd = (TextView)dialogEnd.findViewById(R.id.text_description_end);
        textdescribtionEnd.setText(R.string.leveloneEnd);
        final ImageView preview_img_viktory = (ImageView)dialogEnd.findViewById(R.id.preview_img_viktory);
        final ImageView main_img_lose = (ImageView)dialogEnd.findViewById(R.id.main_img_lose);

        // Кнопка которая закрывает диалоговое окно - Начало
        TextView button_close2 = (TextView) dialogEnd.findViewById(R.id.button_close);
        button_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialog.dismiss();  //закрываем диалоговое окно
            }
        });
        // Кнопка которая закрывает диалоговое окно - Конец

        //Кнопка Продолжить - Начало
        final Button btn_continue2 = (Button)dialogEnd.findViewById(R.id.btn_continue_next);
        btn_continue2.setVisibility(View.VISIBLE);

        btn_continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent =new Intent(Level1.this,Level2.class);
                    intent.putExtra("numlev",2);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss();  //закрываем диалоговое окно
            }
        });
        //Кнопка Продолжить - Конец
        //__________________________

        //Кнопка Продолжить - Начало
        final Button btn_continue = (Button)dialog.findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();  //закрываем диалоговое окно

                CountDownTimer myTimer = new CountDownTimer(3010, 900) {
                    @Override
                    public void onTick(long millisUntilFinished)
                    {
                        text_otschet.setVisibility(View.VISIBLE);
                        musicotschet.start();
                        if (millisUntilFinished<=3100)
                        {
                            text_otschet.setText(Long.toString(millisUntilFinished / 900));
                            text_otschet.startAnimation(a2);
                        }
                    }

                    @Override
                    public void onFinish()
                    {
                        musicotschet.stop();
                        musicfon.start();
                        text_otschet.setVisibility(View.GONE);
                        img_left.setEnabled(true);
                        img_right.setEnabled(true);
                        button_back.setEnabled(true);
                    }
                };
                myTimer.start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while ((!Thread.interrupted()) && (sekost < 2000)) {
                            if (musicfon.isPlaying()) {
                                sek++;
                                sekost++;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        text_time.setText(String.format("%d.%02d", sek / 100, (sek % 100)));
                                        if (sekost >= 2000 && sekost<3000) {
                                            musicfon.stop();
                                            preview_img_viktory.setVisibility(View.GONE);  //Прячем радостный смайлик
                                            main_img_lose.setVisibility(View.VISIBLE);  //Выводим грустный смайлик
                                            textdescribtionEnd.setText(R.string.levelEnd);
                                            btn_continue2.setVisibility(View.INVISIBLE);
                                            dialogEnd.show();  //Выводим окно окончания игры
                                        }
                                    }
                                });
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    break;
                                }
                            }
                        }
                    }
                }).start();

            }
        });
        //Кнопка Продолжит - Конец

        dialog.show();  //показать диалоговое окно

         //Кнопка Назад - Начало

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    musicfon.stop();
                    sekost=50001;
                    Intent intent = new Intent(Level1.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        //Кнопка Назад - Конец

        //Маccив для прогресса игры - Начало
        final int[] progress ={
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15, R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20};
        //Массив для прогресса игры - Конец

        numLeft=random.nextInt(array.images1.length); //генерируем случайное число от 0 до 9
        img_left.setImageResource(array.images1[numLeft]);  //достаем из массива картинку
        text_left.setText(array.texts1[numLeft]);  //достаем из массива текст

        numRight=random.nextInt(array.images1.length); //генерируем случайное число от 0 до 9
        //Цикл проверяющий равенство чисел - Начало
        while(numLeft==numRight){
            numRight=random.nextInt(array.images1.length);
        }
        //Цикл проверяющий равенство чисел - Конец
        img_right.setImageResource(array.images1[numRight]);  //достаем из массива картинку
        text_right.setText(array.texts1[numRight]);  //достаем из массива текст

        //Обрабатываем нажатие на левую картинку - Начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //Условие для касания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){  //если коснулся пальцем
                    img_right.setEnabled(false);  //блокируем правую картинку
                    if (numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);

                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                } else if(event.getAction()==MotionEvent.ACTION_UP){   //если убрал палец
                    if (numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);
                        if (count<20){
                            count=count+1;
                        }
                        for (int i=0;i<20;i++){
                            TextView tv =findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i=0;i<count;i++){
                            TextView tv =findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if (count>0){
                            if (count==1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }
                        for (int i=0;i<19;i++){
                            TextView tv =findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i=0;i<count;i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count==20){  //Выход из уровня
                        musicfon.stop();
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);  //Указывает сохнаненный рекорд времени за 1 уровень
                        array.rezult[0] = save.getInt("array.rezult[0]",0);
                        if (array.rezult[0]>0 && array.rezult[0]<sek)
                         {
                             textdescribtionEnd.setText("Уровень пройден.\nВы справились за "+String.format("%d.%02d", sek / 100, (sek % 100))+"\nЧуть-чуть не хватило до рекорда");
                         }else{
                            array.rezult[0]=sek;
                            textdescribtionEnd.setText("Поздравляю!\nВы справились за "+String.format("%d.%02d", array.rezult[0] / 100, (array.rezult[0] % 100))+"\nЭто новый рекорд!");

                            array.rezult[0] = save.getInt("array.rezult[0]",1);
                            SharedPreferences.Editor editor2 = save.edit();
                            editor2.putInt("array.rezult[0]", sek);
                            editor2.commit();
                        }
                        sekost=50001;
                        preview_img_viktory.setVisibility(View.VISIBLE); //Выводим радостный смайлик
                        main_img_lose.setVisibility(View.GONE);   //Прячем грустный смайлик

                        //SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level>1){
                            //пусто
                        }else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 2);
                            editor.commit();
                        }
                           dialogEnd.show();
                    }else {
                        numLeft=random.nextInt(array.images1.length); //генерируем случайное число от 0 до 9
                        img_left.setImageResource(array.images1[numLeft]);  //достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);  //достаем из массива текст

                        numRight=random.nextInt(array.images1.length); //генерируем случайное число от 0 до 9
                        //Цикл проверяющий равенство чисел - Начало
                        while(numLeft==numRight){
                            numRight=random.nextInt(array.images1.length);
                        }
                        //Цикл проверяющий равенство чисел - Конец
                        img_right.setImageResource(array.images1[numRight]);  //достаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);  //достаем из массива текст
                        img_right.setEnabled(true);  //разблокируем правую картинку
                    }
                }
                //Условие для касания картинки - конец

                return true;
            }
        });
        //Обрабатываем нажатие на левую картинку - Конец
        //Обрабатываем нажатие на правую картинку - Начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //Условие для касания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){  //если коснулся пальцем
                    img_left.setEnabled(false);  //блокируем левую картинку
                    if (numRight>numLeft){
                        img_right.setImageResource(R.drawable.img_true);

                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                } else if(event.getAction()==MotionEvent.ACTION_UP){   //если убрал палец
                    if (numRight>numLeft){
                        img_right.setImageResource(R.drawable.img_true);
                        if (count<20){
                            count=count+1;
                        }
                        for (int i=0;i<20;i++){
                            TextView tv =findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i=0;i<count;i++){
                            TextView tv =findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if (count>0){
                            if (count==1){
                                count=0;
                            }else{
                                count=count-2;
                            }

                        }
                        for (int i=0;i<19;i++){
                            TextView tv =findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i=0;i<count;i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count==20){  //Выход из уровня
                        musicfon.stop();
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);  //Указывает сохнаненный рекорд времени за 1 уровень
                        array.rezult[0] = save.getInt("array.rezult[0]",0);
                        if (array.rezult[0]>0 && array.rezult[0]<sek)
                        {
                            textdescribtionEnd.setText("Уровень пройден.\nВы справились за "+String.format("%d.%02d", sek / 100, (sek % 100))+"\nЧуть-чуть не хватило до рекорда");
                        }else{
                            array.rezult[0]=sek;
                            textdescribtionEnd.setText("Поздравляю!\nВы справились за "+String.format("%d.%02d", array.rezult[0] / 100, (array.rezult[0] % 100))+"\nЭто новый рекорд!");

                            array.rezult[0] = save.getInt("array.rezult[0]",1);
                            SharedPreferences.Editor editor2 = save.edit();
                            editor2.putInt("array.rezult[0]", sek);
                            editor2.commit();
                        }
                        sekost=50001;
                        preview_img_viktory.setVisibility(View.VISIBLE); //Выводим радостный смайлик
                        main_img_lose.setVisibility(View.GONE);   //Прячем грустный смайлик

                        //SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level>1){
                            //пусто
                        }else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 2);
                            editor.commit();
                        }

                        dialogEnd.show();
                    }else {
                        numLeft=random.nextInt(array.images1.length); //генерируем случайное число от 0 до 9
                        img_left.setImageResource(array.images1[numLeft]);  //достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);  //достаем из массива текст

                        numRight=random.nextInt(array.images1.length); //генерируем случайное число от 0 до 9
                        //Цикл проверяющий равенство чисел - Начало
                        while(numLeft==numRight){
                            numRight=random.nextInt(array.images1.length);
                        }
                        //Цикл проверяющий равенство чисел - Конец
                        img_right.setImageResource(array.images1[numRight]);  //достаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);  //достаем из массива текст
                        img_left.setEnabled(true);  //разблокируем левую картинку
                    }
                }
                //Условие для касания картинки - конец

                return true;
            }
        });
        //Обрабатываем нажатие на правую картинку - Конец


    }
    //системная кнопка Назад - начало
    @Override
    public void onBackPressed(){
        if (!musicotschet.isPlaying())
        {
            try
            {
                musicfon.stop();
                sekost=50001;
                Intent intent = new Intent(Level1.this, GameLevels.class);
                startActivity(intent);
                finish();
            } catch (Exception e)
            {

            }
        }
    }
    //системная кнопка Назад - конец

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicfon.stop();
        sekost=50001;
    }
}
