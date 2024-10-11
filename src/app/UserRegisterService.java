package domain.service;

import domain.model.IUserRepository;
import domain.model.User;
import domain.model.UserName;

public class UserRegisterService {
    private final IUserRepository userRepository;
    private final UserService userService;

    public UserRegisterService(IUserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void handle(UserRegisterCommand command) {
        UserName userName = new UserName(command.getName());

        User user = new User(userName);

        // 중복된 사용자인지 확인
        if (userService.exists(user.getUserName())) {
            throw new CanNotRegisterUserException(user, "이미 등록된 사용자입니다.");
        }

        userRepository.save(user);
    }
}
