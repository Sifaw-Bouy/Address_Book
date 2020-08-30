package com.example.bookaddress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

/**
 * This the main class, this is where the user information from firebase.
 * This page is always loaded first the app is opened
 */
public class MainActivity extends AppCompatActivity implements UserAdapter.OnUserListener {
    private Button addUser, updateUser;
    private FirebaseFirestore fbStore;
    private RecyclerView mRecView;
    private RecyclerView.Adapter mAdapter;
    private UserAdapter list;
    private UserAdapter usAdapter;
    private RecyclerView.LayoutManager mLayManger;
    public ArrayList<UserModel> userFilerInfo;
    private CollectionReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addUser = findViewById(R.id.createUser);
        fbStore = FirebaseFirestore.getInstance();
        docRef = fbStore.collection("Users");
        ArrayList<UserModel> userInfoM = new ArrayList<>();
        //this takes the user to the user creation view
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CreateUser.class));
            }
        });
        //this goes to the firebase to fetch the present users to be displayed
        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            ArrayList<UserModel> userInfo = new ArrayList<>();
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    String name, fName, lName, address, relation,id;
                    int i;
                    List<DocumentSnapshot> userList = task.getResult()
                            .getDocuments();
                    for (i = 0; i < userList.size(); i++) {
                        fName = userList.get(i).get("FirstName").toString();
                        lName = userList.get(i).get("LastName").toString();
                        address = userList.get(i).get("Address").toString();
                        relation = userList.get(i).get("Relation").toString();
                        id = userList.get(i).get("ID").toString();
                        name = fName + " " + lName;
                        userInfo.add(new UserModel(name, address, relation,id));
                    }
                }
                userFilerInfo=userInfo;
                mAdapter = new UserAdapter(userInfo,MainActivity.this);
                usAdapter = (UserAdapter) mAdapter;
                mRecView.setLayoutManager(mLayManger);
                mRecView.setAdapter(usAdapter);
            }
        });
        mRecView = findViewById(R.id.userListView);
        mRecView.setHasFixedSize(true);
        mAdapter = new UserAdapter(userInfoM,MainActivity.this);
        mLayManger= new LinearLayoutManager(this);
        //this redundant but it makes work, like a refresh
        mRecView.setLayoutManager(mLayManger);
        mRecView.setAdapter(mAdapter);

    }

    /**
     * This is in charge of listing to user touch on a specific
     * and takes to the update user information
     * @param position
     */
    @Override
    public void onUserClick(int position) {
        Intent intent = new Intent(this,updateUser.class);
        intent.putExtra("userKey",userFilerInfo.get(position));
        startActivity(intent);
    }
}