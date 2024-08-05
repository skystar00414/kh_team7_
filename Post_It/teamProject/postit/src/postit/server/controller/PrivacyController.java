package postit.server.controller;

import java.util.Scanner;

import postit.server.model.vo.PrivacyVO;
import postit.server.service.PrivacyService;
import postit.server.service.PrivacyServiceImp;

public class PrivacyController {

    private PrivacyService privacyService = new PrivacyServiceImp();
    private Scanner scan = new Scanner(System.in);

    public PrivacyController(Scanner scan){
        this.scan = scan;
    }

    public PrivacyVO login() {

        System.out.print("ID : ");
        String id = scan.nextLine();

        System.out.print("PW : ");
        String pw = scan.nextLine();

        // PrivacyVO user = privacyService.login();

        return null;
    }

}
