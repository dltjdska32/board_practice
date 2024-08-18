package practice.board.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import practice.board.domain.Member;
import practice.board.domain.Role;
import practice.board.dto.MemberResponseDTO;
import practice.board.dto.MemberSaveRequestDTO;
import practice.board.repository.MemberRepository;
import practice.board.service.GlobalService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class GlobalServiceImpl implements GlobalService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Long join(MemberSaveRequestDTO memberSaveRequestDTO) {
        memberSaveRequestDTO.setPassword(passwordEncoder.encode(memberSaveRequestDTO.getPassword()));

        Member member = Member.builder()
                .email(memberSaveRequestDTO.getEmail())
                .username(memberSaveRequestDTO.getUsername())
                .password(memberSaveRequestDTO.getPassword())
                .role(Role.ROLE_USER)
                .build();

        log.info( member.getUsername());
        return memberRepository.save(member);
    }


    // 컨트롤러에서 중복성 검사를 통해 에러가 있으면 호출.
    /* 회원가입 시, 유효성 및 중복 검사 */
    @Transactional(readOnly = true)         // 읽기 전용 트랜잭션 쓰기(저장 ,수정) 수행 불가
    @Override
    public Map<String, String> validateHandling(Errors errors) {
        // 키와 밸류값을 같는 해쉬맵
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 및 중복 검사에 실패한 필드 목록을 받음 */
        //errors.getFieldErrors() 를 통해서 오류정보를 포함하는 리스트를 반환한다.
        // for루프를 통해 각각의 에러를 키와 벨류로 만들고 해쉬맵에담는다.
        // 이메일 형식 오류나, 이메일 중복 오류 등등..
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }


    @Override
    public void messageHandling(Errors errors, Model model) {
        Map<String, String> validatorResult = validateHandling(errors);
        for (String key : validatorResult.keySet()) {
            model.addAttribute(key, validatorResult.get(key));
        }
    }

    @Override
    public List<MemberResponseDTO> findMembers() {
        List<Member> all = memberRepository.findAll();
        List<MemberResponseDTO> members = new ArrayList<>();

        for (Member member : all) {
            MemberResponseDTO build = MemberResponseDTO.builder()
                    .member(member)
                    .build();
            members.add(build);

        }
        return members;
    }

}
