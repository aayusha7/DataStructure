package ProgrammingAssignment2;

import java.io.FileNotFoundException;

/**
 Program to test the basic functionality of the MyBnb class.
 */
public class RoomsTest {
    public static void main(String[] args) throws FileNotFoundException {

      /**  FileManager manage = new FileManager("C:\\Users\\sital\\OneDrive\\Documents\\");
        System.out.println(manage.getFilesOfExtension("pdf"));

*/

        MyBnb rentals = new MyBnb("rental1.txt");
        System.out.println(rentals);

        System.out.printf("Number of rooms with 2 bed(s): %d\n",
                rentals.numberOfRooms(2));    // Expected: 3
       rentals.chooseRoom(2);
       rentals.chooseRoom(2);
       rentals.chooseRoom(2);
       rentals.chooseRoom(2);
       rentals.chooseRoom(2);

       var n = rentals.numberOfStaffNeeded();
        // Expected: 3
    }

}
