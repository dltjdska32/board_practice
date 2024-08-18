package practice.board.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import practice.board.domain.Member;
import practice.board.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");
        }
        return toUserDetails(member);
    }

    // 멤버 객체를 찾아서 user로 build
    private UserDetails toUserDetails(Member member) {
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .authorities(new SimpleGrantedAuthority(member.getRole().toString()))
                .build();
    }
}
