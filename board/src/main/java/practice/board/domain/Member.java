package practice.board.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor //모든 컬럼 생성자 생성
@NoArgsConstructor // 기본 생성자
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 id생성
    private Long id;

    @Column(nullable = false, unique = true) //null값 허용 x , 중복값 x
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
