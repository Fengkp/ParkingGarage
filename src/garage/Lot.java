package garage;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class Lot {
    private LinkedList<Level> levels;
    
    private Level currentLevel;
    private int numberOfSpaces;
    private boolean isFull;

    public void addLevel() {
        Level newLevel = new Level();
        Level prevLevel = new Level();
        this.currentLevel = newLevel;

        if (levels.isEmpty()) {
            newLevel.setDistanceToEntrance(0);
            levels.addFirst(newLevel);
        }

        else {
            levels.add(newLevel);
            currentLevel.setDistanceToEntrance(prevLevel.getDistanceToEntrance() + 10);
        }
    }
}
