package com.example.rishabhkhanna.letsknown.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rishabhkhanna.letsknown.R;
import com.example.rishabhkhanna.letsknown.models.User;
import com.example.rishabhkhanna.letsknown.view.ChatMessagesActivity;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    FirebaseListAdapter mAdapter;


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");

        databaseReference.keepSynced(true);

        ListView listview = (ListView) view.findViewById(R.id.userlistview);

        mAdapter = new FirebaseListAdapter<User>(getActivity() ,User.class , R.layout.contact_view , databaseReference ) {
            @Override
            protected void populateView(View eachTupleView, User usermodel, int position) {

                if(!usermodel.getUid().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

                    ((TextView) eachTupleView.findViewById(R.id.userNamesTV)).setText(usermodel.getName());

                    eachTupleView.setTag(R.string.uid, usermodel.getUid().toString());
                    eachTupleView.setTag(R.string.name, usermodel.getName().toString());
                    eachTupleView.setTag(R.string.email, usermodel.getEmail().toString());
                    mAdapter.notifyDataSetChanged();
                }
            }


        };

        listview.setAdapter(mAdapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String uid = (String) view.getTag(R.string.uid);
                String name = (String) view.getTag(R.string.name);
                String email = (String) view.getTag(R.string.email);

                Intent intent = new Intent(getActivity() , ChatMessagesActivity.class);
                intent.putExtra("uid" , uid);
                intent.putExtra("name" , name);
                intent.putExtra("email" , email);
                startActivity(intent);


            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
