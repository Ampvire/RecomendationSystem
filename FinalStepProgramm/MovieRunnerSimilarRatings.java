
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings fR = new FourthRatings();
        //RaterDatabase.initialize("ratings.csv")
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println(RaterDatabase.size()+ " number of raters in file");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        // tR.testGetAverageByID();
        // tR.testGetAverageRatings();
        int raters = 1;
        ArrayList <Rating> list = fR.getAverageRatings(raters);
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " +  MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fR = new FourthRatings();
        //RaterDatabase.initialize("ratings.csv")
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println(RaterDatabase.size()+ " number of raters in file");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        // tR.testGetAverageByID();
        // tR.testGetAverageRatings();
        int raters = 1;
        GenreFilter gF = new GenreFilter("Drama");
        YearAfterFilter yF = new YearAfterFilter(1990);
        AllFilters aF = new AllFilters();
        aF.addFilter(gF);
        aF.addFilter(yF);
        ArrayList <Rating> list = fR.getAverageRatingsByFilter(raters, aF);
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " + MovieDatabase.getYear(list.get(i).getItem())+ " " 
            + MovieDatabase.getTitle(list.get(i).getItem()) +
            "\n\t" + MovieDatabase.getGenres(list.get(i).getItem()));
        }
    }
    public void printSimilarRatings(){
        FourthRatings fR = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        //RaterDatabase.initialize("ratings_short.csv");
        System.out.println(RaterDatabase.size()+ " number of raters in file");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        ArrayList <Rating> list = fR.getSimilarRatings("71",20,5);
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " +  MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    public void printSimilarRatingsByGenre(){
        FourthRatings fR = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        //RaterDatabase.initialize("ratings_short.csv");
        System.out.println(RaterDatabase.size()+ " number of raters in file");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        GenreFilter gF = new GenreFilter("Mystery");
        ArrayList <Rating> list = fR.getSimilarRatingsByFilter("964",20,5,gF);
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " +  MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    public void printSimilarRatingsByDirector(){
        FourthRatings fR = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        //RaterDatabase.initialize("ratings_short.csv");
        System.out.println(RaterDatabase.size()+ " number of raters in file");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        DirectorsFilter dF = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList <Rating> list = fR.getSimilarRatingsByFilter("120",10,2,dF);
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " +  MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fR = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        //RaterDatabase.initialize("ratings_short.csv");
        System.out.println(RaterDatabase.size()+ " number of raters in file");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        GenreFilter gF = new GenreFilter("Drama");
        MinutesFilter mF = new MinutesFilter(80,160);
        AllFilters aF = new AllFilters();
        aF.addFilter(gF);
        aF.addFilter(mF);
        ArrayList <Rating> list = fR.getSimilarRatingsByFilter("168",10,3,aF);
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " +  MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fR = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        //RaterDatabase.initialize("ratings_short.csv");
        System.out.println(RaterDatabase.size()+ " number of raters in file");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        MinutesFilter mF = new MinutesFilter(70,200);
        YearAfterFilter yF = new YearAfterFilter(1975);
        AllFilters aF = new AllFilters();
        aF.addFilter(mF);
        aF.addFilter(yF);
        ArrayList <Rating> list = fR.getSimilarRatingsByFilter("314",10,5,aF);
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " +  MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
}
