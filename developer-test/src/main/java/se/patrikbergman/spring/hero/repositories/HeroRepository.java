package se.patrikbergman.spring.hero.repositories;

import se.patrikbergman.spring.hero.domain.Hero;
import se.patrikbergman.spring.hero.exceptions.HeroNotFoundException;

import java.util.Map;

public interface HeroRepository {

    Map<Integer, Hero> list();
    Hero get(Integer id) throws HeroNotFoundException ;
    Hero save(Hero hero);

}
