package se.patrikbergman.spring.hero.exceptions;

public class HeroNotFoundException extends RuntimeException {
    private final int heroId;
    private final String errorMessage;

    public HeroNotFoundException(int heroId) {
        this.heroId = heroId;
        this.errorMessage = String.format("No hero with id %d was found!", heroId);
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
