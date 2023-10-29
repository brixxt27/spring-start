package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

// memberService 는 내부적으로 MemoryMemberRepository 를 가지고 있다.
// 하지만 ServiceTest 에서 Repository 를 사용하기 위해 해당 클래스 내에 MemoryMemberRepository 를 생성해줘야 한다.
// Service 내에서 Repository 를 직접 꺼내는 기능이 없기 때문이다.
// 단순히 아래처럼 진행해도 결과는 정상적으로 나오지만 엄밀히 이야기하면 서로 다른 repository 를 사용하기 때문에 설계가 변겯 되면 이후 에러가 생길 수 있다.
// 결과가 정상적으로 나오는 이유는 repository 내의 store 라는 Map 변수가 static 으로 설정 되어 모든 클래스가 이 변수를 공유하기 때문이다.
// 그래서 이를 해결하기 위해선 DI(Dependency injection) 을 사용하면 쉽게 해결할 수 있다.
@SpringBootTest
@Transactional // 테스트는 반복적으로 할 수 있어야 한다. Transaction 을 생성하여 이를 DB에 반영하지 않고 롤백 시킨다.
class MemberServiceTest {

/*
    private final MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
*/

//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
//    MemberService memberService = new MemberService(memberRepository);
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

        // junit 의 assertThrow 를 이용해 예외 객체
        // class 는 해당 클래스의 메타 데이터를 가지고 있는 'Class' 라는 이름의 객체이다.
/*
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("test1");
*/
        // 여러 줄 주석 option + cmd + /
        try {
//            memberService.join(member2);
        } catch (IllegalStateException error) {
//         then
            fail(error.getMessage());
        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}