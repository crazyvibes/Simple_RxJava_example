package in.crazyvibes.simple_rxjava_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class CompositeDisposableExample extends AppCompatActivity {

    private String greetings = "Hello this is RxJava example";
    private Observable<String> myObservable;
    // private Observer<String> myObserver;
    private DisposableObserver<String> myObserver;
    private DisposableObserver<String> myObserver2;
    private String TAG = this.getClass().getName();

    private TextView tvGreeting;

    CompositeDisposable compositeDisposable=new CompositeDisposable();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composite_disposable);

        tvGreeting = findViewById(R.id.tvGreeting);

        myObservable = Observable.just(greetings);

        myObserver = new DisposableObserver<String>() {
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
        compositeDisposable.add(myObserver);
        myObservable.subscribe(myObserver);

        myObserver2 = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "onNext invoked");

                // tvGreeting.setText(s);

                Toast.makeText(CompositeDisposableExample.this, s, Toast.LENGTH_SHORT).show();
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
        compositeDisposable.add(myObserver2);
        myObservable.subscribe(myObserver2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // disposable.dispose();
        // myObserver.dispose();
        // myObserver2.dispose();

        //subscription will be remove when we will go back to another activity or view.
        //And because of this there will be no crash or memory leak


        /**composite disposable maintain a list of subscription in the pool and
        dispose them all at once */

        compositeDisposable.clear();
    }
}
