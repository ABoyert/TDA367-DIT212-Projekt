package tda367.paybike.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tda367.paybike.R;
import tda367.paybike.model.Bike;
import tda367.paybike.model.Rentable;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class works as an adapter for Bike objects to be displayed in advertisement views
 */

public class CustomBikeAdAdapter extends ArrayAdapter<Rentable> {

    private int layout;
    private ImageView rentableImage;
    private TextView city, name, price;

    private List<Rentable> rentables;
    private Filter bikeFilter;
    private Context context;

    public CustomBikeAdAdapter(@NonNull Context context, int layout, @NonNull List<Rentable> rentables) {
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
        View bikeView = lsuInflator.inflate(layout, parent, false);

        // ImageView image = (ImageView) ad.findViewById(R.id.bikeImage);
        city = (TextView) bikeView.findViewById(R.id.city);
        name = (TextView) bikeView.findViewById(R.id.bikeName);
        price = (TextView) bikeView.findViewById(R.id.price);
        rentableImage = (ImageView) bikeView.findViewById(R.id.bikeImage);

        Rentable rentable = rentables.get(position);

        city.setText(rentable.getPosition().toString());
        name.setText(rentable.getName());
        price.setText(formatPrice(rentable.getPrice()));
        setImageIfPresent(rentable);

        return bikeView;
    }

    private void setImageIfPresent(Rentable rentable) {
        Uri imagePath = rentable.getImagePath();
        if (imagePath != null) {
            Glide
                    .with(getContext())
                    .load(imagePath)
                    .into(rentableImage);
        }
    }

    public void updateBikeView(List<Rentable> newList) {
        rentables = new ArrayList<>();
        rentables.addAll(newList);
        notifyDataSetChanged();
    }

}
