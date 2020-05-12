package in.crazyvibes.simple_rxjava_example;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class SimpleRxJavaExample extends AppCompatActivity {

    private String greetings = "Hello this is RxJava example";
    private Observable<String> myObservable;
    private Observer<String> myObserver;
    private String TAG = this.getClass().getName();
    private TextView tvGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_rx_java_example1);


        tvGreeting = findViewById(R.id.tvGreeting);

        myObservable = Observable.just(greetings);


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
