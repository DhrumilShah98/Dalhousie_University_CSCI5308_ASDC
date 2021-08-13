/**
 * {@code Link} class represents a link between cities.
 */
public class Link implements Comparable<Link> {
    /* Name of city 1. */
    public City city1;

    /* Name of city 2. */
    public City city2;

    /* Length of this link. */
    public int length;

    /* true if and only if this link is part of the set of shortest paths */
    public boolean used;

    /**
     * Constructs this {@code Link} between {@code c1} and {@code c2} with length {@code len}.
     * The city alphanumerically smaller is stored as {@code city1} and the other as {@code city2}.
     * This link is added to both cities.
     * <p>
     * BUG: used = true;
     * This caused this link to be part of the set of shortest paths.
     * FIX: used = false;
     *
     * @param c1  city1 instance.
     * @param c2  city2 instance.
     * @param len length of this link.
     */
    public Link(City c1, City c2, int len) {
        if (c1.compareTo(c2) < 0) {
            city1 = c1;
            city2 = c2;
        } else {
            city1 = c2;
            city2 = c1;
        }
        length = len;
        c1.addLink(this);
        c2.addLink(this);
        used = false;
    }

    /**
     * Returns the {@code length} of this link.
     *
     * @return {@code length} of this link.
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the adjacent city.
     * i.e., {@code city1} if c is {@code city2} and vice versa.
     * <p>
     * BUG: c == city1 ? city2 : city2;
     * This caused the method to return {@code city2} irrespective of the value {@code c}.
     * FIX: c == city1 ? city2 : city1;
     *
     * @param c is either {@code city1} or {@code city2}.
     * @return {@code city1} if c is {@code city2} and vice versa. Returns {@code null} if {@code c} is {@code null} or
     * neither {@code city1} nor {@code city2}.
     */
    public City getAdj(City c) {
        if (c == null) return null;
        if (!city1.name.equals(c.name) && !city2.name.equals(c.name)) return null;
        return c == city1 ? city2 : city1;
    }

    /**
     * Returns {@code true} is this link is part of the set of shortest path.
     *
     * @return {@code true} if this link is part of the set of shortest path otherwise {@code false}.
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Sets this link to be part of the set of shortest path.
     *
     * @param u {@code true} if this link is part of the set of shortest path otherwise {@code false}.
     */
    public void setUsed(boolean u) {
        used = u;
    }

    /**
     * Returns a string representation of this {@code Link} with city names in sorted order. Example: "city1 3 city2"
     *
     * @return string representation of this {@code Link}.
     */
    @Override
    public String toString() {
        return city1.name + " " + length + " " + city2.name;
    }

    /**
     * Compares this link to link {@code l}.
     * Returns {@code 0} if both links have the same {@code city1} and {@code city2}.
     * Returns {@code -1} if this link has {@code city1} < {@code city1} of {@code l} or {@code city1} are equal and this link has {@code city2} < {@code city2} of {@code l}.
     * Returns {@code 1} otherwise.
     *
     * @param l link {@code l} instance.
     * @return {@code 0} if links are same. {@code -1} if this link has {@code city1} < {@code city1} of {@code l} or
     * {@code city1} are equal and this link has {@code city2} < {@code city2} of {@code l} otherwise {@code 1}.
     */
    @Override
    public int compareTo(Link l) {
        int city1Comparison = this.city1.compareTo(l.city1);
        int city2Comparison = this.city2.compareTo(l.city2);
        if (city1Comparison == 0 && city2Comparison == 0) {
            return 0;
        } else if (city1Comparison < 0) {
            return -1;
        } else if (city1Comparison == 0 && city2Comparison < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}