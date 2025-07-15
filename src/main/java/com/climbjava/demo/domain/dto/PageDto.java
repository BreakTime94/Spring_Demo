package com.climbjava.demo.domain.dto;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PageDto {
	private Criteria cri;
	private long total;
	
	private int start;
	private int end;
	
	private boolean left;
	private boolean right;
	
	private boolean doubleLeft;
	private boolean doubleRight;
	
	private int realEnd;
	
	public PageDto(Criteria cri, long total) {
		this.cri = cri;
		this.total = total; //게시글 총 갯수 
		//end값을 먼저 계산 및 start 유도
		//1 ~ 10page는 그대로 보여주고
		//11page 부터 11 ~ 20이다.
		
		end = (cri.getPage() + 9) / 10 * 10 ; 
		start = end - 9;
		realEnd = (int)((total + cri.getAmount() - 1)/cri.getAmount());
		
		//118 12개 119 12개 120 12개 121 13개
		
		if(end > realEnd) {
			end = realEnd;
		}
		
		left = start > 1;
		right = end < realEnd;
		
		doubleLeft = cri.getPage() > 1;
		doubleRight = cri.getPage() < realEnd;
		
	}
}
