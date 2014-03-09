package se.foo.web.test.repositories;

import se.foo.web.test.domain.Hero;

import java.util.Map;

public interface HeroRepository {

    Map<Integer, Hero> list();
    Hero get(Integer id);
    void save(Hero hero);

}
