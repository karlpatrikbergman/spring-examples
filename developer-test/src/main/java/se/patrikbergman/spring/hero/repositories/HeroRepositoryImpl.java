package se.patrikbergman.spring.hero.repositories;

import org.springframework.stereotype.Repository;
import se.patrikbergman.spring.hero.aspect.logging.Logged;
import se.patrikbergman.spring.hero.domain.Gender;
import se.patrikbergman.spring.hero.domain.Hero;
import se.patrikbergman.spring.hero.domain.MarvelHero;
import se.patrikbergman.spring.hero.exceptions.HeroNotFoundException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Repository("heroRepository")
public class HeroRepositoryImpl implements HeroRepository {

    protected Map<Integer,Hero> repository = new HashMap<Integer,Hero>();

    public HeroRepositoryImpl() {
        save(new MarvelHero("SuperMan","Kryptonite",Gender.MAN));
        save(new MarvelHero("Wonder Woman","Having her hands tied by a man",Gender.WOMAN));
        save(new MarvelHero("Power Girl","Any raw, unprocessed natural material",Gender.WOMAN));
        save(new MarvelHero("Captain Marvel Jr.","Saying his own name",Gender.MAN));
        save(new MarvelHero("Thor","Letting go of his hammer for 60 seconds",Gender.MAN));
    }

    @Logged
    @Override
    public Map<Integer, Hero> list() {
        return repository;
    }

    @Logged
    @Override
    public Hero get(Integer id) throws HeroNotFoundException {
        Hero hero = repository.get(id);
        if(hero == null) {
            throw new HeroNotFoundException(id);
        }
        return hero;
    }

    @Logged
    @Override
    public synchronized Hero save(Hero hero) {
        try {
            Field idField = Hero.class.getDeclaredField("id");
            idField.setAccessible(true);
            int newId = repository.size();
            idField.set(hero, newId);
            repository.put(repository.size(), hero);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to save hero %s: %s", hero.toString(), e.getMessage()));
        }
        return hero;
    }

}
