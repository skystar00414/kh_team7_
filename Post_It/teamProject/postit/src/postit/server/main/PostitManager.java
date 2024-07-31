package postit.server.main;

import java.util.Scanner;

import postit.server.controller.PrintController;
import postit.server.controller.PrivacyController;
import postit.server.model.vo.PrivacyVO;

public class PostitManager {

    private Scanner scan = new Scanner(System.in);
    private PrivacyController privacyController = new PrivacyController(scan);
    private PrivacyVO privacyVO = null;



    public void init() {
        String M = "0";

        while (true) {
            PrintController.printMainMenu();

            M = scan.nextLine();

            runMainMenu(M);

            if (M.equals("3")) {
                break;
            }
        }

    }

    private void runMainMenu(String m) {

        if (m.equals("1")) {
            login();
        } else if (m.equals("2")) {
            sighUp();
        } else if (m.equals("3")) {
            PrintController.printExit();
        } else if (m.equals("debug")) {
            
        } else {
            PrintController.printWrongAnswer();
        }
    }

    private void login() {
        System.out.println("---login---");

        privacyVO = privacyController.login();

        PrintController.printBar();

    }

    private void sighUp() {
        
    }



}
