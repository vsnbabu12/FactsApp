package vsn.com.factslist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vsn.com.factslist.adapters.FactListAdapter;
import vsn.com.factslist.models.FactsResponse;
import vsn.com.factslist.services.FactListInterface;
import vsn.com.factslist.services.FactListService;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "";
    private RecyclerView factsListView;
    FactListAdapter factListAdapter;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        factsListView = findViewById(R.id.factsListView);

        FactListInterface listService = FactListService.getClient().create(FactListInterface.class);

        Call<FactsResponse> call = listService.getAllFacts(API_KEY);
        call.enqueue(new Callback<FactsResponse>() {
            @Override
            public void onResponse(Call<FactsResponse> call, Response<FactsResponse> response) {
                displayList(response.body());
            }

            @Override
            public void onFailure(Call<FactsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,""+t.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.println("Error --> "+t.getMessage());
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayList(FactsResponse response){
        Toast.makeText(this,"getting resposne "+response.getTitle(),Toast.LENGTH_LONG).show();
        factListAdapter = new FactListAdapter(response.getRows(),this);
        factsListView.setAdapter(factListAdapter);

        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        factsListView.setLayoutManager(layoutManager);
    }
}
