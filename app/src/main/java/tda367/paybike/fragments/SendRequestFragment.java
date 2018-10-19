package tda367.paybike.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
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
import tda367.paybike.viewmodels.RentableViewModel;

/*
 * Created by Julia Gustafsson
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SendRequestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SendRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendRequestFragment extends Fragment {

    private static final int FROM_DATE = 999;
    private static final int TO_DATE = 888;
    private static final int FROM_TIME = 777;
    private static final int TO_TIME = 666;

    // Widgets
    private EditText fromDate, toDate, fromTime, toTime;
    private TextView totalPrice;
    private Button sendRequestBtn;

    // Resources
    private RentableViewModel viewModel;
    private static Rentable rentable;
    private OnFragmentInteractionListener mListener;
    private Calendar calendar;
    private int openDialog;

    public SendRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static SendRequestFragment newInstance(Rentable r) {
        SendRequestFragment fragment = new SendRequestFragment();
        rentable = r;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(RentableViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View requestFragment = inflater.inflate(R.layout.fragment_send_request, container, false);
        calendar = Calendar.getInstance();
        // No dialog is initially open
        openDialog = 0;

        fromDate = requestFragment.findViewById(R.id.fromDate);
        fromDate.setInputType(InputType.TYPE_NULL);
        fromDate.setOnClickListener(v -> showDialog(FROM_DATE));

        fromTime = requestFragment.findViewById(R.id.fromTime);
        fromTime.setInputType(InputType.TYPE_NULL);
        fromTime.setOnClickListener(v -> showDialog(FROM_TIME));

        toDate = requestFragment.findViewById(R.id.toDate);
        toDate.setInputType(InputType.TYPE_NULL);
        toDate.setOnClickListener(v -> showDialog(TO_DATE));

        toTime = requestFragment.findViewById(R.id.toTime);
        toTime.setInputType(InputType.TYPE_NULL);
        toTime.setOnClickListener(v -> showDialog(TO_TIME));

        totalPrice = requestFragment.findViewById(R.id.totalPrice);
        totalPrice.setText("$" + String.format("%.2f", rentable.getPrice()));
        sendRequestBtn = requestFragment.findViewById(R.id.sendRequestBtn);
        sendRequestBtn.setEnabled(true);
        sendRequestBtn.setOnClickListener(v -> sendRequest());

        initHintTimes();

        return requestFragment;
    }

    public void sendRequest() {
        if (mListener != null) {
            mListener.onSendRequest();
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

    @Override
    public void onResume() {
        super.onResume();
        initHintTimes();
    }

    private void showDialog(int id) {
        Dialog dialog = getDialog(id);
        if (dialog instanceof DatePickerDialog) {
            DatePicker datePicker = ((DatePickerDialog) dialog).getDatePicker();
            datePicker.setMinDate(System.currentTimeMillis() + 1000);
            datePicker.setMaxDate((long) (System.currentTimeMillis() + 3.1556926 * Math.pow(10,10)));
            dialog.show();
        } else if (dialog instanceof TimePickerDialog) {
            dialog.show();
        }
    }

    private Dialog getDialog(int id) {
        LocalDateTime from = viewModel.getFromDateTime();
        LocalDateTime to = viewModel.getToDateTime();
        switch (id) {
            case FROM_DATE:
                openDialog = FROM_DATE;
                return new DatePickerDialog(getActivity(),
                        myDateListener, from.getYear(), from.getMonthValue(), from.getDayOfMonth());
            case FROM_TIME:
                openDialog = FROM_TIME;
                return new TimePickerDialog(getActivity(), myTimeListener,
                        from.getHour(), from.getMinute(), true);
            case TO_DATE:
                openDialog = TO_DATE;
                return new DatePickerDialog(getActivity(),
                        myDateListener, to.getYear(), to.getMonthValue(), to.getDayOfMonth());
            case TO_TIME:
                openDialog = TO_TIME;
                return new TimePickerDialog(getActivity(), myTimeListener,
                        to.getHour(), to.getMinute(), true);
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
                        updateFromDate(year, month+1, day);
                        updatePrice();
                    } else if (openDialog == TO_DATE) {
                        updateToDate(year, month+1, day);
                        updatePrice();
                    }
                    openDialog = 0;
                }
            };

    private TimePickerDialog.OnTimeSetListener myTimeListener = new
            TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker arg0,
                                      int hour, int min) {
                    if (openDialog == FROM_TIME) {
                        updateFromTime(hour, min);
                        updatePrice();
                    } else if (openDialog == TO_TIME) {
                        updateToTime(hour, min);
                        updatePrice();
                    }
                    openDialog = 0;
                }
            };

    private void updateFromDate(int year, int month, int day) {
        viewModel.setFromDate(LocalDate.of(year, month, day));
        fromDate.setText(viewModel.getFromDate().toString());
        updateBtn();
    }

    private void updateToDate(int year, int month, int day) {
        viewModel.setToDate(LocalDate.of(year, month, day));
        toDate.setText(viewModel.getToDate().toString());
        updateBtn();
    }

    private void updateFromTime(int hour, int min) {
        viewModel.setFromTime(LocalTime.of(hour, min));
        fromTime.setText(viewModel.getFromTime().toString());
        updateBtn();
    }

    private void updateToTime(int hour, int min) {
        viewModel.setToTime(LocalTime.of(hour, min));
        toTime.setText(viewModel.getToTime().toString());
        updateBtn();
    }

    private void updatePrice() {
        if (viewModel.timesAreValid()) {
            String stringPrice = "$" + String.format("%.2f", viewModel.getTotalPrice());
            totalPrice.setText(stringPrice);
        } else {
            totalPrice.setText("-");
        }
    }

    private void updateBtn() {
        if (viewModel.timesAreValid()) {
            sendRequestBtn.setEnabled(true);
        } else {
            sendRequestBtn.setEnabled(false);
        }
    }

    public void initHintTimes() {
        viewModel.initTimes();
        fromDate.setText(viewModel.getFromDate().toString());
        fromTime.setText(viewModel.getFromTime().getHour() + ":00");
        toDate.setText(viewModel.getToDate().toString());
        toTime.setText(viewModel.getToTime().getHour() + ":00");
    }

    /*
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onSendRequest();
    }
}
