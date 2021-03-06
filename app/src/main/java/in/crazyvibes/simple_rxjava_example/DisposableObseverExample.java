package in.crazyvibes.simple_rxjava_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class DisposableObseverExample extends AppCompatActivity {

    private String greetings = "Hello this is RxJava example";
    private Observable<String> myObservable;
    // private Observer<String> myObserver;
    private DisposableObserver<String> myObserver;
    private DisposableObserver<String> myObserver2;
    private String TAG = this.getClass().getName();

    private TextView tvGreeting;

    // private Disposable disposable;   going to use disposable observer


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disposable_obsever_example3);

        tvGreeting = findViewById(R.id.tvGreeting);

        myObservable = Observable.just(greetings);


        myObserver=new DisposableObserver<String>() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myObserver.dispose();
    }
}
