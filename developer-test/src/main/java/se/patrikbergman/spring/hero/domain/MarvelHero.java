package se.patrikbergman.spring.hero.domain;

public class MarvelHero extends Hero {

    private String name;
    private String weakness;
    private Gender gender;

    public MarvelHero() { }

    public MarvelHero(String name, String weakness, Gender gender) {
        this.name = name;
        this.weakness = weakness;
        this.gender = gender;
    }

    public int getId() {
        return super.id;
    }

    public String getName() {
        return name;
    }

    public String getWeakness() {
        return weakness;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MarvelHero{");
        sb.append("id='").append(id).append('\'');
        sb.append("name='").append(name).append('\'');
        sb.append(", weakness='").append(weakness).append('\'');
        sb.append(", gender=").append(gender);
        sb.append('}');
        return sb.toString();
    }
}
