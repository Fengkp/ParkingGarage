package garage;

public class Space {
    private String id;

    public Space(String id) {
        this.id = id;
    }

    public String getID() {
        String spaceNum = id.toString();
        return spaceNum;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this.id.equals(o);
    }
}
