package hello.core.singleton;

public class StatefulService {
  private int price; // 상태를 유지하는 필드
  private String name;

  public void order (String name, int price) {
    System.out.println("name = " + name + "price = " + price);
    this.price = price; // 여기가 문제다!
    this.name = name; // 여기가 문제다!
  }

  public int getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }
}
