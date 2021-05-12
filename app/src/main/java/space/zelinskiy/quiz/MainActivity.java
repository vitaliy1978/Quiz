package space.zelinskiy.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView mainImg = (ImageView)findViewById(R.id.main_img);
        final ImageView head2 = (ImageView)findViewById(R.id.imageHead2);
        final ImageView head1 = (ImageView)findViewById(R.id.imageHead1);

        final Animation a = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha3);
        final Animation a2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove1);
        head1.startAnimation(a2);
        mainImg.startAnimation(a);
        head2.startAnimation(a);
        final Animation a3 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphamove2);
        a3.setStartOffset(460);
       // head1.startAnimation(a3);
        head2.startAnimation(a3);

        Button buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
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
            return;
        } else{
            backToast = Toast.makeText(getBaseContext(),getString(R.string.btnBacktwice),Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime=System.currentTimeMillis();
    }
    //системная кнопка Назад - конец

}
