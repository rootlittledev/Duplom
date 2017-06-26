package com.android.littledev.duplom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class SearchTypeFragment extends Fragment {
    ItemActivity itemActivity;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_type_fragment, container, false);
        ImageButton clothes = (ImageButton) view.findViewById(R.id.clothes);
        ImageButton accessories = (ImageButton) view.findViewById(R.id.accessories);
        ImageButton shoes = (ImageButton) view.findViewById(R.id.shoes);
        ImageButton hats = (ImageButton) view.findViewById(R.id.hats);

        itemActivity = new ItemActivity();

        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActivity.setTypeTag("clothes");
            }
        });
        accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {itemActivity.setTypeTag("accessories");
            }
        });
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActivity.setTypeTag("shoes");

            }
        });
        hats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActivity.setTypeTag("hats");

            }
        });
        return view;
    }
}
