package in.crazyvibes.simple_rxjava_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SchedulerExample extends AppCompatActivity {

    private String greetings = "Hello this is RxJava example";
    private Observable<String> myObservable;
    private Observer<String> myObserver;
    private String TAG = this.getClass().getName();
    private TextView tvGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler_example4);
        tvGreeting = findViewById(R.id.tvGreeting);

        myObservable = Observable.just(greetings);

         //implementing schedulers

         myObservable.subscribeOn(Schedulers.io());

        // myObservable.observeOn(AndroidSchedulers.mainThread());


        myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "onSubscribe invoked");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "onNext invoked");
                tvGreeting.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "onError invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete invoked");
            }
        };

        myObservable.subscribe(myObserver);

    }
}
