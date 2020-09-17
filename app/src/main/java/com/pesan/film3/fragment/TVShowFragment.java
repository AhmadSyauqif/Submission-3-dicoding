package com.pesan.film3.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pesan.film3.R;
import com.pesan.film3.activity.DetailActivity;
import com.pesan.film3.adapter.TvAdapter;
import com.pesan.film3.model.tv.TvItem;

import java.util.List;

public class TVShowFragment extends Fragment {

    private TvAdapter tvAdapter;
    private RecyclerView rvTv;
    private ProgressBar pgTv;

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TvViewModel tvViewModel = ViewModelProviders.of (TVShowFragment.this)
                .get (TvViewModel.class);
        tvViewModel.getListTv ().observe (this,getTv);

        rvTv = view.findViewById (R.id.recycler_view_tv);
        pgTv = view.findViewById (R.id.progressBar_tv);

        tvAdapter = new TvAdapter (getActivity ());
        tvAdapter.notifyDataSetChanged ();

        tvViewModel.setListTv ();
        showLoading (true);
        showRecyclerList ();
    }

  private Observer<List<TvItem>>getTv = new Observer<List<TvItem>> () {
      @Override
      public void onChanged(List<TvItem> tvItems) {
          if (tvItems != null){
              tvAdapter.setListData (tvItems);
              showLoading (false);
          }
      }
  };

    private void showRecyclerList() {
        rvTv.setLayoutManager(new LinearLayoutManager (getActivity()));
        rvTv.setAdapter(tvAdapter);

        tvAdapter.setOnItemClickCallback(new TvAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvItem data) {
                showSelectedTv(data);
            }
        });
    }

    private void showSelectedTv(TvItem data) {
        Intent intent = new Intent (getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_DETAIL_DATA, data);
        intent.putExtra(DetailActivity.KEY_JENIS_DATA, "tv");
        startActivity(intent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            pgTv.setVisibility(View.VISIBLE);
        } else {
            pgTv.setVisibility(View.GONE);
        }
    }
}
