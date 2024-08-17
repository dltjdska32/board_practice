package practice.board.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //기본생성자
@AllArgsConstructor  // 모든 필드를 받는 생성자
public class MemberSaveRequestDTO {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @Size(min = 2, max = 15, message = "닉네임은 2 ~ 15자 사이로 입력해주세요.")
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String username;
}
