package vsn.com.factslist.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import vsn.com.factslist.R;
import vsn.com.factslist.models.Row;


public class FactListAdapter extends RecyclerView.Adapter<FactListAdapter.FactListViewHolder> {

    private List<Row> factsResponseList;

    public Context context;

    private Typeface font_MediumRegular;

    public class FactListViewHolder extends RecyclerView.ViewHolder {

        private TextView factText;

        private ImageView factImage;
        private TextView factTitle;



        private FactListViewHolder(View view) {
            super(view);
            // initialize the views here
            factText = view.findViewById(R.id.factText);
            factImage = view.findViewById(R.id.factImage);
            factTitle = view.findViewById(R.id.factTitle);

        }
    }

    public FactListAdapter(List<Row> factsResponseList, Context context) {
        this.factsResponseList = factsResponseList;

        this.context = context;

        //font_MediumRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Effra-Medium.ttf");

    }

    @Override
    public FactListAdapter.FactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fact_item, parent, false);

        return new FactListAdapter.FactListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FactListAdapter.FactListViewHolder holder, int position) {

        final Row fact = factsResponseList.get(position);

        holder.factText.setText(fact.getDescription());
        holder.factTitle.setText(fact.getTitle());
        String imageUrl = null;
        if(fact.getImageHref()!=null){
            imageUrl = fact.getImageHref().toString();
        }

        if(imageUrl != null && imageUrl.length() > 0){
            imageUrl = imageUrl;
            // load images here with TextView in fact_item.xml
            Glide.with(context).load(imageUrl)
                    .apply(new RequestOptions().error(R.drawable.facts))
                    .into(holder.factImage);
        }

        holder.factImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if Required need to add this class

                /*Intent intent = new Intent(context,OfferDetailsActivity.class);
                intent.putExtra("offerBody", deal.getBody());
                intent.putExtra("offerImageUrl",deal.getImage());

                intent.putExtra("offerTitle",deal.getTitle());
                //intent.putExtra("offerId",response.get(0).getToAirport());

                context.startActivity(intent);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return factsResponseList.size();
    }


}

