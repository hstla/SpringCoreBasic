package hello.core;
// 중복 제거와 역할과 구현 클래스가 한눈에 들어온다.
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPlicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("call AppConfig.memberrRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public DiscountPolicy discountPolicy() {
//  return new FixDiscountPolicy();
    return new RateDiscountPlicy();
  }

  @Bean
  public MemberService memberService() {
    System.out.println("call AppConfig.memberService");
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public OrderService orderService() {
    System.out.println("call AppConfig.orderService");
    return new OrderServiceImpl(
        memberRepository(),
        discountPolicy());
  }
}