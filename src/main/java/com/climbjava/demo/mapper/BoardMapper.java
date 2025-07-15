package com.climbjava.demo.mapper;

import com.climbjava.demo.domain.Board;
import com.climbjava.demo.domain.dto.Criteria;

import java.util.List;

public interface BoardMapper {
  List<Board> list(Criteria cri);

  Board selectOne(Long bno);

  void insert(Board board);

  long getCount(Criteria cri);

  void update(Board board);

  void delete(Long bno);

  void increaseCnt(Long bno);

  void updateGrpMyself(Board board);

  void updateSeqIncrease(Board parent);

  void insertChild(Board child);

  int selectMaxSeq(Board parent);
}
