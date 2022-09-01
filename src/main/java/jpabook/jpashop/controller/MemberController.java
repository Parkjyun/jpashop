package jpabook.jpashop.controller;

import jpabook.jpashop.Service.MemberService;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor//final이 붙은 필드의 생성자를 자동으로 생성해준다.

public class MemberController {

    private final MemberService memberService;


    @GetMapping("/members/new")
    public String createForm(Model model){//model?controller에서 view로 넘어갈때 model의 데이터를 넘김
        //이제 creatememberform에서는 변수명 memberform을 사용할 수 있고 그 변수의 값은 new MemberForm()으 생성된 객체가 들어간다
        model.addAttribute("memberForm", new MemberForm());//view로 넘어갈때 MemberForm의 빈객체를 들고김
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {//@valid -> memberform의 @notempty를 사용할 것임을 알림 그래서
        if(result.hasErrors()) {
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
