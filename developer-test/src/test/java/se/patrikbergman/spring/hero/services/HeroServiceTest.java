package se.patrikbergman.spring.hero.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.patrikbergman.spring.hero.domain.Gender;
import se.patrikbergman.spring.hero.domain.Hero;
import se.patrikbergman.spring.hero.domain.MarvelHero;
import se.patrikbergman.spring.hero.exceptions.HeroNotFoundException;
import se.patrikbergman.spring.hero.repositories.HeroRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class HeroServiceTest {

    @Mock
    HeroRepository repository;

    @InjectMocks
    HeroService heroService = new HeroService();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void thatSuperManIsReturned() {
        try {
            int id = 0;
            Hero hero = new MarvelHero("AppleMan", "Pealers", Gender.MAN);
            when(repository.get(id)).thenReturn(hero);

            Hero result = heroService.get(id);
            assertNotNull(result);
            assertEquals(result.getName(), "AppleMan");
            assertEquals(result.getWeakness(), "Pealers");
            assertEquals(result.getGender(), Gender.MAN);

        } catch (HeroNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void thatHeroNotFoundExceptionIsThrown() {
        int id = Integer.MAX_VALUE;
        when(repository.get(id)).thenThrow(new HeroNotFoundException(id));
        try {
            heroService.get(id);
        } catch (HeroNotFoundException e) {
            assertNotNull(e);
            System.out.println("Correctly recieved error message: " + e.getMessage());
        }
    }
}
