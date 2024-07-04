package search_with_coffee;


public class User extends DataBase {

    String id;
    String passward;

    public User(){//기본 생성자

    }
    public User(String id, String passward) {//생성자
        this.id = id;
        this.passward = passward;
    }

    void signin(String id, String passward){//생성자와 같으나 회원가입용
        this.id = id;
        this.passward = passward;
    }
    
    //toString 사용 안함
    //equals 사용하려면 기준 ID로 비밀번호는 중복 가능하기 때문임

}
