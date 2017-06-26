package com.android.littledev.duplom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by LittleDev on 22-Jun-17.
 */

public class SearchSeasonFragment extends Fragment {

    ItemActivity itemActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_season_fragment, container, false);
        ImageButton summer = (ImageButton) view.findViewById(R.id.summer);
        ImageButton anthem = (ImageButton) view.findViewById(R.id.anthem);
        ImageButton winter = (ImageButton) view.findViewById(R.id.winter);
        ImageButton spring = (ImageButton) view.findViewById(R.id.spring);

        itemActivity = new ItemActivity();

        summer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActivity.setSeasonTag("summer");

            }
        });
        anthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActivity.setSeasonTag("anthem");

            }
        });
        winter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActivity.setSeasonTag("winter");

            }
        });
        spring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActivity.setSeasonTag("spring");

            }
        });

        return view;
    }
}
