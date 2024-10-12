package app;

import domain.model.IUserRepository;
import domain.model.User;
import domain.model.UserName;
import domain.service.UserService;

public class UserRegisterService {
    private final IUserRepository userRepository;
    private final UserService userService;

    public UserRegisterService(IUserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void register(UserRegisterCommand command) {
        UserName userName = new UserName(command.getUserName().getValue());

        User user = new User(userName);

        // 중복된 사용자인지 확인
        if (userService.exists(user)) {
            throw new IllegalArgumentException("cannot register user due to duplication");
        }

        userRepository.save(user);
    }
}
