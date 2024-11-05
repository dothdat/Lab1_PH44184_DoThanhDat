package com.datddtph44184.lab1_ph44184_dothanhdat;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThemSP extends AppCompatActivity {
    Button btnSub,btnCan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_sp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.themsp), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCan = findViewById(R.id.buttonCancel);
        btnSub = findViewById(R.id.buttonSubmit);
    }
}