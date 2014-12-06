package uk.ac.kcl.worldstatus.app.backend;

/**
 * A class which holds the value of a country i.e. how favourable it is to the user.
 * Higher is better.
 */
public class CountryValue {

    private String name;
    private float val;
    private float score = 0;

    /**
     * Initialise the country value.
     *
     * @param name Name of the country.
     * @param val  Value of the country.
     */
    public CountryValue(String name, float val) {
        this.name = name;
        this.val = val;

    }

    /**
     * Get the name of the country.
     *
     * @return The name of the country.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the value of the country.
     *
     * @return The value of the country.
     */
    public float getVal() {
        return val;

    }

    /**
     * Get the score of the country.
     *
     * @return The score of the country.
     */
    public float getScore() {
        return score;
    }

    /**
     * Increment the score of the country.
     *
     * @param score The score to increment by.
     */
    public void setScore(float score) {
        this.score = score + this.score;
    }

}
