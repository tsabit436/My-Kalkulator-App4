package com.example.mykalkulatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView tvHasil;

    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvHasil = findViewById(R.id.tvHasil);

        btnShare = findViewById(R.id.btnShare);

        Intent intent = getIntent();

        double hasil =
                intent.getDoubleExtra("hasil", 0.0);

        String operasi =
                intent.getStringExtra("operasi");

        String kategori =
                intent.getStringExtra("kategori");

        if (operasi == null) {
            operasi = "Operasi";
        }

        if (kategori == null) {
            kategori = "Umum";
        }

        String hasilText;

        if (hasil == (int) hasil) {
            hasilText = String.valueOf((int) hasil);
        } else {
            hasilText = String.valueOf(hasil);
        }

        String text =
                "Kategori : " + kategori +
                        "\nOperasi : " + operasi +
                        "\nHasil : " + hasilText;

        tvHasil.setText(text);

        btnShare.setOnClickListener(v -> {

            Intent share =
                    new Intent(Intent.ACTION_SEND);

            share.setType("text/plain");

            share.putExtra(
                    Intent.EXTRA_TEXT,
                    text
            );

            startActivity(
                    Intent.createChooser(
                            share,
                            "Bagikan Hasil"
                    )
            );
        });
    }
}