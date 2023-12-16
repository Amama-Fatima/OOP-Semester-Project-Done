public class User {
    private int id;
    protected String firstName, lastName, region;

    public User(String firstName, String lastName, String region) {
        // No need to insert here; MongoDB will do it when the document is inserted
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
