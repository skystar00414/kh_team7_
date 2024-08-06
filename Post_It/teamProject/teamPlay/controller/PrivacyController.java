package teamPlay.controller;

import java.util.Scanner;

import teamPlay.model.vo.PrivacyVO;
import teamPlay.service.PrivacyService;
import teamPlay.service.PrivacyServiceImp;

public class PrivacyController {
	
	private PrivacyService privacyService = new PrivacyServiceImp();
	private Scanner scan = new Scanner(System.in);
	
	public PrivacyController(Scanner scan){
		this.scan = scan;
	}

	public boolean checkUser(String idCh) {
		
		if (privacyService.selectCheckID(idCh)) {
			System.out.println("[Log :" + idCh + " is now checked]");
			return true;
		} else {
			System.out.println("[Log :" + idCh + " is checking fail]");
			return false;
		}
	}

	public PrivacyVO loginUser(String input) {
		if (input.length() == 0 || input == null) {
			return null;
		}
		
		//id@@pw@@
		input = input.trim();
		String arrinput[] = input.split("@@");
		String idtmp = null;
		String pwtmp = null;
		
		PrivacyVO user = null;
		
		
		try {
			idtmp = arrinput[0];
			pwtmp = arrinput[1];
			user = privacyService.LoginUser(idtmp, pwtmp);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		return user;
	}

	public boolean joinUser(String input) {

		PrivacyVO user = null;
		
		try {
			String arr[] = input.split("@@");
			String id = arr[0];
			String pw = arr[1];
			String gender = arr[2];
			String birthday = arr[3];
			String phone = arr[4];
			String email = arr[5];
			String authority = "user";
			
			user = new PrivacyVO(id, pw, gender, birthday, phone, email, authority);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("join fail NullPointExeption");
			return false;
		}
		
		
		
		return privacyService.joinUser(user);
	}

	public void failedid(String failid) {
		if (failid.length() == 0 || failid == null) {
			return;
		}
		
		String arr[] = failid.split("@@");
		String id = arr[0];
		
		privacyService.failedIdUpdate(id);
	}

	public void succeedLogin(String pr_id) {
		
		privacyService.succeedLogin(pr_id);
	}

	
	
	
}
