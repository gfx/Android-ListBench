package com.example.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                .add(R.id.container, new PlaceholderFragment())
                .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBenchmark();
            }
        });
    }

    private void startBenchmark() {
        final List<String> list = new ArrayList<>();
        final ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 100; ++i) {
            list.add(String.valueOf(i));
            arrayList.add(String.valueOf(i));
        }

        long t0 = System.currentTimeMillis();

        int sum = 0;
        for (int x = 0; x < 100000; ++x) {
            for (int i = 0; i < list.size(); ++i) {
                sum += list.get(i).length();

            }
        }

        final long t1 = (System.currentTimeMillis() - t0);

        t0 = System.currentTimeMillis();

        sum = 0;
        for (int x = 0; x < 100000; ++x) {
            for (int i = 0; i < arrayList.size(); ++i) {
                sum += arrayList.get(i).length();
            }
        }

        final long t2 = (System.currentTimeMillis() - t0);

        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(textView.getText() + String.format("List<T>: %dms, ArrayList<T>+ %dms\n", t1, t2));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
