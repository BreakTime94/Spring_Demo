package com.climbjava.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("reply")
public class Reply {
	private Long rno; 
	private String content;
	private String id;
	private String regdate;
	private Long bno;
	
}

