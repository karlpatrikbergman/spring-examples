package se.patrikbergman.spring.hero.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import se.patrikbergman.spring.hero.domain.Gender;
import se.patrikbergman.spring.hero.domain.Hero;
import se.patrikbergman.spring.hero.domain.MarvelHero;
import se.patrikbergman.spring.hero.exceptions.HeroNotFoundException;
import se.patrikbergman.spring.hero.services.HeroService;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class HeroControllerTest {

    MockMvc mockMvc;

    @Mock
    HeroService heroService;

    @InjectMocks
    HeroController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void thatCandyWomanIsResponded() {
        try {
            Hero hero = new MarvelHero("CandyWoman", "Liquorice", Gender.WOMAN);
            int id = 0;
            when(heroService.get(id)).thenReturn(hero);

            this.mockMvc.perform(get("/heroes/{id}", id).accept(MediaType.APPLICATION_JSON))
                    //.andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(jsonPath("$.name", is("CandyWoman")))
                    .andExpect(jsonPath("$.gender", is(Gender.WOMAN.toString())))
                    .andExpect(jsonPath("$.weakness", is("Liquorice")));

            verify(heroService, times(1)).get(id);
            verifyNoMoreInteractions(heroService);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void thatNotFoundIsReturnedOnNonExistingHeroId() {
        try {
            int id = Integer.MAX_VALUE;
            when(heroService.get(id)).thenThrow(new HeroNotFoundException(id));

            this.mockMvc.perform(get("/heroes/{id}", id).accept(MediaType.APPLICATION_JSON))
                    //.andDo(print())
                    .andExpect(status().isNotFound());

            verify(heroService, times(1)).get(id);
            verifyNoMoreInteractions(heroService);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void thatBadRequestIsReturnedOnNumberFormatException() {
        try {
            this.mockMvc.perform(get("/heroes/x").accept(MediaType.APPLICATION_JSON))
                    //.andDo(print())
                    .andExpect(status().isBadRequest());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
