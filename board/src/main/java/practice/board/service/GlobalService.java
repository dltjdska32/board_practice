package practice.board.service;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import practice.board.dto.MemberResponseDTO;
import practice.board.dto.MemberSaveRequestDTO;

import java.util.List;
import java.util.Map;

public interface GlobalService {
    Long join(MemberSaveRequestDTO memberFormDTO);

    /**
     * 회원가입 시, 유효성 및 중복 검사
     * @param errors
     * @return
     */
    Map<String, String> validateHandling(Errors errors);


    /**
     * 중복 검사 및 유효성 검사 에러 메세지 핸들링
     * @param errors
     * @param model
     */
    void messageHandling(Errors errors, Model model);

    List<MemberResponseDTO> findMembers();
}
