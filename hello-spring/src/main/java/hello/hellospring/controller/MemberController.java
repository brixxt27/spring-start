package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
   private final MemberService memberService;

   // 아래와 같은 방식으로 Component 등록을 한다. 이는 컴포넌트 스캔과 자동 의존관계 설정이라 부른다.
   @Autowired // 멤버 컨트롤러 객체가 셍성될 때 해당 생성자를 실행하는데 스프링 빈(spring bean)에 등록되어 있는 MemberService 객체를 가져와서 설정한다.
    public MemberController(MemberService memberService) {
       this.memberService = memberService;
   }

   @GetMapping(value = "/members/new")
   public String createForm() {
      return "members/createMemberForm";
   }

   @PostMapping(value = "members/new")
   public String create(MemberForm form) {
      Member member = new Member();

      member.setName(form.getName());
      memberService.join(member);
      return "redirect:/";
   }
}
