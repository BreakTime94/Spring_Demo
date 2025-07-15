package com.climbjava.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Alias("board")
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private Long bno; //Primary Key
	private String title;
	private String content;
	private String id;
	private String regdate;
	private String moddate;
	private Integer cnt;
	private Integer cno;
	private int replyCnt;
	private int attachCnt;
	
	//답글을 위한 3개의 필드
	private Long grp; //원글은 grp가 없다 null 일 수 있다.
	@Builder.Default
	private int seq = 1;
	@Builder.Default
	private int depth = 1;
	

	public Board(Long bno, String title, String content, String id, String regdate, String moddate, Integer cnt,
			Integer cno, int replyCnt, int attachCnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.id = id;
		this.regdate = regdate;
		this.moddate = moddate;
		this.cnt = cnt;
		this.cno = cno;
		this.replyCnt = replyCnt;
		this.attachCnt = attachCnt;
	}

	@Builder.Default
	private List<Attach> attachs = new ArrayList<>();
}
