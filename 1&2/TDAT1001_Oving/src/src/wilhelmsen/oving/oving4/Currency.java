package src.wilhelmsen.oving.oving4;

/**
 * Created by Harald Wilhelmsen on 9/15/2014.
 */
public class Currency {

    public float toNokRate;
    public String isoCode;

    public float value;

    public Currency(float toNokRate, String isoCode) {
        this.toNokRate = toNokRate;
        this.isoCode = isoCode;
    }
}
