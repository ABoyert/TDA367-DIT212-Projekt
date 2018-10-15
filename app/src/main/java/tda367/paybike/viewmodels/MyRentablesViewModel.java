package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomBikeAdAdapter;
import tda367.paybike.model.Rentable;
import tda367.paybike.repository.Repository;

import static java.util.stream.Collectors.toCollection;

public class  MyRentablesViewModel extends ViewModel {

    private Repository r;
    private CustomBikeAdAdapter adapter;
    private Rentable selected;

    public MyRentablesViewModel() {
        r = new Repository();
    }

    // TODO Anropa metod som filtrerar bort cyklar från andra användare
    public List<Rentable> getCurrentUserRentables() {
        return r.updateAndGetRentables().stream()
                .collect(toCollection(ArrayList::new));
    }

    public void setSelected(Rentable selected) {
        this.selected = selected;
    }

    public Rentable getSelected() {
        return selected;
    }

}
