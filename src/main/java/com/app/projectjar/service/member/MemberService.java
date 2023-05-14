package com.app.projectjar.service.member;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.repository.member.MemberRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface MemberService extends UserDetailsService {
//    회원가입
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder);

//    이메일 중복 검사
    public Long checkEmail(String memberEmail);

//    휴대폰 번호 중복 검사
    public Long checkPhoneNumber(String memberPhoneNumber);

//    비밀번호 변경
    public
//    비밀번호 찾기

    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder().
                id(memberDTO.getId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberPhoneNumber(memberDTO.getMemberPhoneNumber())
                .memberName(memberDTO.getMemberName())
                .memberNickname(memberDTO.getMemberNickname())
                .memberStatus(memberDTO.getMemberStatus())
                .badgeType(memberDTO.getBadgeType())
                .memberType(memberDTO.getMemberType())
                .build();
    }
}
