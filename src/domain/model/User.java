package domain.model;

import java.util.UUID;

public class User {
    private final UserID userID;
    private final UserName userName;

    public User(UserID userID, UserName userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public User(UserName userName) {
        this.userID = new UserID(UUID.randomUUID().toString());
        this.userName = userName;
    }

    public UserID getUserID() {
        return userID;
    }

    public UserName getUserName() {
        return userName;
    }
}
