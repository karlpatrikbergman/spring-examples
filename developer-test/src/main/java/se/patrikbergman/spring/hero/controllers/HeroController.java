package se.patrikbergman.spring.hero.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import se.patrikbergman.spring.hero.aspect.logging.Logged;
import se.patrikbergman.spring.hero.domain.Gender;
import se.patrikbergman.spring.hero.domain.Hero;
import se.patrikbergman.spring.hero.domain.MarvelHero;
import se.patrikbergman.spring.hero.exceptions.HeroNotFoundException;
import se.patrikbergman.spring.hero.services.HeroService;

import java.util.Map;

@Controller
@EnableWebMvc
@RequestMapping("heroes")
public class HeroController {

    private static final Logger log = Logger.getLogger(HeroController.class);

    @Autowired
    HeroService heroService;

    @RequestMapping(method = RequestMethod.GET, value = "/get/all")
    public ResponseEntity<Map<Integer,Hero>> listHeroes() {
        return new ResponseEntity<Map<Integer,Hero>>(heroService.list(), HttpStatus.OK);
    }

    @Logged
    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public ResponseEntity<Hero> getHero(@PathVariable String id) {
        return new ResponseEntity<Hero>(heroService.get(Integer.parseInt(id)), HttpStatus.OK);
    }

    @Logged
    @RequestMapping(method = RequestMethod.PUT, value = "/add")
    public ResponseEntity<Hero> addHero(@RequestBody MarvelHero hero) {
        heroService.save(hero);
        return new ResponseEntity<Hero>(hero, HttpStatus.OK);
    }

    @Logged
    @ExceptionHandler(HeroNotFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleHeroNotFoundException() {
        return "handleHeroNotFoundException";
    }

    @Logged
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleTypeMismatchException() {
        return "handleTypeMismatchException";
    }

    @Logged
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleNumberFormatException() {
        return "handleNumberFormatException";
    }

    @Logged
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleException() {
        return "handleException";
    }
}
