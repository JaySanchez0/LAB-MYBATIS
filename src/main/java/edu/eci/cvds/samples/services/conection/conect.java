package edu.eci.cvds.samples.services.conection;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class conect {
	 public static SqlSessionFactory getSqlSessionFactory() {
	        SqlSessionFactory sqlSessionFactory = null;
	        if (sqlSessionFactory == null) {
	            InputStream inputStream;
	            try {
	                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
	                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	            } catch (IOException e) {
	                throw new RuntimeException(e.getCause());
	            }
	        }
	        return sqlSessionFactory;
	    }
}
