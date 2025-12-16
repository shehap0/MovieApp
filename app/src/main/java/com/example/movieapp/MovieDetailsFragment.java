package com.example.movieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MovieDetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        ImageView poster = view.findViewById(R.id.imgPoster);
        TextView title = view.findViewById(R.id.txtTitle);
        TextView year = view.findViewById(R.id.txtYear);
        TextView rating = view.findViewById(R.id.txtRating);
        TextView duration = view.findViewById(R.id.txtDuration);
        TextView description = view.findViewById(R.id.txtDescription);
        TextView btnBack = view.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack()
        );

        if (getArguments() != null) {
            title.setText(getArguments().getString("title"));
            year.setText(getArguments().getString("year"));
            rating.setText("⭐ " + getArguments().getString("rating"));
            duration.setText("⏱ " + getArguments().getString("duration"));
            description.setText(getArguments().getString("description"));
            poster.setImageResource(getArguments().getInt("image"));
        }

        return view;
    }
}
