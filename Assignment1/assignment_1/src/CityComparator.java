import java.util.Comparator;

/**
 * {@code CityComparator} class compare two cities for use in sorting or data structures.
 * {@code CityComparator} class implements {@code Comparator} interface with generic type {@code City} class.
 */
public class CityComparator implements Comparator<City> {
    /**
     * Compare cities by their distance from the start of the rail network.
     * <p>
     * BUG: return x.compare(y, x);
     * This caused the method to return wrong calculation answer.
     * FIX: return x.compare(x, y);
     *
     * @param x {@code city1}.
     * @param y {@code city2}.
     * @return {@code 0} if cities are at equal distance from the start of the rail network,
     * negative value if {@code x} is closer to 0.
     * positive value is {@code y} is closer to 0.
     */
    public int compare(City x, City y) {
        return x.compare(x, y);
    }
}
