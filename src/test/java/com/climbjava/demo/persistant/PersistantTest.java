package com.climbjava.demo.persistant;

//import com.climbjava.demo.mapper.TestMapper;
import com.zaxxer.hikari.HikariConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
@Slf4j
public class PersistantTest {

    @Autowired
    private HikariConfig hikariConfig;
    @Autowired
    private DataSource dataSource;
//    @Autowired
//    private TestMapper testMapper;

    @Test
    public void testHikariConfig(){
        log.info("hikari :: {}",hikariConfig);
    }

    @Test
    public void testDataSource() {
        log.info("ds :: {}", dataSource);
    }

//    @Test
//    public void testTestMapper() {
//        log.info("testMapper :: {}", testMapper);
//        log.info(testMapper.now());
//    }



}
