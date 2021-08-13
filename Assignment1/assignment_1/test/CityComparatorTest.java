import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CityComparatorTest class tests the CityComparator class")
class CityComparatorTest {
    /**
     * {@code compare(City x, City y)} function in {@code CityComparator} class returns a negative value if city {@code x} is closer from the start of the rail network.
     */
    @Test
    @DisplayName("compare_xSmaller() returns a negative value if city x is closer from the start of the rail network.")
    void compare_xSmaller() {
        CityComparator cityComparator = new CityComparator();
        final City cityX = new City("Dallas");
        final City cityY = new City("Omaha");
        cityX.distance = 7;
        cityY.distance = 10;
        assertTrue((cityComparator.compare(cityX, cityY) < 0), "city x is closer from the start of the rail network.");
    }

    /**
     * {@code compare(City x, City y)} function in {@code CityComparator} class returns {@code 0} if cities are at equal distance from the start of the rail network.
     */
    @Test
    @DisplayName("compare_xEqual() returns 0 if cities are at equal distance from the start of the rail network.")
    void compare_xEqual() {
        CityComparator cityComparator = new CityComparator();
        final City cityX = new City("Dallas");
        final City cityY = new City("Winnipeg");
        cityX.distance = 7;
        cityY.distance = 7;
        assertTrue((cityComparator.compare(cityX, cityY) == 0), "city x and city y are at equal distance from the start of the rail network.");
    }

    /**
     * {@code compare(City x, City y)} function in {@code CityComparator} class returns a positive value if city {@code y} is closer from the start of the rail network.
     */
    @Test
    @DisplayName("compare_xLarger() returns a positive value if city y is closer from the start of the rail network.")
    void compare_xLarger() {
        CityComparator cityComparator = new CityComparator();
        final City cityX = new City("Omaha");
        final City cityY = new City("Dallas");
        cityX.distance = 10;
        cityY.distance = 7;
        assertTrue((cityComparator.compare(cityX, cityY) > 0), "city y is closer from the start of the rail network.");
    }
}