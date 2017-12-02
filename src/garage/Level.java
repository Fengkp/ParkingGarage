package garage;

<<<<<<< HEAD
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Level {
    private PriorityQueue<Space> spaces;
    Comparator<Space> comparator = new StringSumComparator();

    public Level() {
        this.spaces = new PriorityQueue<>(10, comparator);
    }

    public void addSpace(Space newSpace) {
        this.spaces.offer(newSpace);
    }

    public void showQueue() {
        Iterator<Space> space = this.spaces.iterator();

        while(space.hasNext())
            System.out.println(space.next().getID());

    }
}

class StringSumComparator implements Comparator<Space> {

    @Override
    public int compare(Space o1, Space o2) {
        int o1Sum = o1.getID().chars().sum();
        int o2Sum = o2.getID().chars().sum();
=======
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

>>>>>>> 3d7df89466d8a29fa037e65a0cbd09e10c2518a0

        if (o1Sum > o2Sum)
            return 1;
        else if (o1Sum < o2Sum)
            return -1;
        return 0;
    }
}
