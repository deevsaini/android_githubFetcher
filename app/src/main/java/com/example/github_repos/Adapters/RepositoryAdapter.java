package com.example.github_repos.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github_repos.MainActivity2;
import com.example.github_repos.Model.Repository;
import com.example.github_repos.R;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {
    List<Repository> repos;
    Context context;

    public RepositoryAdapter(List<Repository> repos, Context context) {
        this.repos = repos;
        this.context = context;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        holder.fullname.setText("Fullname :- "+repos.get(position).getFullName());
        holder.name.setText("Name :- "+repos.get(position).getName());
        holder.size.setText("Size :- "+repos.get(position).getSize());
        holder.language.setText("Language :- "+repos.get(position).getLanguage());
        int stars = repos.get(position).getStargazersCount();
        int watchers = repos.get(position).getWatchersCount();
        holder.card.setOnClickListener(view -> {
            Intent intent = new Intent(context, MainActivity2.class);
            intent.putExtra("name",repos.get(position).getName());
            intent.putExtra("stars",String.valueOf(stars));
            intent.putExtra("watch",String.valueOf(watchers));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


        });



    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        TextView fullname,name,size,language;
        ConstraintLayout card;
        public RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);
            fullname=itemView.findViewById(R.id.fullname);
            name=itemView.findViewById(R.id.name);
            size=itemView.findViewById(R.id.size);
            language=itemView.findViewById(R.id.language);
            card=itemView.findViewById(R.id.card);
        }
    }
}
