package oosoistest;

import java.io.Serializable;

//직렬화 => 오브젝트 전송 가능
public class Message implements Serializable{

    private final String text;

    public Message(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
