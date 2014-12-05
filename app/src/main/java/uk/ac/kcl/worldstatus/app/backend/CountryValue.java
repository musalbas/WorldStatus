package uk.ac.kcl.worldstatus.app.backend;

/**
 * @author Harrison Perry
 *         A class which holds the value of a country i.e. how favourable it is to the user.
 *         Higher is better.
 */
public class CountryValue {

    public String name;
    public float val;
    public float score = 0;

    public CountryValue(String name, float val) {
        this.name = name;
        this.val = val;

    }

    public String getName() {
        return name;
    }

    public float getVal() {
        return val;

    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score + this.score;
    }

}
