package teamPlay.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import teamPlay.dao.PostitDAO;
import teamPlay.model.vo.BorderVO;



public class PostitServiceImp implements PostitService{

	private PostitDAO postitDao;
	
	public PostitServiceImp() {
		String resource = "teamPlay/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			postitDao = session.getMapper(PostitDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean createNewBorder(String newBorder) {
		boolean ok = false;
		
		try {
			ok = postitDao.insertNewBorder(newBorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteBorder(String deleteBorder) {
		boolean ok = false;
		
		try {
			ok = postitDao.deleteBorder(deleteBorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
	@Override
	public BorderVO selectBorder(String border) {
		
		if (border.length() == 0 || border == null) {
			return null;
		}
		
		return postitDao.selectBorder(border);
	}

	@Override
	public void updateBorder(BorderVO border, String oldTitle, String title, String detail) {
		
		
		if (postitDao.updateBorder(oldTitle,title,detail)) {
			System.out.println("Border update completed");
		} else {
			System.out.println("Border update fail");
		}
		
		
	}

	@Override
	public List<BorderVO> selectBorderListOutput() {
		
		return postitDao.selectBorderListOutput();
	}
}
