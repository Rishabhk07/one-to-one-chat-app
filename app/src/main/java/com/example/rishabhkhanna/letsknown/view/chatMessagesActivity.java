package com.example.rishabhkhanna.letsknown.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rishabhkhanna.letsknown.R;
import com.example.rishabhkhanna.letsknown.models.UserChats;
import com.example.rishabhkhanna.letsknown.models.chatsmessages;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Date;

public class ChatMessagesActivity extends AppCompatActivity {
    Button sendMessage;
    TextView messageText;
    FirebaseListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_messages);
        sendMessage = (Button) findViewById(R.id.sendButton);

        Intent i = getIntent();
        final String uid = i.getStringExtra("uid");
        final String name = i.getStringExtra("name");
        final String email = i.getStringExtra("email");
        messageText = (TextView) findViewById(R.id.msgTV);
        ListView listview = (ListView) findViewById(R.id.chatmessageLV);
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ChatMessage").child(email);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = messageText.getText().toString();

                ref.setValue(new chatsmessages(name, email , uid , message , ServerValue.TIMESTAMP));

            }
        });


        mAdapter = new FirebaseListAdapter<chatsmessages>(this , chatsmessages.class , R.layout.chat_messages_layout , ref ) {
            @Override
            protected void populateView(View v, chatsmessages model, int position) {

            }

        };






    }
}
