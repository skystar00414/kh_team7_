package postit.server.controller;

public class PrintController {

    public static void printMainMenu() {
        PrintController.printBar();
        System.out.println("1. Login\n2. SignUp\n3. Exit");
        PrintController.printBar();
    }


    public static void printBar(){
        System.out.println("-----------");
    }


    public static void printWrongAnswer() {
        System.out.println("잘못된 입력입니다.");
    }


    public static void printExit() {
        System.out.println("프로그램 종료");
    }
}
