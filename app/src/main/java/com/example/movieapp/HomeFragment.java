package com.example.movieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView moviesRecycler;
    private MovieAdapter movieAdapter;
    private List<Movie> allMovies;

    private TextView homeTitle, txtPlaceholder;
    private Button btnAction, btnHorror, btnDrama, btnComedy;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        homeTitle = view.findViewById(R.id.homeTitle);
        txtPlaceholder = view.findViewById(R.id.txtPlaceholder);
        moviesRecycler = view.findViewById(R.id.moviesRecycler);

        btnAction = view.findViewById(R.id.btnAction);
        btnHorror = view.findViewById(R.id.btnHorror);
        btnDrama  = view.findViewById(R.id.btnDrama);
        btnComedy = view.findViewById(R.id.btnComedy);

        moviesRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        movieAdapter = new MovieAdapter(new ArrayList<>(), movie -> {
            Bundle b = new Bundle();
            b.putString("title", movie.getTitle());
            b.putString("year", movie.getYear());
            b.putString("rating", movie.getRating());
            b.putString("duration", movie.getDuration());
            b.putString("description", movie.getDescription());
            b.putInt("image", movie.getImageResId());

            MovieDetailsFragment details = new MovieDetailsFragment();
            details.setArguments(b);

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, details)
                    .addToBackStack("details")
                    .commit();
        });

        moviesRecycler.setAdapter(movieAdapter);

        // Movies list
        allMovies = Arrays.asList(

                new Movie("John Wick", "2014", R.drawable.action1, "Action",
                        "8.5", "1h 41m",
                        "An ex-hitman comes out of retirement after his dog is killed."),

                new Movie("Mad Max", "2015", R.drawable.action2, "Action",
                        "8.1", "2h",
                        "In a post-apocalyptic wasteland, Max helps rebels escape a tyrant."),

                new Movie("The Dark Knight", "2008", R.drawable.action3, "Action",
                        "9.0", "2h 32m",
                        "Batman faces the Joker."),

                new Movie("The Conjuring", "2013", R.drawable.horror1, "Horror",
                        "7.5", "1h 52m",
                        "Paranormal investigators help a family."),

                new Movie("IT", "2017", R.drawable.horror2, "Horror",
                        "7.3", "2h 15m",
                        "A group of kids face an ancient evil."),

                new Movie("Hereditary", "2018", R.drawable.horror3, "Horror",
                        "7.3", "2h 7m",
                        "A family uncovers dark secrets."),

                new Movie("Forrest Gump", "1994", R.drawable.drama1, "Drama",
                        "8.8", "2h 22m",
                        "Life story of Forrest Gump."),

                new Movie("Fight Club", "1999", R.drawable.drama2, "Drama",
                        "8.8", "2h 19m",
                        "An underground fight club."),

                new Movie("Shawshank Redemption", "1994", R.drawable.drama3, "Drama",
                        "9.3", "2h 22m",
                        "Two prisoners bond over years."),

                new Movie("The Mask", "1994", R.drawable.comedy1, "Comedy",
                        "6.9", "1h 41m",
                        "A man discovers a magical mask."),

                new Movie("Superbad", "2007", R.drawable.comedy2, "Comedy",
                        "7.6", "1h 53m",
                        "Two teens before graduation."),

                new Movie("21 Jump Street", "2012", R.drawable.comedy3, "Comedy",
                        "7.2", "1h 49m",
                        "Two cops undercover at school.")
        );

        btnAction.setOnClickListener(v -> showCategory("Action"));
        btnHorror.setOnClickListener(v -> showCategory("Horror"));
        btnDrama.setOnClickListener(v -> showCategory("Drama"));
        btnComedy.setOnClickListener(v -> showCategory("Comedy"));

        if (getArguments() != null) {
            String category = getArguments().getString("category");
            if (category != null && !category.isEmpty()) {
                showCategory(category);
                return;
            }
        }

        showHome();
    }

    private void showHome() {
        homeTitle.setText("Trending Movies");

        txtPlaceholder.setVisibility(View.GONE);
        moviesRecycler.setVisibility(View.GONE);

        btnAction.setVisibility(View.VISIBLE);
        btnHorror.setVisibility(View.VISIBLE);
        btnDrama.setVisibility(View.VISIBLE);
        btnComedy.setVisibility(View.VISIBLE);
    }

    private void showCategory(String category) {
        homeTitle.setText(category + " Movies");

        List<Movie> filtered = new ArrayList<>();
        for (Movie m : allMovies) {
            if (m.getCategory().equalsIgnoreCase(category)) {
                filtered.add(m);
            }
        }

        txtPlaceholder.setVisibility(View.GONE);
        moviesRecycler.setVisibility(View.VISIBLE);

        // Hide Buttons
        btnAction.setVisibility(View.GONE);
        btnHorror.setVisibility(View.GONE);
        btnDrama.setVisibility(View.GONE);
        btnComedy.setVisibility(View.GONE);

        movieAdapter.updateMovies(filtered);
    }
}
