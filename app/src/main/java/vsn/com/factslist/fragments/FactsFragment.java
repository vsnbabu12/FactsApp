package vsn.com.factslist.fragments;

import android.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vsn.com.factslist.MainActivity;
import vsn.com.factslist.R;
import vsn.com.factslist.adapters.FactListAdapter;
import vsn.com.factslist.models.FactsResponse;
import vsn.com.factslist.models.MyViewModel;

public class FactsFragment extends Fragment {

    private RecyclerView factsListView;
    FactListAdapter factListAdapter;
    private final static String API_KEY = "";
    MyViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fact_fragment, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        factsListView = view.findViewById(R.id.factsListView);
    }

    public void displayList(FactsResponse response){
        //Toast.makeText(this,"getting resposne "+response.getTitle(),Toast.LENGTH_LONG).show();
        factListAdapter = new FactListAdapter(response.getRows(),this.getActivity());
        factsListView.setAdapter(factListAdapter);

        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        factsListView.setLayoutManager(layoutManager);
    }
}

