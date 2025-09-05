package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); // 자기자신을 인스턴스 객체로 딱 하나만 생성함 -> 런타임 실행시, static 영역에 객체 인스턴스가 생성되서 올라옴

    public static SingletonService getInstance() { // 외부에서 인스턴스의 참조를 꺼낼 수 있는 유일한 방법 -> static 메서드 호출
        return instance;
    }

    private SingletonService() { // 외부에서 호출하지 못하도록, 생성자를 내부에서 private 로 선언하여 호출함
    }

    public void login() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
