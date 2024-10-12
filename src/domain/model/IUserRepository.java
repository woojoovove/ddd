package domain.model;

public interface IUserRepository {
    void save(User user);
    User findOrNull(UserName userName);
}
