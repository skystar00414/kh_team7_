package teamPlay.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import teamPlay.dao.PrivacyDAO;
import teamPlay.model.vo.PrivacyVO;

public class PrivacyServiceImp implements PrivacyService {
	
	private PrivacyDAO privacyDao;

	
	public PrivacyServiceImp() {
		String resource = "teamPlay/config/mybatis-config.xml";
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


	@Override
	public boolean selectCheckID(String idCh) {
		idCh = idCh.trim();
		if (idCh == null || idCh.length() == 0) {
			System.out.println("Log : IDcheck Null");
			return false;
		}
		
		boolean ok = false;
		try {
			ok = privacyDao.selectCheckPrivacyID(idCh);
		} catch (Exception e) {
			return false;
		}
		return ok;
	}


	@Override
	public PrivacyVO LoginUser(String idtmp, String pwtmp) {
		idtmp = idtmp.trim();
		pwtmp = pwtmp.trim();
		
		if (idtmp.length() == 0 || pwtmp.length() == 0) {
			System.out.println("[id, pw was null]");
			return null;
		}
		
		return privacyDao.selectLoginUser(idtmp, pwtmp);
	}


	@Override
	public boolean joinUser(PrivacyVO user) {
		if (user == null) {
			return false;
		}
		
		return privacyDao.insertJoinUser(user);
	}


	@Override
	public void failedIdUpdate(String id) {
		privacyDao.updateFailedId(id);
	}


	@Override
	public void succeedLogin(String pr_id) {
		privacyDao.updateSucceedId(pr_id);
	}
}
