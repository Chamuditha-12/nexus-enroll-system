package nexusenroll.models;

public class Administrator extends User {
    public Administrator(String id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "Administrator";
    }
}