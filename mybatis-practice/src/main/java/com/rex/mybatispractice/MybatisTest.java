package com.rex.mybatispractice;

import com.rex.mybatispractice.entity.User;
import com.rex.mybatispractice.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    public static void main(String[] args) throws IOException {
        mapperWithOutExtends();
    }

    private static void noMapper() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession sqlSession = sqlSessionFactory.openSession();){
            User user = sqlSession.selectOne("UserMapper.selectUser", 1);
            System.out.println(user);
        }
    }

    private static void mapperWithOutExtends() throws IOException {
//        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = userMapper.selectUser(1L);
        System.out.println(user1);
        User user2 = userMapper.selectUser(1L);
        System.out.println(user2);
    }
}
