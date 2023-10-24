package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // HTTP 응답 메시지의 body에 값을 넣어 반환
    public String helloString(@RequestParam("name") String name) {
        return "HTTP Body: hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();

        hello.setName(name);
        return hello;
    }

    static class Hello { // java bean 표준 방식(getter, setter), 프로퍼티 접근 방식
        private String name;

        // cmd + n 에서 getter, setter 추가를 쉽게 할 수 있다.
        public String getName() { // getter 가 없으면 해당 프로퍼티에 HttpMessageConverter 가 접근할 수 없다.
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
