package tda367.paybike.fragments;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NAME = "name";
    private static final String POSITION = "position";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";

  /*  private String name,
            position,
            description;

    private double price; */

    private TextView rentableName,
            rentableDescription,
            rentablePosition,
            rentablePrice;

    private ImageView rentableImage;

    private static Rentable rentable;

    private OnFragmentInteractionListener mListener;

    public RentableDetailsFragment() {
        // Required empty public constructor
    }

    public static RentableDetailsFragment newInstance(Rentable r) {
        RentableDetailsFragment fragment = new RentableDetailsFragment();
        rentable = r;
        /* Bundle args = new Bundle();
        args.putString(NAME, rentable.getName());
        args.putString(POSITION, rentable.getPosition());
        args.putString(DESCRIPTION, rentable.getDescription());
        args.putDouble(PRICE, rentable.getPrice());
        args.put
        fragment.setArguments(args); */
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            name = getArguments().getString(NAME);
            position = getArguments().getString(POSITION);
            description = getArguments().getString(DESCRIPTION);
            price = getArguments().getDouble(PRICE); */
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
        setImageIfPresent(rentable);

        return rentableDetailsView;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // TODO: Rename method, update argument and hook method into UI event, this is method for send request
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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