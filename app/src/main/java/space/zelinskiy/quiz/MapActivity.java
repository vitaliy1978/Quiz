package space.zelinskiy.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {

//    private RecyclerView numbersList;
//    private List<User> result;
//    private NumbersAdapter numbersAdapter;
//    FrameLayout mapActivity;
//    Button reg;
//    private Toast backToast;
//
//    FirebaseAuth auth;
//
//    private FirebaseDatabase database;
//    //  private DatabaseReference reference;
//    DatabaseReference users;

    private static int SIGN_IN_CODE = 1;
    private Toast myToast;
    private FrameLayout mapActivity;
    private FirebaseListAdapter<User> adapter; //позволяет адаптировать данные из фаербэйз в объекты в программе

    //Функция которая отслеживат подключен ли юзер в базе данных
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SIGN_IN_CODE){
            if (requestCode==RESULT_OK){   //если ОК значит юзер авторизовался
                myToast = Toast.makeText(getBaseContext(),getString(R.string.ok_registration),Toast.LENGTH_SHORT);  //Вывод сообщения если юзер авторизован
                myToast.show();
                displayAllMessages();  //Объявление функции для вывода всех данных на экран
            } else {
                myToast = Toast.makeText(getBaseContext(),getString(R.string.error_registration),Toast.LENGTH_SHORT);  //Вывод сообщения если юзер НЕ авторизован
                myToast.show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapActivity = findViewById(R.id.mapActivity);

        //Пользователь ещё не авторизован
        if(FirebaseAuth.getInstance().getCurrentUser()==null){     //если пользователь не авторизован
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_CODE);
        } else{
            myToast = Toast.makeText(getBaseContext(),getString(R.string.ok_registration),Toast.LENGTH_SHORT);  //Вывод сообщения если юзер авторизован
            myToast.show();
            displayAllMessages();  //Объявление функции для вывода всех данных на экран
        }

    }

    //Функция для вывода всех данных на экран
    private void displayAllMessages() {
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        adapter = new FirebaseListAdapter<User>(this,User.class, R.layout.number_list_item, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, User model, int position) {
                TextView tv_name, tv_level, tv_average;   //Объекты в которые будем вкладывать данные
                tv_name = v.findViewById(R.id.tv_holder_item);
                tv_level = v.findViewById(R.id.tv_level_item);
                tv_average = v.findViewById(R.id.tv_average_item);

                User user = new User();
                tv_name.setText(user.getName());
                tv_level.setText(user.getLevel());
                tv_average.setText(user.getMiddleResult());
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public class SimpleAdapter extends RecyclerView.Adapter {
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }…
    }
}
