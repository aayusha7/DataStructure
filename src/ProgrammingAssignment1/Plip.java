package ProgrammingAssignment1; /**
 Date:September 9, 2023
 Course:: CSCI 2073, DATA STRUCTURES, CRN: 44355
 Description: The class Plip changes the direction of the Plip in x-axis with respect to gain and loose of its life unit.
                The class is an extension of Parent class Creature which access the co-ordinate method (x,y) from parent class and
                complete the abstract method move() of the parent class Creature.The toString method returns the value of
                life units and co-ordinates in (x,y) form.
 On my honor, I have neither given nor received unauthorized help while
 completing this assignment.
 Name: Aayusha Kattel and CWID: 30158683.
 */


/**
 * Class represents a Plip
 */
public class Plip extends Creature {

    /** Current lifeUnits of the creature Plip */
    private int lifeUnits;

    /** Current direction of the creature Plip */
    private int direction = 1;

    /**
     * Constructor
     * @param lifeUnites The lifeunits of the Creature Plip
     * @param x          The x- coordinates of the Creature Plip
     * @param y          The y- coordinates of the Creture Plip
     */
    public Plip(int lifeUnites, int x, int y) {
        super(x, y);
        this.lifeUnits = lifeUnites;
    }

    /**
     * increases the current life units of Plip by 1 if the current life unit is more than 2
     */
    public void absorb() {
        if (lifeUnits == 0) {

            System.out.println("A Plip with zero life units will no longer be able to absorb life from the sun");
        } else {
            lifeUnits += 1;
        }
    }

    /**
     * returns the life units of a Plip
     *@return the life units of a Plip
     */
    public int getLife() {

        return lifeUnits;
    }

    /**
     Changes life units of a Plip
     @param newLifeUnits the new life units of a Plip
     */
    public  void setLifeUnits(int newLifeUnits) {

        lifeUnits = newLifeUnits;
    }

    /**
     * moves the position of plip with respect to its position (in x-axis) and direction
     * decreases Plip's life unit by 2 units
     */
    public void move() {

       while(true){
           if( lifeUnits < 2){
               break;
           }
            if (getX()==0){
                direction=1;
            }
            else if (getX()==19) {
                direction =-1;
            }
            super.setX(getX()+direction);
            lifeUnits -= 2;
            break;
        }
    }

    /**
     * returns the current life units and its position in x-axis and y axis
     * @return the current life units and its position in x-axis and y axis
     */
    public String toString() {

        String result =  "The plip has "+getLife() + " life units at (" + getX() + "," + getY() + ")";
        return result;

    }

}// end class
