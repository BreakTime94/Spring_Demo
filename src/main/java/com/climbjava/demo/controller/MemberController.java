package com.climbjava.demo.controller;

import com.climbjava.demo.domain.Member;
import com.climbjava.demo.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("member")
@AllArgsConstructor
public class MemberController {
  private MemberService memberService;
  @GetMapping("login")
  public void loginForm() { // 반환 타입이 void인 경우에는 req.getDispatcher의 forward를 알아서 진행해준다.

  }

  @GetMapping("register")
  public void registerForm() {}

  @PostMapping("login")
  public String login(Member member, HttpSession session, RedirectAttributes redirectAttributes) { //이제 이러면 파라미터를 수집한다.
    log.info("{}", member);
    if(memberService.login(member.getId(), member.getPw())) {
      session.setAttribute("member", memberService.findById(member.getId()));
      return "redirect:/";
    }
      redirectAttributes.addFlashAttribute("msg", "로그인 실패");
      return "redirect:/member/login";
  }

  @PostMapping("register")
  public String register(Member member) {
    memberService.register(member);
    return "redirect:/";
  }

  @RequestMapping("logout") //HttpMethod.GET 옛날 방식 vale= "logout", method=RequestMethod.GET
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }


}
