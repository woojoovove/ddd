package domain.model;

public class UserID {
    private String value;

    public UserID(String value) {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("Value cannot be null");
        this.value = value;
    }
}
