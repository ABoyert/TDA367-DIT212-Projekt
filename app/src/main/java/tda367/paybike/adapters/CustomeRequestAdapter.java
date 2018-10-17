package tda367.paybike.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import tda367.paybike.R;
import tda367.paybike.model.Request;

public class CustomeRequestAdapter extends ArrayAdapter<Request>{

    private int layout;

    private List<Request> requests;
    private Context context;
    private TextView receivingUser, targetRentable;


    public CustomeRequestAdapter(@NonNull Context context, int layout, @NonNull List<Request> requests) {
        super(context, layout, requests);
        this.context = context;
        this.requests = requests;
        this.layout = layout;
    }
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

        receivingUser = (TextView)requestView.findViewById(R.id.receivingUser);
        targetRentable = (TextView)requestView.findViewById(R.id.targetRentable);

        Request request = requests.get(position);

        receivingUser.setText(request.getReceivingUserID().toString());
        targetRentable.setText(request.getTargetRentableID().toString());

        return requestView;
    }

    public void updateBikeView(List<Request> newList) {
        requests = new ArrayList<>();
        requests.addAll(newList);
        notifyDataSetChanged();
    }
}
