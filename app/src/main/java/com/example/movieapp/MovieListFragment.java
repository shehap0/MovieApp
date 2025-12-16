package com.example.movieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieListFragment extends Fragment {

    private RecyclerView moviesRecycler;
    private MovieAdapter movieAdapter;
    private TextView txtCategoryTitle;

    private List<Movie> allMovies;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtCategoryTitle = view.findViewById(R.id.txtCategoryTitle);
        moviesRecycler = view.findViewById(R.id.moviesRecycler);

        moviesRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));


        movieAdapter = new MovieAdapter(new ArrayList<>(), movie -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", movie.getTitle());
            bundle.putString("year", movie.getYear());
            bundle.putString("rating", movie.getRating());
            bundle.putString("duration", movie.getDuration());
            bundle.putString("description", movie.getDescription());
            bundle.putInt("image", movie.getImageResId());

            MovieDetailsFragment fragment = new MovieDetailsFragment();
            fragment.setArguments(bundle);

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        moviesRecycler.setAdapter(movieAdapter);


        allMovies = Arrays.asList(

                // ACTION Part
                new Movie("John Wick", "2014", R.drawable.action1, "Action",
                        "8.5", "1h 41m",
                        "An ex-hitman comes out of retirement after his dog is killed."),

                new Movie("Mad Max", "2015", R.drawable.action2, "Action",
                        "8.1", "2h",
                        "In a post-apocalyptic wasteland, Max helps rebels escape a tyrant."),

                new Movie("The Dark Knight", "2008", R.drawable.action3, "Action",
                        "9.0", "2h 32m",
                        "Batman faces the Joker, Gotham’s most dangerous criminal."),

                // HORROR Part
                new Movie("The Conjuring", "2013", R.drawable.horror1, "Horror",
                        "7.5", "1h 52m",
                        "Paranormal investigators help a family terrorized by dark forces."),

                new Movie("IT", "2017", R.drawable.horror2, "Horror",
                        "7.3", "2h 15m",
                        "A group of kids face an ancient evil that preys on their fears."),

                new Movie("Hereditary", "2018", R.drawable.horror3, "Horror",
                        "7.3", "2h 7m",
                        "A family uncovers dark secrets after their grandmother’s death."),

                // DRAMA Part
                new Movie("Forrest Gump", "1994", R.drawable.drama1, "Drama",
                        "8.8", "2h 22m",
                        "A man with a low IQ experiences historic moments in America."),

                new Movie("Fight Club", "1999", R.drawable.drama2, "Drama",
                        "8.8", "2h 19m",
                        "An office worker forms an underground fight club."),

                new Movie("Shawshank Redemption", "1994", R.drawable.drama3, "Drama",
                        "9.3", "2h 22m",
                        "Two prisoners bond over years inside Shawshank prison."),

                // COMEDY Part
                new Movie("The Mask", "1994", R.drawable.comedy1, "Comedy",
                        "6.9", "1h 41m",
                        "A man discovers a mask that gives him cartoon-like powers."),

                new Movie("Superbad", "2007", R.drawable.comedy2, "Comedy",
                        "7.6", "1h 53m",
                        "Two teens try to enjoy one last wild night before graduation."),

                new Movie("21 Jump Street", "2012", R.drawable.comedy3, "Comedy",
                        "7.2", "1h 49m",
                        "Two cops go undercover at a high school.")
        );

        // Read Category From Arguments

        String category = null;
        if (getArguments() != null) category = getArguments().getString("category");

        if (category == null) category = "Action";

        txtCategoryTitle.setText(category + " Movies");
        showCategory(category);
    }

    private void showCategory(String category) {
        List<Movie> filtered = new ArrayList<>();
        for (Movie m : allMovies) {
            if (m.getCategory().equalsIgnoreCase(category)) {
                filtered.add(m);
            }
        }
        movieAdapter.updateMovies(filtered);
    }
}
