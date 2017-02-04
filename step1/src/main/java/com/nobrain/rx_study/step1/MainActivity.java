package com.nobrain.rx_study.step1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv_main);

        /*
         * What to do
         * from
         * 1, 2, 3, 4, 5, 6,...
         *
         * to
         * a, b, c, d, e, f,...
         */
        List<Integer> datas = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        Observable.fromIterable(datas)
                .map(new Function<Integer, Character>() {
                    @Override
                    public Character apply(Integer data) throws Exception {
                        return ((char) ('a' + data));
                    }
                })
                .subscribe(new Consumer<Character>() {
                    @Override
                    public void accept(Character character) throws Exception {
                        tv.setText(tv.getText().toString() + character);
                    }
                });
    }
}
