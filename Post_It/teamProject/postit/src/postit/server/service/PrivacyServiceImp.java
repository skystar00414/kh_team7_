package postit.server.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import postit.server.dao.PrivacyDAO;

public class PrivacyServiceImp implements PrivacyService{

    private PrivacyDAO privacyDao;

    public PrivacyServiceImp() {
		String resource = "db/community/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			privacyDao = session.getMapper(PrivacyDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}
