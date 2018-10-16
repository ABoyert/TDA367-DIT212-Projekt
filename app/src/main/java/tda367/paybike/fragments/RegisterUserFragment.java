package tda367.paybike.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import tda367.paybike.R;
import tda367.paybike.viewmodels.LoginViewModel;

public class RegisterUserFragment extends Fragment {

    private static final ColorStateList COLOR_CORRECT = ColorStateList.valueOf(Color.parseColor("#30d9af"));
    private static final ColorStateList COLOR_WRONG = ColorStateList.valueOf(Color.parseColor("#e96e6e"));

    private EditText userName,
            userEmail,
            userPassword,
            userRepeatedPassword;

    private Button registerBtn;

    private OnFragmentInteractionListener mListener;
    private LoginViewModel viewModel;

    // Required empty public constructor
    public RegisterUserFragment() { }

    public static RegisterUserFragment newInstance() {
        RegisterUserFragment fragment = new RegisterUserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View registerUserView = inflater.inflate(R.layout.fragment_register_user, container, false);

        userName = registerUserView.findViewById(R.id.userName);
        userName.setText(viewModel.getName());
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setName(userName.getText().toString());
                boolean valid = viewModel.nameIsValid() ? true : false;
                setBackgroundTintListColor(valid, userName);
                updateRegisterBtn();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        userEmail = registerUserView.findViewById(R.id.userEmail);
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

        userPassword = registerUserView.findViewById(R.id.userPassword);
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
        userRepeatedPassword = registerUserView.findViewById(R.id.userRepeatedPassword);
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

        registerBtn = registerUserView.findViewById(R.id.registerUserBtn);
        registerBtn.setEnabled(false);
        registerBtn.setOnClickListener(v -> onButtonPressed());

        return registerUserView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegisterUserFragment.OnFragmentInteractionListener) {
            mListener = (RegisterUserFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onUserRegistration();
        }
    }

    /*
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onUserRegistration();
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
