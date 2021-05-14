package space.zelinskiy.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    MediaPlayer headfly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE); //Указываем сохраненные данные
        final boolean voiceof = save.getBoolean("voiceof", false);  //берем данные о включенности звуков

        final ImageView mainImg = (ImageView)findViewById(R.id.main_img);
        final ImageView head2 = (ImageView)findViewById(R.id.imageHead2);
        final ImageView head1 = (ImageView)findViewById(R.id.imageHead1);
        final ImageView head1light = (ImageView)findViewById(R.id.imageHead1light);
        final ImageView imageIntro1 = (ImageView)findViewById(R.id.imageIntro1);
        final ImageView imageIntro2 = (ImageView)findViewById(R.id.imageIntro2);
        final ImageView imageIntro3 = (ImageView)findViewById(R.id.imageIntro3);
        final ImageView imageIntro4 = (ImageView)findViewById(R.id.imageIntro4);
        final ImageView imageIntro5 = (ImageView)findViewById(R.id.imageIntro5);
        final ImageView imageIntro6 = (ImageView)findViewById(R.id.imageIntro6);
        final ImageView imageIntro7 = (ImageView)findViewById(R.id.imageIntro7);
        final ImageView imageIntro8 = (ImageView)findViewById(R.id.imageIntro8);
        final ImageView imageIntro9 = (ImageView)findViewById(R.id.imageIntro9);
        final ImageView imageIntro10 = (ImageView)findViewById(R.id.imageIntro10);
        final ImageView imageIntro11 = (ImageView)findViewById(R.id.imageIntro11);
        final ImageView imageIntro12 = (ImageView)findViewById(R.id.imageIntro12);

        final Animation a = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha3);
        final Animation a5 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha4);
        final Animation a2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove1);
        final Animation a4 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove3);
        final Animation aIntro1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro1);
        final Animation aIntro2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro2);
        final Animation aIntro3 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro3);
        final Animation aIntro4 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro4);
        final Animation aIntro5 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro5);
        final Animation aIntro6 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro6);
        final Animation aIntro7 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro7);
        final Animation aIntro8 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro8);
        final Animation aIntro9 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro9);
        final Animation aIntro10 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro10);
        final Animation aIntro11 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro11);
        final Animation aIntro12 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove_intro12);

        head1light.setVisibility(View.INVISIBLE);

        imageIntro1.setVisibility(View.VISIBLE);
        imageIntro1.startAnimation(aIntro1);
        imageIntro1.setVisibility(View.INVISIBLE);

        aIntro2.setStartOffset(1300);
        imageIntro2.setVisibility(View.VISIBLE);
        imageIntro2.startAnimation(aIntro2);
        imageIntro2.setVisibility(View.INVISIBLE);

        aIntro3.setStartOffset(2350);
        imageIntro3.setVisibility(View.VISIBLE);
        imageIntro3.startAnimation(aIntro3);
        imageIntro3.setVisibility(View.INVISIBLE);

        aIntro4.setStartOffset(3350);
        imageIntro4.setVisibility(View.VISIBLE);
        imageIntro4.startAnimation(aIntro4);
        imageIntro4.setVisibility(View.INVISIBLE);

        aIntro5.setStartOffset(4150);
        imageIntro5.setVisibility(View.VISIBLE);
        imageIntro5.startAnimation(aIntro5);
        imageIntro5.setVisibility(View.INVISIBLE);

        aIntro6.setStartOffset(4750);
        imageIntro6.setVisibility(View.VISIBLE);
        imageIntro6.startAnimation(aIntro6);
        imageIntro6.setVisibility(View.INVISIBLE);

        aIntro7.setStartOffset(5250);
        imageIntro7.setVisibility(View.VISIBLE);
        imageIntro7.startAnimation(aIntro7);
        imageIntro7.setVisibility(View.INVISIBLE);

        aIntro8.setStartOffset(5650);
        imageIntro8.setVisibility(View.VISIBLE);
        imageIntro8.startAnimation(aIntro8);
        imageIntro8.setVisibility(View.INVISIBLE);

        aIntro9.setStartOffset(5950);
        imageIntro9.setVisibility(View.VISIBLE);
        imageIntro9.startAnimation(aIntro9);
        imageIntro9.setVisibility(View.INVISIBLE);

        aIntro10.setStartOffset(6150);
        imageIntro10.setVisibility(View.VISIBLE);
        imageIntro10.startAnimation(aIntro10);
        imageIntro10.setVisibility(View.INVISIBLE);

        aIntro11.setStartOffset(6350);
        imageIntro11.setVisibility(View.VISIBLE);
        imageIntro11.startAnimation(aIntro11);
        imageIntro11.setVisibility(View.INVISIBLE);

        aIntro12.setStartOffset(6550);
        imageIntro12.setVisibility(View.VISIBLE);
        imageIntro12.startAnimation(aIntro12);
        imageIntro12.setVisibility(View.INVISIBLE);

        a2.setStartOffset(6550);
        a4.setStartOffset(6550);
        head1.startAnimation(a2);
        head1light.startAnimation(a4);
        head1light.setVisibility(View.INVISIBLE);
        mainImg.startAnimation(a);
        final Animation a3 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove2);
        a3.setStartOffset(6970);
        head2.startAnimation(a3);
        headfly = MediaPlayer.create(this,R.raw.headfly);
        if (voiceof==false) {
            headfly.start();
        }

        Button buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    headfly.stop();
                    Intent intent = new Intent(MainActivity.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });

         TextView buttonOption = (TextView) findViewById(R.id.buttonOption);
        buttonOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    headfly.stop();
                    Intent intent = new Intent(MainActivity.this,OptionHelp.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
    //системная кнопка Назад - начало
    @Override
    public void onBackPressed() {
        if (backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            headfly.stop();
            return;
        } else{
            backToast = Toast.makeText(getBaseContext(),getString(R.string.btnBacktwice),Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime=System.currentTimeMillis();
    }
    //системная кнопка Назад - конец

    protected void onDestroy() {
        super.onDestroy();
        headfly.stop();
     }
}
