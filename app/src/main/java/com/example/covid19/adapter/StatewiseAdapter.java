package com.example.covid19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19.R;
import com.example.covid19.model.Statewise;

import java.util.List;

public class StatewiseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Statewise> viewitemlists;
    private Context context;

    public StatewiseAdapter(Context context, List<Statewise> viewitemlists) {
        this.context = context;
        this.viewitemlists = viewitemlists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            // Here Inflating your recyclerview item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_HEADER) {
            // Here Inflating your header view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_header, parent, false);
            return new HeaderViewHolder(itemView);
        }
        else return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderViewHolder){
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;


        }
        else if (holder instanceof ItemViewHolder){

            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            Statewise object = viewitemlists.get(position);

            itemViewHolder.state.setText(object.getState());
            itemViewHolder.activeCase.setText(object.getActive());
            itemViewHolder.recoverCase.setText(object.getRecovered());
            itemViewHolder.confirmCase.setText(object.getConfirmed());
            itemViewHolder.deathCase.setText(object.getDeaths());

            itemViewHolder.conDeath.setText(" ↑ "+object.getDeltaconfirmed());
            itemViewHolder.acDeath.setText(" ↑ "+"0");
            itemViewHolder.recDeath.setText(" ↑ "+object.getDeltarecovered());
            itemViewHolder.deceasDeath.setText(" ↑ "+object.getDeltadeaths());


        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }


    // getItemCount increasing the position to 1. This will be the row of header
    @Override
    public int getItemCount() {
        return viewitemlists.size();
    }


    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View view) {
            super(view);

        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView activeCase,confirmCase,recoverCase,deathCase,state,conDeath,acDeath,recDeath,deceasDeath;

        public ItemViewHolder(View itemView) {
            super(itemView);
            activeCase = itemView.findViewById(R.id.activeTv);
            confirmCase = itemView.findViewById(R.id.confirmedTv);
            recoverCase = itemView.findViewById(R.id.recoveredTv);
            deathCase = itemView.findViewById(R.id.deceasedTv);
            state = itemView.findViewById(R.id.stateTv);
            conDeath = itemView.findViewById(R.id.conDeath);
            acDeath = itemView.findViewById(R.id.acDeath);
            recDeath = itemView.findViewById(R.id.recDeath);
            deceasDeath = itemView.findViewById(R.id.deceasDeath);

        }
    }
}
