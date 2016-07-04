package app.connect.com.connect.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.connect.com.connect.Objetos.Perfil;
import app.connect.com.connect.R;
import app.connect.com.connect.RecyclerViewOnClickListener;

/**
 * Created by aluno on 28/06/16.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<Perfil> mContactDataset;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;


    public ContactsAdapter(List<Perfil> myDataset) {
        mContactDataset = myDataset;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_contacts, parent, false);

        ContactsAdapter.ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTVContactNome.setText(mContactDataset.get(position).getNome());
        holder.mTVContactEmail.setText(mContactDataset.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return mContactDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTVContactNome;
        public TextView mTVContactEmail;
        public ViewHolder(View v) {
            super(v);
            mTVContactNome = (TextView) v.findViewById(R.id.tv_contact_nome);
            mTVContactEmail = (TextView) v.findViewById(R.id.tv_contact_email);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mRecyclerViewOnClickListener != null){
                        mRecyclerViewOnClickListener.onClickListener(v, getLayoutPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(mRecyclerViewOnClickListener != null){
                        mRecyclerViewOnClickListener.onLongPressClickListener(v, getLayoutPosition());
                    }
                    return false;
                }
            });
        }
    }

    public void setRecyclerViewOnClickListener(final RecyclerViewOnClickListener rvClickListener){
        mRecyclerViewOnClickListener = rvClickListener;
    }
}


