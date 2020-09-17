package com.pesan.film3.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.pesan.film3.R;
import com.pesan.film3.model.movie.MovieItem;
import com.pesan.film3.model.tv.TvItem;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    public static String KEY_DETAIL_DATA = "detail_data";
    public static String KEY_JENIS_DATA = "jenis_data";
    public String JenisData;
    private String title, year, description, poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView txtDetailTitle = findViewById(R.id.txt_detail_title);
        TextView txtDetailYear = findViewById(R.id.txt_detail_year);
        TextView txtDetailDescription = findViewById(R.id.txt_detail_description);
        ImageView imgDetailPoster = findViewById(R.id.img_detail_poster);

        JenisData = getIntent().getStringExtra(KEY_JENIS_DATA);

        if (JenisData.equals("movie")) {
            MovieItem movie = getIntent().getParcelableExtra(KEY_DETAIL_DATA);
            title = movie.getTitle();
            year = movie.getReleaseDate();
            description = movie.getOverview();
            poster = movie.getPosterPath();
            setTitle(title);
        } else if (JenisData.equals("tv")) {
            TvItem tvShow = getIntent().getParcelableExtra(KEY_DETAIL_DATA);
            title = tvShow.getName();
            year = tvShow.getFirstAirDate();
            description = tvShow.getOverview();
            poster = tvShow.getPosterPath();
            setTitle(title);
        }

        txtDetailTitle.setText(title);
        txtDetailYear.setText(year);
        txtDetailDescription.setText(description);

        String baseUrlImage = "https://image.tmdb.org/t/p/original";
        Glide.with(this).load(baseUrlImage + poster).into(imgDetailPoster);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
