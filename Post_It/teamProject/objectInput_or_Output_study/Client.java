package oosoistest;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) throws Exception{
        
        Socket socket = new Socket("127.0.0.1",7777);
        System.out.println("connected!");

        System.out.println("get the output stream from the socket");
        System.out.println("create an object output stream from the output stream");
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        System.out.println("ObjectOutputStream is now ready");



        List<Message> MessageList = new ArrayList<>();
        MessageList.add(new Message("test MSG"));
        MessageList.add(new Message("contact1"));
        MessageList.add(new Message("cocococococo"));
        MessageList.add(new Message("cocoparoma"));

        System.out.println("send Message Object to the ServerSocket");
        oos.writeObject(MessageList);

        Message msg = new Message("test text");
        oos.writeObject(msg);

        System.out.println("close socket");
        socket.close();
    }
    
}
