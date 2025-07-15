package com.climbjava.demo.config.listener;

import com.climbjava.demo.mapper.CategoryMapper;
import com.climbjava.demo.util.PropsLoaderUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;

@WebListener
@Component
public class ContextPathListener implements ServletContextListener {
  @Autowired
  private CategoryMapper mapper;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext sc = sce.getServletContext();
    sc.setAttribute("cp", sc.getContextPath());
    sc.setAttribute("cate", mapper.list());

		Properties props = PropsLoaderUtil.getProperties("secret/aws_s3_properties");
		String s3url = String.format("https://%s.s3.%s.amazonaws.com/upload/", props.getProperty("bucket-name"), props.getProperty("region-name"));
		sc.setAttribute("s3url", s3url);

    //카테고리 정보를 apllication 영역객체에 보관
//		try(SqlSession session = MybatisUtil.getSqlSession()){
//			CategoryMapper mapper = session.getMapper(CategoryMapper.class);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}

  }

}
