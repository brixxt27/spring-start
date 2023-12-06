package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    // final 은 한 번 할당 되면 더이상 변경되지 않는다는 의미이다.
    private final MemberRepository memberRepository;

    // DI, 의존성 주입
//    @Autowired // Component 스캔 방식을 사용하면 꼭 이 방식으로 의존성을 해결해야 한다.
    public MemberService(MemberRepository repository) {
        this.memberRepository = repository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicatedName(member);
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicatedName(Member member) { // cmd + b 는 현재 파일에서 해당 메소드를 사용한 곳이나 선언한 곳으로 이동
        memberRepository.findByName(member.getName())
                .ifPresent(duplicatedNameMember -> {
                    throw new IllegalStateException(duplicatedNameMember.getName() + " 은(는) 이미 존재하는 닉네임입니다.");
                });
    }

    /**
     * 모든 멤버 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * member id 로 특정 멤버 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
