package practice.board.service;

import org.springframework.validation.Errors;
import practice.board.dto.MemberResponseDTO;
import practice.board.dto.MemberSaveRequestDTO;

import java.util.List;
import java.util.Map;

public interface MemberService {
    Long join(MemberSaveRequestDTO memberFormDTO);

    /**
     * 회원가입 시, 유효성 및 중복 검사
     * @param errors
     * @return
     */
    Map<String, String> validateHandling(Errors errors);

    List<MemberResponseDTO> findMembers();
}
