package practice.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import practice.board.dto.MemberResponseDTO;
import practice.board.dto.MemberSaveRequestDTO;
import practice.board.service.MemberService;
import practice.board.validator.CheckEmailValidator;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final CheckEmailValidator checkEmailValidator;

    // 유효성 검증
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkEmailValidator);
    }


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/members/new")
    public String createMemberForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(@Valid MemberSaveRequestDTO memberFormDTO, Errors errors, Model model) {

        //검증
        if(errors.hasErrors()) {

            log.info(errors.getAllErrors().toString());
            //회원 가입 실패시 입력 데이터 유지
            model.addAttribute("dto", memberFormDTO);

            //유효성 검사 통과 못한 필드 , 메세지 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for(String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }


            //회원가입 페이지로 리턴
            return "members/createMemberForm";
        }


        Long memberId = memberService.join(memberFormDTO);
        return "home";
    }

    @GetMapping("/members")
    public String members(Model model) {
        List<MemberResponseDTO> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
