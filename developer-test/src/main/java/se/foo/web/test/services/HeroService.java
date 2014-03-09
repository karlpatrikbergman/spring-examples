package se.foo.web.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.foo.web.test.domain.Hero;
import se.foo.web.test.repositories.HeroRepository;

import java.util.Map;

@Service
public class HeroService {

    @Autowired
    HeroRepository inMemoryRepository;

    public Map<Integer, Hero> list() {
        return inMemoryRepository.list();
    }

    public Hero get(Integer id) {
        return inMemoryRepository.get(id);
    }

}
