import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Location {

    /** TODO
     * declare private final locationId, description, exits
     */

    private int locationId;
    private String description;
    private Map  <String, Integer> exits;

    public Location(int locationId, String description, Map<String, Integer> exits) {
        /** TODO
         * set the locationId and the description
         */

        this.locationId = locationId;
        this.description = description;
        if (exits == null){
            this.exits.put("Q", 0);
        } else {
            Set set = this.exits.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
                Map.Entry entry = (Map.Entry)iterator.next();
                addExit((String)entry.getKey(),(Integer)entry.getValue());
            }
        }

        /** TODO
         * if exits are not null, set the exit
         * otherwise, set the exit HashMap to (Q,0)
         */
    }

    protected void addExit(String direction, int location) {
        /** TODO
         * put the direction and the location in the exits HashMap
         */
        this.exits.put(direction, location);
    }

    public int getLocationId() {
        /** TODO
         * complete getter to return the location id
         */
        return locationId;
    }

    public String getDescription() {
        /** TODO
         * complete getter to return the description
         */
        return description;
    }

    public Map<String, Integer> getExits() {
        /** TODO
         * complete getter to return a copy of exits
         * (preventing modification of exits from outside the Location instance)
         */
        return this.exits;
    }
}
