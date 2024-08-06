package oosoistest;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept();
        System.out.println("Connection from " + socket + "!");


        InputStream is = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);

        List<Message> list = (List<Message>)ois.readObject();
        System.out.println("Received [" + list.size() + "] messages from: " + socket);

        System.out.println("read Message");
        list.forEach((msg)->System.out.println(msg.getText()));

        Message msg = (Message)ois.readObject();
        String msgText = msg.getText();
        System.out.println(msgText);

        System.out.println("closing Server socket");
        ss.close();
        socket.close();
    }
}
