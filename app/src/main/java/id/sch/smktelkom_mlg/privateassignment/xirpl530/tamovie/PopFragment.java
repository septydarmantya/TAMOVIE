package id.sch.smktelkom_mlg.privateassignment.xirpl530.tamovie;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl530.tamovie.adapter.PopularAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl530.tamovie.model.Result;
import id.sch.smktelkom_mlg.privateassignment.xirpl530.tamovie.model.ResultsResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl530.tamovie.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl530.tamovie.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopFragment extends Fragment {
    ArrayList<Result> mList = new ArrayList<>();
    PopularAdapter PopmAdapter;

    public PopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pop, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.poprecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        PopmAdapter = new PopularAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(PopmAdapter);

        downloadDataSources();
    }

    private void downloadDataSources() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=0c0d5f9e08e0ec928a762ec0710e3bf1";

        GsonGetRequest<ResultsResponse> myRequest = new GsonGetRequest<ResultsResponse>
                (url, ResultsResponse.class, null, new Response.Listener<ResultsResponse>() {

                    @Override
                    public void onResponse(ResultsResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        mList.addAll(response.results);
                        PopmAdapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }
}
