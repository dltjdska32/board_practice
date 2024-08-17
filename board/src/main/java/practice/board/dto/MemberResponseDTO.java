package practice.board.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.board.domain.Member;

@Getter
@NoArgsConstructor
public class MemberResponseDTO {
    private String email;
    private String username;

    @Builder
    public MemberResponseDTO(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
    }
}
