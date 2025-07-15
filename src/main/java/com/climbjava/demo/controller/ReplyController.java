package com.climbjava.demo.controller;

import com.climbjava.demo.domain.Member;
import com.climbjava.demo.domain.Reply;
import com.climbjava.demo.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("reply")
@AllArgsConstructor
public class ReplyController {
  @GetMapping("test1")
  public String test1() {
    return "test1";
  }

  @GetMapping("test2")
  public Member test2() {
    return Member.builder().build();
  }



  private ReplyService replyService;

  @GetMapping("{rno}")
  public Reply get(@PathVariable Long rno){
    return replyService.findBy(rno);
  }

  @GetMapping("test3")
  public Reply test3() {
    return replyService.findBy(1L);
  }

  @GetMapping({"list/{bno}", "list/{bno}/{lastRno}"})
  public List<Reply> list(@PathVariable Long bno, @PathVariable(required = false) Long lastRno) {
    return replyService.list(bno, lastRno);
  }

  @PostMapping("/")
  public Map<String, Object> write(@RequestBody Reply reply) {
    replyService.register(reply);
    return Map.of("result", true,"reply", reply);
  }

  @PutMapping("/")
  public Map<String, Object> modify(@RequestBody Reply reply) {
    replyService.modify(reply);
    return Map.of("result", true,"reply", reply);
  }

  @DeleteMapping("{rno}")
  public ResponseEntity<Map<String, Object>> remove(@PathVariable Long rno) {
    replyService.remove(rno);
    return ResponseEntity.ok().body(Map.of("result", true));
  }
}
