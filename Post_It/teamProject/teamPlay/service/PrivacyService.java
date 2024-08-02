package teamPlay.service;

import teamPlay.model.vo.PrivacyVO;

public interface PrivacyService {

	boolean selectCheckID(String idCh);

	PrivacyVO LoginUser(String idtmp, String pwtmp);

	boolean joinUser(PrivacyVO user);

	void failedIdUpdate(String id);

	void succeedLogin(String pr_id);

}
