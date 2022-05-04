
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> list = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            list.add(new Movie(record.get("id"),record.get("title"),record.get("year"),
            record.get("genre"),record.get("director"),
            record.get("country"),
            record.get("poster"),
            Integer.parseInt(record.get("minutes"))));
            
        }
        return list;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        HashMap<String,Rater> myMap = new HashMap<String,Rater>();
        ArrayList<Rater> list = new ArrayList<Rater>();
        Rater rater;
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            String raterId = record.get("rater_id");
            String movieId = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            if (!myMap.containsKey(raterId)){
                rater = new EfficientRater(raterId);
                rater.addRating(movieId,rating);
                myMap.put(raterId, rater);
            }
            else {
                rater = myMap.get(raterId);
                rater.addRating(movieId,rating);
                myMap.put(raterId, rater);
            }
        }
        for (String s : myMap.keySet()){
            list.add(myMap.get(s));
        }
        return list;
    }
    private HashMap<String,Integer> directorMoviesMap(ArrayList<Movie> list){
        HashMap <String,Integer> directorMap = new HashMap<String, Integer>();
        for (Movie movie : list){
            if (!movie.getDirector().contains(",")){
                if(!directorMap.containsKey(movie.getDirector())){
                    directorMap.put(movie.getDirector(),1);
                }
                else {
                    int value = directorMap.get(movie.getDirector());
                    directorMap.put(movie.getDirector(),value+1);
                }
            }
            else {
                String [] directors = movie.getDirector().split(",");
                for(int i = 0; i < directors.length; i++){
                    String director = directors[i].trim();
                    if(!directorMap.containsKey(director)){
                        directorMap.put(director,1);
                    }
                    else {
                        int value = directorMap.get(director);
                        directorMap.put(director,value+1);
                    }
                }    
            }
        }
        return directorMap;
    }
    public void testLoadMovies(){
        ArrayList<Movie> list = loadMovies("data/ratedmoviesfull.csv");
        HashMap <String,Integer> directorMap = directorMoviesMap(list);
        ArrayList<String> directorsWithMax = new ArrayList<String>();
        int countComedy = 0;
        int countMinutes = 0;
        int maxNumOfFilms = 0;
        for (String s : directorMap.keySet()){
            //System.out.println("Director " + s + " number of movies are: " + directorMap.get(s));
            if (directorMap.get(s) > maxNumOfFilms){
                maxNumOfFilms = directorMap.get(s);
            }
        }
        for (String s : directorMap.keySet()){
            if (directorMap.get(s) == maxNumOfFilms){
                directorsWithMax.add(s);
            }
        }
        System.out.println("Max number of films directed by one director is: " + maxNumOfFilms);
        System.out.println("Names of the directors with max num of films is: " + directorsWithMax);
        System.out.println("Number of movies is: " + list.size());
        for (Movie movie : list){
            if (movie.getGenres().contains("Comedy")){
                countComedy ++;
            }
            if (movie.getMinutes() > 150){
                countMinutes++;
            }
        }
        System.out.println("Number of movies with gener Comedy is: " + countComedy);
        System.out.println("Number of movies longer than 150 minutes is: " + countMinutes);
        
    }
    
    public void testLoadRaters(){
        ArrayList<Rater> list;
        ArrayList<Rater> maxNumRatersList = new ArrayList<Rater>();
        ArrayList<Rater> particularMovieRaters = new ArrayList<Rater>();
        ArrayList<String> differentMovies = new ArrayList<String>();
        int count = 0;
        int maxNumRatings = 0;
        String particularMovie = "1798709";
        String raterIdCheck = "193";
        //list = loadRaters("data/ratings_short.csv");
        list = loadRaters("data/ratings.csv");
        System.out.println("The total num of Raters is: " + list.size());
        for (Rater rater : list){
            // System.out.println("Rater ID: " + rater.getID() + " num of ratings " + 
                                    // rater.numRatings());
            ArrayList<String> itemsRated = rater.getItemsRated();
            for (String s : itemsRated){
                if (!differentMovies.contains(s)){
                    differentMovies.add(s);
                }
                if (s.equals(particularMovie)){
                    particularMovieRaters.add(rater);
                }
                double rating = rater.getRating(s);
                //System.out.println("[" + s + " | " + rating + "]");
            }
            //------------------Rater-Id-Check------------------------------------
            if (rater.getID().equals(raterIdCheck)){
                System.out.println(rater.numRatings());
            }
            //--------------------Max-num-ratings----------------------------------
            if (rater.numRatings() > maxNumRatings){
                maxNumRatings = rater.numRatings();
            }
        }
        for (Rater rater : list){
            if (rater.numRatings() == maxNumRatings){
                maxNumRatersList.add(rater);
            }
        }
        System.out.println("Max num of ratings by any rater is: " + maxNumRatings);
        for (Rater rater : maxNumRatersList){
            System.out.println("Rater ID: " + rater.getID() + " maximum num of ratings " + 
                                rater.numRatings());
        }
        System.out.println("Number of ratings of "+ particularMovie + " is " 
                            + particularMovieRaters.size());
        System.out.println("Number of different movies is: " + differentMovies.size());
    }
}
