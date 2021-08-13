import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LinkTest class tests the Link class")
class LinkTest {
    /**
     * {@code getLength()} function in {@code Link} class returns a length of a link.
     */
    @Test
    @DisplayName("getLength() returns a length of a link between city1 and city2.")
    void getLength() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link = new Link(city1, city2, city1city2Distance);
        assertEquals(7, link.getLength(), "Length returned is true.");
    }

    /**
     * {@code getAdj(City c)} function in {@code Link} class returns {@code city1} if {@code c} is {@code city2}.
     */
    @Test
    @DisplayName("getAdjCity1() returns city1 that is adjacent to city2.")
    void getAdjCity1() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link = new Link(city1, city2, city1city2Distance);
        assertEquals(city1, link.getAdj(city2), "Returned city is city1");
        assertEquals(city1.name, link.getAdj(city2).name, "Returned city1 name is Dallas");
    }

    /**
     * {@code getAdj(City c)} function in {@code Link} class returns {@code city2} if {@code c} is {@code city1}.
     */
    @Test
    @DisplayName("getAdjCity2() returns city2 that is adjacent to city1.")
    void getAdjCity2() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link = new Link(city1, city2, city1city2Distance);
        assertEquals(city2, link.getAdj(city1), "Returned city is city2");
        assertEquals(city2.name, link.getAdj(city1).name, "Returned city2 name is Omaha");
    }

    /**
     * {@code getAdj(City c)} function in {@code Link} class returns {@code null} if {@code c} is {@code null}.
     */
    @Test
    @DisplayName("getAdjCityForNullValue() returns null if c is null.")
    void getAdjCityForNullValue() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link = new Link(city1, city2, city1city2Distance);
        assertNull(link.getAdj(null), "null value returned since c is null.");
    }

    /**
     * {@code getAdj(City c)} function in {@code Link} class returns {@code null} if {@code c} is neither {@code city1} nor {@code city2}.
     */
    @Test
    @DisplayName("getAdjCityForRandomCity() returns null if c is neither city1 nor city2.")
    void getAdjCityForRandomCity() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link = new Link(city1, city2, city1city2Distance);
        final City city3 = new City("Helena");
        assertNull(link.getAdj(city3), "null value returned since city3 is not city1 or city2.");
    }

    /**
     * {@code isUsed()} function in {@code Link} class returns {@code true} if link is part of the set of shortest path.
     */
    @Test
    @DisplayName("isUsedTrue() returns true if link is part of the set of shortest path.")
    void isUsedTrue() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link = new Link(city1, city2, city1city2Distance);
        link.setUsed(true);
        assertTrue(link.isUsed(), "Link is part of the set of shortest path.");
    }

    /**
     * {@code isUsed()} function in {@code Link} class returns {@code false} if link is not part of the set of shortest path.
     */
    @Test
    @DisplayName("isUsedFalse() returns false if link is not part of the set of shortest path.")
    void isUsedFalse() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link = new Link(city1, city2, city1city2Distance);
        link.setUsed(false);
        assertFalse(link.isUsed(), "Link is not part of the set of shortest path.");
    }

    /**
     * {@code isUsed()} function in {@code Link} class returns {@code false} initially as link is not part of the set of shortest path.
     */
    @Test
    @DisplayName("getInitialUsedValue() returns false initially as link is not part of the set of shortest path.")
    void getInitialUsedValue() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link = new Link(city1, city2, city1city2Distance);
        assertFalse(link.isUsed(), "Initially, link is not part of the set of shortest path.");
    }

    /**
     * {@code setUsed(boolean u)} function in {@code Link} class sets {@code used} to {@code true}.
     */
    @Test
    @DisplayName("setUsedTrue() sets used to true.")
    void setUsedTrue() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link = new Link(city1, city2, city1city2Distance);
        link.setUsed(true);
        assertTrue(link.isUsed(), "used was set to true");
    }

    /**
     * {@code setUsed(boolean u)} function in {@code Link} class sets {@code used} to {@code false}.
     */
    @Test
    @DisplayName("setUsedFalse() sets used to false.")
    void setUsedFalse() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link = new Link(city1, city2, city1city2Distance);
        link.setUsed(false);
        assertFalse(link.isUsed(), "user was set to false");
    }

    /**
     * {@code toString()} function in {@code Link} class returns a string representation of the {@code Link}.
     */
    @Test
    @DisplayName("printLinkC1LessC2() returns a string representation of the link as 'city1 distance city2'.")
    void printLinkC1LessC2() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link1 = new Link(city1, city2, city1city2Distance);
        assertEquals("Dallas 7 Omaha", link1.toString(), "String 'city1 distance city2' in sorted order.");
    }

    /**
     * {@code toString()} function in {@code Link} class returns a string representation of the {@code Link}.
     */
    @Test
    @DisplayName("printLinkC2LessC1() returns a string representation of the link as 'city2 distance city1'.")
    void printLinkC2LessC1() {
        final City city1 = new City("Omaha");
        final City city2 = new City("Dallas");
        final int city1city2Distance = 7;
        final Link link1 = new Link(city1, city2, city1city2Distance);
        assertEquals("Dallas 7 Omaha", link1.toString(), "String 'city2 distance city1' in sorted order.");
    }

    /**
     * {@code compareTo(Link l)} function in {@code Link} class returns {@code 0} if both the links have same {@code city1} and {@code city2}.
     */
    @Test
    @DisplayName("linksSameCities() returns 0 if both the links have same cities.")
    void linksSameCities() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link1 = new Link(city1, city2, city1city2Distance);
        final Link link2 = new Link(city1, city2, city1city2Distance);
        assertEquals(0, link1.compareTo(link2), "Both links have same cities.");
    }

    /**
     * {@code compareTo(Link l)} function in {@code Link} class returns {@code -1} if {@code city1} of {@code link1} is smaller than {@code city1} of {@code link2}.
     */
    @Test
    @DisplayName("link1City1SmallerLink2City1() returns -1 if city1 of link1 is smaller than city2 of link2.")
    void link1City1SmallerLink2City1() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Omaha");
        final int city1city2Distance = 7;
        final Link link1 = new Link(city1, city2, city1city2Distance);
        final City city3 = new City("Helena");
        final int city3city2Distance = 13;
        final Link link2 = new Link(city3, city2, city3city2Distance);
        assertEquals(-1, link1.compareTo(link2), "City1 of link1 is smaller than city1 of link2.");
    }

    /**
     * {@code compareTo(Link l)} function in {@code Link} class returns {@code -1} if {@code city1} of {@code link1} is equal to {@code city1} of {@code link2} and {@code city2} of {@code link1} is smaller than {@code city2} of {@code link2}.
     */
    @Test
    @DisplayName("link1City2SmallerLink2City2() returns -1 if city1 of link1 is equal to city1 of link2 and city2 of link1 is smaller than city2 of link 2.")
    void link1City2SmallerLink2City2() {
        final City city1 = new City("Dallas");
        final City city2 = new City("Helena");
        final int city1city2Distance = 13;
        final Link link1 = new Link(city1, city2, city1city2Distance);
        final City city3 = new City("Omaha");
        final int city1city3Distance = 7;
        final Link link2 = new Link(city1, city3, city1city3Distance);
        assertEquals(-1, link1.compareTo(link2), "City1 of link1 is equal to city1 of link2 and city2 of link1 is smaller than city2 of link2.");
    }

    /**
     * {@code compareTo(Link l)} function in {@code Link} class returns {@code 1} if link1 cities are greater than link2 cities.
     */
    @Test
    @DisplayName("link1CitiesGreaterLink2Cities() returns 1 if link1 cities are greater than link2 cities.")
    void link1CitiesGreaterLink2Cities() {
        final City city1 = new City("Helena");
        final City city2 = new City("Winnipeg");
        final int city1city2Distance = 13;
        final Link link1 = new Link(city1, city2, city1city2Distance);
        final City city3 = new City("Dallas");
        final City city4 = new City("Omaha");
        final int city3city4Distance = 7;
        final Link link2 = new Link(city3, city4, city3city4Distance);
        assertEquals(1, link1.compareTo(link2), "Link1 cities are greater than link2 cities.");
    }
}