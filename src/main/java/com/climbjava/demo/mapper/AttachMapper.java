package com.climbjava.demo.mapper;

import com.climbjava.demo.domain.Attach;


import java.util.List;


public interface AttachMapper {
	void insert(Attach attach);
	List<Attach> list(Long bno);
	Attach selcectOne(String uuid);
	void delete (String uuid);
	void deleteByBno(Long bno);
	
	List<Attach> selectYesterdayList();
}
