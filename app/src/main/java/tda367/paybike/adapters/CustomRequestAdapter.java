package tda367.paybike.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import tda367.paybike.R;
import tda367.paybike.model.Rentable;
import tda367.paybike.model.Request;
import tda367.paybike.repository.Repository;

public class CustomRequestAdapter extends ArrayAdapter<Request>{

    private int layout;

    private List<Request> requests;
    private Context context;
    private TextView location, title, startTime, endTime, price;
    private Repository r;


    public CustomRequestAdapter(@NonNull Context context, int layout, @NonNull List<Request> requests) {
        super(context, layout, requests);
        this.context = context;
        this.requests = requests;
        this.layout = layout;
    }

    //complement the start time to the desired string
    private String formatFromTime(LocalDateTime fromTime) {
        return "From: " + fromTime.toLocalDate().toString() + " " + fromTime.toLocalTime().toString();
    }

    //complement the end time to the desired string
    private String formatToTime(LocalDateTime toTime) {
        return "To: " + toTime.toLocalDate().toString() + " " + toTime.toLocalTime().toString();
    }

    //complement the price to the desired string
    private String formatPrice(double price) { return "$" + price;}

    @Override
    public Request getItem(int position){
        return requests.get(position);
    }

    @Override
    public int getCount() {
        return requests.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater lsuInflator = LayoutInflater.from(getContext());
        View requestView = lsuInflator.inflate(layout, parent, false);
        r = new Repository();

        Request request = requests.get(position);
        Rentable rentable = r.getPayBike().getRentableFromId(request.getTargetRentableId());

        location = requestView.findViewById(R.id.city);
        title = requestView.findViewById(R.id.targetRentable);
        startTime = requestView.findViewById(R.id.startTime);
        endTime = requestView.findViewById(R.id.endTime);
        price = requestView.findViewById(R.id.price);


        location.setText(rentable.getPosition().getStreet() + ", " + rentable.getPosition().getCity());
        title.setText(rentable.getName());
        startTime.setText(formatFromTime(request.getFromDateTime()));
        endTime.setText(formatToTime(request.getToDateTime()));
        price.setText(formatPrice(request.getPrice()));

        return requestView;
    }

    public void updateBikeView(List<Request> newList) {
        requests = new ArrayList<>();
        requests.addAll(newList);
        notifyDataSetChanged();
    }
}
