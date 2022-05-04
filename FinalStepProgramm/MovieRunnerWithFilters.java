
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        //ThirdRatings tR = new ThirdRatings("data/ratings.csv");
        ThirdRatings tR = new ThirdRatings("data/ratings_short.csv");
        System.out.println(tR.getRaterSize()+ " number of raters in file");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        // tR.testGetAverageByID();
        // tR.testGetAverageRatings();
        int raters = 1;
        ArrayList <Rating> list = tR.getAverageRatings(raters);
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " +  MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    public void printAverageRatingsByYearAfter(){
        ThirdRatings tR = new ThirdRatings("data/ratings.csv");
        //ThirdRatings tR = new ThirdRatings("data/ratings_short.csv");
        System.out.println(tR.getRaterSize()+ " number of raters in file");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        // tR.testGetAverageByID();
        // tR.testGetAverageRatings();
        int raters = 20;
        int year = 2000;
        ArrayList <Rating> list = tR.getAverageRatingsByFilter(raters, new YearAfterFilter(year));
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " + MovieDatabase.getYear(list.get(i).getItem()) +
            " " + MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    public void printAverageRatingsByGenre(){
        ThirdRatings tR = new ThirdRatings("data/ratings.csv");
        //ThirdRatings tR = new ThirdRatings("data/ratings_short.csv");
        System.out.println(tR.getRaterSize()+ " number of raters in file");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        // tR.testGetAverageByID();
        // tR.testGetAverageRatings();
        int raters = 20;
        String genre = "Comedy";
        ArrayList <Rating> list = tR.getAverageRatingsByFilter(raters, new GenreFilter(genre));
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " + MovieDatabase.getTitle(list.get(i).getItem()) +
            "\n\t" + MovieDatabase.getGenres(list.get(i).getItem()));
        }
    }
    public void printAverageRatingsByMinutes(){
        ThirdRatings tR = new ThirdRatings("data/ratings.csv");
        //ThirdRatings tR = new ThirdRatings("data/ratings_short.csv");
        System.out.println(tR.getRaterSize()+ " number of raters in file");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        // tR.testGetAverageByID();
        // tR.testGetAverageRatings();
        int raters = 5;
        ArrayList <Rating> list = tR.getAverageRatingsByFilter(raters, new MinutesFilter(105,135));
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " + "Time: " +
            MovieDatabase.getMinutes(list.get(i).getItem()) + " " + MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    public void printAverageRatingsByDirectors(){
        ThirdRatings tR = new ThirdRatings("data/ratings.csv");
        //ThirdRatings tR = new ThirdRatings("data/ratings_short.csv");
        System.out.println(tR.getRaterSize()+ " number of raters in file");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        // tR.testGetAverageByID();
        // tR.testGetAverageRatings();
        int raters = 4;
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        ArrayList <Rating> list = tR.getAverageRatingsByFilter(raters, new DirectorsFilter(directors));
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " + MovieDatabase.getTitle(list.get(i).getItem()) +
            "\n\t" + MovieDatabase.getDirector(list.get(i).getItem()));
        }
    }
    public void printAverageRatingsByYearAfterAndGenre(){
        //ThirdRatings tR = new ThirdRatings("data/ratings.csv");
        ThirdRatings tR = new ThirdRatings("data/ratings_short.csv");
        System.out.println(tR.getRaterSize()+ " number of raters in file");
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
        ArrayList <Rating> list = tR.getAverageRatingsByFilter(raters, aF);
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " " + MovieDatabase.getYear(list.get(i).getItem())+ " " 
            + MovieDatabase.getTitle(list.get(i).getItem()) +
            "\n\t" + MovieDatabase.getGenres(list.get(i).getItem()));
        }
    }
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings tR = new ThirdRatings("data/ratings.csv");
        //ThirdRatings tR = new ThirdRatings("data/ratings_short.csv");
        System.out.println(tR.getRaterSize()+ " number of raters in file");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size() + " number of movies in database");
        // tR.testGetAverageByID();
        // tR.testGetAverageRatings();
        int raters = 3;
        MinutesFilter mF = new MinutesFilter(90,180);
        DirectorsFilter dF = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        AllFilters aF = new AllFilters();
        aF.addFilter(mF);
        aF.addFilter(dF);
        ArrayList <Rating> list = tR.getAverageRatingsByFilter(raters, aF);
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue() + " Time: " +
            MovieDatabase.getMinutes(list.get(i).getItem())+ " " + MovieDatabase.getTitle(list.get(i).getItem()) +
            "\n\t" + MovieDatabase.getDirector(list.get(i).getItem()));
        }
    }
}
