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

public class SearchCategoryFragment extends Fragment {

    ItemActivity itemActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_category_fragment, container, false);
        ImageButton kid = (ImageButton) view.findViewById(R.id.kid);
        ImageButton man = (ImageButton) view.findViewById(R.id.man);
        ImageButton woman = (ImageButton) view.findViewById(R.id.woman);

         itemActivity = new ItemActivity();

        kid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActivity.setCategoryTag("kid");

            }
        });
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActivity.setCategoryTag("man");

            }
        });
        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActivity.setCategoryTag("woman");

            }
        });

        return view;
    }

}
