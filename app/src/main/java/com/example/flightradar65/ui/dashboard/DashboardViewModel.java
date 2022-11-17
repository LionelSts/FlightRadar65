package com.example.flightradar65.ui.dashboard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flightradar65.data.Dataset;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<Dataset> mDataset = new MutableLiveData<>();


    public MutableLiveData<Dataset> getDataset() {
        return mDataset;
    }

    public void loadDataset(Dataset dataset) {
        mDataset.setValue(dataset);
    }
}