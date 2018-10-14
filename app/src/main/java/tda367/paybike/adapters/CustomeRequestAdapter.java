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

import java.util.List;


import tda367.paybike.R;
import tda367.paybike.model.Request;

public class CustomeRequestAdapter extends ArrayAdapter<Request>{

    private int layout;

    private List<Request> requests;
    private Context context;


    public CustomeRequestAdapter(@NonNull Context context, int layout, @NonNull List<Request> requests) {
        super(context, layout, requests);
        this.context = context;
        this.requests = requests;
        this.layout = layout;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater lsuInflator = LayoutInflater.from(getContext());
        View requestView = lsuInflator.inflate(layout, parent, false);


        Movie currentMovie = moviesList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
        image.setImageResource(currentMovie.getmImageDrawable());

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentMovie.getmName());

        TextView release = (TextView) listItem.findViewById(R.id.textView_release);
        release.setText(currentMovie.getmRelease());

        return listItem;
    }

}
