package garage.model.garage;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Level {
    private String id;
    private int numOfSpaces;
    private boolean full;
    private PriorityQueue<Space> spaces;
    private Comparator<Space> comparator = new StringSumComparator();

    public Level(String id, int spaceCount) {
        this.id = id;
        full = false;
        numOfSpaces = spaceCount;
        spaces = new PriorityQueue<>(spaceCount, comparator);
        initSpaces();
    }

    public String getID() {
        return id;
    }

    private void initSpaces() {
        for(int i = 1; i <= numOfSpaces; i++) {
            spaces.add(new Space(id + i));
        }
    }

    public void addSpace(Space newSpace) {
        spaces.add(newSpace);
        full = false;
    }

    public Space occupySpace() {
        return this.spaces.poll();
    }

    public boolean isFull() {
        if (this.spaces.isEmpty())
            full = true;
        return full;
    }
}

class StringSumComparator implements Comparator<Space> {

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
