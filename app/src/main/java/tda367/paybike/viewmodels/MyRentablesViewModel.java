package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.adapters.CustomRentableAdapter;
import tda367.paybike.model.Rentable;
import tda367.paybike.repository.Repository;

import static java.util.stream.Collectors.toCollection;

public class  MyRentablesViewModel extends ViewModel {

    private Repository r;
    private CustomRentableAdapter adapter;
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

    public void deleteSelectedRentable() {
        r.deleteRentable(selected);
    }

    public void signOut() {
        r.signOut();
    }
}
