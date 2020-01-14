package ru.reliableteam.observerrxjava2easyexample;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class TeaPresenter {
    private static final String TAG = "TeaPresenter";

    public Observable<String> getCupOfTea() {

        //Observable источник множества событий
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                try {
                    for (int i = 0; i < 5; i++) {
                        TimeUnit.SECONDS.sleep(1);
                        String cup = "cup: " + i;
                        Log.d(TAG, "getCupOfTea: " + Thread.currentThread().getName() + ": " + cup);
                        emitter.onNext(cup);
                    }

//                    emitter.onError(new NullPointerException());
//
//                    emitter.onNext("cup: 5");
//
//                    emitter.onComplete();

                } catch (InterruptedException e) {
                    Log.d(TAG, "getCupOfCoffee: not disposed");
                }
            }
        }).subscribeOn(Schedulers.io());
        //.subscribeOn(Schedulers.io())

        return observable;
    }

}
