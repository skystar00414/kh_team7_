package teamPlay.model.vo;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
private final String text;

  public Message(String text){
      this.text = text;
  }

  public String getText(){
      return text;
  }
}