package id.sch.smktelkom_mlg.privateassignment.xirpl530.tamovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl530.tamovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl530.tamovie.model.Result;

/**
 * Created by Septy on 5/13/2017.
 */

public class NowAdapter extends RecyclerView.Adapter<NowAdapter.ViewHolder> {
    ArrayList<Result> list;
    NowAdapter.INowAdapter mINowAdapter;
    Context context;

    public NowAdapter(Context context, ArrayList<Result> list) {
        this.list = list;
        this.context = context;
        mINowAdapter = (NowAdapter.INowAdapter) context;
    }

    @Override
    public NowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.now_list, parent, false);
        NowAdapter.ViewHolder vh = new NowAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NowAdapter.ViewHolder holder, int position) {
        Result result = list.get(position);
        holder.tvName.setText(result.title);
        holder.tvDesc.setText(result.overview);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500" + result.poster_path)
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public interface INowAdapter {
        void showArticles(String title, String overview, String poster_path);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDesc;
        ImageView ivPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            tvName = (TextView) itemView.findViewById(R.id.textViewName);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
        }
    }
}
