package test;

import app.UserRegisterCommand;
import app.UserRegisterService;
import domain.infra.InMemoryUserRepository;
import domain.model.IUserRepository;
import domain.model.UserName;
import domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import system.IoCContainer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 1. 각 테스트에서 새로운 Repo 인스턴스를 가져와서 사용하는 이유
 * == 테스트 간 데이터 독립성 : 모든 테스트가 동일한 인스턴스를
 *      공유하면 하나의 테스트에서 변경된 데이터가 다른 테스트에 영향을 줄 수 있음
 *      특히 InMemoryRepository는 HashMap으로 메모리에 사용자 데이터를 가지고 있으므로
 *      테스트마다 새로운 Repo를 쓰지 않으면 테스트 간에 사용자 데이터를 공유하게 됨.
 *
 * 2. Repo를 클래스의 멤버 변수로 두고, setUp()에서 새로운 인스턴스를 할당하는 방법
 *    vs. 각 테스트 메소드에서 Repo를 새로 선언하는 방법
 *      -> 테스트간 독립성이나 테스트마다 다른 Repo를 사용하고 싶으면 후자
 *      -> 모든 테스트에 동일한 Repo를 사용하는 경우에 코드 중복을 철저히고 싶으면 전자
 */
public class UserRegisterTest {
    private IoCContainer container;
    private UserRegisterService userRegisterService;

    @BeforeEach
    public void setUp() {
        container = new IoCContainer();
        container.register(IUserRepository.class, new InMemoryUserRepository());
        container.register(UserService.class, new UserService(
                container.resolve(IUserRepository.class)));
        container.register(UserRegisterService.class, new UserRegisterService(
                        container.resolve(IUserRepository.class),
                        container.resolve(UserService.class)));
        userRegisterService = container.resolve(UserRegisterService.class);
    }

    @Test
    public void registerMinimumLengthUserName() {
        UserName userName = new UserName("123");
        UserRegisterCommand minUserNameInputData = new UserRegisterCommand(userName);
        userRegisterService.register(minUserNameInputData);

        IUserRepository userRepository = container.resolve(IUserRepository.class);
        assertNotNull(userRepository.findOrNull(userName));
    }

    @Test
    public void createLessThanMinimumLengthUserName() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            UserName userName = new UserName("12");
        });
        String expectedMessage = "UserName must be longer than 3 letters";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
