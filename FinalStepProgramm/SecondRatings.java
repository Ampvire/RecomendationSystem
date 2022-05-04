
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fR = new FirstRatings();
        myMovies = fR.loadMovies(moviefile);
        myRaters = fR.loadRaters(ratingsfile);
    }
    public int getMoviesSize(){
        return myMovies.size();
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
        for (int i = 0; i < myMovies.size(); i++){
            if (getAverageByID(myMovies.get(i).getID(), minimalRaters) != 0.0){
                rList.add(new Rating(myMovies.get(i).getID(), 
                          getAverageByID(myMovies.get(i).getID(), minimalRaters)));
            }
        }
        return rList;
    }
    public String getTitle(String id){
        for (int i = 0; i < myMovies.size(); i++){
            if (myMovies.get(i).getID().equals(id)){
                return myMovies.get(i).getTitle();
            }
        }
        return "The ID was not found";
    }
    public String getID(String title){
        for (int i = 0; i < myMovies.size(); i++){
            if(myMovies.get(i).getTitle().equals(title)){
                return myMovies.get(i).getID();
            }
        }
        return "NO SUCH TITLE";
    }
    public void testGetAverageByID(){
        for (int k = 0; k < myMovies.size(); k++){
            String id = myMovies.get(k).getID();
            System.out.println(getAverageByID(id, 2) + " is average rating by movie ID " + id);
        }
    }
    public void testGetAverageRatings(){
        ArrayList<Rating> test = getAverageRatings(3);
        for (Rating s : test){
            System.out.println("The movie with average Rating is: " + s.toString());
        }
    }
}
