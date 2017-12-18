package garage.model.garage;

import java.io.Serializable;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The Level class stores and keeps track of spaces.
 * It'll create spaces based on how many spaces are needed per level.
 * This class will also allow us to quickly assign spaces to vehicles using a priority queue.
 * Its priority is based on the number of the space;
 * a spaces number determines how close it is to the entrance i.e. 'A1' is closer than 'A2', so on and so forth.
 *
 * (An entrance distance variable could be introduced depending on the layout of the parking lot, but would otherwise
 *  be redundant.)
 *
 * @author Feng Parra
 * @version 1.0 Dec. 17, 2017.
 */
public class Level implements Serializable {
    private String id;
    private int numOfSpaces;
    private boolean full;
    private PriorityQueue<Space> spaces;
    private Comparator<Space> comparator = new StringSumComparator();

    /**
     * Constructs and initializes the Level class, creating new spaces in the process.
     * The spaces are stored in a priority queue where space ID determines priority.
     * @param id The levels ID ('A', 'B', ect...)
     * @param spaceCount The amount of spaces within the level.
     */
    public Level(String id, int spaceCount) {
        this.id = id;
        full = false;
        numOfSpaces = spaceCount;
        spaces = new PriorityQueue<>(spaceCount, comparator);
        initSpaces();
    }

    /**
     * Returns the levels ID.
     * @return
     */
    public String getID() {
        return id;
    }

    /**
     * Creates spaces within the level class.
     * Each space will have its own ID based on the amount of spaces per level.
     */
    private void initSpaces() {
        for(int i = 1; i <= numOfSpaces; i++) {
            spaces.add(new Space(id + i));
        }
    }

    /**
     * Adds the space to the priority queue.
     * @param newSpace Space to be added,
     */
    public void addSpace(Space newSpace) {
        spaces.add(newSpace);
        full = false;
    }

    /**
     * Returns the next available space.
     * @return
     */
    public Space findEmptySpace() {
        return spaces.peek();
    }

    /**
     * Returns and removes the next available space from the queue.
     * @return
     */
    public Space occupySpace() {
        return this.spaces.poll();
    }

    /**
     * Returns whether the level is out of spaces.
     * @return
     */
    public boolean isFull() {
        if (spaces.isEmpty())
            full = true;
        return full;
    }
}

class StringSumComparator implements Comparator<Space>, Serializable {

    @Override
    public int compare(Space o1, Space o2) {
        int o1Sum = o1.getID().chars().sum();
        int o2Sum = o2.getID().chars().sum();

        if (o1Sum > o2Sum)
            return 1;
        else if (o1Sum < o2Sum)
            return -1;
        return 0;
    }
}
