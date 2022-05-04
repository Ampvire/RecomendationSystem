
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    
    public FourthRatings() {
        // default constructor
    }

    private double getAverageByID(String id, int minimalRaters){
        //id = movieID
        //minimalRaters = how many min raters was rate this movie
        int ratersCount = 0;
        double RatingSum = 0;
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for (int i = 0 ; i < raters.size() ; i++){
            if (raters.get(i).hasRating(id)){
                double currRating = raters.get(i).getRating(id);
                RatingSum += currRating;
                ratersCount ++;
            }
        }
        if (ratersCount < minimalRaters){
            return 0.0;
        }
        else {
            return RatingSum / ratersCount;
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
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> items = MovieDatabase.filterBy(new TrueFilter());
        double dotProduct = 0.0;
        Rater meCurr = RaterDatabase.getRater(me.getID());
        Rater rCurr = RaterDatabase.getRater(r.getID());
        for (String item : items){
            if (meCurr.hasRating(item) && rCurr.hasRating(item)){
                double meRating = meCurr.getRating(item) - 5.0;
                double rRating = rCurr.getRating(item) - 5.0;
                double temp = meRating * rRating;
                dotProduct += temp;
            }
        }
        return dotProduct;
    }
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        Rating rating;
        for (Rater r : RaterDatabase.getRaters()){
            if (r != me){
                rating = new Rating(r.getID(),dotProduct(me,r));
                if (dotProduct(me,r) > 0.0){
                    ratingList.add(rating);
                }
            }
        }
        Collections.sort(ratingList, Collections.reverseOrder());
        return ratingList;
    }
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> rList = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        ArrayList<Rating> simList = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for (String movie : movies){
            int count = 0;
            double weightedRating = 0.0;
            for (int i = 0; i < numSimilarRaters; i++){
                Rating r = simList.get(i);
                String raterId = r.getItem();
                Rater simRater = RaterDatabase.getRater(raterId);
                if(simRater.hasRating(movie)){
                    count++;
                    double simRating = simRater.getRating(movie);
                    double weight = r.getValue();
                    weightedRating += simRating*weight;
                }
            }
            if (count >= minimalRaters){
                double avgWeight = weightedRating/count;
                rList.add(new Rating(movie,avgWeight));
            }
        }
        Collections.sort(rList, Collections.reverseOrder());
        return rList;
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, 
                                                        Filter filterCriteria){
        ArrayList<Rating> rList = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        ArrayList<Rating> simList = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for (String movie : movies){
            int count = 0;
            double weightedRating = 0.0;
            for (int i = 0; i < numSimilarRaters; i++){
                Rating r = simList.get(i);
                String raterId = r.getItem();
                Rater simRater = RaterDatabase.getRater(raterId);
                if(simRater.hasRating(movie)){
                    count++;
                    double simRating = simRater.getRating(movie);
                    double weight = r.getValue();
                    weightedRating += simRating*weight;
                }
            }
            if (count >= minimalRaters){
                double avgWeight = weightedRating/count;
                rList.add(new Rating(movie,avgWeight));
            }
        }
        Collections.sort(rList, Collections.reverseOrder());
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
