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
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import tda367.paybike.R;
import tda367.paybike.model.Bike;

import static java.util.stream.Collectors.*;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lsuInflator = LayoutInflater.from(getContext());
        View bikeView = lsuInflator.inflate(layout, parent, false);

        // TODO Implement imageView and update method to reflect correct bike attributes
        // ImageView image = (ImageView) ad.findViewById(R.id.bikeImage);
        TextView city = (TextView) bikeView.findViewById(R.id.city);
        TextView name = (TextView) bikeView.findViewById(R.id.name);
        TextView price = (TextView) bikeView.findViewById(R.id.price);

        Bike bike = bikes.get(position);

        city.setText(bike.getPosition().toString());
        name.setText(bike.getId());
        price.setText(formatPrice(bike.getPrice()));

        return bikeView;
    }

    @Override
    public Filter getFilter() {
        if (bikeFilter == null)
            bikeFilter = new CustomBikeFilter();
        return bikeFilter;
    }

    // Filter to enable searches for specific bikes, filtering on the bike name
    private class CustomBikeFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented will return the list in its entirety
                results.values = bikes;
                results.count = bikes.size();
            }
            else {
                // Perform filtering operation
                // TODO Filter on bike name instead of Id
                List<Bike> nBikeList = bikes.stream()
                        .filter(bike ->
                                bike.getId().toUpperCase()
                                        .startsWith(constraint.toString().toUpperCase()))
                        .collect(Collectors.toCollection(ArrayList::new));

                results.values = nBikeList;
                results.count = nBikeList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                // TODO Check if this is valid
                bikes = (ArrayList<Bike>) results.values;
                notifyDataSetChanged();
            }
        }
    }

}
