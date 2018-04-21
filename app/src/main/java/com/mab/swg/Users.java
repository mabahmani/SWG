package com.mab.swg;

public class Users {
    private int score;
    private String name;

    public int getScor() {
        return score;
    }

    public void setScor(int scor) {
        this.score = scor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Users{" +
                "score=" + score +
                ", name='" + name + '\'' +
                '}';
    }
}
