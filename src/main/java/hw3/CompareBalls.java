package hw3;

import java.util.Comparator;

//compares values of ball volume for use in sorting TreeSet in Box getBallsFromSmallest.
public class CompareBalls implements Comparator<Ball> {

    //returns int to determine which ball is bigger
    public int compare (Ball i, Ball j){
        double val = i.getVolume() - j.getVolume();
        return (int) val;
    }
}
