package ProgrammingAssignment2; /**
 Date:September 19, 2023
 Course:: CSCI 2073, DATA STRUCTURES, CRN: 44355
 Description: This MyBnb takes input from given source.txt file and reads String id and int number of Beds using RentalRoom
                The class consist of three methods
                numberOfRooms: returns the number of rooms containing the required number of beds
                chooseRoom: returns the  first room id which meets the required number of beds
                 numberOfStaffNeeded : returns the number of staff required to clean the room according to number of bed in each room
 On my honor, I have neither given nor received unauthorized help while
 completing this assignment.
 Name: Aayusha Kattel and CWID: 30158683.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;


/**
 * Class represents a MyBnb
 */
public class MyBnb {
    /**
     * filename of the .txt input
     */
    private String filename;

    /**
     * RentalRoom array is created which takes String id and int number of beds as input
     */
    ArrayList<RentalRoom> list = new ArrayList<>();

    /**
     * string roomInfo is initialized
     */
    String roomInfo;

    /**
     * Constructor MyBin is created
     * @param filename The .txt file to read the input file; thrown fileNotFound exception if the input file is not found
     */

    public MyBnb(String filename) {
        this.filename = filename;

    try {
        File file = new File(filename);


        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {


            roomInfo = scan.nextLine();
            System.out.println(roomInfo);
            String[] idAndRoom = roomInfo.split(" ");
            RentalRoom rent = new RentalRoom(String.valueOf(idAndRoom[0]), Integer.valueOf(idAndRoom[1]));
            list.add(rent);

        }
    }
            catch (FileNotFoundException ex){
                System.out.println("File not found");
            }
    }

    /**
     * numberOfRooms method
     * @param beds The number of bed provided by the user
     * @return the number of room containing the required no of bed as the instruction
     */
    public int numberOfRooms(int beds) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            RentalRoom room = list.get(i);
            if (room.getNumBeds() == beds) {
                count++;

            }
        }
        return count;
    }


    /**
     * chooseRoom method
     * @param minBeds The number of required bed in a room
     * @return The first room that meets the number of bed requirement; "NONE" if required condition
     * is not fulfilled
     */
    public String chooseRoom(int minBeds) {


        for (int i = 0; i < list.size(); i++) {
            RentalRoom room = list.get(i);
            if (room.getNumBeds() >= minBeds && room.isAvailable()) {
                room.setAvailable(false);
                return room.getID();
            }
        }
        return "NONE";
    }

    /**
     * This method calculates number of staff according to given rules
     * @returns the number of staff needed as per the number of rooms and
     * number of beds in each room
     */
    public int numberOfStaffNeeded() {
        int staffNum = 0;
        int threeBedRoomCount = 0;
        int twoBedRoomCount = 0;

        for (int i = 0; i < list.size(); i++) {
            RentalRoom room = list.get(i);
            if (!(room.isAvailable())) {
                if(room.getNumBeds()>=3){
                    threeBedRoomCount+=1;

                }
                if(room.getNumBeds()<=2){

                    twoBedRoomCount+=1;
                }
            }
        }
        staffNum +=threeBedRoomCount*2;
        if(twoBedRoomCount<=3 && twoBedRoomCount>0){

            staffNum+=1;
        }else {
            int quotient = twoBedRoomCount / 3;
            int remainder = twoBedRoomCount % 3;
            staffNum += quotient + 1;
        }
        return staffNum;
        
    }
}// end class
