package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되지 않아 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 * 회원 저장소는 싱글톤 패턴을 적용했다.
 * 스프링을 사용하면 스프링 빈으로 등록하면 되지만, 지금은 최대한 스프링 없이 순수 서블릿 만으로 구현하는 것이 목적이다.
 */
public class MemberRepository {
    // static으로 선언되어 있기 때문에 MemberRepository 인스턴스가
    // 계속 생성되더라도 store, sequence는 하나만 생성된다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 싱글톤으로 생성
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    // 싱글톤 패턴은 객체를 단 하나만 생생해서 공유해야 하므로 생성자를 private 접근자로 막아둔다.
    private MemberRepository() {

    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // 스토어에 있는 모든 값들을 다 꺼내서 새로운 ArrayList에 담아 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
