package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // 테스트는 반복적으로 할 수 있어야 한다. Transaction 을 생성하여 이를 DB에 반영하지 않고 롤백 시킨다.
class MemberServiceIntegrationTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;

    // 이제는 스프링 컨테이너에게 객체를 달라고 해야 한다. DI를 통해 객체를 가져오는데 대표적으로 생성자 방식과 필드 방식이 있다.
//    @BeforeEach
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long memberId = memberService.join(member);

        // then
        assertThat(member.getId()).isEqualTo(memberId); // static import 하여 Assertions 이 전역으로 import 됨
    }

    @Test
    public void 중복_닉네임_검사() {
        // given
        Member member1 = new Member(); // shift + F6 으로 현재 자신의 라인부터 함수 마지막 라인까지의 동일한 단어를 변경 가능하다.
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        // when
        memberService.join(member1);

        // 여러 줄 주석 option + cmd + /
        try {
//            memberService.join(member2);
        } catch (IllegalStateException error) {
//         then
            fail(error.getMessage());
        }
    }
//
//    @Test
//    void findMembers() {
//    }
//
//    @Test
//    void findOne() {
//    }
}