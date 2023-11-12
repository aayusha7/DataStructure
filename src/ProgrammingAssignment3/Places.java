package ProgrammingAssignment3;

public class Places {
    private final double distance;
    private final String direction;
    private final  String streetName;

    public Places(double distance, String direction, String streetName){

        this.direction = direction;
        this.streetName = streetName;
        this.distance = distance;

    }


    /**
     * Returns direction of given two character directions in full form.
     * @param direction The two character direction string.
     * @return The direction.
     */
    public String getFullDirection(String direction){
        switch (direction){
            case "NO":
                return "North";
            case "WE":
                return "West";
            case "SO":
                return "South";
            case "EA":
                return "East";
            default:
                throw new IllegalArgumentException("Don't provide bs direction");
        }

    }


    /**
     * Returns opposite of given two character directions in full form.
     * @param direction The two character direction string.
     * @return The opposite direction.
     */
    public String getFullDirectionInverse(String direction){
        switch (direction){
            case "NO":
                return "South";
            case "WE":
                return "East";
            case "SO":
                return "North";
            case "EA":
                return "West";
            default:
                throw new IllegalArgumentException("Don't provide bs direction");
        }

    }

    public double getDistance() {
        return distance;
    }
    public String getDirection() {
        return direction;
    }
    public String getStreetName() {
        return streetName;
    }

}
