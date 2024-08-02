package teamPlay.service;

import teamPlay.model.vo.BorderVO;

public interface PostitService {

	boolean createNewBorder(String newBorder);

	boolean deleteBorder(String deleteBorder);

	BorderVO selectBorder(String border);

	void updateBorder(BorderVO border, String oldTitle, String title, String detail);

}