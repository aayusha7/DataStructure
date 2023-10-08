package ProgrammingAssignment1; /**
 Date:          August 2023
 Course:        CSCI 2073
 Description:   Program to test the basic functionality of the Plip class hierarchy
 Author:        Dr. Jose Cordova
 */
/**
 Date:          August 2023
 Course:        CSCI 2073
 Description:   Program to test the basic functionality of the Plip class hierarchy
 Author:        Dr. Jose Cordova
 */
public class PlipTest
{
    public static void main(String [] args)
    {
        Plip p1 = new Plip(50,  18,  6);
        Creature p2 = new Plip(40, 19, 15);
        for(int i=0; i<=30; i++){
            System.out.println(p1.toString());
            //System.out.println("Plip  1: " + p1.getLife() + " life units at (" + p1.getX() + "," + p1.getY() + ")");
            p1.move();
        }

        System.out.println("Plip  1: " + p1.getLife() + " life units at (" + p1.getX() + "," + p1.getY() + ")");
        p2.move();
        p1.move();
        System.out.println("\nPlip  1: " + p1);
        System.out.println("\nPlip  2: " + p2);

        Plip p3 = new Plip(10, 10, 11);
       p3.absorb();
        System.out.println("\nPlip  3: " + p3);
    }
}