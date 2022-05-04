import java.util.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender{
    
    public ArrayList<String> getItemsToRate(){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> listOfMovies = new ArrayList<String>();
        Collections.shuffle(movies);
        for (int i = 0; i < 20; i++){
            listOfMovies.add(movies.get(i));
        }
        return listOfMovies;
    }
    public void printRecommendationsFor(String webRaterID){
        FourthRatings fR = new FourthRatings();
        TrueFilter tF = new TrueFilter();
        ArrayList<Rating> list = fR.getSimilarRatingsByFilter(webRaterID,10,2,tF);
        // for (int i = 0; i < list.size(); i++){
            // System.out.println(list.get(i).getValue() + " " +  MovieDatabase.getTitle(list.get(i).getItem()));
        // }
        StringBuilder html = new StringBuilder("<table>");
        //html.append("<th>" + "Poster" + "</th>");
        html.append("<tr>" + "<th>" + "Title" + "</th>");
        html.append("<th>" + "Year" + "</th>");
        html.append("<th>" + "Minutes" + "</th>");
        html.append("<th>" + "Country" + "</th>" + "</tr>");
        for (Rating r : list){
            String movieId = r.getItem();
            String moviePoster = MovieDatabase.getPoster(movieId);
            String movieTitle = MovieDatabase.getTitle(movieId);
            int movieYear = MovieDatabase.getYear(movieId);
            int movieMinutes = MovieDatabase.getMinutes(movieId);
            String movieCountry = MovieDatabase.getCountry(movieId);
            //html.append("<td>" + moviePoster + "</td>");
            html.append("<tr>" + "<td>" + movieTitle + "</td>");
            html.append("<td>" + movieYear + "</td>");
            html.append("<td>" + movieMinutes + "</td>");
            html.append("<td>" + movieCountry + "</td>" + "</tr>");
        }
        html.append("</table>");
        System.out.println(html.toString());
    }
}
