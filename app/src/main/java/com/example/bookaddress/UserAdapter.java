package com.example.bookaddress;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * The class is what makes the recycleView work. It displays the created
 * users information in CardView form.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<UserModel> mUserModel;//holds UserModel class information
    private OnUserListener onUserList;

    /**
     * This takes in the UserModel for each use and displays in the recycleView
     */
    public static class UserViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private TextView nameU;
        private TextView address;
        private TextView relation;
        private OnUserListener userLis;
        public UserViewHolder(@NonNull View itemView,OnUserListener onUserListener) {
            super(itemView);
            nameU = itemView.findViewById(R.id.nameInfo);
            address = itemView.findViewById(R.id.addressName);
            relation = itemView.findViewById(R.id.relationName);
            this.userLis = onUserListener;
            itemView.setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) { userLis.onUserClick(getAdapterPosition());

        }
    }
    public UserAdapter(ArrayList<UserModel> userM, OnUserListener onUserListener){
        mUserModel = userM;
        onUserList = onUserListener;
    }
    /**
     * Called when RecyclerView needs a new of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     */
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userinfo_card,parent,false);
        return new UserViewHolder(view,onUserList);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the ViewHolder to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use ViewHolder #getAdapterPosition() which will
     * have the updated adapter position.
     * <p>
     * Override onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        UserModel currentUser = mUserModel.get(position);
        holder.nameU.setText(currentUser.getNameU());
        holder.relation.setText(currentUser.getRelationU());
        holder.address.setText(currentUser.getAddressU());
    }

    /**
     * This listens to user clicking the specific user information
     * in the recycleViewer
     */
    public interface OnUserListener{
        void onUserClick(int position);
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {

        return mUserModel.size();
    }
}
