package garage;

import vehicle.Vehicle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public final class Lot {
    private static Lot mainLot = null;
    private final int NUM_OF_LEVELS = 5;
    private final int NUM_OF_SPACES_PER_LEVEL = 50;

    private Map<String, Space> occupiedSpaces;
    private LinkedList<Level> levels = new LinkedList<>();
    private Level currentLevel;
    private Space currentSpace;

    private Lot() {
        occupiedSpaces = new HashMap<>();
        this.initLevels();
    }

    public static Lot getInstance() {
        if (mainLot == null) {
            mainLot = new Lot();
        }
        return mainLot;
    }

    private void initLevels() {
        String levelID;
        int levelASCII = 65;

        for (int i = 0; i < NUM_OF_LEVELS; i++) {
            levelID = Character.toString((char) levelASCII++);
            levels.add(new Level(levelID, NUM_OF_SPACES_PER_LEVEL));
        }
    }

    public void closeSpace(Vehicle newVehicle) {
        this.retrieveEmptySpace();
        this.currentLevel.isFull();
        this.currentSpace.addVehicle(newVehicle);
        this.currentSpace.setActive(true);
        this.occupiedSpaces.put(newVehicle.getLicensePlate(), this.currentSpace);
    }

    public void openSpace(String key) {
        this.occupiedSpaces.get(key).removeVehicle();
        this.occupiedSpaces.get(key).setActive(false);
        findLevel(this.occupiedSpaces.get(key));
        this.occupiedSpaces.remove(key);
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

    private void retrieveEmptySpace() {
        Iterator<Level> level = this.levels.iterator();

        while (level.hasNext()) {
            this.currentLevel = level.next();
            if (!currentLevel.isFull())
                break;
        }
        this.currentSpace = this.currentLevel.occupySpace();
    }

    

}
