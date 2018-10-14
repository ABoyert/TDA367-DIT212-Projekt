package tda367.paybike.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.Optional;

import tda367.paybike.R;
import tda367.paybike.model.Rentable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RentableDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RentableDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RentableDetailsFragment extends Fragment {

    private TextView rentableName,
            rentableDescription,
            rentablePosition,
            rentablePrice;

    private ImageView rentableImage;

    private Button rentBikeBtn;

    private static Rentable rentable;

    private OnFragmentInteractionListener mListener;

    // Required empty public constructor
    public RentableDetailsFragment() { }

    public static RentableDetailsFragment newInstance(Rentable r) {
        RentableDetailsFragment fragment = new RentableDetailsFragment();
        rentable = r;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rentableDetailsView = inflater.inflate(R.layout.fragment_bike_details, container, false);

        rentableName = (TextView) rentableDetailsView.findViewById(R.id.bikeName);
        rentableName.setText(rentable.getName());
        rentablePosition = (TextView) rentableDetailsView.findViewById(R.id.bikePosition);
        rentablePosition.setText(rentable.getPosition());
        rentableDescription = (TextView) rentableDetailsView.findViewById(R.id.bikeDescription);
        rentableDescription.setText(rentable.getDescription());
        rentablePrice = (TextView) rentableDetailsView.findViewById(R.id.bikePrice);
        rentablePrice.setText(formatPrice(rentable.getPrice()));
        rentableImage = (ImageView) rentableDetailsView.findViewById(R.id.bikeImage);
        rentBikeBtn = (Button) rentableDetailsView.findViewById(R.id.rentBikeBtn);
        rentBikeBtn.setOnClickListener(v -> onButtonPressed());

        setImageIfPresent(rentable);

        return rentableDetailsView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RentableDetailsFragment.OnFragmentInteractionListener) {
            mListener = (RentableDetailsFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /*
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onMakeRequest(Rentable rentable);
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onMakeRequest(rentable);
        }
    }

    private String formatPrice(double price) {
        return "$" + price + "/h";
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

}