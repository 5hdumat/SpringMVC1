package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @ServletComponentScan (스프링부트가 지원하는 서블릿 애노테이션)
 * 스프링이 자동으로 현재 내 hello.servlet 패키지의 하위 패키지(본인 포함)에 들어있는 서블릿을
 * 다 찾아서 등록, 실행 할 수 있도록 도와주는 역할
 */
@ServletComponentScan
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

}
