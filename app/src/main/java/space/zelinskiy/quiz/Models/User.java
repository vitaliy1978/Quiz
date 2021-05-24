package space.zelinskiy.quiz.Models;

public class User {
    private String name, pass, key;
    private int level, middleResult, number;

    public User() {
    }

     public User(String name, String pass, int level, int middleResult) {
        this.name = name;
        this.pass = pass;
        this.level = level;
        this.middleResult = middleResult;
    }

    public User(String name, int level, int middleResult, String key) {
        this.name = name;
        this.level = level;
        this.middleResult = middleResult;
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
}
