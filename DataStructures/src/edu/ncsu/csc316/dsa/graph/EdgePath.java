package edu.ncsu.csc316.dsa.graph;
import edu.ncsu.csc316.dsa.Weighted;

/**
 * A class that implements weighted that is used in ShortestPathUtil and minimum spanning tree
 * @author Siddhant Joshi
 */
public class EdgePath implements Weighted {
	
	/**The length of the path*/
    private int length;
    
    /**
	 * Constructor
	 * @param l the length of the path
	 */
    public EdgePath(int l) {
        setLength(l);
    }

    /**
	 * Gets the length of the a path
	 * @return the length of the path
	 */
    public int getLength() {
        return length;
    }

    /**
	 * sets the length of the a path
	 * @param length that is to be set
	 */
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int getWeight() {
        return getLength();
    }
}