package garage.model.garage;

import garage.model.vehicle.Vehicle;

import java.util.*;

public final class Lot {
    private static Lot mainLot = null;
    private final int NUM_OF_LEVELS = 5;
    private final int NUM_OF_SPACES_PER_LEVEL = 50;

    private Map<String, Space> occupiedSpaces;
    private LinkedList<Level> levels = new LinkedList<>();
    private Level currentLevel;
    private Space currentSpace;
    private int availableSpaces;
    private boolean full;

    private Lot() {
        occupiedSpaces = new HashMap<>();
        initLevels();
        availableSpaces = NUM_OF_SPACES_PER_LEVEL * NUM_OF_LEVELS;
        full = false;
    }

    public static Lot getInstance() {
        if (mainLot == null)
            mainLot = new Lot();
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
        if (!isFull()) {
            currentSpace = retrieveEmptySpace();
            newVehicle.setSpaceNum(currentSpace.getID());
            currentSpace.addVehicle(newVehicle);
            currentSpace.setActive(true);
            occupiedSpaces.put(newVehicle.getLicensePlate(), currentSpace);
        }
    }

    public void openSpace(String key) {
        occupiedSpaces.get(key).removeVehicle();
        occupiedSpaces.get(key).setActive(false);
        returnSpace(occupiedSpaces.get(key));
        occupiedSpaces.remove(key);
    }

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

    public boolean isFull() {
        return full;
    }

    private Space retrieveEmptySpace() {
        Iterator<Level> level = levels.iterator();

        while (level.hasNext()) {
            this.currentLevel = level.next();
            if (!currentLevel.isFull())
                break;
        }
        return currentLevel.occupySpace();
    }

    

}
