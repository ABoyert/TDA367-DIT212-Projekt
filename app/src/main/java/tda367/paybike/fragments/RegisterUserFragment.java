package tda367.paybike.fragments;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import tda367.paybike.R;
import tda367.paybike.model.Bike;
import tda367.paybike.viewmodels.AddBikeViewModel;
import tda367.paybike.viewmodels.MainViewModel;

public class RegisterUserFragment extends Fragment {

    private static final ColorStateList COLOR_CORRECT = ColorStateList.valueOf(Color.parseColor("#30d9af"));
    private static final ColorStateList COLOR_WRONG = ColorStateList.valueOf(Color.parseColor("#e96e6e"));

    private EditText userName,
            userEmail,
            userPassword,
            userRepeatedPassword;

    private Button registerBtn;

    private OnFragmentInteractionListener mListener;
    private MainViewModel viewModel;

    public RegisterUserFragment() {
        // Required empty public constructor
    }

    public static RegisterUserFragment newInstance() {
        RegisterUserFragment fragment = new RegisterUserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        /*if (getArguments() != null) {
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View registerUserView = inflater.inflate(R.layout.fragment_register_user, container, false);

        userName = (EditText) registerUserView.findViewById(R.id.userName);
        userName.setText(viewModel.getName());
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setName(userName.getText().toString());
                System.out.println(viewModel.getName());
                boolean valid = viewModel.nameIsValid() ? true : false;
                setBackgroundTintListColor(valid, userName);
                updateRegisterBtn();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        userEmail = (EditText) registerUserView.findViewById(R.id.userEmail);
        userEmail.setText(viewModel.getEmail());
        userEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setEmail(userEmail.getText().toString());
                boolean valid = viewModel.emailIsValid() ? true : false;
                setBackgroundTintListColor(valid, userEmail);
                updateRegisterBtn();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        userPassword = (EditText) registerUserView.findViewById(R.id.userPassword);
        userPassword.setText(viewModel.getPassword());
        userPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setPassword(userPassword.getText().toString());
                boolean valid = viewModel.passwordIsValid() ? true : false;
                setBackgroundTintListColor(valid, userPassword);
                updateRegisterBtn();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        userRepeatedPassword = (EditText) registerUserView.findViewById(R.id.userRepeatedPassword);
        userRepeatedPassword.setText(viewModel.getRepeatedPassword());
        userRepeatedPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setRepeatedPassword(userRepeatedPassword.getText().toString());
                boolean valid = viewModel.passwordsMatch() ? true : false;
                setBackgroundTintListColor(valid, userRepeatedPassword);
                updateRegisterBtn();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        registerBtn = (Button) registerUserView.findViewById(R.id.registerUserBtn);
        registerBtn.setEnabled(false);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.createUser()) {
                    getFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(),
                            "Unable to register user at the moment. Please try again later.",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        return registerUserView;
    }
/*
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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

    private void setBackgroundTintListColor(boolean valid, EditText editText) {
        if (valid) {
            ViewCompat.setBackgroundTintList(editText, COLOR_CORRECT);
        } else {
            ViewCompat.setBackgroundTintList(editText, COLOR_WRONG);
        }
    }

    private void updateRegisterBtn() {
        registerBtn.setEnabled(viewModel.inputIsValid());
    }
}
