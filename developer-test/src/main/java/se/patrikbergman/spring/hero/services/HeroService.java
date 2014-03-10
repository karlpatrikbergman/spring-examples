package se.patrikbergman.spring.hero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.patrikbergman.spring.hero.aspect.timing.Timed;
import se.patrikbergman.spring.hero.domain.Hero;
import se.patrikbergman.spring.hero.exceptions.HeroNotFoundException;
import se.patrikbergman.spring.hero.repositories.HeroRepository;

import java.util.Map;

@Service("heroService")
public class HeroService {

    @Autowired
    HeroRepository heroRepository;

    public Map<Integer, Hero> list() {
        return heroRepository.list();
    }

    @Timed
    public Hero get(Integer id) throws HeroNotFoundException {
        return heroRepository.get(id);
    }
}
