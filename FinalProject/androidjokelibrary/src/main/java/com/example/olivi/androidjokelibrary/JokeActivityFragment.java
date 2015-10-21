package com.example.olivi.androidjokelibrary;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        TextView jokeTextView = (TextView) rootView.findViewById(R.id.text_view_joke);
        jokeTextView.setText(getActivity().getIntent().getStringExtra(JokeActivity.EXTRA_JOKE));
        return rootView;
    }
}
