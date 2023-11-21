package com.uniagustiniana.proyecto_final_foro.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniagustiniana.proyecto_final_foro.R;
import com.uniagustiniana.proyecto_final_foro.dto.Group;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private List<Group> groups;
    private LayoutInflater inflater;
    private Context context;

    public GroupAdapter(Context context, List<Group> groups) {
        this.inflater = LayoutInflater.from(context);
        this.groups = groups;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.group_element, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.ViewHolder holder, int position) {
        holder.bindData(groups.get(position));
    }

    public void setItems(List<Group> items) {
        this.groups = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, status;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.groupNameTextView);
            description = itemView.findViewById(R.id.groupDescriptionTextView);
            status = itemView.findViewById(R.id.groupStatusTextView);
        }

        void bindData(final Group item) {
            name.setText(item.getName());
            description.setText(item.getDescription());
            status.setText(item.getStatus());
        }
    }

}
