package com.android.littledev.duplom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


public class BasketFragment extends Fragment {

    ImageView item_image;
    TextView item_label;
    TextView item_amount;
    TextView item_cost;
    TextView item_currency;
    TextView item_discount;
    TextView item_end_price;

    public static BasketFragment newInstance(int _Id, int image_source, String label_string, String currency, int amount, float cost_per_item) {
        BasketFragment basketFragment = new BasketFragment();
        Bundle args = new Bundle();
        args.putInt("id", _Id);
        args.putInt("imageSource", image_source);
        args.putString("labelString", label_string);
        args.putString("currency", currency);
        args.putInt("amount", amount);
        args.putFloat("coastPerItem", cost_per_item);
        basketFragment.setArguments(args);
        return basketFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basket_fragment, container, false);


        item_image = (ImageView) view.findViewById(R.id.item_image);
        item_label = (TextView) view.findViewById(R.id.item_label);
        item_amount = (TextView) view.findViewById(R.id.item_amount);
        item_cost = (TextView) view.findViewById(R.id.item_cost);
        item_currency = (TextView) view.findViewById(R.id.item_currency);
        item_discount = (TextView) view.findViewById(R.id.item_discount);
        item_end_price = (TextView) view.findViewById(R.id.item_end_price);

        item_image.setImageResource(getArguments().getInt("imageSource"));
        item_label.setText(getArguments().getString("labelString"));
        item_amount.setText(String.format(Locale.getDefault(),"%1$d",getArguments().getInt("amount")));
        item_cost.setText(String.format(Locale.getDefault(),"%1$.2f",getArguments().getFloat("coastPerItem")));
        item_currency.setText(getArguments().getString("currency"));
        item_end_price.setText(String.format(Locale.getDefault(),"%1$.2f",getArguments().getFloat("coastPerItem") * getArguments().getInt("amount")));




        return view;
    }
}