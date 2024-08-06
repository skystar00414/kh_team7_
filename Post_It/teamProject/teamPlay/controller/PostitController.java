package teamPlay.controller;

import java.util.List;
import java.util.Scanner;

import teamPlay.model.vo.BorderVO;
import teamPlay.model.vo.CategoryVO;
import teamPlay.model.vo.PostitVO;
import teamPlay.model.vo.PrivacyVO;
import teamPlay.service.PostitService;
import teamPlay.service.PostitServiceImp;

public class PostitController {


	private PostitService postitService = new PostitServiceImp();
	private Scanner scan;
	
	
	public PostitController(Scanner scan) {
		this.scan = scan;
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

	public List<PostitVO> postListOutput(String input) {

		return postitService.selectPostListOutput(input);
	}

	public List<CategoryVO> categoryListOutput(String input) {
		
		return postitService.selectCategoryListOutput(input);
	}

	public PostitVO postitDetail(int postN) {
		
		return postitService.selectPostOutput(postN);
	}

	public void postInsert(String input) {
		//border category id title content
		String arr[] = input.split("@@");

		BorderVO borderGetN =  selectBorder(arr[0]);
		int border = borderGetN.getBo_number();
		
		CategoryVO categoryGetN = selectCategory(arr[1]);
		int category = categoryGetN.getCa_number();
		
		PrivacyVO privacyGetN = selectId(arr[2]);
		int id = privacyGetN.getPr_number();

		String title = arr[3];
		
		String content = arr[4];
		
//		PostitVO postInsertPhase = new PostitVO();
		
		postitService.insertPostInput(border, category, id, title, content);
		
		
	}


	private PrivacyVO selectId(String input) {
		
		if (input.length() == 0) {
			return null;
		}
		return postitService.selectId(input);
	}


	private CategoryVO selectCategory(String input) {
		
		if (input.length() == 0 || input == null) {
			return null;
		}
		return postitService.selectCategory(input);
	}



 

}
