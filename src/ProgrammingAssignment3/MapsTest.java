package ProgrammingAssignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This program can be used to test the MyMaps class methods.
 *
 * @author Dr.C.
 */
public class MapsTest {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "directions.txt";
        // Construct the file object
        MyMaps fallTrip = new MyMaps("src/ProgrammingAssignment3/" + fileName);
       System.out.println("\nDirections from source to destination: \n" + fallTrip.getDirections());
        // System.out.printf("Total time: %.2f minutes\n", fallTrip.travelTime());            // Expected: 16.75 minutes

       System.out.println("\nDirections from destination to source: \n" + fallTrip.returnTrip());
        System.out.printf("Total time: %.2f minutes", fallTrip.returnTripTime());           // Expected: 16.25 minutes
    }
    }
