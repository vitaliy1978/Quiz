package space.zelinskiy.quiz;

public class Array {

    //Массив для результатов - Начало
    public int[] rezult = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    //Массив для результатов - Конец

    //Массив для первого уровня - Начало
    final int[] images1 = {
            R.drawable.onelevel0,            R.drawable.onelevel1,            R.drawable.onelevel2,            R.drawable.onelevel3,            R.drawable.onelevel4,
            R.drawable.onelevel5,            R.drawable.onelevel6,            R.drawable.onelevel7,            R.drawable.onelevel8,            R.drawable.onelevel9,
    };
    final int[] texts1 ={
            R.string.lvl1text0,
            R.string.lvl1text1,
            R.string.lvl1text2,
            R.string.lvl1text3,
            R.string.lvl1text4,
            R.string.lvl1text5,
            R.string.lvl1text6,
            R.string.lvl1text7,
            R.string.lvl1text8,
            R.string.lvl1text9,
    };

    //Массив для первого уровня - Конец

    //Массив для второго уровня - Начало
    final int[] images2 = {
            R.drawable.twolevel1,            R.drawable.twolevel2,            R.drawable.twolevel3,            R.drawable.twolevel4,            R.drawable.twolevel5,
            R.drawable.twolevel6,            R.drawable.twolevel7,            R.drawable.twolevel8,            R.drawable.twolevel9,            R.drawable.twolevel10,
    };
    final int[] texts2 ={
            R.string.lvl2text1,
            R.string.lvl2text2,
            R.string.lvl2text3,
            R.string.lvl2text4,
            R.string.lvl2text5,
            R.string.lvl2text6,
            R.string.lvl2text7,
            R.string.lvl2text8,
            R.string.lvl2text9,
            R.string.lvl2text10,
    };
        //Массив для второго уровня - Конец

        //Массив для третьего уровня - Начало
        final int[] images3 = {
                R.drawable.threelevel_a, R.drawable.threelevel_b, R.drawable.threelevel_c, R.drawable.threelevel_d,
                R.drawable.threelevel_e, R.drawable.threelevel_f, R.drawable.threelevel_g, R.drawable.threelevel_h,
                R.drawable.threelevel_i, R.drawable.threelevel_j, R.drawable.threelevel_k, R.drawable.threelevel_l,
                R.drawable.threelevel_m, R.drawable.threelevel_n, R.drawable.threelevel_o, R.drawable.threelevel_p,
                R.drawable.threelevel_q, R.drawable.threelevel_r, R.drawable.threelevel_s, R.drawable.threelevel_t,
                R.drawable.threelevel_u, R.drawable.threelevel_v, R.drawable.threelevel_w, R.drawable.threelevel_x,
                R.drawable.threelevel_y, R.drawable.threelevel_z,
        };
    final int[] texts3 ={
            R.string.lvl3textA,            R.string.lvl3textB,            R.string.lvl3textC,            R.string.lvl3textD,            R.string.lvl3textE,
            R.string.lvl3textF,            R.string.lvl3textG,            R.string.lvl3textH,            R.string.lvl3textI,            R.string.lvl3textJ,
            R.string.lvl3textK,            R.string.lvl3textL,            R.string.lvl3textM,            R.string.lvl3textN,            R.string.lvl3textO,
            R.string.lvl3textP,            R.string.lvl3textQ,            R.string.lvl3textR,            R.string.lvl3textS,            R.string.lvl3textT,
            R.string.lvl3textU,            R.string.lvl3textV,            R.string.lvl3textW,            R.string.lvl3textX,            R.string.lvl3textY,
            R.string.lvl3textZ,
    };
    //Массив для третьего уровня - Конец

    //Массив для четвертого уровня - Начало
    final int[] images4 = {
            R.drawable.level4_clok1,            R.drawable.level4_clok2,            R.drawable.level4_clok3,            R.drawable.level4_clok4,
            R.drawable.level4_clok5,            R.drawable.level4_clok6,            R.drawable.level4_clok7,            R.drawable.level4_clok8,
            R.drawable.level4_clok9,            R.drawable.level4_clok10,            R.drawable.level4_clok11,            R.drawable.level4_clok12,
            R.drawable.level4_clok13,            R.drawable.level4_clok14,            R.drawable.level4_clok15,            R.drawable.level4_clok16,
            R.drawable.level4_clok17,            R.drawable.level4_clok18,            R.drawable.level4_clok19,            R.drawable.level4_clok20,
    };
    final int[] texts4 ={
            R.string.lvl4text1,
            R.string.lvl4text2,            R.string.lvl4text3,            R.string.lvl4text4,            R.string.lvl4text5,            R.string.lvl4text6,            R.string.lvl4text7,            R.string.lvl4text8,
            R.string.lvl4text9,            R.string.lvl4text10,            R.string.lvl4text11,            R.string.lvl4text12,            R.string.lvl4text13,            R.string.lvl4text14,            R.string.lvl4text15,
            R.string.lvl4text16,            R.string.lvl4text17,            R.string.lvl4text18,            R.string.lvl4text19,            R.string.lvl4text20,    };
    //Массив для четвертого уровня - Конец

    //Массив для пятого уровня - Начало
    final int[] images5 = {
            R.drawable.fivelevel1,            R.drawable.fivelevel2,            R.drawable.fivelevel3,            R.drawable.fivelevel4,            R.drawable.fivelevel5,
            R.drawable.fivelevel6,            R.drawable.fivelevel7,            R.drawable.fivelevel8,            R.drawable.fivelevel9,            R.drawable.fivelevel10,
            R.drawable.fivelevel11,            R.drawable.fivelevel12,            R.drawable.fivelevel13,            R.drawable.fivelevel14,            R.drawable.fivelevel15,
            R.drawable.fivelevel16,

    };
    final int[] texts5 ={
            R.string.lvl5text1,            R.string.lvl5text2,            R.string.lvl5text3,            R.string.lvl5text4,            R.string.lvl5text5,
            R.string.lvl5text6,            R.string.lvl5text7,            R.string.lvl5text8,            R.string.lvl5text9,            R.string.lvl5text10,
            R.string.lvl5text11,            R.string.lvl5text12,            R.string.lvl5text13,            R.string.lvl5text14,            R.string.lvl5text15,
            R.string.lvl5text16,
    };
    //Массив для пятого уровня - Конец

    //Массив для шестого уровня - Начало
    final int[] images6 = {
            R.drawable.a6level1,            R.drawable.a6level2,            R.drawable.a6level3,            R.drawable.a6level4,
            R.drawable.a6level5,            R.drawable.a6level6,            R.drawable.a6level7,            R.drawable.a6level8,
            R.drawable.a6level9,            R.drawable.a6level10,            R.drawable.a6level11,            R.drawable.a6level12,
            R.drawable.a6level13,            R.drawable.a6level14,            R.drawable.a6level15,            R.drawable.a6level16,
            R.drawable.a6level17,            R.drawable.a6level18,            R.drawable.a6level19,            R.drawable.a6level20,
            R.drawable.a6level21,

    };
    final int[] texts6 ={
            R.string.lvl6text1,            R.string.lvl6text2,            R.string.lvl6text3,            R.string.lvl6text4,
            R.string.lvl6text5,            R.string.lvl6text6,            R.string.lvl6text7,            R.string.lvl6text8,
            R.string.lvl6text9,            R.string.lvl6text10,            R.string.lvl6text11,            R.string.lvl6text12,
            R.string.lvl6text13,            R.string.lvl6text14,            R.string.lvl6text15,            R.string.lvl6text16,
            R.string.lvl6text17,            R.string.lvl6text18,            R.string.lvl6text19,            R.string.lvl6text20,
            R.string.lvl6text21,
    };
    //Массив для шестого уровня - Конец

    //Массив для седьмого уровня - Начало
    final int[] images7 = {
            R.drawable.a7level1,            R.drawable.a7level2,            R.drawable.a7level3,             R.drawable.a7level4,
            R.drawable.a7level5,            R.drawable.a7level6,            R.drawable.a7level7,            R.drawable.a7level8,
            R.drawable.a7level9,            R.drawable.a7level10,            R.drawable.a7level11,            R.drawable.a7level12,
            R.drawable.a7level13,            R.drawable.a7level14,           R.drawable.a7level15,
    };
    final int[] texts7 ={
            R.string.lvl7text1,            R.string.lvl7text2,            R.string.lvl7text3,            R.string.lvl7text4,
            R.string.lvl7text5,            R.string.lvl7text6,            R.string.lvl7text7,            R.string.lvl7text8,
            R.string.lvl7text9,            R.string.lvl7text10,            R.string.lvl7text11,            R.string.lvl7text12,
            R.string.lvl7text13,            R.string.lvl7text14,      R.string.lvl7text15,
    };
    //Массив для седьмого уровня - Конец

    //Массив для восьмого уровня - Начало
    final int[] images8 = {
            R.drawable.a8level1,            R.drawable.a8level2,            R.drawable.a8level3,             R.drawable.a8level4,
            R.drawable.a8level5,            R.drawable.a8level6,            R.drawable.a8level7,            R.drawable.a8level8,
            R.drawable.a8level9,            R.drawable.a8level10,            R.drawable.a8level11,            R.drawable.a8level12,
            R.drawable.a8level13,            R.drawable.a8level14,            R.drawable.a8level15,            R.drawable.a8level16,
            R.drawable.a8level17,            R.drawable.a8level18,            R.drawable.a8level19,            R.drawable.a8level20,
            R.drawable.a8level21,            R.drawable.a8level22,            R.drawable.a8level23,            R.drawable.a8level24,
    };
    final int[] texts8 ={
            R.string.lvl8text1,            R.string.lvl8text2,            R.string.lvl8text3,            R.string.lvl8text4,
            R.string.lvl8text5,            R.string.lvl8text6,            R.string.lvl8text7,            R.string.lvl8text8,
            R.string.lvl8text9,            R.string.lvl8text10,            R.string.lvl8text11,            R.string.lvl8text12,
            R.string.lvl8text13,            R.string.lvl8text14,            R.string.lvl8text15,            R.string.lvl8text16,
            R.string.lvl8text17,            R.string.lvl8text18,            R.string.lvl8text19,            R.string.lvl8text20,
            R.string.lvl8text21,            R.string.lvl8text22,            R.string.lvl8text23,            R.string.lvl8text24,
    };
    //Массив для восьмого уровня - Конец

    //Массив для девятого уровня - Начало
    final int[] images9 = {
            R.drawable.a9level1,            R.drawable.a9level2,            R.drawable.a9level3,            R.drawable.a9level4,
            R.drawable.a9level5,            R.drawable.a9level6,            R.drawable.a9level7,            R.drawable.a9level8,
            R.drawable.a9level9,            R.drawable.a9level10,            R.drawable.a9level11,            R.drawable.a9level12,
            R.drawable.a9level13,            R.drawable.a9level14,            R.drawable.a9level15,            R.drawable.a9level16,
            R.drawable.a9level17,            R.drawable.a9level18,            R.drawable.a9level19,
    };
    final int[] texts9 ={
            R.string.lvl9text1,            R.string.lvl9text2,            R.string.lvl9text3,            R.string.lvl9text4,
            R.string.lvl9text5,            R.string.lvl9text6,            R.string.lvl9text7,            R.string.lvl9text8,
            R.string.lvl9text9,            R.string.lvl9text10,            R.string.lvl9text11,            R.string.lvl9text12,
            R.string.lvl9text13,            R.string.lvl9text14,            R.string.lvl9text15,            R.string.lvl9text16,
            R.string.lvl9text17,            R.string.lvl9text18,            R.string.lvl9text19,
    };
    //Массив для девятого уровня - Конец

    //Массив для десятого уровня - начало
    final int[] images10 = {
            R.drawable.a10level1,            R.drawable.a10level2,            R.drawable.a10level3,            R.drawable.a10level4,
            R.drawable.a10level5,            R.drawable.a10level6,            R.drawable.a10level7,            R.drawable.a10level8,
            R.drawable.a10level9,            R.drawable.a10level10,            R.drawable.a10level11,            R.drawable.a10level12,
            R.drawable.a10level13,            R.drawable.a10level14,            R.drawable.a10level15,            R.drawable.a10level16,
            R.drawable.a10level17,            R.drawable.a10level18,            R.drawable.a10level19,            R.drawable.a10level20,
            R.drawable.a10level21,
    };
    final int[] texts10 ={
            R.string.lvl10text1,              R.string.lvl10text2,             R.string.lvl10text3,             R.string.lvl10text4,
            R.string.lvl10text5,              R.string.lvl10text6,                R.string.lvl10text7,           R.string.lvl10text8,
            R.string.lvl10text9,             R.string.lvl10text10,              R.string.lvl10text11,           R.string.lvl10text12,
            R.string.lvl10text13,            R.string.lvl10text14,              R.string.lvl10text15,            R.string.lvl10text16,
            R.string.lvl10text17,             R.string.lvl10text18,             R.string.lvl10text19,          R.string.lvl10text20,
            R.string.lvl10text21,
    };
    //Массив для десятогоо уровня - Конец
    //Массив для одинадцатого уровня - начало
    final int[] images11 = {
            R.drawable.a11level1,            R.drawable.a11level2,            R.drawable.a11level3,            R.drawable.a11level4,
            R.drawable.a11level5,            R.drawable.a11level6,            R.drawable.a11level7,            R.drawable.a11level8,
            R.drawable.a11level9,            R.drawable.a11level10,            R.drawable.a11level11,            R.drawable.a11level12,
            R.drawable.a11level13,            R.drawable.a11level14,            R.drawable.a11level15,            R.drawable.a11level16,
            R.drawable.a11level17,            R.drawable.a11level18,            R.drawable.a11level19,            R.drawable.a11level20,
            R.drawable.a11level21,              R.drawable.a11level22,          R.drawable.a11level23,            R.drawable.a11level24,
            R.drawable.a11level25
    };
    final int[] texts11 ={
            R.string.lvl11text1,              R.string.lvl11text2,             R.string.lvl11text3,             R.string.lvl11text4,
            R.string.lvl11text5,              R.string.lvl11text6,              R.string.lvl11text7,           R.string.lvl11text8,
            R.string.lvl11text9,             R.string.lvl11text10,              R.string.lvl11text11,           R.string.lvl11text12,
            R.string.lvl11text13,            R.string.lvl11text14,              R.string.lvl11text15,            R.string.lvl11text16,
            R.string.lvl11text17,             R.string.lvl11text18,             R.string.lvl11text19,          R.string.lvl11text20,
            R.string.lvl11text21,              R.string.lvl11text22,            R.string.lvl11text23,           R.string.lvl11text24,
            R.string.lvl11text25,
    };
    //Массив для одинадцатого уровня - Конец
    //Массив для двенадцатого уровня - начало
    final int[] images12 = {
            R.drawable.a12level1,            R.drawable.a12level2,            R.drawable.a12level3,            R.drawable.a12level4,
            R.drawable.a12level5,            R.drawable.a12level6,            R.drawable.a12level7,            R.drawable.a12level8,
            R.drawable.a12level9,            R.drawable.a12level10,            R.drawable.a12level11,            R.drawable.a12level12,
            R.drawable.a12level13,            R.drawable.a12level14,            R.drawable.a12level15,            R.drawable.a12level16,
            R.drawable.a12level17,            R.drawable.a12level18,            R.drawable.a12level19,            R.drawable.a12level20,
    };
    final int[] texts12 ={
            R.string.lvl12text1,              R.string.lvl12text2,             R.string.lvl12text3,             R.string.lvl12text4,
            R.string.lvl12text5,              R.string.lvl12text6,              R.string.lvl12text7,           R.string.lvl12text8,
            R.string.lvl12text9,             R.string.lvl12text10,              R.string.lvl12text11,           R.string.lvl12text12,
            R.string.lvl12text13,            R.string.lvl12text14,              R.string.lvl12text15,            R.string.lvl12text16,
            R.string.lvl12text17,             R.string.lvl12text18,             R.string.lvl12text19,          R.string.lvl12text20,
    };
    //Массив для двенадцатого уровня - Конец
    //Массив для тринадцатого уровня - Конец
    final int[] images13 = {
            R.drawable.a13level1,            R.drawable.a13level2,            R.drawable.a13level3,            R.drawable.a13level4,
            R.drawable.a13level5,            R.drawable.a13level6,            R.drawable.a13level7,            R.drawable.a13level8,
            R.drawable.a13level9,            R.drawable.a13level10,            R.drawable.a13level11,            R.drawable.a13level12,
            R.drawable.a13level13,            R.drawable.a13level14,
    };
    final int[] texts13 ={
            R.string.lvl13text1,              R.string.lvl13text2,             R.string.lvl13text3,             R.string.lvl13text4,
            R.string.lvl13text5,              R.string.lvl13text6,              R.string.lvl13text7,           R.string.lvl13text8,
            R.string.lvl13text9,             R.string.lvl13text10,              R.string.lvl13text11,           R.string.lvl13text12,
            R.string.lvl13text13,            R.string.lvl13text14,
    };
    //Массив для тринадцатого уровня - Конец
    //Массив для четырнадцатого уровня - Конец
    final int[] images14 = {
            R.drawable.a14level1,            R.drawable.a14level2,            R.drawable.a14level3,            R.drawable.a14level4,
            R.drawable.a14level5,            R.drawable.a14level6,            R.drawable.a14level7,            R.drawable.a14level8,
    };
    final int[] texts14 ={
            R.string.lvl14text1,              R.string.lvl14text2,             R.string.lvl14text3,             R.string.lvl14text4,
            R.string.lvl14text5,              R.string.lvl14text6,              R.string.lvl14text7,           R.string.lvl14text8,
    };
    //Массив для четырнадцатого уровня - Конец
    //Массив для пятнадцатого уровня - начало
    final int[] images15 = {
            R.drawable.a15level1,            R.drawable.a15level2,            R.drawable.a15level3,            R.drawable.a15level4,
            R.drawable.a15level5,            R.drawable.a15level6,            R.drawable.a15level7,            R.drawable.a15level8,
            R.drawable.a15level9,            R.drawable.a15level10,            R.drawable.a15level11,            R.drawable.a15level12,
            R.drawable.a15level13,            R.drawable.a15level14,            R.drawable.a15level15,            R.drawable.a15level16,
            R.drawable.a15level17,            R.drawable.a15level18,            R.drawable.a15level19,            R.drawable.a15level20,
            R.drawable.a15level21,              R.drawable.a15level22,          R.drawable.a15level23,
    };
    final int[] texts15 ={
            R.string.lvl15text1,              R.string.lvl15text2,             R.string.lvl15text3,             R.string.lvl15text4,
            R.string.lvl15text5,              R.string.lvl15text6,              R.string.lvl15text7,           R.string.lvl15text8,
            R.string.lvl15text9,             R.string.lvl15text10,              R.string.lvl15text11,           R.string.lvl15text12,
            R.string.lvl15text13,            R.string.lvl15text14,              R.string.lvl15text15,            R.string.lvl15text16,
            R.string.lvl15text17,             R.string.lvl15text18,             R.string.lvl15text19,          R.string.lvl15text20,
            R.string.lvl15text21,              R.string.lvl15text22,            R.string.lvl15text23,
    };
    //Массив для пятнадцатого уровня - Конец
    //Массив для шестнадяцатого уровня - начало
    final int[] images16 = {
            R.drawable.a16level1,            R.drawable.a16level2,            R.drawable.a16level3,            R.drawable.a16level4,
            R.drawable.a16level5,            R.drawable.a16level6,            R.drawable.a16level7,            R.drawable.a16level8,
            R.drawable.a16level9,            R.drawable.a16level10,            R.drawable.a16level11,            R.drawable.a16level12,
            R.drawable.a16level13,            R.drawable.a16level14,            R.drawable.a16level15,            R.drawable.a16level16,
            R.drawable.a16level17,            R.drawable.a16level18,            R.drawable.a16level19,            R.drawable.a16level20,
            R.drawable.a16level21,              R.drawable.a16level22,          R.drawable.a16level23,            R.drawable.a16level24,
            R.drawable.a16level25,              R.drawable.a16level26,          R.drawable.a16level27,            R.drawable.a16level28,
            R.drawable.a16level29,
    };
    final int[] texts16 ={
            R.string.lvl16text1,              R.string.lvl16text2,             R.string.lvl16text3,             R.string.lvl16text4,
            R.string.lvl16text5,              R.string.lvl16text6,              R.string.lvl16text7,           R.string.lvl16text8,
            R.string.lvl16text9,             R.string.lvl16text10,              R.string.lvl16text11,           R.string.lvl16text12,
            R.string.lvl16text13,            R.string.lvl16text14,              R.string.lvl16text15,            R.string.lvl16text16,
            R.string.lvl16text17,             R.string.lvl16text18,             R.string.lvl16text19,          R.string.lvl16text20,
            R.string.lvl16text21,              R.string.lvl16text22,            R.string.lvl16text23,           R.string.lvl16text24,
            R.string.lvl16text25,              R.string.lvl16text26,            R.string.lvl16text27,           R.string.lvl16text28,
            R.string.lvl16text29,
    };
    //Массив для шестнадцатого уровня - Конец
    //Массив для семнадцатого уровня - начало
    final int[] images17 = {
            R.drawable.a17level1,            R.drawable.a17level2,            R.drawable.a17level3,            R.drawable.a17level4,
            R.drawable.a17level5,            R.drawable.a17level6,            R.drawable.a17level7,            R.drawable.a17level8,
            R.drawable.a17level9,            R.drawable.a17level10,            R.drawable.a17level11,            R.drawable.a17level12,
            R.drawable.a17level13,            R.drawable.a17level14,            R.drawable.a17level15,            R.drawable.a17level16,
            R.drawable.a17level17,            R.drawable.a17level18,            R.drawable.a17level19,            R.drawable.a17level20,
            R.drawable.a17level21,              R.drawable.a17level22,          R.drawable.a17level23,
    };
    final int[] texts17 ={
            R.string.lvl17text1,              R.string.lvl17text2,             R.string.lvl17text3,             R.string.lvl17text4,
            R.string.lvl17text5,              R.string.lvl17text6,              R.string.lvl17text7,           R.string.lvl17text8,
            R.string.lvl17text9,             R.string.lvl17text10,              R.string.lvl17text11,           R.string.lvl17text12,
            R.string.lvl17text13,            R.string.lvl17text14,              R.string.lvl17text15,            R.string.lvl17text16,
            R.string.lvl17text17,             R.string.lvl17text18,             R.string.lvl17text19,          R.string.lvl17text20,
            R.string.lvl17text21,              R.string.lvl17text22,            R.string.lvl17text23,
    };
    //Массив для семнадцатого уровня - Конец
    //Массив для восемнадцатого уровня - начало
    final int[] images18 = {
            R.drawable.a18level1,            R.drawable.a18level2,            R.drawable.a18level3,            R.drawable.a18level4,
            R.drawable.a18level5,            R.drawable.a18level6,            R.drawable.a18level7,            R.drawable.a18level8,
            R.drawable.a18level9,            R.drawable.a18level10,            R.drawable.a18level11,            R.drawable.a18level12,
            R.drawable.a18level13,            R.drawable.a18level14,            R.drawable.a18level15,            R.drawable.a18level16,
            R.drawable.a18level17,            R.drawable.a18level18,            R.drawable.a18level19,            R.drawable.a18level20,
            R.drawable.a18level21,              R.drawable.a18level22,          R.drawable.a18level23,           R.drawable.a18level24,
            R.drawable.a18level25,              R.drawable.a18level26,          R.drawable.a18level27,           R.drawable.a18level28,
            R.drawable.a18level29,              R.drawable.a18level30,          R.drawable.a18level31,           R.drawable.a18level32,
            R.drawable.a18level33,              R.drawable.a18level34,          R.drawable.a18level35,           R.drawable.a18level36,
            R.drawable.a18level37,               R.drawable.a18level38,          R.drawable.a18level39,
    };
    final int[] texts18 ={
            R.string.lvl18text1,              R.string.lvl18text2,             R.string.lvl18text3,             R.string.lvl18text4,
            R.string.lvl18text5,              R.string.lvl18text6,              R.string.lvl18text7,           R.string.lvl18text8,
            R.string.lvl18text9,             R.string.lvl18text10,              R.string.lvl18text11,           R.string.lvl18text12,
            R.string.lvl18text13,            R.string.lvl18text14,              R.string.lvl18text15,            R.string.lvl18text16,
            R.string.lvl18text17,             R.string.lvl18text18,             R.string.lvl18text19,            R.string.lvl18text20,
            R.string.lvl18text21,              R.string.lvl18text22,            R.string.lvl18text23,           R.string.lvl18text24,
            R.string.lvl18text25,              R.string.lvl18text26,            R.string.lvl18text27,           R.string.lvl18text28,
            R.string.lvl18text29,              R.string.lvl18text30,            R.string.lvl18text31,           R.string.lvl18text32,
            R.string.lvl18text33,              R.string.lvl18text34,            R.string.lvl18text35,           R.string.lvl18text36,
            R.string.lvl18text37,              R.string.lvl18text38,            R.string.lvl18text39,
    };
    //Массив для восемнадцатого уровня - Конец
    //Массив для девятнадцатого уровня - начало
    final int[] images19 = {
            R.drawable.a19level1,            R.drawable.a19level2,            R.drawable.a19level3,            R.drawable.a19level4,
            R.drawable.a19level5,            R.drawable.a19level6,            R.drawable.a19level7,            R.drawable.a19level8,
            R.drawable.a19level9,            R.drawable.a19level10,            R.drawable.a19level11,            R.drawable.a19level12,
            R.drawable.a19level13,            R.drawable.a19level14,            R.drawable.a19level15,            R.drawable.a19level16,
            R.drawable.a19level17,            R.drawable.a19level18,            R.drawable.a19level19,            R.drawable.a19level20,
            R.drawable.a19level21,              R.drawable.a19level22,          R.drawable.a19level23,           R.drawable.a19level24,
            R.drawable.a19level25,              R.drawable.a19level26,          R.drawable.a19level27,           R.drawable.a19level28,
            R.drawable.a19level29,              R.drawable.a19level30,          R.drawable.a19level31,           R.drawable.a19level32,
            R.drawable.a19level33,              R.drawable.a19level34,          R.drawable.a19level35,           R.drawable.a19level36,
            R.drawable.a19level37,               R.drawable.a19level38,          R.drawable.a19level39,          R.drawable.a19level40,
            R.drawable.a19level41,
    };
    final int[] texts19 ={
            R.string.lvl19text1,              R.string.lvl19text2,             R.string.lvl19text3,             R.string.lvl19text4,
            R.string.lvl19text5,              R.string.lvl19text6,              R.string.lvl19text7,           R.string.lvl19text8,
            R.string.lvl19text9,             R.string.lvl19text10,              R.string.lvl19text11,           R.string.lvl19text12,
            R.string.lvl19text13,            R.string.lvl19text14,              R.string.lvl19text15,            R.string.lvl19text16,
            R.string.lvl19text17,             R.string.lvl19text18,             R.string.lvl19text19,            R.string.lvl19text20,
            R.string.lvl19text21,              R.string.lvl19text22,            R.string.lvl19text23,           R.string.lvl19text24,
            R.string.lvl19text25,              R.string.lvl19text26,            R.string.lvl19text27,           R.string.lvl19text28,
            R.string.lvl19text29,              R.string.lvl19text30,            R.string.lvl19text31,           R.string.lvl19text32,
            R.string.lvl19text33,              R.string.lvl19text34,            R.string.lvl19text35,           R.string.lvl19text36,
            R.string.lvl19text37,              R.string.lvl19text38,            R.string.lvl19text39,           R.string.lvl19text40,
            R.string.lvl19text41,
    };
    //Массив для девятнадцатого уровня - Конец
    //Массив для ддвадцатого уровня - начало
    final int[] images20 = {
            R.drawable.a20level1, R.drawable.a20level2, R.drawable.a20level3, R.drawable.a20level4,
            R.drawable.a20level5, R.drawable.a20level6, R.drawable.a20level7, R.drawable.a20level8,
            R.drawable.a20level9, R.drawable.a20level10, R.drawable.a20level11, R.drawable.a20level12,
            R.drawable.a20level13, R.drawable.a20level14, R.drawable.a20level15, R.drawable.a20level16,
            R.drawable.a20level17, R.drawable.a20level18, R.drawable.a20level19, R.drawable.a20level20,
            R.drawable.a20level21, R.drawable.a20level22, R.drawable.a20level23, R.drawable.a20level24,
            R.drawable.a20level25, R.drawable.a20level26,
    };
    final int[] texts20 ={
            R.string.lvl20text1,              R.string.lvl20text2,             R.string.lvl20text3,             R.string.lvl20text4,
            R.string.lvl20text5,              R.string.lvl20text6,              R.string.lvl20text7,           R.string.lvl20text8,
            R.string.lvl20text9,             R.string.lvl20text10,              R.string.lvl20text11,           R.string.lvl20text12,
            R.string.lvl20text13,            R.string.lvl20text14,              R.string.lvl20text15,            R.string.lvl20text16,
            R.string.lvl20text17,             R.string.lvl20text18,             R.string.lvl20text19,            R.string.lvl20text20,
            R.string.lvl20text21,              R.string.lvl20text22,            R.string.lvl20text23,           R.string.lvl20text24,
            R.string.lvl20text25,              R.string.lvl20text26,
    };
    //Массив для двадцатого уровня - Конец
    //Массив для ддвадцать первого уровня - начало
    final int[] images21 = {
            R.drawable.a21level1, R.drawable.a21level2, R.drawable.a21level3, R.drawable.a21level4,
            R.drawable.a21level5, R.drawable.a21level6, R.drawable.a21level7, R.drawable.a21level8,
            R.drawable.a21level9, R.drawable.a21level10, R.drawable.a21level11, R.drawable.a21level12,
            R.drawable.a21level13, R.drawable.a21level14, R.drawable.a21level15, R.drawable.a21level16,
            R.drawable.a21level17, R.drawable.a21level18, R.drawable.a21level19, R.drawable.a21level20,
            R.drawable.a21level21, R.drawable.a21level22, R.drawable.a21level23, R.drawable.a21level24,
            R.drawable.a21level25, R.drawable.a21level26, R.drawable.a21level27, R.drawable.a21level28,
            R.drawable.a21level29, R.drawable.a21level30,
    };
    final int[] texts21 ={
            R.string.lvl21text1,              R.string.lvl21text2,             R.string.lvl21text3,             R.string.lvl21text4,
            R.string.lvl21text5,              R.string.lvl21text6,              R.string.lvl21text7,           R.string.lvl21text8,
            R.string.lvl21text9,             R.string.lvl21text10,              R.string.lvl21text11,           R.string.lvl21text12,
            R.string.lvl21text13,            R.string.lvl21text14,              R.string.lvl21text15,            R.string.lvl21text16,
            R.string.lvl21text17,             R.string.lvl21text18,             R.string.lvl21text19,            R.string.lvl21text20,
            R.string.lvl21text21,              R.string.lvl21text22,            R.string.lvl21text23,           R.string.lvl21text24,
            R.string.lvl21text25,              R.string.lvl21text26,            R.string.lvl21text27,              R.string.lvl21text28,
            R.string.lvl21text29,              R.string.lvl21text30,
    };
    //Массив для двадцать первого уровня - Конец

}
