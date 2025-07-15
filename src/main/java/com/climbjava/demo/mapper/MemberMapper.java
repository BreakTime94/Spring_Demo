package com.climbjava.demo.mapper;

import com.climbjava.demo.domain.Member;

import java.util.List;

public interface MemberMapper {
	
	List<Member> select();
	
	Member findByNo(Long no);
	Member findById(String id);
	
	int insert(Member member);
	
	int delete(Long no);
	
	int update(Member member);
}
