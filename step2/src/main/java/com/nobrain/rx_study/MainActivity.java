package com.nobrain.rx_study;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private EditText etMain;
    private PublishSubject<String> subject;
    private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        etMain = (EditText) findViewById(R.id.et_main);

        subject = PublishSubject.create();

        disposable = subject.observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> {
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                }).filter(s -> !TextUtils.isEmpty(s))
                .observeOn(Schedulers.io())
                .concatMap(query -> {
                    return new DaumImageSearchApi().getImages(query);
                })
                .observeOn(Schedulers.computation())
                .flatMap(it -> Observable.fromIterable(it.channel.item))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchImage -> {
                    adapter.add(searchImage);
                    adapter.notifyDataSetChanged();
                }, t -> {
                    t.printStackTrace();
                    Toast.makeText(this, "Error!!", Toast.LENGTH_SHORT).show();
                });


        etMain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                subject.onNext(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchAdapter();
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }


}












