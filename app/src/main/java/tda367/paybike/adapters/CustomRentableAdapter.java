package tda367.paybike.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.R;
import tda367.paybike.model.Rentable;
import tda367.paybike.repository.Repository;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class works as an adapter for Rentable objects to be displayed in advertisement views
 */

public class CustomRentableAdapter extends ArrayAdapter<Rentable> {

    /* Widgets */
    private int layout;
    private ImageView rentableImage;
    private TextView city, name, price;
    private Switch available;

    private List<Rentable> rentables;
    private Context context;
    private Repository r;

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
    public Rentable getItem(int position) {
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
        r = new Repository();

        city = rentableView.findViewById(R.id.city);
        name = rentableView.findViewById(R.id.name);
        price = rentableView.findViewById(R.id.price);
        rentableImage = rentableView.findViewById(R.id.rentableImage);

        Rentable rentable = rentables.get(position);

        city.setText(rentable.getPosition().getCity());
        name.setText(rentable.getName());
        price.setText(formatPrice(rentable.getPrice()));
        setImageIfPresent(rentable);

        /* Use toggle button to change and display availability in MyRentablesActivity */
        if (layout == R.layout.view_layout_my_rentable) {
            available = rentableView.findViewById(R.id.available);
            if (rentable.isAvailable()) {
                available.setChecked(true);
            } else {
                available.setChecked(false);
            }
            available.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    rentable.setAvailable(!rentable.isAvailable());
                    r.updateRentable(rentable);
                }
            });
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
