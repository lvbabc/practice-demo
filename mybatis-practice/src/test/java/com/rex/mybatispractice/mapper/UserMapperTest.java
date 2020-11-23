package com.rex.mybatispractice.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private UserMapper userMapper;
    private SqlSession sqlSession;

    @BeforeEach
    void setUp() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        sqlSession = sqlSessionFactory.openSession();
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        this.userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    void selectUser() {
        userMapper.selectUser(1L);
    }
}