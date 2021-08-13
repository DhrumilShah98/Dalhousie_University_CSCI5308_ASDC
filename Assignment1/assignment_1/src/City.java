import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * {@code City} class represents a city.
 */
public class City {
    /* Lookup table of all cities by name. */
    public static HashMap<String, City> cities = new HashMap<>();

    /* Name of this city. */
    public String name;

    /* Adjacent links. */
    public final HashSet<Link> links = new HashSet<>();

    /* Shortest path distance. */
    public int distance;

    /* Shortest path parent. */
    public Link parent;

    /**
     * Constructs this {@code City} with name {@code nm}.
     * Add to the hash map of cities.
     *
     * @param nm name of this city.
     */
    public City(String nm) {
        name = nm;
        cities.put(name, this);
    }

    /**
     * Add a link to {@code links}.
     * If link is {@code null}, do not add it in {@code links}.
     *
     * @param link link to add.
     */
    public void addLink(Link link) {
        if (link == null) return;
        links.add(link);
    }

    /**
     * Returns all {@code links} to this city.
     *
     * @return {@code links} to this city.
     */
    public HashSet<Link> getLinks() {
        return links;
    }

    /**
     * Compare cities by their name.
     * <p>
     * BUG: name.compareTo(c2.name);
     * This caused the method to return wrong value on different case city names.
     * FIX: name.compareToIgnoreCase(c2.name);
     *
     * @param c2 {@code city2}.
     * @return {@code 0} if names are same,
     * negative value if this city is alphanumerically smaller or
     * positive value is this city is alphanumerically greater.
     */
    public int compareTo(City c2) {
        return name.compareToIgnoreCase(c2.name);
    }

    /**
     * Returns the {@code name} of this city.
     * <p>
     * BUG: return name + distance;
     * This caused the method to return wrong output string.
     * FIX: return name;
     *
     * @return {@code name} of this city.
     */
    public String toString() {
        return name;
    }

    /**
     * Compare cities by their distance from the start of the rail network.
     * <p>
     * BUG: return c2.distance - c1.distance;
     * This caused the method to return wrong calculation answer.
     * FIX: return c1.distance - c2.distance;
     *
     * @param c1 {@code city1}.
     * @param c2 {@code city2}.
     * @return {@code 0} if cities are at equal distance from the start of the rail network,
     * negative value if {@code c1} is closer to 0.
     * positive value is {@code c2} is closer to 0.
     */
    public int compare(City c1, City c2) {
        return c1.distance - c2.distance;
    }

    /**
     * Finds a path from this city to the destination city.
     * <p>
     * BUG: return conditions were inverted.
     * This caused the method to return wrong output when a path was found.
     * FIX: return true; changed to return false; and vice versa.
     *
     * @param dest       destination city.
     * @param routeLinks set of all the links followed to reach the destination.
     * @return {@code true} if a path is found otherwise {@code false}.
     */
    public boolean getLinksTo(City dest, Set<Link> routeLinks) {
        for (Link l : links) {
            if (l.isUsed() && (l != parent)) {
                City child = l.getAdj(this);
                if ((dest == child) || child.getLinksTo(dest, routeLinks)) {
                    routeLinks.add(l);
                    return true;
                }
            }
        }
        return false;
    }
}