package com.example.mediatorlivedatamvvm;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> source1 = new MutableLiveData<>();
    private MutableLiveData<String> source2 = new MutableLiveData<>();
    private MediatorLiveData<String> mediatorLiveData = new MediatorLiveData<>();

    public MediatorLiveData<String> getMediatorLiveData() {
        return mediatorLiveData;
    }

    public MainViewModel() {
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                source1.postValue("from source 1: ");
            }
        }, 1000, 1000);


        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                source2.postValue("from source 2: ");
            }
        }, 1000, 3000);


        mediatorLiveData.addSource(source1, value -> mediatorLiveData.setValue(value));
        mediatorLiveData.addSource(source2, value -> mediatorLiveData.setValue(value));


    }
}
