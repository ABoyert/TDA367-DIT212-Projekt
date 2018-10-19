package tda367.paybike.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import tda367.paybike.R;
import tda367.paybike.model.Rentable;
import tda367.paybike.model.Request;
import tda367.paybike.model.User;
import tda367.paybike.repository.Repository;

public class CustomRequestAdapter extends ArrayAdapter<Request>{

    /* Widgets */
    private List<Request> requests;
    private TextView location, title, time, price;
    private ImageView image, type, pending;
    private ImageButton acceptBtn, declineBtn;

    /* Resources */
    private Context context;
    private Repository r;
    private int layout;
    private Bitmap receivedRequest;

    public CustomRequestAdapter(@NonNull Context context, int layout, @NonNull List<Request> requests) {
        super(context, layout, requests);
        this.context = context;
        this.requests = requests;
        this.layout = layout;
    }

    /* Formats String to display date on format 2018-08-16 18:00 - 2018-08-17 12:00 */
    private String formatTime(LocalDateTime fromTime, LocalDateTime toTime) {
        String from = fromTime.toLocalDate().toString() + " " + fromTime.getHour() + ":";
        /* Add an extra zero if number of min is less than 10. Hence, 9 min are expressed as 09. */
        from += fromTime.getMinute() < 10 ? "0" + fromTime.getMinute() : fromTime.getMinute();
        String to = toTime.toLocalDate().toString() + " " + toTime.getHour() + ":";
        to += toTime.getMinute() < 10 ? "0" + toTime.getMinute() : toTime.getMinute();
        return from + " - " + to;
    }

    /* Formats the price with a dollar sign and two decimals */
    private String formatPrice(double price) { return "$" + String.format("%.2f", price); }

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
        User currentUser = r.getCurrentUser();
        receivedRequest = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.baseline_move_to_inbox_white_48dp);

        Request request = requests.get(position);
        Rentable rentable = r.getPayBike().getRentableFromId(request.getTargetRentableId());

        image = requestView.findViewById(R.id.bikeImage);
        location = requestView.findViewById(R.id.city);
        title = requestView.findViewById(R.id.targetRentable);
        time = requestView.findViewById(R.id.time);
        price = requestView.findViewById(R.id.price);
        type = requestView.findViewById(R.id.type);
        pending = requestView.findViewById(R.id.pending);
        acceptBtn = requestView.findViewById(R.id.acceptBtn);
        declineBtn = requestView.findViewById(R.id.declineBtn);

        location.setText(rentable.getPosition().getStreet() + ", " + rentable.getPosition().getCity());
        title.setText(rentable.getName());
        time.setText(formatTime(request.getFromDateTime(), request.getToDateTime()));
        price.setText(formatPrice(request.getPrice()));
        setImageIfPresent(rentable);

        /* Check if request was sent by this user */
        if (request.getSendingUserId().equals(currentUser.getUserID())) {
            /* If request is not yet answered, display pending */
            if (request.isAnswered()) {
                pending.setVisibility(View.GONE);
                if (request.isAccepted()) {
                    acceptBtn.setVisibility(View.VISIBLE);
                    acceptBtn.setEnabled(false);
                } else {
                    declineBtn.setVisibility(View.VISIBLE);
                    declineBtn.setEnabled(false);
                }

            } else {
                showButtons(false);
                pending.setVisibility(View.VISIBLE);
            }

        } else {
            /* Set icon to indicate received */
            type.setImageBitmap(receivedRequest);
            if (request.isAnswered()) {
                if (request.isAccepted()) {
                    acceptBtn.setVisibility(View.VISIBLE);
                    acceptBtn.setEnabled(false);
                } else {
                    declineBtn.setVisibility(View.VISIBLE);
                    declineBtn.setEnabled(false);
                }
            } else {
                pending.setVisibility(View.GONE);
                showButtons(true);
            }
        }

        acceptBtn.setOnClickListener(v -> acceptRequest(request));
        declineBtn.setOnClickListener(v -> declineRequest(request));

        return requestView;

    }

    private void acceptRequest(Request request) {
        request.setAnswered(true);
        request.setAccepted(true);
        r.updateRequest(request);
    }

    private void declineRequest(Request request) {
        request.setAnswered(true);
        request.setAccepted(false);
        r.updateRequest(request);
    }

    private void showButtons(boolean visible) {
        if (visible) {
            acceptBtn.setVisibility(View.VISIBLE);
            declineBtn.setVisibility(View.VISIBLE);
        } else {
            acceptBtn.setVisibility(View.GONE);
            declineBtn.setVisibility(View.GONE);
        }
    }

    public void updateBikeView(List<Request> newList) {
        requests = new ArrayList<>();
        requests.addAll(newList);
        notifyDataSetChanged();
    }

    private void setImageIfPresent(Rentable rentable) {
        Uri imagePath = rentable.getImagePath();
        if (imagePath != null) {
            Glide
                    .with(context)
                    .load(imagePath)
                    .into(image);
        }
    }

}
