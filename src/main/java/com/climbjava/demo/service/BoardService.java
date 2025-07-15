package com.climbjava.demo.service;

import com.climbjava.demo.domain.Board;
import com.climbjava.demo.domain.dto.Criteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.climbjava.demo.mapper.AttachMapper;
import com.climbjava.demo.mapper.BoardMapper;
import com.climbjava.demo.mapper.ReplyMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import util.MybatisUtil;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BoardService {
  private BoardMapper mapper; // 생성자 주입?
  private AttachMapper attachMapper;
  private ReplyMapper replyMapper;

	public List<Board> list(Criteria cri) {
			return mapper.list(cri); //현재 지정값 page 1 amount 10

	}

	public Board findBy(Long bno) {
			mapper.increaseCnt(bno);
			return mapper.selectOne(bno);
	}

//	public void increaseCnt(Long bno) {
//		try(SqlSession session = MybatisUtil.getSqlSession()){
//			BoardMapper mapper = session.getMapper(BoardMapper.class);
//			mapper.increaseCnt(bno);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
  @Transactional
	public void write(Board board) {
			Long bno = board.getBno();
			if(bno == null) { // 답글아닌 신규글
				mapper.insert(board);
				mapper.updateGrpMyself(board);
			} else { //답글일 경우
				//1. 부모글 조회 -> bno와 그 밖의 정보를 가져오기 위함
				Board parent = mapper.selectOne(bno);

				// 2. maxSeq 취득
				//select 필요

				int maxSeq = mapper.selectMaxSeq(parent);
				board.setSeq(maxSeq+ 1); //얘가 답답글인지 답글인지에 따라서 좀 조정이 필요하다. 복잡하다!

				// 3. 해당 조건의 게시글들의 seq 밀어내기
				board.setGrp(parent.getGrp()); //확정
				board.setDepth(parent.getDepth() + 1); // 확정
				mapper.updateSeqIncrease(board); //그렇기에 이 친구도 수정이 필요하다.

				// 4. insert 수행
				log.info("{}", board);
				mapper.insertChild(board);
			}

			board.getAttachs().forEach(a -> {
				a.setBno(board.getBno());
				attachMapper.insert(a);
			});
	}

	public long getCount(Criteria cri) {
			return mapper.getCount(cri);
	}
  @Transactional
	public void modify(Board board) {
			mapper.update(board);
			//기존 첨부파일 메타데이터 제거
			attachMapper.deleteByBno(board.getBno());

			//새로 첨부파일 메타데이터 등록
			board.getAttachs().forEach(a -> {
				a.setBno(board.getBno());
				attachMapper.insert(a);
			});
	}
  @Transactional
	public void remove(Long bno) {
			//기존 첨부파일 메타데이터 제거
			replyMapper.deleteByBno(bno);
			attachMapper.deleteByBno(bno);
			mapper.delete(bno);
	}
//
//
//
//	public static void main(String[] args) {
//		new BoardService().list(new Criteria()).forEach(b -> log.info("{}" , b.getTitle()));
//	}
	
}
