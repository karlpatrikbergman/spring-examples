package se.patrikbergman.spring.hero.repositories;


import org.junit.Test;
import se.patrikbergman.spring.hero.domain.Gender;
import se.patrikbergman.spring.hero.domain.Hero;
import se.patrikbergman.spring.hero.domain.MarvelHero;
import se.patrikbergman.spring.hero.exceptions.HeroNotFoundException;

import static org.junit.Assert.*;

public class HeroInMemoryRepositoryTest {

    HeroRepositoryImpl inMemoryRepository = new HeroRepositoryImpl();

    @Test
    public void thatSupermanIsReturned() {
        try {
            Hero hero = inMemoryRepository.get(0);
            assertNotNull(hero);
            assertEquals("SuperMan", hero.getName());
            assertEquals("Kryptonite", hero.getWeakness());
            assertEquals(Gender.MAN, hero.getGender());
            System.out.println(hero);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void thatSpiderWomanIsSaved() {
        try {
            Hero hero = inMemoryRepository.save(new MarvelHero("SpiderWoman","Rain", Gender.WOMAN));
            assertNotNull(hero);
            assertNotEquals(0, hero.getId());
            assertEquals("SpiderWoman", hero.getName());
            assertEquals("Rain", hero.getWeakness());
            assertEquals(Gender.WOMAN, hero.getGender());
            System.out.println(hero);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void thatHeroNotFoundExceptionIsThrown() {
        try {
            Hero hero = inMemoryRepository.get(inMemoryRepository.repository.size());
            fail("HeroNotFoundException was not thrown!");
        } catch (HeroNotFoundException e) {
            assertNotNull(e);
            System.out.print(e);
        }
    }
}
