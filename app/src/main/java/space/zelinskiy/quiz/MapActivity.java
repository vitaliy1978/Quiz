package space.zelinskiy.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import space.zelinskiy.quiz.Models.User;

public class MapActivity extends AppCompatActivity {

    private RecyclerView numbersList;
   // private List<User> result;
    private  NumbersAdapter numbersAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

      //  result = new ArrayList<>();

        numbersList = findViewById(R.id.toplist);
        numbersList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(linearLayoutManager);
       // linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    //    createResult();

        numbersAdapter = new NumbersAdapter(100);  //количество элементов списка
        numbersList.setAdapter(numbersAdapter );

        }
//        private void createResult(){
//            for(int i=0;i<10;i++){
//                result.add(new User("name",5,5));
//            }
//    }
}
