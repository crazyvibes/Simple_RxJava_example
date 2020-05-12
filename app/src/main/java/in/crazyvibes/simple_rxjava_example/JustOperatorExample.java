package in.crazyvibes.simple_rxjava_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class JustOperatorExample extends AppCompatActivity {
    private String[] greetings = {"Hello A","Hello B","Hello C"};
    private Observable<String[]> myObservable;
    // private Observer<String> myObserver;
    private DisposableObserver<String[]> myObserver;
    private DisposableObserver<String> myObserver2;
    private String TAG = this.getClass().getName();


    private TextView tvGreeting;

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_operator_example);


      //  tvGreeting = findViewById(R.id.tvGreeting);

        myObservable = Observable.just(greetings);

        compositeDisposable.add(
                myObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserver())
        );



    }

    private DisposableObserver getObserver()
    {
        myObserver = new DisposableObserver<String[]>() {
            @Override
            public void onNext( String[] s) {
                Log.i(TAG, "onNext invoked " +s);

               // tvGreeting.setText(s);
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
