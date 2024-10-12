package system;

import java.util.HashMap;
import java.util.Map;

public class IoCContainer {
    private Map<Class<?>, Object> container = new HashMap<>();

    // 객체 등록
    public <T> void register(Class<T> interfaceType, T implementationInstance) {
        container.put(interfaceType, implementationInstance);
    }

    // 의존성 주입된 객체 반환
    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> interfaceType) {
        return (T) container.get(interfaceType);
    }
}