package mobile.tiy.chatapplication;

import dalvik.annotation.TestTargetClass;

/**
 * Created by Justins PC on 5/11/2016.
 */
public class testChatClient {
    ChatClient chatClient;

    @Before
    public void setUp() throws Exception {
        chatClient = new ChatClient();
    }

    @After
    public void tearDown() throws Exception {
        // clean up work (such as closing connections, ...) goes here
    }

    @Test
    public void testSendMessage() throws Exception {
        String message ="This is a test";
        chatClient.sendMessage(message);

    }


}
