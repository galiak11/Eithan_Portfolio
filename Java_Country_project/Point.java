/**
 * This class represents a point in the polar coordinate system.
 * 
 * @ Eithan Alexander Lavi
 * @ 23/11/18
 */
import java.util.Scanner;

public class Point
{

    final int DEF_VAL = 0; 

    private double _radius; // distance from (0,0)

    private double _alpha; // degrees

    final double TO_DEGREES = 180/Math.PI; // convert from radians to derees

    final int DEG = 90; // Determins the value of the alpha if x=0 

    /**
     * Get's the value of (_x,_y) and sets it according to the rules,
     * also calculates the radius and the degree
     */
    public Point(double x, double y)
    {
        double _x = (x > DEF_VAL) ? x : DEF_VAL;
        double _y = (y > DEF_VAL) ? y : DEF_VAL;

        _radius = getRad(_x,_y);

        if(_x == 0)
        {
            _alpha = DEG;
        }
        else
        {
            _alpha = getDeg(_x,_y);
        }
    }

    /**
     * Copies a point into a new point 
     */
    public Point (Point other)
    {
        _radius = other._radius;
        _alpha = other._alpha;
    }

    /**
     * Set's the value of x and claculating the new radius and alpha 
     */
    public void setX(double num)
    {
        double y4x = getY(); 

        if (num > DEF_VAL)
        {
            _radius = getRad(num,y4x); 
            _alpha = getDeg(num,y4x);      
        }

        if (num==0)
        {
            _radius = y4x; 
            _alpha = DEG;
        }
    }

    /**
     * Set's the value of y and claculating the new radius and alpha 
     */
    public void setY(double num)
    {
        double x4y = getX();

        if(num > DEF_VAL){
            _radius = getRad(x4y,num);
            _alpha = getDeg(x4y,num);
        }

        if(num == 0){
            _radius = x4y;
            _alpha = DEF_VAL;
        }
    }

    /**
     * Get's the value of x and rounding the out come
     */
    public double getX()
    {
        return Math.round((_radius*(Math.cos(_alpha/(TO_DEGREES))))*(1000))/(double)1000;
    }

    /**
     * Get's the value of y and rounding the out come
     */
    public double getY()
    {        
        return Math.round((Math.sin(_alpha/(TO_DEGREES))*_radius)*1000)/(double)1000;
    }

    /**
     * Printing the values as a pair
     */
    public String toString()
    {
        return "("+getX()+" ,"+getY()+")";
    }

    /**
     * Checking if the user has inserted the same point more than once
     */
    boolean equals (Point other)
    {
        double xEq = getX();
        double xEqOther = other.getX();
        double yEq = getY();
        double yEqOther = other.getY();
        if(xEq == xEqOther && yEq == yEqOther)
        {
            return true;
        }
        return false;  
    }

    /**
     * Check's if one point is above the other
     */
    boolean isAbove (Point other)
    {
        double yAbove = getY();
        double yAboveOther = other.getY();
        if( yAbove > yAboveOther )
        {
            return true;
        }
        return false;  
    }

    /**
     * Check's if one point is under the other, if it not above or at the same 
     * y coordinate than it is under
     */
    boolean isUnder(Point other)
    {
        boolean abv = isAbove(other);
        double yUnd = getY();
        double yUndOther = other.getY();

        if(!abv && yUnd < yUndOther)
        {
            return true;
        }
        return false;

    }

    /**
     * Check's if one point if left of the other
     */
    boolean isLeft(Point other)
    {
        boolean isLeft = false;
        double xLeft = getX();
        double xLeftOther = other.getX();

        if(xLeft < xLeftOther)
        {
            return true;
        }
        return isLeft;
    }

    /**
     * Check's if one point if right of the other
     */
    boolean isRight (Point other)
    {
        boolean left = isLeft(other);
        double xRight = getX();
        double xRightOther = other.getX();

        if (!left && xRight > xRightOther)
        {
            return true;
        }
        return false;
    }

    /**
     * Calculate's the distance between two points
     */
    double distance (Point p)
    {
        double x1 = getX();
        double x2 = p.getX();
        double y1 = getY();
        double y2 = p.getY();

        double distanceBet = Math.sqrt((((y2-y1)*(y2-y1)))+((x2-x1)*(x2-x1)));

        return distanceBet;
    }

    /**
     * Set's a new value of (x,y) only in the value remain non negative
     */
    void move (double dx, double dy)
    {
        double x = getX();
        double y = getY();

        if (x + dx >= DEF_VAL && y + dy >= DEF_VAL)
        {
            setX(x + dx);
            setY(y + dy);
        }
    }

     /**
     * Calculate's the radius and rounding the outcome
     */
    public double getRad(double x, double y)
    {
        double radCalc = Math.sqrt((x*x)+(y*y));
        radCalc = Math.round(radCalc*1000)/(double)1000;

        return radCalc;
    }

    /**
     * Calculate's the degree and converting the outcome, depending on radians or gedrees
     */
    public double getDeg(double x, double y)
    {
        double degCalc = DEF_VAL;

        if (x>0)
        {
            degCalc = Math.atan(y/x)*(TO_DEGREES);
        }
        return degCalc;
    }
}

   
