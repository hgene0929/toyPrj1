package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원 정보 수집 : 회원 등록 페이지로 넘겨주기 위한 컨트롤러
    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    //회원 등록 : 회원 등록 페이지에서 받은 회원 등록 정보를 넘겨주기 위한 컨트롤러
    @PostMapping(value = "members/new")
    public String create(@Valid MemberForm form, BindingResult result) { //@Valid 어노테이션을 통해 MemberForm 객체 내의 어노테이션 해석

        if(result.hasErrors()) { //BindingResult를 통해 오류를 담은채 코드가 실행될 수 있도록 한다
            return "members/createMemberForm";
        }

        //주소 생성
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        //회원 생성
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/"; //리다이렉트를 통해 홈으로 보내버린다.
    }

    //회원 목록 조회 : 회원 목록을 조회하기 위한 페이지를 넘겨주기 위한 컨트롤러
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
