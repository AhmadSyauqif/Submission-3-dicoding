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
import com.pesan.film3.adapter.MovieAdapter;
import com.pesan.film3.model.movie.MovieItem;

import java.util.List;

public class MovieShowFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private RecyclerView rvMovie;
    private ProgressBar pgMovie;


    public MovieShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return getView() != null ? getView() :
                inflater.inflate(R.layout.fragment_movie_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MovieViewModel movieViewModel = ViewModelProviders.of(MovieShowFragment.this)
                .get(MovieViewModel.class);
        movieViewModel.getListMovies().observe(this, getMovie);

        rvMovie = view.findViewById(R.id.recycler_view);
        pgMovie = view.findViewById(R.id.pg_movie);


        movieAdapter = new MovieAdapter(getActivity());
        movieAdapter.notifyDataSetChanged();

        movieViewModel.setListMovies();
        showLoading(true);
        showRecyclerList();
    }

    private Observer<List<MovieItem>> getMovie = new Observer<List<MovieItem>>() {
        @Override
        public void onChanged(@Nullable List<MovieItem> movieItems) {
            if (movieItems != null) {
                movieAdapter.setListData(movieItems);
                showLoading(false);
            }
        }
    };

    private void showRecyclerList() {
        rvMovie.setLayoutManager(new LinearLayoutManager (getActivity()));
        rvMovie.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MovieItem data) {
                showSelectedMovie(data);
            }
        });
    }

    private void showSelectedMovie(MovieItem movie) {
        Intent intent = new Intent (getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_DETAIL_DATA, movie);
        intent.putExtra(DetailActivity.KEY_JENIS_DATA, "movie");
        startActivity(intent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            pgMovie.setVisibility(View.VISIBLE);
        } else {
            pgMovie.setVisibility(View.GONE);
        }
    }
}
