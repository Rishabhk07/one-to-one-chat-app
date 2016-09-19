package com.example.rishabhkhanna.letsknown.view;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rishabhkhanna.letsknown.R;
import com.example.rishabhkhanna.letsknown.models.UserChats;
import com.example.rishabhkhanna.letsknown.models.chatsmessages;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Date;

import me.himanshusoni.chatmessageview.ChatMessageView;

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

        setTitle(name);

        messageText = (TextView) findViewById(R.id.msgTV);
        ListView listview = (ListView) findViewById(R.id.chatmessageLV);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String userUid = user.getUid();
        final String usermail = user.getEmail();


          final DatabaseReference chatMessages = FirebaseDatabase.getInstance().getReference("chats");
            chatMessages.keepSynced(true);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = messageText.getText().toString();
                String sender = userUid.toString();
                String receiver = uid.toString();

                messageText.setText("");

                chatsmessages  newMessage = new chatsmessages(sender , receiver , message);
                chatMessages.push().setValue(newMessage);


            }
        });



// /       DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("ChatMessage").child(usermailsplit).child(emailsplit);

        mAdapter = new FirebaseListAdapter<chatsmessages>(this , chatsmessages.class , R.layout.chat_messages_layout , chatMessages ) {
            @Override
            protected void populateView(View v, chatsmessages model, int position) {




                if(model.getSender().toString().equals(userUid) && model.getReceiver().toString().equals(uid)){

                    ChatMessageView messageView = (ChatMessageView) v.findViewById(R.id.chatmessageVIEW);
                    TextView text = (TextView) v.findViewById(R.id.chattextTV);


                    messageView.setVisibility(View.VISIBLE);
                    messageView.setGravity(Gravity.LEFT);
                    messageView.setBackgroundColors(R.color.colorAccent , R.color.buttonColorPressed);
                    text.setText(model.getMessage());

                    mAdapter.notifyDataSetChanged();


                }
                else if(model.getSender().toString().equals(uid) && model.getReceiver().toString().equals(userUid) ){

                    ChatMessageView messageView = (ChatMessageView) v.findViewById(R.id.chatmessageVIEW);
                    TextView text = (TextView) v.findViewById(R.id.chattextTV);

                    messageView.setVisibility(View.VISIBLE);
                    messageView.setGravity(Gravity.RIGHT);
                    messageView.setBackgroundColors(R.color.colorPrimaryDark , R.color.authui_colorAccent);
                    text.setText(model.getMessage());

                    mAdapter.notifyDataSetChanged();

                }


            }

        };

        listview.setAdapter(mAdapter);





    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
