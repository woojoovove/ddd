package domain.model;

public class UserName {
    private final String value;

    public UserName(String value) {
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        if (value.length() < 3) throw new IllegalArgumentException("UserName must be longer than 3 letters");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
