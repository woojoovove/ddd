package domain.service;

import domain.model.IUserRepository;
import domain.model.User;

public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean exists(User user) {
        User found = userRepository.findOrNull(user.getUserName());

        return found != null;
    }
}
