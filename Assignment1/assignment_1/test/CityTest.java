import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CityTest class tests the City class")
class CityTest {
    /**
     * {@code addLink(Link link)} function in {@code City} class adds a link to the {@code links}.
     */
    @Test
    @DisplayName("addLink() adds a link to the links in a city.")
    void addLink() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1City2Distance = 7;
        new Link(city1, city2, city1City2Distance);
        assertEquals(1, city1.getLinks().size(), "Link between city1 and city2 added in city1.");
        assertEquals(1, city2.getLinks().size(), "Link between city1 and city2 added in city2.");
    }

    /**
     * {@code addLink(Link link)} function in {@code City} class does not add {@code null} link to the {@code links}.
     */
    @Test
    @DisplayName("addNullLink() does not add null link to the links in a city.")
    void addNullLink() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        city1.addLink(null);
        city2.addLink(null);
        assertEquals(0, city1.getLinks().size(), "Null link is not added in city1.");
        assertEquals(0, city2.getLinks().size(), "Null link is not added in city2.");
    }

    /**
     * {@code getLinks()} function in {@code City} class returns links added to a city.
     */
    @Test
    @DisplayName("getAddedLinks() returns links added in a city.")
    void getAddedLinks() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final City city3 = new City("Helena");
        final int city1City2Distance = 7;
        final int city1City3Distance = 13;
        final Link link1 = new Link(city1, city2, city1City2Distance);
        final Link link2 = new Link(city1, city3, city1City3Distance);
        city1.addLink(link1);
        city1.addLink(link2);
        city1.addLink(null);
        assertEquals(2, city1.getLinks().size(), "Correct number of links returned by city1.");
    }

    /**
     * {@code compareTo(City c2)} function in {@code City} class returns {@code 0} if both cities are same.
     */
    @Test
    @DisplayName("compareToSameCities() returns 0 if both cities are same.")
    void compareToSameCities() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Dallas");
        assertEquals(0, city1.compareTo(city2), "city1 name and city2 name are same.");
    }

    /**
     * {@code compareTo(City c2)} function in {@code City} class returns a negative value if {@code city1} {@code name} is alphanumerically smaller than {@code city2} {@code name}.
     */
    @Test
    @DisplayName("compareToCity1SmallerCity2() returns a negative value if city1 name is alphanumerically smaller than city2.")
    void compareToCity1SmallerCity2() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        assertTrue((city1.compareTo(city2) < 0), "city1 name is smaller than city2 name.");
    }

    /**
     * {@code compareTo(City c2)} function in {@code City} class returns a positive value if {@code city1} {@code name} is alphanumerically greater than {@code city2} {@code name}.
     */
    @Test
    @DisplayName("compareToCity1GreaterCity2() returns a positive value if city1 name is alphanumerically greater than city2.")
    void compareToCity1GreaterCity2() {
        final City city1 = new City("Omaha");
        final City city2 = new City("Dallas");
        assertTrue((city1.compareTo(city2) > 0), "city1 name is greater than city2 name.");
    }

    /**
     * {@code compareTo(City c2)} function in {@code City} class.
     * Case Insensitive Test
     */
    @Test
    @DisplayName("compareToCaseInsensitive() case insensitive test.")
    void compareToCaseInsensitive() {
        final City city1 = new City("dallas");
        final City city2 = new City("Omaha");
        final City city3 = new City("Dallas");
        assertTrue((city1.compareTo(city2) < 0), "city1 name is smaller than city2 name, case insensitive.");
        assertTrue((city2.compareTo(city1) > 0), "city2 name is greater than city1 name, case insensitive.");
        assertTrue((city1.compareTo(city3) == 0), "city1 name is same as city3 name, case insensitive.");
    }

    /**
     * {@code toString()} function in {@code City} class returns the name of the city.
     */
    @Test
    @DisplayName("testToString() returns the name of the city.")
    void testToString() {
        final City city = new City("Dallas");
        assertEquals("Dallas", city.toString(), "City name is Dallas");
    }

    /**
     * {@code compare(City c1, City c2)} function in {@code City} class returns a negative value if {@code city1} is closer from the start of the rail network.
     */
    @Test
    @DisplayName("compareCity1Closer() returns a negative value if city1 is closer from the start of the rail network.")
    void compareCity1Closer() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        city1.distance = 7;
        city2.distance = 10;
        assertTrue((city1.compare(city1, city2) < 0), "city1 is closer from the start of the rail network");
    }

    /**
     * {@code compare(City c1, City c2)} function in {@code City} class returns {@code 0} if cities are at equal distance from the start of the rail network.
     */
    @Test
    @DisplayName("compareCity1EqualDistanceCity2() returns 0 if cities are at equal distance from the start of the rail network.")
    void compareCity1EqualDistanceCity2() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Winnipeg");
        city1.distance = 7;
        city2.distance = 7;
        assertTrue((city1.compare(city1, city2) == 0), "city1 and city2 are at equal distance from the start of the rail network.");
    }

    /**
     * {@code compare(City c1, City c2)} function in {@code City} class returns a positive value if {@code city2} is closer from the start of the rail network.
     */
    @Test
    @DisplayName("compareCity2Closer() returns a positive value if city2 is closer from the start of the rail network.")
    void compareCity2Closer() {
        final City city1 = new City("Omaha");
        final City city2 = new City("Dallas");
        city1.distance = 10;
        city2.distance = 7;
        assertTrue((city1.compare(city1, city2) > 0), "city2 is closer from the start of the rail network");
    }

    /**
     * {@code getLinksTo(City dest, Set<Link> routeLinks)} function in {@code City} class returns {@code true} if path to destination is found.
     * Chicago => Saint_Louis => Little_Rock => Oklahoma_City
     * Number of links: 3
     */
    @Test
    @DisplayName("getLinksToRouteFound() returns true if path to destination is found.")
    void getLinksToRouteFound() {
        final City city1 = new City("Chicago");
        final City city2 = new City("Saint_Louis");
        final City city3 = new City("Little_Rock");
        final City city4 = new City("Oklahoma_City");
        final City city5 = new City("Denver");
        final City city6 = new City("Kansas_City");
        final Link link1 = new Link(city1, city2, 2);
        final Link link2 = new Link(city2, city3, 2);
        final Link link3 = new Link(city3, city4, 2);
        final Link link4 = new Link(city4, city6, 2);
        final Link link5 = new Link(city5, city4, 4);
        final Link link6 = new Link(city5, city6, 4);
        final Link link7 = new Link(city6, city2, 2);
        city1.parent = null;
        city2.parent = link1;
        city3.parent = link2;
        city4.parent = link3;
        link1.setUsed(true);
        link2.setUsed(true);
        link3.setUsed(true);
        link4.setUsed(false);
        link5.setUsed(false);
        link6.setUsed(false);
        link7.setUsed(false);
        HashSet<Link> routes = new HashSet<>();
        assertTrue(city1.getLinksTo(city4, routes), "A path exists between Chicago and Oklahoma city.");
        assertEquals(3, routes.size(), "Path from Chicago to Oklahoma contains 3 links.");
    }

    /**
     * {@code getLinksTo(City dest, Set<Link> routeLinks)} function in {@code City} class returns {@code false} if path to destination is not found.
     */
    @Test
    @DisplayName("getLinksToRouteNotFound() returns true if path to destination is found otherwise false.")
    void getLinksToRouteNotFound() {
        final City city1 = new City("Helena");
        final City city2 = new City("Denver");
        final City city3 = new City("Kansas_City");
        final City city4 = new City("Nashville");
        final City city5 = new City("Little_Rock");
        final Link link1 = new Link(city1, city2, 2);
        final Link link2 = new Link(city2, city3, 2);
        final Link link3 = new Link(city3, city4, 2);
        final Link link5 = new Link(city5, city4, 4);
        city1.parent = null;
        city2.parent = link1;
        city3.parent = link2;
        city4.parent = link3;
        link1.setUsed(true);
        link2.setUsed(true);
        link3.setUsed(false);
        link5.setUsed(false);
        HashSet<Link> routes = new HashSet<>();
        assertFalse(city1.getLinksTo(city4, routes), "No path found between Chicago and Oklahoma city.");
    }
}