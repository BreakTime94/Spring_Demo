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
@Alias("member")
public class Member {
	private Long no; //Primary Key
	private String id;
	private String pw;
	private String name;
	private String email; 
	private String regdate;
}
