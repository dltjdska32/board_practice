package practice.board.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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


    private String username;

    @Column
    private String password;
}
