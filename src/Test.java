import garage.Level;
import garage.Space;

public class Test {

    public static void main(String[] args) {
        Level lvl = new Level();
        Space space1 = new Space("A2");
        lvl.addSpace(space1);
        Space space2 = new Space("A4");
        lvl.addSpace(space2);
        Space space3 = new Space("A1");
        lvl.addSpace(space3);
        Space space4 = new Space("A3");
        lvl.addSpace(space4);

        lvl.showQueue();



    }
}
