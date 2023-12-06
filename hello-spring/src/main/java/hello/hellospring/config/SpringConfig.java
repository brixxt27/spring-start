package hello.hellospring.config;

import hello.hellospring.aop.TimeTraceAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
}
