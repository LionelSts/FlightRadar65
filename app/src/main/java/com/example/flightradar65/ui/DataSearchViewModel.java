package com.example.flightradar65.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flightradar65.data.Dataset;

public class DataSearchViewModel extends ViewModel {
    private final MutableLiveData<Dataset> selectedItem = new MutableLiveData<>();
    public void loadDataset(Dataset dataset) {
        selectedItem.setValue(dataset);
    }
    public LiveData<Dataset> getDataset() {
        return selectedItem;
    }
}