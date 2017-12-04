package garage;

import vehicle.Vehicle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Lot {
    private final int NUM_OF_LEVELS = 5;
    private final int NUM_OF_SPACES_PER_LEVEL = 50;

    private Map<String, Space> occupiedSpaces;
    private LinkedList<Level> levels = new LinkedList<>();
    private Level currentLevel;
    private Space emptySpace;

    public Lot() {
        occupiedSpaces = new HashMap<>();
        this.initLevels();
    }

    private void initLevels() {
        String levelID;
        int levelASCII = 65;

        for (int i = 0; i < NUM_OF_LEVELS; i++) {
            levelID = Character.toString((char) levelASCII++);
            levels.add(new Level(levelID, NUM_OF_SPACES_PER_LEVEL));
        }
    }

    public void retrieveEmptySpace() {
        this.traverseLevels();
        this.emptySpace = this.currentLevel.occupySpace();
    }

    public void parkVehicle(Vehicle newVehicle) {
        this.retrieveEmptySpace();
        this.currentLevel.isFull();
        this.occupiedSpaces.put(newVehicle.getLicensePlate(), emptySpace);
    }

    public void returnSpace(String key) {
//        if (!occupiedSpaces.containsValue(key))
//            return;
        Space spaceToReturn = occupiedSpaces.get(key);
        findLevel(spaceToReturn);
        occupiedSpaces.remove(key);
    }

    public void findLevel(Space space) {
        Iterator<Level> level = this.levels.iterator();
        char levelID = space.getID().charAt(0);

        while (level.hasNext()) {
            this.currentLevel = level.next();
            if (currentLevel.getID().charAt(0) == levelID)
                break;
        }
        currentLevel.addSpace(space);
    }

    public void traverseLevels() {
        Iterator<Level> level = this.levels.iterator();

        while (level.hasNext()) {
            this.currentLevel = level.next();
            if (!currentLevel.isFull())
                break;
        }
    }

    

}
