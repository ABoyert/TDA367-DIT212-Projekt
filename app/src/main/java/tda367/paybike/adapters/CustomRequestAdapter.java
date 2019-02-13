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
import tda367.paybike.repositori.Repository;

import static tda367.paybike.model.Request.Answer.*;

/*
 * Created by Anton Boyert on 2018-10-18.
 *
 * This class works as an adapter for Request objects to be displayed in the Request feed.
 */

public class CustomRequestAdapter extends ArrayAdapter<Request> {

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
        r = new Repository();
    }

    /* Formats the price with a dollar sign and two decimals */
    private String formatPrice(double price) {
        return "$" + String.format("%.2f", price);
    }

    @Override
    public Request getItem(int position) {
        return requests.get(position);
    }

    @Override
    public int getCount() {
        return requests.size();
    }

    /* Automatically called when a new view of a request is created */
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater lsuInflator = LayoutInflater.from(getContext());
        View requestView = lsuInflator.inflate(layout, parent, false);
        r = new Repository();

        /* Fetch the current user */
        User currentUser = r.getCurrentUser();

        /* Fetch the current request */
        Request request = requests.get(position);

        /* Fetch the current rentable */
        Rentable rentable = r.getPayBike().getRentableFromId(request.getTargetRentableId());

        /* Locate all widgets in View */
        image = requestView.findViewById(R.id.bikeImage);
        location = requestView.findViewById(R.id.city);
        title = requestView.findViewById(R.id.targetRentable);
        time = requestView.findViewById(R.id.time);
        price = requestView.findViewById(R.id.price);
        type = requestView.findViewById(R.id.type);
        pending = requestView.findViewById(R.id.pending);
        acceptBtn = requestView.findViewById(R.id.acceptBtn);
        declineBtn = requestView.findViewById(R.id.declineBtn);
        receivedRequest = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.baseline_move_to_inbox_white_48dp);

        /* Set text to display information about the current request */
        location.setText(rentable.getPosition().getStreet() + ", " + rentable.getPosition().getCity());
        title.setText(rentable.getName());
        time.setText(formatTime(request.getFromDateTime(), request.getToDateTime()));
        price.setText(formatPrice(request.getPrice()));
        setImageIfPresent(rentable);

        /* Since the same adapter is used for received and sent requests, this part
           handles the logic which decides what information and options to display */

        /* The standard bitmap is an image that represents a sent request. If the user is
         * the receiver, this bitmap is changed */
        if (!userIsSender(request, currentUser)) {
            type.setImageBitmap(receivedRequest);
        }

        /* A request can have a total of three states. ACCEPTED, DENIED or UNANSWERED.
           ACCEPTED and DENIED is displayed in the same way, while UNANSWERED needs to take into account
           who is the current user. */
        switch (request.getAnswer()) {
            case ACCEPTED:
                viewAsAccepted();
                break;
            case DENIED:
                viewAsDeclined();
                break;
            case UNANSWERED:
                /* Checks if request was sent by this user */
                if (userIsSender(request, currentUser)) {
                    viewAsUnansweredSent();
                    break;
                } else {
                    viewAsUnansweredRecieved();
                    break;
                }
        }

        /* Configure buttons in view */
        acceptBtn.setOnClickListener(v -> {
            acceptRequest(request, position);
        });
        declineBtn.setOnClickListener(v -> {
            if (userIsSender(request, currentUser)) {
                deleteRequest(request, position);
            } else {
                declineRequest(request, position);
            }
        });

        return requestView;

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

    /* Inserts a picture in the View if one exists */
    private void setImageIfPresent(Rentable rentable) {
        Uri imagePath = rentable.getImagePath();
        if (imagePath != null) {
            Glide
                    .with(context)
                    .load(imagePath)
                    .into(image);
        }
    }

    /* Used to delete the request, also removes it from the list of items */
    private void deleteRequest(Request request, int position) {
        r.deleteRequest(request);
        requests.remove(position);
        notifyDataSetChanged();
    }

    /* Help method which can decide if the current user sent or received the request */
    private boolean userIsSender(Request request, User currentUser) {
        return request.getSendingUserId().equals(currentUser.getUserID());
    }

    /* Accepts the chosen request. Will only be called by a receiver. */
    private void acceptRequest(Request request, int position) {
        request.setAnswer(ACCEPTED);
        r.updateRequest(request);
        requests.get(position).setAnswer(ACCEPTED);
        notifyDataSetChanged();
    }

    /* Accepts the chosen request. Will only be called by a receiver. */
    private void declineRequest(Request request, int position) {
        request.setAnswer(DENIED);
        r.updateRequest(request);
        requests.get(position).setAnswer(DENIED);
        notifyDataSetChanged();
    }

    /* The following methods are used to view different versions of the view
    *  depending on what status it has and who is the current user. It affects the
    *  buttons and their visibility. */
    private void viewAsAccepted() {
        acceptBtn.setVisibility(View.VISIBLE);
        acceptBtn.setEnabled(false);
        declineBtn.setVisibility(View.GONE);
        pending.setVisibility(View.GONE);
    }

    private void viewAsDeclined() {
        declineBtn.setVisibility(View.VISIBLE);
        declineBtn.setEnabled(false);
        acceptBtn.setVisibility(View.GONE);
        pending.setVisibility(View.GONE);
    }

    private void viewAsUnansweredSent() {
        pending.setVisibility(View.VISIBLE);
        acceptBtn.setVisibility(View.GONE);
        declineBtn.setVisibility(View.VISIBLE);
    }

    private void viewAsUnansweredRecieved() {
        pending.setVisibility(View.GONE);
        acceptBtn.setVisibility(View.VISIBLE);
        declineBtn.setVisibility(View.VISIBLE);
    }

    /* Allows updates of the feed */
    public void updateBikeView(List<Request> newList) {
        requests = new ArrayList<>();
        requests.addAll(newList);
        notifyDataSetChanged();
    }

}
