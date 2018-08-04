package vsn.com.factslist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import vsn.com.factslist.adapters.FactListAdapter;
import vsn.com.factslist.fragments.FactsFragment;
import vsn.com.factslist.models.FactsResponse;
import vsn.com.factslist.models.MyViewModel;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "";
    private RecyclerView factsListView;
    FactListAdapter factListAdapter;
    ActionBar actionBar;
    ProgressBar progressBar;
    FactsFragment factsFragment;
    MyViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                refreshList();

            }
        });

        actionBar = getSupportActionBar();


        factsListView = findViewById(R.id.factsListView);
        progressBar =findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        factsFragment = (FactsFragment) getFragmentManager().findFragmentById(R.id.factFragment);

        model = ViewModelProviders.of(this).get(MyViewModel.class);

        model.getUsers().observe(this, new Observer<FactsResponse>() {
            public void onChanged(@Nullable FactsResponse response) {
                actionBar.setTitle(response.getTitle());
                factsFragment.displayList(response);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {model.getLatestList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refreshList(){
        model.getLatestList();
    }

}
