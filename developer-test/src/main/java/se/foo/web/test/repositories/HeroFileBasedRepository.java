package se.foo.web.test.repositories;

import org.springframework.stereotype.Repository;
import se.foo.web.test.domain.Hero;

import java.util.Map;

@Repository("fileBasedRepository")
public class HeroFileBasedRepository implements HeroRepository{

    public Map<Integer, Hero> list() {
        return null;
    }

    public Hero get(Integer id) {
        return null;
    }

    public void save(Hero hero) {

    }

}
