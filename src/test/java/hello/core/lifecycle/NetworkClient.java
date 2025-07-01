package hello.core.lifecycle;
/*
스프링 빈의 이벤트 라이프사이클
스프링 컨테이너 생성 - 스프링 빈 생성 - 의존관계 주입 - 초기화 콜백 - 애플리케이션 동작(사용) - 소멸전 콜백 - 스프링 종료

초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
소멸전 콜백: 빈이 소멸되기 직전에 호출
 */
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + ", " + "message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }
}
