package se.patrikbergman.spring.hero.controllers;

import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class HeroControllerIT {

    private static final Logger log = Logger.getLogger(HeroControllerIT.class);

    @Test
    public void dummyTest() {
        log.info("Executing integration test");
        assertTrue(true);
    }
}