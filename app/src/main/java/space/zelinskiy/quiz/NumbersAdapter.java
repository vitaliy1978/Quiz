package space.zelinskiy.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder> {

    private List<User> list;

    public NumbersAdapter(List<User> list) {
        this.list = list;
    }

    private static int viewHolderCount;
    private int numberItems;

    public NumbersAdapter(int numberOfItems){
        numberItems = numberOfItems;
        viewHolderCount = 0;
    }

//     @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdForListItem, parent, false);

        NumberViewHolder viewHolder = new NumberViewHolder(view);
        viewHolder.viewHolderIndex.setText("ViewHolder index: "+viewHolderCount);

        viewHolderCount++;

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
     //   User user = list.get(position);
        User user = new User();
        user.setName("qwer");
        user.setLevel(21);
        user.setMiddleResult(123);
//        holder.bind(position);
//        holder.viewHolderIndex.setText(user.getName());
//        holder.viewLevelIndex.setText(user.getLevel()+"");
//        holder.viewAverageIndex.setText(user.getMiddleResult()+"");
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class  NumberViewHolder extends RecyclerView.ViewHolder{

        TextView listItemNumberView;
        TextView viewHolderIndex;
        TextView viewLevelIndex;
        TextView viewAverageIndex;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemNumberView = itemView.findViewById(R.id.tv_number_item);
            viewHolderIndex = itemView.findViewById(R.id.tv_holder_item);
            viewLevelIndex = itemView.findViewById(R.id.tv_level_item);
            viewAverageIndex = itemView.findViewById(R.id.tv_average_item);
        }
        void bind(int listIndex){
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }

}
