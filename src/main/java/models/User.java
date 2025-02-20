package models;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private int id;
    private Role role;

    public User(String firstName, String lastName, String email, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }


    @Override
    public String toString() {
        return "[FirstName: " + "'" + firstName + "'," + " LastName: " + "'" + lastName + "'," +
                " Email: " + "'" + email + "'," + " Role: " + "'" + role + "'," + " ID: " + id + "]";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
