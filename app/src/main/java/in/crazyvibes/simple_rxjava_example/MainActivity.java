package in.crazyvibes.simple_rxjava_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private String greetings="Hello this is RxJava example";
    private Observable<String> myObservable;
    private Observer<String> myObserver;
    private String TAG=this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myObservable=Observable.just(greetings);

        myObserver=new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"onSubscribe invoked");
            }

            @Override
            public void onNext(@NonNull String s) {

                Log.i(TAG,"onNext invoked");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"onError invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"onComplete invoked");
            }

        };
    }
}
