package com.android.littledev.duplom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;


public class ItemFragment extends Fragment {

    ImageButton item_image;
    ImageButton item_subtract;
    ImageButton item_add;
    TextView item_label;
    EditText item_amount;
    TextView item_cost;
    TextView item_currency;
    Button item_order;

    public static ItemFragment newInstance(int _Id, int image_source, String label_string, String currency, int amount, float cost_per_item) {
        ItemFragment fragmentDemo = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt("id", _Id);
        args.putInt("imageSource", image_source);
        args.putString("labelString", label_string);
        args.putString("currency", currency);
        args.putInt("amount", amount);
        args.putFloat("coastPerItem", cost_per_item);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment, container, false);
        item_image = (ImageButton) view.findViewById(R.id.item_image);
        item_subtract = (ImageButton) view.findViewById(R.id.item_subtract);
        item_add = (ImageButton) view.findViewById(R.id.item_add);
        item_label = (TextView) view.findViewById(R.id.item_label);
        item_amount = (EditText) view.findViewById(R.id.item_amount);
        item_cost = (TextView) view.findViewById(R.id.item_cost);
        item_currency = (TextView) view.findViewById(R.id.item_currency);
        item_order = (Button) view.findViewById(R.id.item_order);

        item_image.setImageResource(getArguments().getInt("imageSource"));
        item_label.setText(getArguments().getString("labelString"));
        item_amount.setText("1");
        item_cost.setText(String.format(Locale.getDefault(),"%1$.2f",getArguments().getFloat("coastPerItem")));
        item_currency.setText(getArguments().getString("currency"));

        final Database database = new Database(getActivity());

        item_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.onAddOrder(getArguments().getInt("id"), Integer.parseInt(item_amount.getText().toString()));

            }
        });

        item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ItemInfo.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",getArguments().getInt("id"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        item_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedAmount = Integer.parseInt(item_amount.getText().toString());
                if(getArguments().getInt("amount") >  selectedAmount) {
                    selectedAmount++;
                    item_cost.setText(String.format(Locale.getDefault(), "%1$.2f", getArguments().getFloat("coastPerItem") * selectedAmount));
                    item_amount.setText(String.format(Locale.getDefault(), "%1$d", selectedAmount));
                }
            }
        });
        item_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedAmount = Integer.parseInt(item_amount.getText().toString());
                if(0 <  selectedAmount){
                    selectedAmount--;
                    item_cost.setText(String.format(Locale.getDefault(),"%1$.2f",getArguments().getFloat("coastPerItem") * selectedAmount));
                    item_amount.setText(String.format(Locale.getDefault(),"%1$d",selectedAmount));
                }
            }
        });

        return view;
    }
}
