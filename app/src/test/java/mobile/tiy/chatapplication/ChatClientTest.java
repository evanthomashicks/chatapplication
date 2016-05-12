package mobile.tiy.chatapplication;

import static org.junit.Assert.*;

/**
 * Created by Justins PC on 5/11/2016.
 */
public class ChatClientTest {
    ChatClient chatClient;

    @org.junit.Before
    public void setUp() throws Exception {
        chatClient = new ChatClient();
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testSendMessage() throws Exception {
        try {
            String messageToSend = "Testing HERE and NOW";
            String complexResponse = " from:" + messageToSend + " =>";
            String expectedResponse = messageToSend;
            String serverResponse = chatClient.sendMessage(messageToSend);
            int messageIndexInServerResponse = serverResponse.indexOf(complexResponse);
            String embeddedMessage = serverResponse.substring(messageIndexInServerResponse,
                    messageIndexInServerResponse + complexResponse.length());
            String actualMessage = embeddedMessage.substring(6,complexResponse.length()-2);
            System.out.println("Message OUT::" + messageToSend);
            System.out.println("Response IN::" + serverResponse);
            System.out.println("Complex    ::" + complexResponse);
            System.out.println("Index of Complex in Response::" + messageIndexInServerResponse);
            System.out.println("Embedded   ::" + embeddedMessage);
            System.out.println("Actual message receieved: " + actualMessage);

            assertNotEquals(-1, messageIndexInServerResponse);
//            assertEquals(expectedResponse, serverResponse);
        } catch (NullPointerException a) {
            a.printStackTrace();
        }
    }
}