package com.mobility.roomdatabasedemo;//package com.mobility.sggi.tagging.misc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<User> originalData = null;
    private static LayoutInflater inflater = null;
    private Context mContext;
    static private UserListRecycleAdapter.ItemClickListener clickListener;

    public UserListRecycleAdapter(Context context, List<User> userDetails) {
        this.originalData = userDetails;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_user_selection, null);
        UserListRecycleAdapter.CustomViewHolder viewHolder = new UserListRecycleAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder ch, final int position) {
        User user = originalData.get(position);
        CustomViewHolder customViewHolder = (CustomViewHolder) ch;
        customViewHolder.onBind(user);
    }

    @Override
    public int getItemCount() {
        return (null != originalData ? originalData.size() : 0);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        clickListener = itemClickListener;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView firstName;
        TextView secondName;
        TextView email;

        CustomViewHolder(View view) {
            super(view);
            this.email = view.findViewById(R.id.email);
            this.firstName = view.findViewById(R.id.firstName);
            this.secondName = view.findViewById(R.id.lastName);
        }

         void onBind(User user) {
            email.setText(user.getEmail());
            firstName.setText(user.getFirstName());
            secondName.setText(user.getLastName());
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }
}
