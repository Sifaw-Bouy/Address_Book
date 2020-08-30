package com.example.bookaddress;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class help in creating users grouped information
 * to be used by the UserAdapter class in which the recycleView is
 * implemented.
 */
public class UserModel  implements Parcelable {
    private String nameU,addressU,relationU,uid;

    /**
     * This is the constructor that will be called to
     * create user information to be displayed.
     * @param name
     * @param address
     * @param relation
     * @param id
     */
    public UserModel(String name, String address,String relation,String id){
        nameU= name;
        addressU = address;
        relationU = relation;
        uid = id;
    }

    /**
     *  This reads the given information from the constructor
     * @param in
     */
    protected UserModel(Parcel in) {
        nameU = in.readString();
        addressU = in.readString();
        relationU = in.readString();
        uid = in.readString();
    }

    /**
     * This calls the creator interface and it's methods to
     * generate a parcel to be used with given user information
     */
    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    /**
     * Returns a string to be displayed on the recycleViewHolder
     * @return string name of the user
     */
    public String getNameU(){
        return nameU;
    }
    /**
     * Returns a string to be displayed on the recycleViewHolder
     * @return string address of the user
     */
    public String getAddressU(){
        return addressU;
    }
    /**
     * Returns a string to be displayed on the recycleViewHolder
     * @return string of relationship of the user
     */
    public  String getRelationU(){
        return relationU;
    }

    /**
     * Returns the given firebase id to the specific user
     * used when updating the information
     * @return
     */
    public String getUid(){return  uid;}
    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameU);
        dest.writeString(addressU);
        dest.writeString(relationU);
        dest.writeString(uid);
    }
}
