package teamPlay.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import db.community.model.vo.PostVO;
import teamPlay.model.vo.BorderVO;
import teamPlay.model.vo.PostitVO;
import teamPlay.service.PostitService;
import teamPlay.service.PostitServiceImp;

public class PostitController {


	private PostitService postitService = new PostitServiceImp();
	private Scanner scan;
	
	
	public PostitController(Scanner scan) {
		this.scan = scan;
	}

	public void writePostIt(String input_String) {
		// id@@content@@ 뭐가있지?
		
		
	
		
		
		
		
		
	
		
		
		
	}

	
	
	
	// Administrator only
	public boolean createNewBorder(String newBorder) {
		if (newBorder.length() == 0 || newBorder == null) {
			return false;
		}
		BorderVO border = selectBorder(newBorder);
		
		if (border != null) {
			System.out.println("[Duplicate Border Value]");
			return false;
		}
		
		if (postitService.createNewBorder(newBorder)) {
			System.out.println("create New Border");
			return true;
		} else {
			System.out.println("fail New Border");
			return false;
		}
		
	}

	public boolean deleteBorder(String deleteBorder) {
		if (deleteBorder.length() == 0 || deleteBorder == null) {
			return false;
		}
		if (postitService.deleteBorder(deleteBorder)) {
			System.out.println("Delete Border");
			return true;
		} else {
			System.out.println("Fail Delete Border");
			return false;
		}
		
	}

	public BorderVO selectBorder(String borderStr) {
		
		
		BorderVO border = postitService.selectBorder(borderStr);
		
		if (border == null) {
			System.out.println("doesn't exist");
			return null;
		}
		
		System.out.println(border.getBo_title());
		
		return border;
	}

	public void updateBorder(String borderStr) {
		
		BorderVO border = postitService.selectBorder(borderStr);
		
		if (border == null) {
			System.out.println("doesn't exist");
			return;
		}
		
		String oldTitle = border.getBo_title();
		
		System.out.print("set New Border Title\n>>");
		String title = scan.nextLine();
		
		System.out.print("set Border Detail\n>>");
		String Detail = scan.nextLine();
		
		if (title.length() == 0) {
			System.out.println("Null Border Title");
			return;
		}
		
		
		System.out.println(border.getBo_title());
		
		postitService.updateBorder(border, oldTitle, title, Detail);
	}

	public List<BorderVO> borderListOutput() {
		
		return postitService.selectBorderListOutput();
	}

	public ArrayList<PostitVO> postListOutput(String input_String) {
		// input_string에 페이지가 들어가있어야 함
		String arr[] = input_String.split("@@");
		
		
		
		return null;
	}

}
