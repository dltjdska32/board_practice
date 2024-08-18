package practice.board.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.board.domain.Member;
import practice.board.domain.Role;

@Getter
@NoArgsConstructor
public class MemberResponseDTO {
    private String email;
    private String username;
    private Role role;

    @Builder
    public MemberResponseDTO(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.role = member.getRole();
    }
}
