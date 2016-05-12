package mobile.tiy.chatapplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.SyncFailedException;
import java.net.Socket;

/**
 * Created by Justins PC on 5/11/2016.
 */
public class ChatClient {
    public static String textTest;
    public static Socket clientSocket;

    public static String sendMessage(String userText) throws Exception {
        String serverResponse = null;
        try {
            clientSocket = new Socket("10.0.0.27", 8005);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);//sends input from client to server
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //sends input into the server
            System.out.println(userText);
            out.println(userText); //input from server
//            String serverResponse; //converting server reponse to string
            MainActivity callInClass = new MainActivity(); //creating new main activity class
//            textTest = userText;
            serverResponse = in.readLine();
            while((serverResponse = in.readLine()) !=null) {
                callInClass.printMessage(serverResponse); // sending server response to app
                System.out.println(serverResponse);
                if (serverResponse.equals("(done)")) {
                    System.out.println("server finished printing last request");
                    break;
                }
                if(serverResponse == null || serverResponse.isEmpty()) {
                    System.out.println("Server response is empty");
                    break;
                }
            }


        } catch (Exception a) {
            a.printStackTrace();
        }

        return serverResponse;
    }
}


