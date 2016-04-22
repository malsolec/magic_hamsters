package com.magic_hamsters.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wookie on 4/22/16.
 */
public class SqlSessionFactoryProvider {

    @Produces
    @ApplicationScoped
    public SqlSessionFactory produceFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream reader = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        return sqlSessionFactory;
    }
}
