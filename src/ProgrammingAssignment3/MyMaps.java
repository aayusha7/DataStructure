package ProgrammingAssignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MyMaps extends LinkedStack {
    /**
     * Name of the file
     */
    public String filename;

    /**
     *  Holds Places object to calculate direction and trip time in reverse order.
     */
    StackInt<Places> myStack = new LinkedStack<>();

    /**
     *  Holds Places object to calculate direction and trip time.
     */
    Queue<Places> queue = new LinkedList<Places>() {
    };

    /**
     * Initializes the object.
     * Reads from a file that contains directions and populates the stack and queue with Places objects.
     * @param filename The name of the file.
     */
    public MyMaps(String filename) {
        this.filename = filename;
        String currentDirection = "";
        String line = "";
        try {
            File file = new File(filename);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                System.out.println(line);
                String[] splitPlaces = line.split(" ");

                String street = extractStreet(splitPlaces);
                Places place = new Places(Double.parseDouble(splitPlaces[0]), splitPlaces[1], street);

                if(!splitPlaces[1].equals(currentDirection)){
                    myStack.push(place);
                    queue.add(place);
                }
                currentDirection = place.getDirection();
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("File: "+filename+" was not found");
        }

    }

    /**
     * Extract the street name from a line
     * @param splitPlaces An array containing single line of direction.
     * @return The street name.
     */
    private  String extractStreet(String[] splitPlaces) {
        String street = "";
        for (int i = 2; i < splitPlaces.length; i++) {
            street += splitPlaces[i]+" ";
        }
        return  street.substring(0, street.length() - 1);
    }

    /**
     * Returns a string that provides direction from source to destination.
     * @return The formatted string containing the direction
     */
    public String getDirections() {
        //Places newPlace = new Places();
        String result = "\n";
        Queue<Places> temp = new LinkedList<Places>();

        while (!queue.isEmpty()) {
            Places currentPlace = queue.remove();
            temp.add(currentPlace);
            result +="Turn "+ currentPlace.getFullDirection(currentPlace.getDirection())+ " and travel "+  currentPlace.getDistance() +" mile(s) on "+ currentPlace.getStreetName();

            if(!queue.isEmpty()){
                result +="\n";
            }

        }
        queue = temp;
        return  result;

    }

    /**
     * Returns a string that provides direction for a return trip.
     * The directions are reversed compared to the original trip
     * @return The formatted string containing the direction
     */
    public String returnTrip() {
        String result = "\n";
        StackInt<Places> temp = new LinkedStack<Places>();
        while (!myStack.empty()) {
            Places currentPlace = myStack.pop();

            result +="Turn "+ currentPlace.getFullDirectionInverse(currentPlace.getDirection())+ " and travel "+  currentPlace.getDistance() +" mile(s) on "+ currentPlace.getStreetName();

            temp.push(currentPlace);
            if(!myStack.empty()){
                result +="\n";
            }
        }

        myStack = restoreStack(temp);
        return  result;

    }

    /**
     * Helper method that restores stack to its previous state
     * @param temp The temporary stack that holds the element of the previous stack in reverse order
     * @return Stack containing elements in order of the previous stack
     */
    private StackInt<Places> restoreStack(StackInt<Places> temp) {
        StackInt<Places> myStack = new LinkedStack<Places>();
        while (!temp.empty()) {
            Places currentPlace = temp.pop();
            myStack.push(currentPlace);
        }
        return myStack;
    }






    /**
     * Calculates the travel time from source to destination.
     * Each mile takes 1.5 minutes and each left turn takes 0.5 minutes
     * @return The return trip time in minutes
     */
    public double travelTime(){
        Queue<Places> temp = new LinkedList<Places>();
        double travelTime = 0.0;
        String previousDirection = "";
        while (!queue.isEmpty()) {
            Places currentPlace = queue.remove();
            if(addLeftTurn(previousDirection,currentPlace.getDirection())){
                travelTime += 0.5;
            }
            temp.add(currentPlace);
            travelTime += currentPlace.getDistance() * 1.5;
            previousDirection = currentPlace.getDirection();

        }
        queue = temp;
        return travelTime;
    }


    /**
     * Calculates the return trip time from destination to source.
     * Each mile takes 1.5 minutes and each left turn takes 0.5 minutes
     * @return The return trip time in minutes
     */
    public double returnTripTime(){
        StackInt<Places> temp = new LinkedStack<>();
        double travelTime = 0.0;
        String previousDirection = "";
        while (!myStack.empty()) {
            Places currentPlace = myStack.pop();
            if(addLeftTurn(previousDirection,currentPlace.getDirection())){
                travelTime += 0.5;
            }
            temp.push(currentPlace);
            travelTime += currentPlace.getDistance() * 1.5;
            previousDirection = currentPlace.getDirection();
        }
        myStack = restoreStack(temp);
        return travelTime;
    }


    /**
     * Helper method that figures out if there is a left turn based on previous and current direction
     * @param previousDirection Direction the person is moving in
     * @param direction Direction the person wants to turn to
     * @return True if there is a left turn
     */
    private boolean addLeftTurn(String previousDirection, String direction) {

        if(previousDirection.equals("NO") && direction.equals("WE")){
            return  true;
        }
        if(previousDirection.equals("EA") && direction.equals("NO")){
            return  true;
        }
        if(previousDirection.equals("WE") && direction.equals("SO")){
            return  true;
        }
        if(previousDirection.equals("SO") && direction.equals("EA")){
            return  true;
        }
        return false;
    }

}