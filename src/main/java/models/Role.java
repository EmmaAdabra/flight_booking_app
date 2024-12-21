package models;

public enum Role {
    USER,
    ADMIN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
