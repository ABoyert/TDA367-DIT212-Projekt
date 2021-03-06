package tda367.paybike.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import tda367.paybike.R;
import tda367.paybike.model.Rentable;
import tda367.paybike.viewmodels.RentableViewModel;

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

    private RentableViewModel viewModel;

    // Required empty public constructor
    public RentableDetailsFragment() {
    }

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
        View rentableDetailsView = inflater.inflate(R.layout.fragment_rentable_details, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(RentableViewModel.class);

        rentableName = rentableDetailsView.findViewById(R.id.name);
        rentableName.setText(rentable.getName());
        rentablePosition = rentableDetailsView.findViewById(R.id.position);
        rentablePosition.setText(rentable.getPosition().getStreet() + ", " +
                rentable.getPosition().getZipCode() + ", " +
                rentable.getPosition().getCity());
        rentableDescription = rentableDetailsView.findViewById(R.id.description);
        rentableDescription.setText(rentable.getDescription());
        rentablePrice = rentableDetailsView.findViewById(R.id.price);
        rentablePrice.setText(formatPrice(rentable.getPrice()));
        rentableImage = rentableDetailsView.findViewById(R.id.rentableImage);
        rentBikeBtn = rentableDetailsView.findViewById(R.id.rentBtn);
        rentBikeBtn.setOnClickListener(v -> onButtonPressed());

        /* Can't rent your own bike, disable button if owner = current user */
        if (viewModel.isUserRentableOwner()) {
            rentBikeBtn.setEnabled(false);
            rentBikeBtn.setText("You own this bike");
        }

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
        void onMakeRequest();
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onMakeRequest();
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