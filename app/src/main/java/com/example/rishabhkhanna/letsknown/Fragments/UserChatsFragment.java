package com.example.rishabhkhanna.letsknown.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rishabhkhanna.letsknown.R;
import com.example.rishabhkhanna.letsknown.models.User;
import com.example.rishabhkhanna.letsknown.models.chatsmessages;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserChatsFragment extends Fragment {


    FirebaseListAdapter mAdapter;
    FirebaseListAdapter mAdapterUser;


    public UserChatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_chats, container, false);

        final ArrayList<String> contacts = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("chats");

        DatabaseReference refUsers = FirebaseDatabase.getInstance().getReference("user");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();

        mAdapter = new FirebaseListAdapter<chatsmessages>(getActivity() , chatsmessages.class , R.layout.fragment_chat_page , ref) {

            @Override
            protected void populateView(View v, chatsmessages model, int position) {

                if(model.getSender().equals(uid) ){

                    contacts.add(model.getReceiver());

                }else if(model.getReceiver().equals(uid)){
                    contacts.add(model.getSender());
                }

            }
        };

        mAdapterUser = new FirebaseListAdapter<User>(getActivity() ,User.class , R.layout.contact_view , refUsers ) {
            @Override
            protected void populateView(View v, User model, int position) {







            }
        };





        return view;
    }

}
