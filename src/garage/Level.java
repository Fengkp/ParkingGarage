package garage;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Level {
    private String id;
    private int numOfSpaces;
    private boolean full;
    private PriorityQueue<Space> spaces;
    private Comparator<Space> comparator = new StringSumComparator();

    public Level(String id, int spaceCount) {
        this.id = id;
        this.full = false;
        this.numOfSpaces = spaceCount;
        this.spaces = new PriorityQueue<>(spaceCount, comparator);
        this.initSpaces();
    }

    private void initSpaces() {
        for(int i = 1; i <= numOfSpaces; i++) {
            spaces.add(new Space(this.id + i));
        }
    }

    public void addSpace(Space newSpace) {
        this.spaces.add(newSpace);
        this.full = false;
    }

    public Space occupySpace() {
        return this.spaces.poll();
    }

    public boolean isFull() {
        if (this.spaces.isEmpty())
            this.full = true;
        return this.full;
    }

    public void traverseSpaces() {
        Iterator<Space> space = this.spaces.iterator();

        while(space.hasNext())
            space.next();
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
