package teamPlay.service;

import java.util.List;

import teamPlay.model.vo.BorderVO;
import teamPlay.model.vo.CategoryVO;
import teamPlay.model.vo.PostitVO;
import teamPlay.model.vo.PrivacyVO;

public interface PostitService {

	boolean createNewBorder(String newBorder);

	boolean deleteBorder(String deleteBorder);

	BorderVO selectBorder(String border);

	void updateBorder(BorderVO border, String oldTitle, String title, String detail);

	List<BorderVO> selectBorderListOutput();

	List<CategoryVO> selectCategoryListOutput(String input);

	List<PostitVO> selectPostListOutput(String input);

	PostitVO selectPostOutput(int postN);

	CategoryVO selectCategory(String input);

	void insertPostInput(int border, int category, int id, String title, String content);

	PrivacyVO selectId(String input);

}
