package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드 - 10000원 에서 20000원으로 변경됨

//    public void order(String name, int price) {
//        System.out.println("name = " + name + ", price = " + price);
//        this.price = price; // 문제가 발생하는 부분(인스턴스가 가지고 있는 필드의 상태를 변경함)
//    }

    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        return price; // 지역변수를 반환하도로 변경
    }

//    public int getPrice() {
//        return price;
//    }
}
