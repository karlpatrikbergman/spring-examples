package se.patrikbergman.spring.hero.domain;

public abstract class Hero {
    protected int id;
    public abstract int getId();
    public abstract String getName();
    public abstract Gender getGender();
    public abstract String getWeakness();
}
