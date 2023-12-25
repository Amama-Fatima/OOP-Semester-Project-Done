import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private static int count;
    protected String firstName, lastName, region;

    public User(String firstName, String lastName, String region) {
        count++;
        this.id = count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRegion() {
        return region;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
