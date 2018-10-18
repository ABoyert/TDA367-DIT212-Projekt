package tda367.paybike.adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.R;
import tda367.paybike.model.Rentable;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class works as an adapter for Rentable objects to be displayed in advertisement views
 */

public class CustomRentableAdapter extends ArrayAdapter<Rentable> {

    private int layout;
    private ImageView rentableImage;
    private TextView city, name, price;
    private LinearLayout body;

    private List<Rentable> rentables;
    private Context context;

    public CustomRentableAdapter(@NonNull Context context, int layout, @NonNull List<Rentable> rentables) {
        super(context, layout, rentables);
        this.context = context;
        this.rentables = rentables;
        this.layout = layout;
    }

    private String formatPrice(double price) {
        return "$" + price + "/h";
    }

    @Override
    public Rentable getItem(int position){
        return rentables.get(position);
    }

    @Override
    public int getCount() {
        return rentables.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lsuInflator = LayoutInflater.from(getContext());
        View rentableView = lsuInflator.inflate(layout, parent, false);

        city = rentableView.findViewById(R.id.city);
        name = rentableView.findViewById(R.id.name);
        price = rentableView.findViewById(R.id.price);
        rentableImage = rentableView.findViewById(R.id.rentableImage);
        body = rentableView.findViewById(R.id.body);

        Rentable rentable = rentables.get(position);

        city.setText(rentable.getPosition().toString());
        name.setText(rentable.getName());
        price.setText(formatPrice(rentable.getPrice()));
        setImageIfPresent(rentable);

        /* Make available bikes have light green background in MyRentablesActivity */
        if (layout == R.layout.view_layout_my_rentable && rentable.isAvailable()) {
            body.setBackgroundColor(Color.parseColor("#4d2fd9af"));
        }

        return rentableView;
    }

    private void setImageIfPresent(Rentable rentable) {
        Uri imagePath = rentable.getImagePath();
        if (imagePath != null) {
            Glide
                    .with(context)
                    .load(imagePath)
                    .into(rentableImage);
        }
    }

    public void updateRentableView(List<Rentable> newList) {
        rentables = new ArrayList<>();
        rentables.addAll(newList);
        notifyDataSetChanged();
    }
}
