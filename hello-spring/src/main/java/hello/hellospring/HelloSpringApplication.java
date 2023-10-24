package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // component scan을 사용해 해당 패키지(hello.hellospring) 하위에 존재하는 모든 파일에서 컴포넌트를 찾아 등록한다.
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
