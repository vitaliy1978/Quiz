package space.zelinskiy.quiz;

import java.util.Comparator;

public class User implements Comparable<User> {  // Добавили в заголовок implements Comparable<User> для того чтобы можно было испольовать метод сортировки
    public String name, pass;
    public int level, middleResult, number, key;

    public User() {
    }

     public User(String name, String pass, int level, int middleResult) {
        this.name = name;
        this.pass = pass;
        this.level = level;
        this.middleResult = middleResult;
    }

    public User(String name, int level, int middleResult, int key) {
        this.name = name;
        this.level = level;
        this.middleResult = middleResult;
    }

//    public int getNumber() { return number; }
//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

//    public void setNumber(int number) {
//        this.number = number;
//    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMiddleResult() {
        return middleResult;
    }

    public void setMiddleResult(int middleResult) {
        this.middleResult = middleResult;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public int compareTo(User o) {
        return this.level - o.getLevel();
    }

    //Сравнение по уровням - начало
    public static Comparator<User> levelSort = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o2.getLevel() - o1.getLevel();
        }
    };
    //равнение по уровням - конец

    //Сравнение по среднему времени - начало
    public static Comparator<User> AverageSort = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getMiddleResult() - o2.getMiddleResult();
        }
    };
    //равнение по среднему времени - конец

}
