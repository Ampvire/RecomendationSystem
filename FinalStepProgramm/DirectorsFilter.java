
/**
 * Write a description of DirectirsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String [] myDirectors;
    public DirectorsFilter(String directors){
        myDirectors = directors.split(",");
    }
    @Override
    public boolean satisfies(String id) {
	for (String s : myDirectors){
	    if (MovieDatabase.getDirector(id).contains(s)){
	        return true;
	    }
	}
        return false;
    }  
}
