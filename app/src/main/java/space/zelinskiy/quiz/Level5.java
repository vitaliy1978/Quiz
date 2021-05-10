package space.zelinskiy.quiz;

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

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level5 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;  //Переменная для левой картинки + текст
    public int numRight;  //Переменная для правой картинки + текст
    Array array = new Array(); //Создали новый оъект из класса Array
    Random random = new Random(); //для генерации случайных чисел
    public int count =0;  //Счетчик правильных ответов
    MediaPlayer musicfon, musicotschet, fanfary1, fanfary2, timeend;
    public int sek=0, sekost=0;  //подсчет секунд и подсчет секунд для остановки после превышения
    public int numlev, start=0;
    int min=0, max=0;  //для диапазона в котором будет генерироваться случайное число.
    public int middleResult=0, formiddle=0;  //среднее время за все уровни




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE); //Указываем сохраненные данные
        final boolean muzof = save.getBoolean("muzof", false);  //берем данные о вкдюченности музыки
        final boolean voiceof = save.getBoolean("voiceof", false);  //берем данные о включенности звуков

        Intent intent =getIntent();  //получить intent
        numlev  = intent.getIntExtra("numlev",1);  //метод getStringExtra читает строку
//        Intent intent2 =getIntent();  //получить intent
//        formiddle  = intent2.getIntExtra("formiddle",1);  //метод getStringExtra читает строку

       // final int last = save.getInt("lastStr",0);
        final int last =0;

        TextView text_levels = (TextView)findViewById(R.id.text_levels);
        text_levels.setText(getString(R.string.wordlevel)+" "+Integer.toString(numlev));
      //  text_levels.setText(getString(R.string.wordlevel)+" "+Integer.toString(last));

        final int level = save.getInt("Level", 1);

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
        array.rezult[22] = save.getInt("arrayRezult 22",0);

        final int[] prevImg={R.drawable.preview_img_one,R.drawable.preview_img_two,R.drawable.preview_img_three,R.drawable.preview_img4,
                R.drawable.preview_img5,R.drawable.preview_img6,R.drawable.preview_img7,R.drawable.preview_img8,R.drawable.preview_img9,
                R.drawable.preview_img10,R.drawable.preview_img11,R.drawable.preview_img12,R.drawable.preview_img13,R.drawable.preview_img14,
                R.drawable.preview_img15,R.drawable.preview_img16,R.drawable.preview_img17,R.drawable.preview_img18,R.drawable.preview_img19,
                R.drawable.preview_img20,R.drawable.preview_img21,R.drawable.preview_img22};
        final int[] descrip1={R.string.levelone,R.string.leveltwo,R.string.levelthree,R.string.levelfour,
                R.string.levelfive,R.string.levelsix,R.string.levelseven,R.string.leveleight,R.string.levelnine,R.string.levelten,R.string.leveleleven,
                R.string.leveltwelve,R.string.levelthirteen,R.string.levelfourteen,R.string.levelfifteen,R.string.levelsixteen,R.string.levelseventeen,R.string.leveleighteen,
                R.string.levelnineteen,R.string.leveltwenty,R.string.leveltwentyone,R.string.leveltwentytwo};
        final int[] descrip2={R.string.levelone2,R.string.leveltwo2,R.string.levelthree2,
                R.string.levelfour2,R.string.levelfive2,R.string.levelsix2,R.string.levelseven2,R.string.leveleight2,
                R.string.levelnine2,R.string.levelten2,R.string.leveleleven2,R.string.leveltwelve2,R.string.levelthirteen2,R.string.levelfourteen2,
                R.string.levelfifteen2,R.string.levelsixteen2,R.string.levelseventeen2,R.string.leveleighteen2,R.string.levelnineteen2,R.string.leveltwenty2,
                R.string.leveltwentyone2,R.string.leveltwentytwo2};
        final int[][] masOfImgMas ={{array.images1[0],array.images1[1],array.images1[2],array.images1[3],array.images1[4],array.images1[5],
                array.images1[6],array.images1[7],array.images1[8],array.images1[9]},
                {array.images2[0],array.images2[1],array.images2[2],array.images2[3],array.images2[4],array.images2[5],
                        array.images2[6],array.images2[7],array.images2[8],array.images2[9]},
                {array.images3[0],array.images3[1],array.images3[2],array.images3[3],array.images3[4],array.images3[5],
                        array.images3[6],array.images3[7],array.images3[8],array.images3[9],array.images3[10],array.images3[11],array.images3[12],
                        array.images3[13],array.images3[14],array.images3[15],array.images3[16],array.images3[17],array.images3[18],array.images3[19],
                        array.images3[20],array.images3[21],array.images3[22],array.images3[23],array.images3[24],array.images3[25]},
                {array.images4[0],array.images4[1],array.images4[2],array.images4[3],array.images4[4],array.images4[5],array.images4[6],
                        array.images4[7],array.images4[8],array.images4[9],array.images4[10],array.images4[11],array.images4[12],array.images4[13],
                        array.images4[14],array.images4[15],array.images4[16],array.images4[17],array.images4[18],array.images4[19]},
                {array.images5[0],array.images5[1],array.images5[2],array.images5[3],array.images5[4],array.images5[5],array.images5[6],
                        array.images5[7],array.images5[8],array.images5[9],array.images5[10],array.images5[11],array.images5[12],array.images5[13],
                        array.images5[14],array.images5[15]},
                {array.images6[0],array.images6[1],array.images6[2],array.images6[3],array.images6[4],array.images6[5],array.images6[6],
                        array.images6[7],array.images6[8],array.images6[9],array.images6[10],array.images6[11],array.images6[12],
                        array.images6[13], array.images6[14], array.images6[15], array.images6[16], array.images6[17], array.images6[18],
                        array.images6[19], array.images6[20]},
                {array.images7[0],array.images7[1],array.images7[2],array.images7[3],array.images7[4],array.images7[5],array.images7[6],
                        array.images7[7], array.images7[8],  array.images7[9],  array.images7[10], array.images7[11], array.images7[12],
                        array.images7[13],array.images7[14]},
                {array.images8[0],array.images8[1],array.images8[2],array.images8[3],array.images8[4],array.images8[5],array.images8[6],
                        array.images8[7], array.images8[8],  array.images8[9],  array.images8[10], array.images8[11], array.images8[12],
                        array.images8[13],array.images8[14],array.images8[15],array.images8[16],array.images8[17],array.images8[18],
                        array.images8[19],array.images8[20],array.images8[21],array.images8[22], array.images8[23]},
                {array.images9[0],array.images9[1],array.images9[2],array.images9[3],array.images9[4],array.images9[5],array.images9[6],
                        array.images9[7], array.images9[8],  array.images9[9],  array.images9[10], array.images9[11], array.images9[12],
                        array.images9[13],array.images9[14],array.images9[15],array.images9[16],array.images9[17],array.images9[18]},
                {array.images10[0],array.images10[1],array.images10[2],array.images10[3],array.images10[4],array.images10[5],array.images10[6],
                        array.images10[7], array.images10[8],  array.images10[9],  array.images10[10], array.images10[11], array.images10[12],
                        array.images10[13],array.images10[14],array.images10[15],array.images10[16],array.images10[17],array.images10[18],
                        array.images10[19],array.images10[20]},
                {array.images11[0],array.images11[1],array.images11[2],array.images11[3],array.images11[4],array.images11[5],array.images11[6],
                        array.images11[7], array.images11[8],  array.images11[9],  array.images11[10], array.images11[11], array.images11[12],
                        array.images11[13],array.images11[14],array.images11[15],array.images11[16],array.images11[17],array.images11[18],
                        array.images11[19],array.images11[20],array.images11[21],array.images11[22],array.images11[23],array.images11[24]},
                {array.images12[0],array.images12[1],array.images12[2],array.images12[3],array.images12[4],array.images12[5],array.images12[6],
                        array.images12[7], array.images12[8],  array.images12[9],  array.images12[10], array.images12[11], array.images12[12],
                        array.images12[13],array.images12[14],array.images12[15],array.images12[16],array.images12[17],array.images12[18],
                        array.images12[19]},
                {array.images13[0],array.images13[1],array.images13[2],array.images13[3],array.images13[4],array.images13[5],array.images13[6],
                        array.images13[7], array.images13[8],  array.images13[9],  array.images13[10], array.images13[11], array.images13[12],
                        array.images13[13]},
                {array.images14[0],array.images14[1],array.images14[2],array.images14[3],array.images14[4],array.images14[5],array.images14[6],
                        array.images14[7]},
                {array.images15[0],array.images15[1],array.images15[2],array.images15[3],array.images15[4],array.images15[5],array.images15[6],
                        array.images15[7], array.images15[8],array.images15[9],array.images15[10],array.images15[11],array.images15[12],
                        array.images15[13],array.images15[14],array.images15[15],array.images15[16],array.images15[17],array.images15[18],
                        array.images15[19],array.images15[20],array.images15[21],array.images15[22]},
                {array.images16[0],array.images16[1],array.images16[2],array.images16[3],array.images16[4],array.images16[5],array.images16[6],
                        array.images16[7], array.images16[8],array.images16[9],array.images16[10],array.images16[11],array.images16[12],
                        array.images16[13],array.images16[14],array.images16[15],array.images16[16],array.images16[17],array.images16[18],
                        array.images16[19],array.images16[20],array.images16[21],array.images16[22],array.images16[23],array.images16[24],
                        array.images16[25],array.images16[26],array.images16[27],array.images16[28]},
                {array.images17[0],array.images17[1],array.images17[2],array.images17[3],array.images17[4],array.images17[5],array.images17[6],
                        array.images17[7], array.images17[8],array.images17[9],array.images17[10],array.images17[11],array.images17[12],
                        array.images17[13],array.images17[14],array.images17[15],array.images17[16],array.images17[17],array.images17[18],
                        array.images17[19],array.images17[20],array.images17[21],array.images17[22]},
                {array.images18[0],array.images18[1],array.images18[2],array.images18[3],array.images18[4],array.images18[5],array.images18[6],
                        array.images18[7], array.images18[8],array.images18[9],array.images18[10],array.images18[11],array.images18[12],
                        array.images18[13],array.images18[14],array.images18[15],array.images18[16],array.images18[17],array.images18[18],
                        array.images18[19],array.images18[20],array.images18[21],array.images18[22],array.images18[23],array.images18[24],
                        array.images18[25],array.images18[26],array.images18[27],array.images18[28],array.images18[29],array.images18[30],
                        array.images18[31],array.images18[32],array.images18[33],array.images18[34],array.images18[35],array.images18[36],
                        array.images18[37],array.images18[38]},
                {array.images19[0], array.images19[1], array.images19[2], array.images19[3], array.images19[4], array.images19[5], array.images19[6],
                        array.images19[7], array.images19[8], array.images19[9], array.images19[10], array.images19[11], array.images19[12],
                        array.images19[13], array.images19[14], array.images19[15], array.images19[16], array.images19[17], array.images19[18],
                        array.images19[19], array.images19[20], array.images19[21], array.images19[22], array.images19[23], array.images19[24],
                        array.images19[25], array.images19[26], array.images19[27], array.images19[28], array.images19[29], array.images19[30],
                        array.images19[31], array.images19[32], array.images19[33], array.images19[34], array.images19[35], array.images19[36],
                        array.images19[37], array.images19[38], array.images19[39], array.images19[40]},
                {array.images20[0], array.images20[1], array.images20[2], array.images20[3], array.images20[4], array.images20[5], array.images20[6],
                        array.images20[7], array.images20[8], array.images20[9], array.images20[10], array.images20[11], array.images20[12],
                        array.images20[13], array.images20[14], array.images20[15], array.images20[16], array.images20[17], array.images20[18],
                        array.images20[19], array.images20[20], array.images20[21], array.images20[22], array.images20[23], array.images20[24],
                        array.images20[25]},
                {array.images21[0], array.images21[1], array.images21[2], array.images21[3], array.images21[4], array.images21[5], array.images21[6],
                        array.images21[7], array.images21[8], array.images21[9], array.images21[10], array.images21[11], array.images21[12],
                        array.images21[13], array.images21[14], array.images21[15], array.images21[16], array.images21[17], array.images21[18],
                        array.images21[19], array.images21[20], array.images21[21], array.images21[22], array.images21[23], array.images21[24],
                        array.images21[25], array.images21[26], array.images21[27], array.images21[28], array.images21[29]},
                {array.images22[0], array.images22[1], array.images22[2], array.images22[3], array.images22[4], array.images22[5], array.images22[6],
                        array.images22[7], array.images22[8], array.images22[9], array.images22[10], array.images22[11], array.images22[12],
                        array.images22[13], array.images22[14], array.images22[15], array.images22[16], array.images22[17], array.images22[18],
                        array.images22[19], array.images22[20], array.images22[21], array.images22[22], array.images22[23], array.images22[24],
                        array.images22[25], array.images22[26], array.images22[27], array.images22[28], array.images22[29], array.images22[30],
                        array.images22[31], array.images22[32], array.images22[33], array.images22[34], array.images22[35], array.images22[36],
                        array.images22[37], array.images22[38], array.images22[39]}
        };
        final int[][] masOfTextMas ={{array.texts1[0],array.texts1[1],array.texts1[2],array.texts1[3],array.texts1[4],array.texts1[5],
                array.texts1[6],array.texts1[7],array.texts1[8],array.texts1[9]},
                {array.texts2[0],array.texts2[1],array.texts2[2],array.texts2[3],array.texts2[4],array.texts2[5],
                        array.texts2[6],array.texts2[7],array.texts2[8],array.texts2[9]},
                {array.texts3[0],array.texts3[1],array.texts3[2],array.texts3[3],array.texts3[4],array.texts3[5],
                        array.texts3[6],array.texts3[7],array.texts3[8],array.texts3[9],array.texts3[10],array.texts3[11],array.texts3[12],
                        array.texts3[13],array.texts3[14],array.texts3[15],array.texts3[16],array.texts3[17],array.texts3[18],array.texts3[19],
                        array.texts3[20],array.texts3[21],array.texts3[22],array.texts3[23],array.texts3[24],array.texts3[25]},
                {array.texts4[0],array.texts4[1],array.texts4[2],array.texts4[3],array.texts4[4],array.texts4[5],array.texts4[6],
                        array.texts4[7],array.texts4[8],array.texts4[9],array.texts4[10],array.texts4[11],array.texts4[12],array.texts4[13],
                        array.texts4[14],array.texts4[15],array.texts4[16],array.texts4[17],array.texts4[18],array.texts4[19]},
                {array.texts5[0],array.texts5[1],array.texts5[2],array.texts5[3],array.texts5[4],array.texts5[5],array.texts5[6],
                        array.texts5[7],array.texts5[8],array.texts5[9],array.texts5[10],array.texts5[11],array.texts5[12],array.texts5[13],
                        array.texts5[14],array.texts5[15]},
                {array.texts6[0],array.texts6[1],array.texts6[2],array.texts6[3],array.texts6[4],array.texts6[5],array.texts6[6],
                        array.texts6[7],array.texts6[8],array.texts6[9],array.texts6[10],array.texts6[11],array.texts6[12],array.texts6[13],
                        array.texts6[14],array.texts6[15],array.texts6[16],array.texts6[17],array.texts6[18],array.texts6[19],array.texts6[20]},
                {array.texts7[0],array.texts7[1],array.texts7[2],array.texts7[3],array.texts7[4],array.texts7[5],array.texts7[6],
                        array.texts7[7],array.texts7[8],array.texts7[9],array.texts7[10],array.texts7[11],array.texts7[12],array.texts7[13],
                        array.texts7[14]},
                {array.texts8[0],array.texts8[1],array.texts8[2],array.texts8[3],array.texts8[4],array.texts8[5],array.texts8[6],
                        array.texts8[7],array.texts8[8],array.texts8[9],array.texts8[10],array.texts8[11],array.texts8[12],
                        array.texts8[13],array.texts8[14],array.texts8[15],array.texts8[16],array.texts8[17],array.texts8[18],
                        array.texts8[19],array.texts8[20],array.texts8[21],array.texts8[22],array.texts8[23]},
                {array.texts9[0],array.texts9[1],array.texts9[2],array.texts9[3],array.texts9[4],array.texts9[5],array.texts9[6],
                        array.texts9[7],array.texts9[8],array.texts9[9],array.texts9[10],array.texts9[11],array.texts9[12],
                        array.texts9[13],array.texts9[14],array.texts9[15],array.texts9[16],array.texts9[17],array.texts9[18]},
                {array.texts10[0],array.texts10[1],array.texts10[2],array.texts10[3],array.texts10[4],array.texts10[5],array.texts10[6],
                        array.texts10[7],array.texts10[8],array.texts10[9],array.texts10[10],array.texts10[11],array.texts10[12],
                        array.texts10[13],array.texts10[14],array.texts10[15],array.texts10[16],array.texts10[17],array.texts10[18],
                        array.texts10[19],array.texts10[20]},
                {array.texts11[0],array.texts11[1],array.texts11[2],array.texts11[3],array.texts11[4],array.texts11[5],array.texts11[6],
                        array.texts11[7],array.texts11[8],array.texts11[9],array.texts11[10],array.texts11[11],array.texts11[12],
                        array.texts11[13],array.texts11[14],array.texts11[15],array.texts11[16],array.texts11[17],array.texts11[18],
                        array.texts11[19],array.texts11[20],array.texts11[21],array.texts11[22],array.texts11[23],array.texts11[24]},
                {array.texts12[0],array.texts12[1],array.texts12[2],array.texts12[3],array.texts12[4],array.texts12[5],array.texts12[6],
                        array.texts12[7],array.texts12[8],array.texts12[9],array.texts12[10],array.texts12[11],array.texts12[12],
                        array.texts12[13],array.texts12[14],array.texts12[15],array.texts12[16],array.texts12[17],array.texts12[18],
                        array.texts12[19]},
                {array.texts13[0],array.texts13[1],array.texts13[2],array.texts13[3],array.texts13[4],array.texts13[5],array.texts13[6],
                        array.texts13[7],array.texts13[8],array.texts13[9],array.texts13[10],array.texts13[11],array.texts13[12],
                        array.texts13[13]},
                {array.texts14[0],array.texts14[1],array.texts14[2],array.texts14[3],array.texts14[4],array.texts14[5],array.texts14[6],
                        array.texts14[7]},
                {array.texts15[0],array.texts15[1],array.texts15[2],array.texts15[3],array.texts15[4],array.texts15[5],array.texts15[6],
                        array.texts15[7],array.texts15[8],array.texts15[9],array.texts15[10],array.texts15[11],array.texts15[12],
                        array.texts15[13],array.texts15[14],array.texts15[15],array.texts15[16],array.texts15[17],array.texts15[18],
                        array.texts15[19],array.texts15[20],array.texts15[21],array.texts15[22]},
                {array.texts16[0],array.texts16[1],array.texts16[2],array.texts16[3],array.texts16[4],array.texts16[5],array.texts16[6],
                        array.texts16[7],array.texts16[8],array.texts16[9],array.texts16[10],array.texts16[11],array.texts16[12],
                        array.texts16[13],array.texts16[14],array.texts16[15],array.texts16[16],array.texts16[17],array.texts16[18],
                        array.texts16[19],array.texts16[20],array.texts16[21],array.texts16[22],array.texts16[23],array.texts16[24],
                        array.texts16[25],array.texts16[26],array.texts16[27],array.texts16[28]},
                {array.texts17[0],array.texts17[1],array.texts17[2], array.texts17[3], array.texts17[4], array.texts17[5], array.texts17[6],
                       array.texts17[7], array.texts17[8], array.texts17[9], array.texts17[10], array.texts17[11], array.texts17[12],
                       array.texts17[13], array.texts17[14], array.texts17[15], array.texts17[16], array.texts17[17], array.texts17[18],
                       array.texts17[19], array.texts17[20], array.texts17[21], array.texts17[22],},
                {array.texts18[0],array.texts18[1],array.texts18[2],array.texts18[3],array.texts18[4],array.texts18[5],array.texts18[6],
                        array.texts18[7],array.texts18[8],array.texts18[9],array.texts18[10],array.texts18[11],array.texts18[12],
                        array.texts18[13],array.texts18[14],array.texts18[15],array.texts18[16],array.texts18[17],array.texts18[18],
                        array.texts18[19],array.texts18[20],array.texts18[21],array.texts18[22],array.texts18[23],array.texts18[24],
                        array.texts18[25],array.texts18[26],array.texts18[27],array.texts18[28],array.texts18[29],array.texts18[30],
                        array.texts18[31],array.texts18[32],array.texts18[33],array.texts18[34],array.texts18[35],array.texts18[36],
                        array.texts18[37],array.texts18[38]},
                {array.texts19[0], array.texts19[1], array.texts19[2], array.texts19[3], array.texts19[4], array.texts19[5], array.texts19[6],
                        array.texts19[7], array.texts19[8], array.texts19[9], array.texts19[10], array.texts19[11], array.texts19[12],
                        array.texts19[13], array.texts19[14], array.texts19[15], array.texts19[16], array.texts19[17], array.texts19[18],
                        array.texts19[19], array.texts19[20], array.texts19[21], array.texts19[22], array.texts19[23], array.texts19[24],
                        array.texts19[25], array.texts19[26], array.texts19[27], array.texts19[28], array.texts19[29], array.texts19[30],
                        array.texts19[31], array.texts19[32], array.texts19[33], array.texts19[34], array.texts19[35], array.texts19[36],
                        array.texts19[37], array.texts19[38], array.texts19[39], array.texts19[40]},
                {array.texts20[0], array.texts20[1], array.texts20[2], array.texts20[3], array.texts20[4], array.texts20[5], array.texts20[6],
                        array.texts20[7], array.texts20[8], array.texts20[9], array.texts20[10], array.texts20[11], array.texts20[12],
                        array.texts20[13], array.texts20[14], array.texts20[15], array.texts20[16], array.texts20[17], array.texts20[18],
                        array.texts20[19], array.texts20[20], array.texts20[21], array.texts20[22], array.texts20[23], array.texts20[24],
                        array.texts20[25]},
                {array.texts21[0], array.texts21[1], array.texts21[2], array.texts21[3], array.texts21[4], array.texts21[5], array.texts21[6],
                        array.texts21[7], array.texts21[8], array.texts21[9], array.texts21[10], array.texts21[11], array.texts21[12],
                        array.texts21[13], array.texts21[14], array.texts21[15], array.texts21[16], array.texts21[17], array.texts21[18],
                        array.texts21[19], array.texts21[20], array.texts21[21], array.texts21[22], array.texts21[23], array.texts21[24],
                        array.texts21[25], array.texts21[26], array.texts21[27], array.texts21[28], array.texts21[29]},
                {array.texts22[0], array.texts22[1], array.texts22[2], array.texts22[3], array.texts22[4], array.texts22[5], array.texts22[6],
                        array.texts22[7], array.texts22[8], array.texts22[9], array.texts22[10], array.texts22[11], array.texts22[12],
                        array.texts22[13], array.texts22[14], array.texts22[15], array.texts22[16], array.texts22[17], array.texts22[18],
                        array.texts22[19], array.texts22[20], array.texts22[21], array.texts22[22], array.texts22[23], array.texts22[24],
                        array.texts22[25], array.texts22[26], array.texts22[27], array.texts22[28], array.texts22[29], array.texts22[30],
                        array.texts22[31], array.texts22[32], array.texts22[33], array.texts22[34], array.texts22[35], array.texts22[36],
                        array.texts22[37], array.texts22[38], array.texts22[39]},
        };

        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_left.setClipToOutline(true);//код, который скругляет углы
        img_right.setClipToOutline(true);//код, который скругляет углы

        final TextView text_left = findViewById(R.id.text_left);  //Путь к левой TextView
        final TextView text_right = findViewById(R.id.text_right);  //Путь к правой TextView
        final TextView text_otschet = findViewById(R.id.text_otschet); //Путь к индикатору отсчета перед игрой
        final TextView text_time = findViewById(R.id.text_time); //Путь к индикатору секунд в игре
        final Button button_back = (Button) findViewById(R.id.button_back); //Путь к кнопке Назад
        if (numlev == 5 || numlev == 6 || numlev == 7 || numlev == 8 || numlev == 9 || numlev == 10 || numlev == 11 || numlev == 13 || numlev == 15 || numlev == 16
                || numlev == 17 || numlev == 18 || numlev == 19 || numlev == 20 || numlev == 22) {
            text_left.setTextSize((float) (text_left.getTextSize() * 0.28));  //Уменьшаем шрифт подписей картинок
            text_right.setTextSize((float) (text_right.getTextSize() * 0.28));  //Уменьшаем шрифт подписей картинок
        }
        if (numlev == 21) {
            text_left.setTextSize((float) (text_left.getTextSize() * 0.23));  //Уменьшаем шрифт подписей картинок
            text_right.setTextSize((float) (text_right.getTextSize() * 0.23));  //Уменьшаем шрифт подписей картинок
        }
        musicfon = MediaPlayer.create(this, R.raw.musicfon);
        musicotschet = MediaPlayer.create(this, R.raw.musicotschet);
        fanfary1 = MediaPlayer.create(this, R.raw.fanfary1);
        fanfary2 = MediaPlayer.create(this, R.raw.fanfary2);
        timeend = MediaPlayer.create(this, R.raw.time_end);

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
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        dialog.setCancelable(false);  //окно нельзя открыть кнопкой назад

        //Устанавливаем картинку в диалоговое окно - Начало
        ImageView preview_img =(ImageView)dialog.findViewById(R.id.preview_img);
        preview_img.setImageResource(prevImg[numlev-1]);
        //Устанавливаем картинку в диалоговое окно - Конец

        //Устанавливаем описание задания - Начало
        TextView text_description = (TextView)dialog.findViewById(R.id.text_description);
        TextView text_description2 = (TextView)dialog.findViewById(R.id.text_description2);
        text_description.setText(descrip1[numlev-1]);
        text_description2.setText(descrip2[numlev-1]);
        //Устанавливаем описание задания - Конец

        // Кнопка которая закрывает диалоговое окно - Начало
        TextView button_close = (TextView) dialog.findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level5.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialog.dismiss();  //закрываем диалоговое окно
            }
        });
        // Кнопка которая закрывает диалоговое окно - Конец

        //Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level5.this,R.anim.alpha);
        final Animation a2 = AnimationUtils.loadAnimation(Level5.this,R.anim.alpha2);
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
                    Intent intent = new Intent(Level5.this,GameLevels.class);
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
                if (numlev<22) {
                    try {
                        Intent intent = new Intent(Level5.this, Level5.class);
                        intent.putExtra("numlev", numlev + 1);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {

                    }
               }
                else{
                    try {
                        Intent intent = new Intent(Level5.this, GameLevels.class);
                        intent.putExtra("numlev", numlev);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {

                    }
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

                CountDownTimer myTimer = new CountDownTimer(2790, 900) {
                    @Override
                    public void onTick(long millisUntilFinished)
                    {
                        text_otschet.setVisibility(View.VISIBLE);
                        if (voiceof==false) {
                            musicotschet.start();
                        }
                        if (millisUntilFinished<=3100)
                        {
                            text_otschet.setText(Long.toString(millisUntilFinished / 900));
                            text_otschet.startAnimation(a2);
                        }
                    }

                    @Override
                    public void onFinish()
                    {
                        text_otschet.setText("");
                        text_otschet.setVisibility(View.GONE);
                        musicotschet.stop();
                        start=1;
                        if (muzof==false) {
                            musicfon.start();
                        }
                        text_otschet.setText("");
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
                        while ((!Thread.interrupted()) && (sekost < 12000)) {
                            if (start==1) {
                                sek++;
                                sekost++;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        text_time.setText(String.format("%d.%02d", sek / 100, (sek % 100)));
                                        if (sekost >= 12000 && sekost<12500) {
                                            musicfon.stop();
                                            if (voiceof==false) {
                                                timeend.start();
                                            }
                                            start=0;
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
                    fanfary1.stop();
                    fanfary2.stop();
                    timeend.stop();
                    start=0;
                    sekost=50001;
                    Intent intent = new Intent(Level5.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        //Кнопка Назад - Конец

        //Массив для прогресса игры - Начало
        final int[] progress ={
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15, R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20};
        //Массив для прогресса игры - Конец

        numLeft=random.nextInt(masOfImgMas[numlev-1].length); //генерируем случайное число от 0 до 9
        img_left.setImageResource(masOfImgMas[numlev-1][numLeft]);  //достаем из массива картинку
        text_left.setText(masOfTextMas[numlev-1][numLeft]);  //достаем из массива текст
if (numlev!=1 && numlev!=2 && numlev!=3 && numlev!=4 && numlev!=13 && numlev!=14) {
    if (numLeft <= 5) {
        min = 0;
        max = 8;
        numRight = random.nextInt(max);
        while (numLeft == numRight) {
            numRight = random.nextInt(max);
        }
    }
    if (numLeft >= masOfImgMas[numlev - 1].length - 5) {
        min = masOfImgMas[numlev-1].length - 8;
        max = masOfImgMas[numlev - 1].length;
        numRight = random.nextInt(max - min)+min;
        while (numLeft == numRight) {
            numRight = random.nextInt(max);
       }
    }
    if (numLeft > 5 && numLeft < masOfImgMas[numlev - 1].length - 5) {
        min = numLeft - 6;
        max = numLeft + 6;
        numRight = random.nextInt(max-min)+min; //генерируем случайное число от 0 до 9
        //Цикл проверяющий равенство чисел - Начало
        while (numLeft == numRight) {
          numRight = min+random.nextInt(max-min); //генерируем случайное число от 0 до 9
       }
    }
}else{
    numRight = random.nextInt(masOfImgMas[numlev - 1].length); //генерируем случайное число от 0 до 9
    //Цикл проверяющий равенство чисел - Начало
    while (numLeft == numRight) {
        numRight = random.nextInt(masOfImgMas[numlev - 1].length);
    }
}
         //Цикл проверяющий равенство чисел - Конец
        img_right.setImageResource(masOfImgMas[numlev-1][numRight]);  //достаем из массива картинку
        text_right.setText(masOfTextMas[numlev-1][numRight]);  //достаем из массива текст

        //Обрабатываем нажатие на левую картинку - Начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //Условие для касания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){  //если коснулся пальцем
                    img_right.setEnabled(false);  //блокируем правую картинку
                    if (numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);
                        img_left.setBackgroundResource(R.drawable.style_img_universal_green);

                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                        img_left.setBackgroundResource(R.drawable.style_img_universal_red);
                    }
                } else if(event.getAction()==MotionEvent.ACTION_UP){   //если убрал палец
                    img_left.setBackgroundResource(R.drawable.style_img_universal);
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
                        img_left.setBackgroundResource(R.drawable.style_img_universal);
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
                        start=0;
                      //  img_left.setImageResource(masOfImgMas[numlev-1][numLeft]);
                        img_left.setVisibility(View.INVISIBLE);
                        img_right.setVisibility(View.INVISIBLE);
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);  //Указывает сохнаненный рекорд времени за 1 уровень
                        String arrayRezult = "arrayRezult"+" "+Integer.toString(numlev-1);
                        array.rezult[numlev-1] = save.getInt(arrayRezult.toString(),0);
                        if (array.rezult[numlev-1]>0 && array.rezult[numlev-1]<sek)
                        {
                            if (voiceof==false) {
                                fanfary1.start();
                            }
                            if (numlev<22 || last==1){
                               // textdescribtionEnd.setText("Уровень пройден.\nВы справились за "+String.format("%d.%02d", sek / 100, (sek % 100))+"\nЧуть-чуть не хватило до рекорда");
                                for (int i = 0; i < level; i++) {
                                    middleResult = middleResult + array.rezult[i];
                                    if (array.rezult[i]>0){
                                        formiddle++;
                                    }
                                }
                                middleResult = middleResult / formiddle;
                                textdescribtionEnd.setText("Поздравляю!\nВы справились за " + String.format("%d.%02d", sek / 100, (sek % 100))
                                        +"\nДобро пожаловать в список лидеров."+"\nВаше среднее время за все уровни: "+String.format("%d.%02d", middleResult / 100, (middleResult % 100))
                                        +"\nВы можете пройти любой уровень ещё раз и улучшить ваш результат.");
                           }
                            if (numlev>=22 && last==0){
                                btn_continue2.setVisibility(View.INVISIBLE);
                                for (int i = 0; i < level; i++) {
                                    middleResult = middleResult + array.rezult[i];
                                    if (array.rezult[i]>0){
                                        formiddle++;
                                    }
                                }
                                middleResult = middleResult / formiddle;
                                textdescribtionEnd.setText("Поздравляю!\nВы справились за " + String.format("%d.%02d", sek / 100, (sek % 100))
                                        +"\nДобро пожаловать в список лидеров."+"\nВаше среднее время за все уровни: "+String.format("%d.%02d", middleResult / 100, (middleResult % 100))
                                        +"\nВы можете пройти любой уровень ещё раз и улучшить ваш результат.");
                                SharedPreferences.Editor editor3 = save.edit();
                                editor3.putInt("lastStr".toString(), 1);
                                editor3.commit();
                            }
                        }else{
                            if (voiceof==false) {
                                fanfary2.start();
                            }
                            array.rezult[numlev-1]=sek;
                            if (numlev<22 || last==1) {
                              //  textdescribtionEnd.setText("Поздравляю!\nВы справились за " + String.format("%d.%02d", array.rezult[numlev - 1] / 100, (array.rezult[numlev - 1] % 100)) + "\nЭто новый рекорд!");
                                for (int i = 0; i < level; i++) {
                                    middleResult = middleResult + array.rezult[i];
                                    if (array.rezult[i]>0){
                                        formiddle++;
                                    }
                                }
                                middleResult = middleResult / formiddle;
                                textdescribtionEnd.setText("Поздравляю!\nВы справились за " + String.format("%d.%02d", sek / 100, (sek % 100))
                                        +"\nДобро пожаловать в список лидеров."+"\nВаше среднее время за все уровни: "+String.format("%d.%02d", middleResult / 100, (middleResult % 100))
                                        +"\nВы можете пройти любой уровень ещё раз и улучшить ваш результат.");
                            }
                            if (numlev>=22 && last==0){
                                btn_continue2.setVisibility(View.INVISIBLE);
                                for (int i = 0; i < level; i++) {
                                    middleResult = middleResult + array.rezult[i];
                                    if (array.rezult[i]>0){
                                        formiddle++;
                                    }
                                }
                                middleResult = middleResult / formiddle;
                                textdescribtionEnd.setText("Поздравляю!\nВы справились за " + String.format("%d.%02d", sek / 100, (sek % 100))
                                +"\nДобро пожаловать в список лидеров."+"\nВаше среднее время за все уровни: "+String.format("%d.%02d", middleResult / 100, (middleResult % 100))+"\nВы можете пройти любой уровень ещё раз и улучшить ваш результат.");
                                SharedPreferences.Editor editor3 = save.edit();
                                editor3.putInt("lastStr".toString(), 1);
                                editor3.commit();
                            }

                            array.rezult[numlev-1] = save.getInt(arrayRezult.toString(),1);
                            SharedPreferences.Editor editor4 = save.edit();
                            editor4.putInt(arrayRezult.toString(), sek);
                            editor4.commit();
                        }
                        sekost=50001;
                        preview_img_viktory.setVisibility(View.VISIBLE); //Выводим радостный смайлик
                        main_img_lose.setVisibility(View.GONE);   //Прячем грустный смайлик

                        final int level = save.getInt("Level", 1);
                        if (level>numlev){
                            //пусто
                        }
                        if (level<=numlev && numlev<22){
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", numlev+1);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else {
                        numLeft=random.nextInt(masOfImgMas[numlev-1].length); //генерируем случайное число от 0 до 9
                        img_left.setImageResource(masOfImgMas[numlev-1][numLeft]);  //достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(masOfTextMas[numlev-1][numLeft]);  //достаем из массива текст

                        if (numlev!=1 && numlev!=2 && numlev!=3 && numlev!=4 && numlev!=13 && numlev!=14) {
                            if (numLeft <= 5) {
                                min = 0;
                                max = 8;
                                numRight = random.nextInt(max);
                                while (numLeft == numRight) {
                                    numRight = random.nextInt(max);
                                }
                            }
                            if (numLeft >= masOfImgMas[numlev - 1].length - 5) {
                                min = masOfImgMas[numlev-1].length - 8;
                                max = masOfImgMas[numlev - 1].length;
                                numRight = random.nextInt(max - min)+min;
                                while (numLeft == numRight) {
                                    numRight = random.nextInt(max);
                                }
                            }
                            if (numLeft > 5 && numLeft < masOfImgMas[numlev - 1].length - 5) {
                                min = numLeft - 6;
                                max = numLeft + 6;
                                numRight = random.nextInt(max-min)+min; //генерируем случайное число от 0 до 9
                                //Цикл проверяющий равенство чисел - Начало
                                while (numLeft == numRight) {
                                    numRight = min+random.nextInt(max-min); //генерируем случайное число от 0 до 9
                                }
                            }
                        }else{
                            numRight = random.nextInt(masOfImgMas[numlev - 1].length); //генерируем случайное число от 0 до 9
                            //Цикл проверяющий равенство чисел - Начало
                            while (numLeft == numRight) {
                                numRight = random.nextInt(masOfImgMas[numlev - 1].length);
                            }
                        }

                        //Цикл проверяющий равенство чисел - Конец
                        img_right.setImageResource(masOfImgMas[numlev-1][numRight]);  //достаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(masOfTextMas[numlev-1][numRight]);  //достаем из массива текст
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
                        img_right.setBackgroundResource(R.drawable.style_img_universal_green);

                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                        img_right.setBackgroundResource(R.drawable.style_img_universal_red);
                    }
                } else if(event.getAction()==MotionEvent.ACTION_UP){   //если убрал палец
                    img_right.setBackgroundResource(R.drawable.style_img_universal);
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
                        img_right.setBackgroundResource(R.drawable.style_img_universal);
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
                        start=0;
                       // img_right.setImageResource(masOfImgMas[numlev-1][numLeft]);
                        img_left.setVisibility(View.INVISIBLE);
                        img_right.setVisibility(View.INVISIBLE);
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);  //Указывает сохнаненный рекорд времени за 1 уровень
                        String arrayRezult = "arrayRezult"+" "+Integer.toString(numlev-1);
                        array.rezult[numlev-1] = save.getInt(arrayRezult.toString(),0);
                        if (array.rezult[numlev-1]>0 && array.rezult[numlev-1]<sek)
                        {
                            if (voiceof==false) {
                                fanfary1.start();
                            }
                            if (numlev<22 || last==1){
                             //   textdescribtionEnd.setText("Уровень пройден.\nВы справились за "+String.format("%d.%02d", sek / 100, (sek % 100))+"\nЧуть-чуть не хватило до рекорда");
                                for (int i = 0; i < level; i++) {
                                    middleResult = middleResult + array.rezult[i];
                                    if (array.rezult[i]>0){
                                        formiddle++;
                                    }
                                }
                                middleResult = middleResult / formiddle;
                                textdescribtionEnd.setText("Поздравляю!\nВы справились за " + String.format("%d.%02d", sek / 100, (sek % 100))
                                        +"\nДобро пожаловать в список лидеров."+"\nВаше среднее время за все уровни: "+String.format("%d.%02d", middleResult / 100, (middleResult % 100))
                                        +"\nВы можете пройти любой уровень ещё раз и улучшить ваш результат.");
                            }
                            if (numlev>=22 && last==0){
                                btn_continue2.setVisibility(View.INVISIBLE);
                                for (int i = 0; i < level; i++) {
                                    middleResult = middleResult + array.rezult[i];
                                    if (array.rezult[i]>0){
                                        formiddle++;
                                    }
                                }
                                middleResult = middleResult / formiddle;
                                textdescribtionEnd.setText("Поздравляю!\nВы справились за " + String.format("%d.%02d", sek / 100, (sek % 100))
                                        +"\nДобро пожаловать в список лидеров."+"\nВаше среднее время за все уровни: "+String.format("%d.%02d", middleResult / 100, (middleResult % 100))
                                        +"\nВы можете пройти любой уровень ещё раз и улучшить ваш результат.");
                                SharedPreferences.Editor editor3 = save.edit();
                                editor3.putInt("lastStr".toString(), 1);
                                editor3.commit();
                            }
                        }else{
                            if (voiceof==false) {
                                fanfary2.start();
                            }
                            array.rezult[numlev-1]=sek;
                            if (numlev<22 || last==1) {
                             //   textdescribtionEnd.setText("Поздравляю!\nВы справились за " + String.format("%d.%02d", array.rezult[numlev - 1] / 100, (array.rezult[numlev - 1] % 100)) + "\nЭто новый рекорд!");
                                for (int i = 0; i < level; i++) {
                                    middleResult = middleResult + array.rezult[i];
                                    if (array.rezult[i]>0){
                                        formiddle++;
                                    }
                                }
                                middleResult = middleResult / formiddle;
                                textdescribtionEnd.setText("Поздравляю!\nВы справились за " + String.format("%d.%02d", sek / 100, (sek % 100))
                                        +"\nДобро пожаловать в список лидеров."+"\nВаше среднее время за все уровни: "+String.format("%d.%02d", middleResult / 100, (middleResult % 100))
                                        +"\nВы можете пройти любой уровень ещё раз и улучшить ваш результат.");
                            }
                            if (numlev>=22 && last==0){
                                btn_continue2.setVisibility(View.INVISIBLE);
                                for (int i = 0; i < level; i++) {
                                    middleResult = middleResult + array.rezult[i];
                                    if (array.rezult[i]>0){
                                        formiddle++;
                                    }
                                }
                                middleResult = middleResult / formiddle;
                                textdescribtionEnd.setText("Поздравляю!\nВы справились за " + String.format("%d.%02d", sek / 100, (sek % 100))
                                        +"\nДобро пожаловать в список лидеров."+"\nВаше среднее время за все уровни: "+String.format("%d.%02d", middleResult / 100, (middleResult % 100))
                                        +"\nВы можете пройти любой уровень ещё раз и улучшить ваш результат.");
                                SharedPreferences.Editor editor3 = save.edit();
                                editor3.putInt("lastStr".toString(), 1);
                                editor3.commit();
                            }

                            array.rezult[numlev-1] = save.getInt(arrayRezult.toString(),1);
                            SharedPreferences.Editor editor4 = save.edit();
                            editor4.putInt(arrayRezult.toString(), sek);
                            editor4.commit();
                        }
                        sekost=50001;
                        preview_img_viktory.setVisibility(View.VISIBLE); //Выводим радостный смайлик
                        main_img_lose.setVisibility(View.GONE);   //Прячем грустный смайлик

                        final int level = save.getInt("Level", 1);
                        if (level>numlev){
                            //пусто
                        }
                        if (level<=numlev && numlev<22){
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", numlev+1);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else {
                        numLeft=random.nextInt(masOfImgMas[numlev-1].length); //генерируем случайное число от 0 до 9
                        img_left.setImageResource(masOfImgMas[numlev-1][numLeft]);  //достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(masOfTextMas[numlev-1][numLeft]);  //достаем из массива текст

                        if (numlev!=1 && numlev!=2 && numlev!=3 && numlev!=4 && numlev!=13 && numlev!=14) {
                            if (numLeft <= 5) {
                                min = 0;
                                max = 8;
                                numRight = random.nextInt(max);
                                while (numLeft == numRight) {
                                    numRight = random.nextInt(max);
                                }
                            }
                            if (numLeft >= masOfImgMas[numlev - 1].length - 5) {
                                min = masOfImgMas[numlev-1].length - 8;
                                max = masOfImgMas[numlev - 1].length;
                                numRight = random.nextInt(max - min)+min;
                                while (numLeft == numRight) {
                                    numRight = random.nextInt(max);
                                }
                            }
                            if (numLeft > 5 && numLeft < masOfImgMas[numlev - 1].length - 5) {
                                min = numLeft - 6;
                                max = numLeft + 6;
                                numRight = random.nextInt(max-min)+min; //генерируем случайное число от 0 до 9
                                //Цикл проверяющий равенство чисел - Начало
                                while (numLeft == numRight) {
                                    numRight = min+random.nextInt(max-min); //генерируем случайное число от 0 до 9
                                }
                            }
                        }else{
                            numRight = random.nextInt(masOfImgMas[numlev - 1].length); //генерируем случайное число от 0 до 9
                            //Цикл проверяющий равенство чисел - Начало
                            while (numLeft == numRight) {
                                numRight = random.nextInt(masOfImgMas[numlev - 1].length);
                            }
                        }

                        //Цикл проверяющий равенство чисел - Конец
                        img_right.setImageResource(masOfImgMas[numlev-1][numRight]);  //достаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(masOfTextMas[numlev-1][numRight]);  //достаем из массива текст
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
        if (!musicotschet.isPlaying()) {
            try {
                musicfon.stop();
                fanfary1.stop();
                fanfary2.stop();
                timeend.stop();
                start=0;
                sekost=50001;
                Intent intent = new Intent(Level5.this, GameLevels.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {

            }
        }
    }
    //системная кнопка Назад - конец
    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicfon.stop();
        fanfary1.stop();
        fanfary2.stop();
        timeend.stop();
        start=0;
        sekost=50001;
    }
}
