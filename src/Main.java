import app.UserRegisterCommand;
import app.UserRegisterService;
import domain.infra.InMemoryUserRepository;
import domain.model.IUserRepository;
import domain.model.UserName;
import domain.service.UserService;
import system.IoCContainer;

public class Main {
    public static void main(String[] args) {

        //  IoC Container 생성
        IoCContainer container = new IoCContainer();


        container.register(IUserRepository.class, new InMemoryUserRepository());
        container.register(UserService.class, new UserService(container.resolve(IUserRepository.class)));
        container.register(UserRegisterService.class, new UserRegisterService(container.resolve(IUserRepository.class), container.resolve(UserService.class)));

        UserRegisterService userRegisterService = container.resolve(UserRegisterService.class);

        UserName newUserName = new UserName("wawa");
        UserRegisterCommand command = new UserRegisterCommand(newUserName);
        userRegisterService.register(command);
    }
}