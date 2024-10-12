package domain.infra;

import domain.model.IUserRepository;
import domain.model.User;
import domain.model.UserID;
import domain.model.UserName;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements IUserRepository {
    // 테스트 케이스 확인을 위해 외부에서 접근할 수 있게 public으로 둡니다.
    private Map<UserID, User> store = new HashMap<>();

    @Override
    public User findOrNull(UserName userName) {
        // 사용자 이름으로 대상을 찾습니다.
        for (User user : store.values()) {

            if (userName.getValue().equals(user.getUserName().getValue())) {
                // 찾은 사용자의 깊은 복사본을 반환합니다.
                return cloneUser(user);
            }
        }
        return null; // 사용자를 찾지 못하면 null을 반환합니다.
    }

    @Override
    public void save(User user) {
        // 저장할 때 깊은 복사를 수행합니다.
        store.put(user.getUserID(), cloneUser(user));
    }

    // 사용자 객체의 깊은 복사를 담당하는 메서드
    private User cloneUser(User user) {
        return new User(user.getUserID(), user.getUserName());
    }
}
