package tda367.paybike.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

import tda367.paybike.R;
import tda367.paybike.model.Bike;
import tda367.paybike.model.Rentable;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class works as an adapter for Bike objects to be displayed in advertisement views
 */

public class CustomBikeAdAdapter extends ArrayAdapter<Bike> {

    private int layout;
    private ArrayList<Bike> bikes;
    private Filter bikeFilter;

    public CustomBikeAdAdapter(@NonNull Context context, int layout, @NonNull ArrayList<Bike> bikes) {
        super(context, layout, bikes);
        this.bikes = bikes;
        this.layout = layout;
    }

    private String formatPrice(double price) {
        return "$" + price + "/h";
    }

    @Override
    public Bike getItem(int position){
        return bikes.get(position);
    }

    @Override
    public int getCount() {
        return bikes.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lsuInflator = LayoutInflater.from(getContext());
        View bikeView = lsuInflator.inflate(layout, parent, false);

        // TODO Implement imageView and update method to reflect correct bike attributes
        // ImageView image = (ImageView) ad.findViewById(R.id.bikeImage);
        TextView city = (TextView) bikeView.findViewById(R.id.city);
        TextView name = (TextView) bikeView.findViewById(R.id.bikeName);
        TextView price = (TextView) bikeView.findViewById(R.id.price);

        Bike bike = bikes.get(position);

        city.setText(bike.getPosition().toString());
        name.setText(bike.getName());
        price.setText(formatPrice(bike.getPrice()));

        return bikeView;
    }

    public void updateBikeView(ArrayList<Bike> newList) {
        bikes = new ArrayList<>();
        bikes.addAll(newList);
        notifyDataSetChanged();
    }

}
