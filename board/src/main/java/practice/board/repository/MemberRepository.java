package practice.board.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import practice.board.domain.Member;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    private static final Logger log = LoggerFactory.getLogger(MemberRepository.class);
    private final EntityManager em;



    //.persist() 를 통해 객체를 엔티티를 영속화
    // 그후 , commit 시 디비에 저장.
    public Long save(Member member) {
        em.persist(member);
        log.info("Member saved: {}", member);
        return member.getId();
    }

    /**
     * 유효성 검사 - 중복 체크
     * @param email 회원 이메일
     * @return true if an entity with the given email exists, otherwise false
     */
    public boolean existByEmail(String email) {
        //jpql 정의a
        String jpql = "SELECT COUNT(m) FROM Member m WHERE m.email = :email";

        // em.createQuery() 함수를 사용해서 쿼리 생성하고,
        // createQuery() 함수는 jqpl 쿼리 문자열과 쿼리 결과의 타입을 인자로 받는다.
        // TypedQuery<Long>은 jpa 쿼리 결과 타입을 명시적으로 지정하는 인터페이스. 여기선 롱타입 반환.
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        // :email에 사용될 실제 값 설정.
        query.setParameter("email", email);

        //쿼리가 정확히 하나의 결과를 반환할때 사용 -> getSingleResult();
        Long count = query.getSingleResult();
        // 0보다 클경우 주어진 이메일을 가진 멤버가 존재
        // 이메일이 존재할 경우 true를 반환.

        log.info("Member with email count{} ", count);
        return count > 0;
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
