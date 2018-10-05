package tda367.paybike.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import tda367.paybike.R;
import tda367.paybike.model.Bike;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BikeDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BikeDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BikeDetailsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NAME = "name";
    private static final String POSITION = "position";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";

    private String name,
            position,
            description;

    private double price;

    private TextView bikeName,
            bikeDescription,
            bikePosition,
            bikePrice;

    private OnFragmentInteractionListener mListener;

    public BikeDetailsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BikeDetailsFragment newInstance(String name, String description, String position, double price) {
        BikeDetailsFragment fragment = new BikeDetailsFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(POSITION, position);
        args.putString(DESCRIPTION, description);
        args.putDouble(PRICE, price);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
            position = getArguments().getString(POSITION);
            description = getArguments().getString(DESCRIPTION);
            price = getArguments().getDouble(PRICE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View bikeDetailsView = inflater.inflate(R.layout.fragment_bike_details, container, false);

        bikeName = (TextView) bikeDetailsView.findViewById(R.id.bikeName);
        bikeName.setText(name);
        bikePosition = (TextView) bikeDetailsView.findViewById(R.id.bikePosition);
        bikePosition.setText(position);
        bikeDescription = (TextView) bikeDetailsView.findViewById(R.id.bikeDescription);
        bikeDescription.setText(description);
        bikePrice = (TextView) bikeDetailsView.findViewById(R.id.bikePrice);
        bikePrice.setText(formatPrice(price));

        return bikeDetailsView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private String formatPrice(double price) {
        return "$" + price + "/h";
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
}