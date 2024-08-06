String 값도 직렬화가 필요함
sending msg도 직렬화, 객체화가 필요함
class Message로 객체화
Serializable로 직렬화

output
Message msg = new Message("sending message");
oos.writeObject(msg);

input
Message msg = (Message)ois.readObject();
String msgText = msg.getText();
System.out.println(msgText);