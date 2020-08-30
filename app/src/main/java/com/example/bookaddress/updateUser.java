package com.example.bookaddress;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * This class deals with update the user information in firebase
 */
public class updateUser extends AppCompatActivity {
    private EditText fName,lName,address, relation;
    private ImageView backButton;
    private Button updateFireB, deleteUs;
    private Switch isChild;
    private Map<String, Object> updatedUInfo;//holds the information related to this user
    private FirebaseFirestore fbStore;
    private DocumentReference docRef;
    private String[] fullName;
    private String userId;
    private UserModel userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user);
        if(getIntent().hasExtra("userKey")){
            //information we passed in from the MainActivity
            userInfo = getIntent().getParcelableExtra("userKey");
        }
        backButton = findViewById(R.id.backToM);//back button
        updateFireB = findViewById(R.id.upDate);//update button
        deleteUs = findViewById(R.id.deleteUser);//delete button
        fName = findViewById(R.id.updateFirstN);
        lName = findViewById(R.id.updateLastN);
        address = findViewById(R.id.updateAddress);
        relation = findViewById(R.id.updateRelation);
        //Get a string array with first and last name
        fullName = userInfo.getNameU().split(" ");
        //initializing the input boxes to user info
        fName.setText(fullName[0]);
        lName.setText(fullName[1]);
        address.setText(userInfo.getAddressU());
        relation.setText(userInfo.getRelationU());
        userId = userInfo.getUid();
        //firebase
        fbStore = FirebaseFirestore.getInstance();
        docRef = fbStore.collection("Users").document(userId);
        //update
        updateFireB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstN = fName.getText().toString();
                String lastN = lName.getText().toString();
                String addRess = address.getText().toString();
                String related = relation.getText().toString();
                if(TextUtils.isEmpty(firstN)){
                    fName.setError("Firstname required!");
                    return;
                }
                if(TextUtils.isEmpty(lastN)){
                    lName.setError("Lastname required!");
                    return;
                }
                if(TextUtils.isEmpty(addRess)){
                    address.setError("Address required!");
                    return;
                }
                updateFirebaseD(firstN,lastN,addRess,related);
                backToMain();
            }
        });
        //This listens for user click on the delete button and executes
        deleteUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef.delete();
                backToMain();
            }
        });
        //This listens for user click on the back button and executes
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    /**
     * This method is what updates the current information in firebase with
     * the given new information, by update the hashMap.
     * @param firstN
     * @param lastN
     * @param addRess
     * @param related
     */
    private void updateFirebaseD(String firstN,String lastN,String addRess,String related){
        updatedUInfo = new HashMap<>();
        updatedUInfo.put("FirstName",firstN);
        updatedUInfo.put("LastName",lastN);
        updatedUInfo.put("Address",addRess);
        updatedUInfo.put("Relation",related);
        docRef.update(updatedUInfo);

    }

    /**
     * when user click update they are taken back to the main view page
     * @retrun void
     */
    private void backToMain() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

}
