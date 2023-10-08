package ProgrammingAssignment1;

/**
 An abstract class to represent various types of Creatures that roam the world.
 Every Creature has a position represented in terms of x and y coordinates.
 */

public abstract  class Creature
{
    private int xVal;
    private int yVal;

    /**
     Constructor to initialize the x and y coordinates representing the initial position of a Creature.
     @param x the initial horizontal position
     @param y the initial vertical position
     */
    public Creature(int x, int y)
    {
        this.xVal = x;
        this.yVal = y;
    }

    /**
     Returns the horizontal position of a Creature
     @return the horizontal position
     */
    public int getX()
    {
        return xVal;
    }

    /**
     Returns the vertical position of a Creature
     @return the vertical position
     */
    public int getY()
    {
        return yVal;
    }

    /**
     Changes the horizontal position of a Creature
     @param newX the new horizontal position
     */
    public void setX(int newX)
    {
        xVal = newX;
    }

    /**
     Changes the vertical position of a Creature
     @param newY the new vertical position
     */
    public void setY(int newY)
    {
        yVal = newY;
    }

    public abstract void move();


  //  public abstract void move(int lifeUnits);
}