package mobile.tiy.chatapplication;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
    ListView list;
    EditText text;
    Button addButton;
    ArrayAdapter<String> sentMessages;
    ArrayAdapter<String> receivedMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        list = (ListView) findViewById(R.id.listView);
        text = (EditText) findViewById(R.id.editText);
        addButton = (Button) findViewById(R.id.button);

        receivedMessages = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);//
        list.setAdapter(receivedMessages);

        addButton.setOnClickListener(this);
        list.setOnItemLongClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String userMessage = text.getText().toString(); //getting whats in the text field
        ChatClient newChat = new ChatClient();
        try {
            ChatClient.sendMessage(userMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        text.setText(""); //resettinng the text to blank

    }
    public void printMessage(String message) {
        try {
            String complexResponse = " from:" + message + " =>";
//            String expectedResponse = message;
            String serverResponse = message;
            int messageIndexInServerResponse = serverResponse.indexOf(complexResponse);
            String embeddedMessage = serverResponse.substring(messageIndexInServerResponse,
                    messageIndexInServerResponse + complexResponse.length());
            String actualMessage = embeddedMessage.substring(6,complexResponse.length()-3);
            receivedMessages.add(actualMessage);
            System.out.println(actualMessage + "added to message array");
        } catch (NullPointerException a) {
            a.printStackTrace();
        }

    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String item = sentMessages.getItem(position);
        sentMessages.remove(item);
        return true;
    }

}
