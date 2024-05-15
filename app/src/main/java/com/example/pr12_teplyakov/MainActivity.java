package com.example.pr12_teplyakov;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void AlertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title).setMessage(message).setCancelable(false).setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    EditText fio;
    EditText phone;
    EditText address;

    public void OnArrange(View view){
        fio = findViewById(R.id.fio);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);

        if(fio.getText().length() == 0) AlertDialog("Уведомление", "Пожалуйста, укажите Фамилию, Имя и Отчество!");
        else if(phone.getText().length() == 0) AlertDialog("Уведомление", "Пожалуйста, укажите номер телефона!");
        else if(address.getText().length() == 0) AlertDialog("Уведомление", "Пожалуйста, укажите адрес доставки!");
        else
        {
            AlertDialogWithChoice("Подтверждение", "Вы уверены, что готовы сделать заказ?");
        }
    }

    public void AlertDialogWithChoice(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title).setMessage(message).setCancelable(false);

        final String[] items = new String[12];
        for (int i = 0; i < 12; i++) {
            items[i] = String.valueOf(i + 1);
        }
        builder.setSingleChoiceItems(items, -1, null);

        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String[] items = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Выбор столика")
                        .setSingleChoiceItems(items, 0, null)
                        .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog("Уведомление", "Ваша бронь была успешно добавлена, ожидаем вас в любое удобное время!");
                            }
                        })
                        .setNegativeButton("Столик не нужен", null)
                        .show();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}