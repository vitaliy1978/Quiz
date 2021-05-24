package space.zelinskiy.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import space.zelinskiy.quiz.Models.User;

public class MapActivity extends AppCompatActivity {

    private RecyclerView numbersList;
    private List<User> result;
    private  NumbersAdapter numbersAdapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        result = new ArrayList<>();

        numbersList = findViewById(R.id.toplist);
        numbersList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        numbersAdapter = new NumbersAdapter(result);  //количество элементов списка
        numbersList.setAdapter(numbersAdapter );

        updatelist();

        }

    private void updatelist(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                result.add(dataSnapshot.getValue(User.class));   //добавляем элемент в конец списка
                numbersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot datasnapshot, @Nullable String previousChildName) {
                User model = datasnapshot.getValue(User.class);
                int index = getItemIndex(model);
                result.set(index,model); //добавляем элемент в указанную позицию
                numbersAdapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                User model = dataSnapshot.getValue(User.class);
                int index = getItemIndex(model);
                result.remove(index);
                numbersAdapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private int getItemIndex(User user){
        int index=-1;
        for (int i=0;i<result.size();i++){
            if (result.get(i).equals(user.getKey())){
                index=i;
                break;
            }
        }
        return index;
    }
}
