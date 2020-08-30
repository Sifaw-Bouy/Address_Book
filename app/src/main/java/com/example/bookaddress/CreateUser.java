package com.example.bookaddress;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is in charge of creating users and sending the information
 * to firebase for storage. Then it's displayed in the main page.
 */
public class CreateUser extends AppCompatActivity {
    private Switch childUser;
    private boolean disableAd;
    private EditText fName,lName,address, parentN,childN;
    private ImageView backButton;
    private TextView txtCh;
    private TextView txtPr;
    private Button sendToDB;
    private Map<String, Object> userInfo;
    private FirebaseFirestore fbStore;
    private DocumentReference docRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        //Initializing the main API components
        childUser = findViewById(R.id.childSwitch);
        backButton = findViewById(R.id.backToM2);
        //name field
        fName = findViewById(R.id.FirstName);
        lName = findViewById(R.id.LastName);
        //text
        parentN = findViewById(R.id.parentName);
        txtCh = findViewById(R.id.textChview);
        //text
        childN = findViewById(R.id.relationName);
        txtPr = findViewById(R.id.textPrview);
        // address field
        address = findViewById(R.id.addressInput);
        sendToDB = findViewById(R.id.sendToDatabase);
        //Firebase Initialization
        fbStore = FirebaseFirestore.getInstance();

        //this deals with switching if the user is a child, disabling the address input
        childUser.setOnCheckedChangeListener(//Listings to a change in the switch
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){//if switch is ON the address input is disabled
                    address.setText("");//overrides any text put before
                    address.setEnabled(false);
                    childN.setVisibility(View.GONE);
                    txtPr.setVisibility(View.GONE);
                    parentN.setVisibility(View.VISIBLE);
                    txtCh.setVisibility(View.VISIBLE);
                    disableAd = true;

                }else{//if switch is OFF the address input must be used
                    address.setEnabled(true);
                    parentN.setVisibility(View.GONE);
                    txtCh.setVisibility(View.GONE);
                    childN.setVisibility(View.VISIBLE);
                    txtPr.setVisibility(View.VISIBLE);
                    disableAd=false;

                }
            }
        });
        //This executes when the add button is clicked
        sendToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = fName.getText().toString().trim();
                String lastName = lName.getText().toString().trim();
                String relation;
                String addressName = address.getText().toString().trim();
                if(disableAd){
                    relation = parentN.getText().toString().trim();
                }else{
                    relation = childN.getText().toString().trim();
                }
                if(TextUtils.isEmpty(firstName)){
                    fName.setError("Firstname required!");
                    return;
                }
                if(TextUtils.isEmpty(lastName)){
                    fName.setError("Lastname required!");
                    return;
                }
                if(TextUtils.isEmpty(addressName) && !disableAd){
                    fName.setError("Address required!");
                    return;
                }
                if(TextUtils.isEmpty(relation) && disableAd){
                    parentN.setError("Parent Name Required!");
                    return;
                }

                sendToFireStorage(firstName,lastName,addressName,relation);
                backToMain();
            }
        });
        //This executes when the back button is clicked
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });


    }

    /**
     * This method takes in the inputed information by the user
     * and get the document reference in firebase, where the data
     * is add
     * @param firstName
     * @param lastName
     * @param addressName
     * @param related
     */
    private void sendToFireStorage(String firstName, String lastName,
                                   String addressName,String related) {
        docRef = fbStore.collection("Users").document();
        String id;
        userInfo = new HashMap<>();
        userInfo.put("FirstName",firstName);
        userInfo.put("LastName",lastName);
        if(addressName.equals("")){
            addressName = "Child lives with "+related;
        }
        userInfo.put("Address",addressName);
        userInfo.put("Relation",related);
        docRef.set(userInfo);
        userInfo.put("ID",docRef.getId());
        docRef.set(userInfo);

    }

    /**
     * This method execute when the add button is pressed, takes the user
     * to the main view.
     */
    private void backToMain() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

}
