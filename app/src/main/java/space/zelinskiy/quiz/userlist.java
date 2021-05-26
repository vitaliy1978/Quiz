package space.zelinskiy.quiz;

import androidx.annotation.NonNull;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class userlist extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<User> list;
    private Toast backToast;
    FirebaseAuth auth;
    FirebaseDatabase db;
    Button buttonReg;
    TextView textTop, button_close_from_userlist;
    public static int alreadyre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        final int alreadyReg = save.getInt("alreadyReg", 0);

        SharedPreferences.Editor editor3 = save.edit();
        editor3.putInt("lastStr".toString(), 1);
        editor3.commit();

        textTop = findViewById(R.id.textTop);
        button_close_from_userlist = findViewById(R.id.button_close_from_userlist);

        buttonReg = findViewById(R.id.buttonReg);
        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();


        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);

                  //  myAdapter.notifyDataSetChanged();

                    Collections.sort(list,User.levelSort);
                //    Collections.sort(list,User.AverageSort);

                }

                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (alreadyReg==1){
            buttonReg.setVisibility(View.INVISIBLE);
        }

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (alreadyReg==0 && alreadyre==0){
                    showRegisterWindows();
                }
//                Collections.sort(list,User.levelSort);
                myAdapter.notifyDataSetChanged();
            }
        });

        button_close_from_userlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(userlist.this,OptionHelp.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });

    }
        public void showRegisterWindows() {
        AlertDialog.Builder dialod = new AlertDialog.Builder(this);
        dialod.setTitle(R.string.title_registration);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View register_windows = layoutInflater.inflate(R.layout.register_windows, null);
        dialod.setView(register_windows);
        MaterialEditText email = register_windows.findViewById(R.id.emailField);
        MaterialEditText password = register_windows.findViewById(R.id.passField);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        final int middleResult = save.getInt("middleResult", 0);
        final int level = save.getInt("Level", 1);

        dialod.setNegativeButton(R.string.back_registration, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialod.setPositiveButton(R.string.ok_registration, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (TextUtils.isEmpty(email.getText().toString())) {
                  //  Snackbar.make(finishLayout, R.string.name_registration, Snackbar.LENGTH_SHORT).show();
                    backToast = Toast.makeText(getBaseContext(),getString(R.string.name_registration),Toast.LENGTH_SHORT);
                    backToast.show();
                    return;
                }
                if (password.getText().toString().length() < 6) {
                 //   Snackbar.make(finishLayout, R.string.password_registration, Snackbar.LENGTH_SHORT).show();
                    backToast = Toast.makeText(getBaseContext(),getString(R.string.password_registration),Toast.LENGTH_SHORT);
                    backToast.show();
                    return;
                }

                //регистрация пользователя
                auth.createUserWithEmailAndPassword(email.getText().toString() + "@mail.ru", password.getText().toString())
                        .addOnSuccessListener(new com.google.android.gms.tasks.OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setName(email.getText().toString() + "@mail.ru");
                                user.setPass(password.getText().toString());
                                user.setLevel(level);
                                user.setMiddleResult(middleResult);

                                database.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                //   Snackbar.make(finishLayout, R.string.add_registration, Snackbar.LENGTH_SHORT).show();
                                                backToast = Toast.makeText(getBaseContext(),getString(R.string.add_registration),Toast.LENGTH_SHORT);
                                                backToast.show();
                                                SharedPreferences.Editor editor = save.edit();
                                                editor.putInt("alreadyReg", 1);
                                                editor.commit();
                                                alreadyre = 1;
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    //    Snackbar.make(finishLayout,R.string.error_registration+e.getMessage(),Snackbar.LENGTH_LONG).show();
                                        backToast = Toast.makeText(getBaseContext(),getString(R.string.error_registration),Toast.LENGTH_SHORT);
                                        backToast.show();
                                    }
                                });
                            }
                        });
            }
        });

        dialod.show();
    }

    //системная кнопка Назад - начало
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(userlist.this,OptionHelp.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
    //системная кнопка Назад - конец
}
