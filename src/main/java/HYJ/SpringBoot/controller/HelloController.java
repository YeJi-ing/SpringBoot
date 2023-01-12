package HYJ.SpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // 내장 톰켓 서버에서 가장 먼저 controller 로 연결
    // 이곳에 없으면, static 폴더로 가서 해당 이름의 html 파일을 찾는다.

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // mvc 방식: return 이 view 로 내려가서 template 엔진에서 hello-templete 을 찾음
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-templete";
    }

    // API 방식: view 없이 HttpMessageConverter(StringConverter or JsonConverter) 동작. return 문자 그대로 내려감
    @GetMapping("hello-string")
    @ResponseBody // 내가 직접 html body 부분에 해당내용을 넣겠다 == 기본 문자 그대로 넘겨라 => StringConverter 동작
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // name: spring => "hello spring"
    }

    // API 방식: JSON 방식 (key, value)
    @GetMapping("hello-api")
    @ResponseBody // 객체를 그대로 넘기는 상황 => JsonConverter 동작
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
