
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsfile){
        FirstRatings fR = new FirstRatings();
        myRaters = fR.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    private double getAverageByID(String id, int minimalRaters){
        //id = movieID
        //minimalRaters = how many min raters was rate this movie
        int raters = 0;
        double RatingSum = 0;
        for (int i = 0 ; i < myRaters.size() ; i++){
            if (myRaters.get(i).hasRating(id)){
                double currRating = myRaters.get(i).getRating(id);
                RatingSum += currRating;
                raters ++;
            }
        }
        if (raters < minimalRaters){
            return 0.0;
        }
        else {
            return RatingSum / raters;
        }
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> rList = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (int i = 0; i < movies.size(); i++){
            if (getAverageByID(movies.get(i), minimalRaters) != 0.0){
                rList.add(new Rating(movies.get(i), 
                          getAverageByID(movies.get(i), minimalRaters)));
            }
        }
        return rList;
    }
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> rList = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (int i = 0; i < movies.size(); i++){
            if (getAverageByID(movies.get(i), minimalRaters) != 0.0){
                rList.add(new Rating(movies.get(i), 
                          getAverageByID(movies.get(i), minimalRaters)));
            }
        }
        return rList;
    }
    // public void testGetAverageByID(){
        // ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        // for (int k = 0; k < movies.size(); k++){
            // String id = movies.get(k);
            // System.out.println(getAverageByID(id, 2) + " is average rating by movie ID " + id);
        // }
    // }
    // public void testGetAverageRatings(){
        // ArrayList<Rating> test = getAverageRatings(3);
        // for (Rating s : test){
            // System.out.println("The movie with average Rating is: " + s.toString());
        // }
    // }
}
