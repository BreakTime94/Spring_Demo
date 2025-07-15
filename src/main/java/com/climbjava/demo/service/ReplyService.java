package com.climbjava.demo.service;

import com.climbjava.demo.domain.Reply;
import com.climbjava.demo.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import util.MybatisUtil;

import java.util.List;

@Service
@AllArgsConstructor
public class ReplyService {
  private ReplyMapper mapper;

  public Reply findBy(Long rno) {
    return mapper.selectOne(rno);
  }

  public void register(Reply reply) {
    mapper.insert(reply);
  }

  public List<Reply> list(Long bno, Long lastRno) {
    return mapper.list(bno, lastRno);
  }

  public void remove(Long rno) {
    mapper.delete(rno);
  }

  public void modify(Reply reply) {
    mapper.update(reply);
  }
}
