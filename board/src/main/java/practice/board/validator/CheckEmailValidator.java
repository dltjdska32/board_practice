package practice.board.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.executable.ExecutableValidator;
import jakarta.validation.metadata.BeanDescriptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import practice.board.dto.MemberSaveRequestDTO;
import practice.board.repository.MemberRepository;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CheckEmailValidator extends AbstractValidator<MemberSaveRequestDTO> {

    private final MemberRepository memberRepository;

    // 유효성 검사 로직
    @Override
    protected void doValidate(MemberSaveRequestDTO dto, Errors errors) {
        if (memberRepository.existByEmail(dto.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }



}
