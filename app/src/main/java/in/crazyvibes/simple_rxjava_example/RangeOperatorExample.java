package in.crazyvibes.simple_rxjava_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RangeOperatorExample extends AppCompatActivity {

    private int[] num = {1,2,3,4,5,6,7};
    private Observable<Integer> myObservable;
    // private Observer<String> myObserver;
    private DisposableObserver<Integer> myObserver;
    private DisposableObserver<String> myObserver2;
    private String TAG = this.getClass().getName();



    private TextView tvGreeting;

    CompositeDisposable compositeDisposable=new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_operator_example);

          tvGreeting = findViewById(R.id.tvGreeting);

        myObservable = Observable.range(1,20);

        compositeDisposable.add(
                myObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserver())
        );

    }

    private DisposableObserver getObserver()
    {
        myObserver = new DisposableObserver<Integer>() {
            @Override
            public void onNext( Integer s) {
                Log.i(TAG, "onNext invoked " +s);

//                tvGreeting.setText(s);
            }

            @Override
            public void onError( Throwable e) {
                Log.i(TAG, "onError invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete invoked");
            }
        };
        return myObserver;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }


}
