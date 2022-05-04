
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings sR = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        System.out.println(sR.getMoviesSize() + " number of movies in file \t" + sR.getRaterSize() +
                            " number of raters in file");
        // sR.testGetAverageByID();
        // sR.testGetAverageRatings();
        int raters = 50;
        ArrayList <Rating> list = sR.getAverageRatings(raters);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " +  sR.getTitle(list.get(i).getItem()));
        }
    }
    public void getAverageRatingOneMovie(){
        SecondRatings sR = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        String title = "The Maze Runner";
        ArrayList <Rating> list = sR.getAverageRatings(1);
        for (int i = 0; i < list.size(); i++){
            String id = sR.getID(title);
            if (list.get(i).getItem().equals(id)){
                System.out.println(list.get(i).getValue());
            }
        }
    }
}
