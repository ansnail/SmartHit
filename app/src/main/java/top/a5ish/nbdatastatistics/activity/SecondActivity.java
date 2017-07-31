package top.a5ish.nbdatastatistics.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import top.a5ish.nbdatastatistics.R;
import top.a5ish.nbdatastatistics.fragment.FirstFragment;
import top.a5ish.nbdatastatistics.fragment.SecondFragment;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();

        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.first_view,firstFragment);
        transaction.replace(R.id.second_view,secondFragment);
        transaction.commit();
        Toast.makeText(this, "不能是空的啊1", Toast.LENGTH_SHORT).show();

    }


}
