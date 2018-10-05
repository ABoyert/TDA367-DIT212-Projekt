package tda367.paybike.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import tda367.paybike.R;
import tda367.paybike.model.Bike;

public class RegisterUserFragment extends Fragment {

    private EditText userFirstName,
            userLastName,
            userEmail,
            userPassword,
            userRepeatedPassword;

    private OnFragmentInteractionListener mListener;

    public RegisterUserFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegisterUserFragment newInstance() {
        RegisterUserFragment fragment = new RegisterUserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View registerUserView = inflater.inflate(R.layout.fragment_register_user, container, false);

        userFirstName = (EditText) registerUserView.findViewById(R.id.userFirstName);
        userLastName = (EditText) registerUserView.findViewById(R.id.userLastName);
        userEmail = (EditText) registerUserView.findViewById(R.id.userEmail);
        userPassword = (EditText) registerUserView.findViewById(R.id.userPassword);
        userRepeatedPassword = (EditText) registerUserView.findViewById(R.id.userRepeatedPassword);

        return registerUserView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
