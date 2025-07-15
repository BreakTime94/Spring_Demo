package com.climbjava.demo.service;

import com.climbjava.demo.domain.Member;
import com.climbjava.demo.util.PasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.climbjava.demo.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
//import util.MybatisUtil;
//import util.PasswordEncoder;

@Slf4j
@AllArgsConstructor
@Service
public class MemberService {
  private MemberMapper mapper;
  private PasswordEncoder encoder;
  public int register(Member member) {
    member.setPw(encoder.encode(member.getPw()));
    return mapper.insert(member);
  }
  public Member findById(String id) {
    return mapper.findById(id);
  }
  public boolean login(String id, String pw) {
    Member member = findById(id);
    if (member == null) {
      return false;
    }
    return encoder.matches(pw, member.getPw());
  }
}
