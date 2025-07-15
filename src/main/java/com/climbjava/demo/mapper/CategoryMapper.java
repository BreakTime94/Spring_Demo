package com.climbjava.demo.mapper;

import com.climbjava.demo.domain.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {
	@Select("select * from tbl_category tc where tc.status = 'ACTIVE' order by odr")
	List<Category> list();
}
