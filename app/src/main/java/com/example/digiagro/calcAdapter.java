package com.example.digiagro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class calcAdapter extends FirebaseRecyclerAdapter<calcModel, calcAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    Context mcontext;
    public calcAdapter(Context applicationContext,@NonNull FirebaseRecyclerOptions<calcModel> options) {
        super(options);
        this.mcontext = applicationContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull calcModel model) {

        holder.mop.setText(model.getMop());
        holder.ssp.setText(model.getSsp());
        holder.urea.setText(model.getUrea());
        holder.area.setText(model.getArea());
//        Glide.with(mcontext).load(model.getUrl()).into(holder.img);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mcontext, subjectActivity.class);
//                i.putExtra("id", model.getID());
//
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mcontext.startActivity(i);
//
//
//            }
//        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fertilizerlist, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView ssp,urea,mop,area;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imagefertilizer);
            ssp = itemView.findViewById(R.id.ssp_weight);
            urea = itemView.findViewById(R.id.urea_weight);
            mop = itemView.findViewById(R.id.mop_weight);
            area = itemView.findViewById(R.id.headingText);
        }

    }
}
