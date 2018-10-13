package tda367.paybike.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

import tda367.paybike.R;
import tda367.paybike.model.Rentable;
import tda367.paybike.viewmodels.AddBikeViewModel;
import tda367.paybike.viewmodels.BikeViewModel;
import tda367.paybike.viewmodels.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SendRequestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SendRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendRequestFragment extends Fragment {

    private static final ColorStateList COLOR_CORRECT = ColorStateList.valueOf(Color.parseColor("#30d9af"));
    private static final ColorStateList COLOR_WRONG = ColorStateList.valueOf(Color.parseColor("#e96e6e"));
    private static final int FROM_DATE = 999;
    private static final int TO_DATE = 888;
    private static final int FROM_TIME = 777;
    private static final int TO_TIME = 666;

    // Widgets
    private EditText fromDate, toDate, fromTime, toTime;
    private TextView totalPrice;
    private Button sendRequestBtn;


    // Resources
    private BikeViewModel viewModel;
    private static Rentable rentable;
    private OnFragmentInteractionListener mListener;
    private Calendar calendar;
    private int openDialog;


    private LocalDateTime fDateTime, tDateTime;
    private LocalDate fDate, tDate;
    private LocalTime fTime, tTime;

    private int fromYear, fromMonth, fromDay, fromHour, fromMin,
            toYear, toMonth, toDay, toHour, toMin;


    public SendRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    //
    public static SendRequestFragment newInstance(Rentable r) {
        SendRequestFragment fragment = new SendRequestFragment();
        rentable = r;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(BikeViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View requestFragment = inflater.inflate(R.layout.fragment_send_request, container, false);
        calendar = Calendar.getInstance();
        // No dialog is initially open
        openDialog = 0;

        fromDate = (EditText) requestFragment.findViewById(R.id.fromDate);
        fromDate.setInputType(InputType.TYPE_NULL);
        fromDate.setOnClickListener(v -> showDialog(FROM_DATE));
        //fromDate.setOnFocusChangeListener((v,f) -> showDialog(FROM_DATE));

        fromTime = (EditText) requestFragment.findViewById(R.id.fromTime);
        fromTime.setInputType(InputType.TYPE_NULL);
        fromTime.setOnClickListener(v -> showDialog(FROM_TIME));
        //fromTime.setOnFocusChangeListener((v,f) -> showDialog(FROM_TIME));

        toDate = (EditText) requestFragment.findViewById(R.id.toDate);
        toDate.setInputType(InputType.TYPE_NULL);
        toDate.setOnClickListener(v -> showDialog(TO_DATE));
        //toDate.setOnFocusChangeListener((v,f) -> showDialog(TO_DATE));

        toTime = (EditText) requestFragment.findViewById(R.id.toTime);
        toTime.setOnClickListener(v -> showDialog(TO_TIME));
        //toTime.setOnFocusChangeListener((v,f) -> showDialog(TO_TIME));

        totalPrice = (TextView) requestFragment.findViewById(R.id.totalPrice);
        totalPrice.setText("$" + rentable.getPrice());
        sendRequestBtn = (Button) requestFragment.findViewById(R.id.sendRequstBtn);
        sendRequestBtn.setEnabled(false);
        sendRequestBtn.setOnClickListener(v -> sendRequest());

        return requestFragment;
    }

    public void sendRequest() {
        if (mListener != null) {
            mListener.onSendRequest(rentable);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void showDialog(int id) {
        Dialog dialog = getDialog(id);
       if (dialog != null) {
           dialog.show();
       }
    }

    private Dialog getDialog(int id) {
        switch (id) {
            case FROM_DATE:
                openDialog = FROM_DATE;
                return new DatePickerDialog(getActivity(),
                        myDateListener, fromYear, fromMonth, fromDay);
            case FROM_TIME:
                openDialog = FROM_TIME;
                return new TimePickerDialog(getActivity(), myTimeListener,
                        fromHour, fromMin, true);
            case TO_DATE:
                openDialog = TO_DATE;
                return new DatePickerDialog(getActivity(),
                        myDateListener, toYear, toMonth, toDay);
            case TO_TIME:
                openDialog = TO_TIME;
                return new TimePickerDialog(getActivity(), myTimeListener,
                        toHour, toMin, true);
            default:
                return null;
        }
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int year, int month, int day) {
                    if (openDialog == FROM_DATE) {
                        fDate = LocalDate.of(year, month+1, day);
                        updateFromDate();
                    } else if (openDialog == TO_DATE) {
                        tDate = LocalDate.of(year, month+1,day);
                        updateToDate();
                    }
                }
            };

    private TimePickerDialog.OnTimeSetListener myTimeListener = new
            TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker arg0,
                                      int hour, int min) {
                    if (openDialog == FROM_TIME) {
                        fTime = LocalTime.of(hour, min);
                        updateFromTime();
                    } else if (openDialog == TO_TIME) {
                        tTime = LocalTime.of(hour, min);
                        updateToTime();
                    }
                }
            };

    private void updateFromDate() {
        viewModel.setFromDate(fDate);
        fromDate.setText(viewModel.getFromDate().toString());
    }

    private void updateToDate() {
        viewModel.setToDate(tDate);
        toDate.setText(viewModel.getToDate().toString());
    }

    private void updateFromTime() {
        viewModel.setFromTime(fTime);
        fromTime.setText(viewModel.getFromTime().toString());
    }

    private void updateToTime() {
        viewModel.setToTime(tTime);
        toTime.setText(viewModel.getToTime().toString());
    }

    /*
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onSendRequest(Rentable rentable);
    }
}
