package garage.model.garage;

import garage.model.vehicle.Vehicle;
import java.io.Serializable;
import java.util.*;

/**
 * The Lot class stores the levels within the parking lot system and its spaces.
 * There can only be one instance of a Lot object at any given point once instantiated.
 * It'll create the levels and spaces based on how many are needed for each one.
 * Along with creating spaces, it'll also open them and close them based on their current state of usage.
 *
 * @author Feng Parra
 * @version 1.0 Dec. 17, 2017.
 */
public final class Lot implements Serializable {
    private static Lot mainLot = null;
    private final int NUM_OF_LEVELS = 5;
    private final int NUM_OF_SPACES_PER_LEVEL = 50;

    private Map<String, Space> occupiedSpaces;
    private LinkedList<Level> levels = new LinkedList<>();
    private Level currentLevel;
    private Space currentSpace;
    private int availableSpaces;
    private boolean full;

    /**
     * Constructs and initializes the Lot class, creating new levels in the process.
     * The amount of available spaces is also tracked and a map is created to store any spaces currently being occupied.
     * This will allow the spaces to be resubmitted into the proper priority queue within the level class.
     */
    private Lot() {
        occupiedSpaces = new HashMap<>();
        initLevels();
        availableSpaces = NUM_OF_SPACES_PER_LEVEL * NUM_OF_LEVELS;
        full = false;
    }

    /**
     * Returns the Lot object and allows for access across all other classes.
     * @return
     */
    public static Lot getInstance() {
        if (mainLot == null)
            mainLot = new Lot();
        return mainLot;
    }

    /**
     * Creates a level within the lot class where all the spaces will be store.
     * Each level will have its own ID starting with the letter 'A' for the first level, 'B' for the second, ect...
     */
    private void initLevels() {
        String levelID;
        int levelASCII = 65;

        for (int i = 0; i < NUM_OF_LEVELS; i++) {
            levelID = Character.toString((char) levelASCII++);
            levels.add(new Level(levelID, NUM_OF_SPACES_PER_LEVEL));
        }
    }

    /**
     * Closes the next empty space and removes it from the levels queue.
     * Assigns the proper attributes to the vehicle associated with the space.
     * @param newVehicle Vehicle assigned to the space.
     */
    public void closeSpace(Vehicle newVehicle) {
        if (!isFull()) {
            findOpenLevel();
            currentSpace = currentLevel.occupySpace();
            newVehicle.setSpaceNum(currentSpace.getID());
            currentSpace.addVehicle(newVehicle);
            currentSpace.setActive(true);
            occupiedSpaces.put(newVehicle.getLicensePlate(), currentSpace);
            availableSpaces--;
        }
    }

    /**
     * Opens the space based on the key then inserts the space back into the proper level when calling the returnSpace method.
     * @param key A vehicles license plate number.
     */
    public void openSpace(String key) {
        occupiedSpaces.get(key).removeVehicle();
        occupiedSpaces.get(key).setActive(false);
        returnSpace(occupiedSpaces.get(key));
        occupiedSpaces.remove(key);
        availableSpaces++;
    }

    /**
     * Locates the level associated with the space to be returned.
     * It then inserts the space back into the levels queue.
     * @param space Space to be opened.
     */
    private void returnSpace(Space space) {
        Iterator<Level> level = levels.iterator();
        char levelID = space.getID().charAt(0);

        while (level.hasNext()) {
            currentLevel = level.next();
            if (currentLevel.getID().charAt(0) == levelID)
                break;
        }
        currentLevel.addSpace(space);
    }

    /**
     * Returns the next available space.
     * @return
     */
    public Space nextOpenSpace() {
        findOpenLevel();
        return currentLevel.findEmptySpace();
    }

    /**
     * Returns whether the lot is out of spaces.
     * @return
     */
    public boolean isFull() {
        return full;
    }

    /**
     * Returns amount of spaces available as a String.
     * @return
     */
    public String getAvailableSpaces() {
        return Integer.toString(availableSpaces);
    }

    /**
     * Finds a level that is not full.
     */
    private void findOpenLevel() {
        Iterator<Level> level = levels.iterator();

        while (level.hasNext()) {
            this.currentLevel = level.next();
            if (!currentLevel.isFull())
                break;
        }
    }
}
