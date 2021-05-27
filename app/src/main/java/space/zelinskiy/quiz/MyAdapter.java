package space.zelinskiy.quiz;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    Context context;
    ArrayList<User> list;
    ArrayList<User> listFull;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.listFull = list;
        this.list = new ArrayList(listFull);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.number.setText(position+1+"");
        holder.name.setText(user.getName().substring(0,user.getName().length()-8));
        holder.level.setText(user.getLevel()+"");
        holder.average.setText(String.format("%d.%02d", user.getMiddleResult() / 100, (user.getMiddleResult() % 100)));

        switch (position){
            case 0: holder.itemView.setBackgroundResource(R.drawable.gold_shape);
                    break;
            case 1: holder.itemView.setBackgroundResource(R.drawable.silver_shape);
                break;
            case 2: holder.itemView.setBackgroundResource(R.drawable.bronse_shape);
                break;
            default: holder.itemView.setBackgroundResource(R.drawable.white_shape);
                break;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return userFilter;
    }

    private final Filter userFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<User> filteredUserList = new ArrayList<>();
            if (constraint==null || constraint.length()==0){
                filteredUserList.addAll(listFull);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(User user:listFull){
                    if (user.name.toLowerCase().contains(filterPattern))
                    {filteredUserList.add(user);}
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredUserList;
            results.count = filteredUserList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            list.clear();
            list.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder   {

        TextView name, level, average, number;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            number = itemView.findViewById(R.id.tvNumber);
            name = itemView.findViewById(R.id.tvname);
            level = itemView.findViewById(R.id.tvlevel);
            average = itemView.findViewById(R.id.tvaverage);
        }
    }
}
