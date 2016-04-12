package slidingdrawer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.faisal.quizassignment.R;

/**
 * Created by root on 22/2/16.
 */
public class SlidingDrawer extends AppCompatActivity implements View.OnClickListener, android.widget.SlidingDrawer.OnDrawerOpenListener, android.widget.SlidingDrawer.OnDrawerCloseListener {
    android.widget.SlidingDrawer slidingDrawer;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidingdrawer_activity);
        slidingDrawer = (android.widget.SlidingDrawer) findViewById(R.id.slidingDrawer);
        listView = (ListView) findViewById(R.id.list1);
        String[] values = new String[]{"PHYSICS", "MATHS", "G.K."};
        ArrayAdapter<String> files = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(files);
        slidingDrawer.setOnDrawerOpenListener(this);
        slidingDrawer.setOnDrawerCloseListener(this);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onDrawerOpened() {
        Toast.makeText(this,"opened",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerClosed() {
        Toast.makeText(this,"closed",Toast.LENGTH_SHORT).show();
    }
}
