package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// ComponentScan 자동스캔으로 bean을 알아서 넣어준다.
// excludeFilters 스캔 제외
@ComponentScan(excludeFilters = @ComponentScan.Filter(
    type = FilterType.ANNOTATION, classes = Configuration.class),
    basePackages = "hello.core"
)
public class AutoAppConfig {
//  @Bean("memoryMemberRepository")
//  MemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//  }
}
