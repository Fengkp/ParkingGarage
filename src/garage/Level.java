package garage;

import java.util.PriorityQueue;

public class Level {
    private String id;
    private PriorityQueue<Space> spaces;
    private int numOfSpaces;
    private boolean full;

    public Level (String id, int numOfSpaces) {
        this.id = id;
        this.numOfSpaces = numOfSpaces;
        spaces = new PriorityQueue<>(numOfSpaces);
    }

    private void addSpace() {
        this.full = isFull();
    }

    private boolean removeSpace() {
        return false;
    }

    public boolean isFull() {
        return this.spaces.isEmpty();
    }


}
