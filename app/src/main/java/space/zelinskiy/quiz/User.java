package space.zelinskiy.quiz;

import java.util.Comparator;
import java.util.Objects;

public class User implements Comparable<User> {  // Добавили в заголовок implements Comparable<User> для того чтобы можно было испольовать метод сортировки
    public String name, pass;
    public int level, middleResult, number, key;
    private String UID;
    public User() {
    }

     public User(String name, String pass, int level, int middleResult) {
        this.name = name;
        this.pass = pass;
        this.level = level;
        this.middleResult = middleResult;
    }

    public User(String name, int level, int middleResult) {
        this.name = name;
        this.level = level;
        this.middleResult = middleResult;
    }

//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }

    public String getUID() {
        return UID;
    }
    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }



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

    //Сравнение по уровням а зетем по времени - начало
    public static Comparator<User> levelSort = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            int result = (int) o2.getLevel() - o1.getLevel();
            if (result !=0){
                return result;
            }else{
                return o1.getMiddleResult() - o2.getMiddleResult();
            }
        }
    };
    //Сравнение по уровням а затем по времени - конец
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User item = (User) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
