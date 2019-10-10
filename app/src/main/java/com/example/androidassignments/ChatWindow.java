package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ChatWindow";

    ListView chat_view;
    EditText mesg_send;
    Button but_send;
    ArrayList<String> chat_mesg = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        chat_view = findViewById(R.id.listView);
        mesg_send = findViewById(R.id.message_string);
        but_send = findViewById(R.id.sendButton);

        final ChatAdapter messageAdapter =new ChatAdapter( this );
        chat_view.setAdapter (messageAdapter);

        but_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                chat_mesg.add(mesg_send.getText() + "");
                mesg_send.setText("");
                messageAdapter.notifyDataSetChanged();
            }
        });
    }

    private class ChatAdapter extends ArrayAdapter<String> {
       public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return chat_mesg.size();
        }

        public String getItem(int position) {
            return chat_mesg.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result;

            if(position%2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }
            TextView message = result.findViewById(R.id.message_text);
            System.out.println(message);
            message.setText(getItem(position)); // get the string at position

            return result;

        }

    }
}
