package com.example.mykalkulatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;

    RadioGroup rg;

    Spinner spinner;

    Button btnHitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.etAngka1);
        et2 = findViewById(R.id.etAngka2);

        rg = findViewById(R.id.radioGroup);

        spinner = findViewById(R.id.spKategori);

        btnHitung = findViewById(R.id.btnHitung);

        String[] kategori = {
                "Pilih Kategori",
                "Matematika",
                "Sains"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                kategori
        );

        spinner.setAdapter(adapter);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new InfoFragment())
                .commit();

        btnHitung.setOnClickListener(v -> {

            String a1 = et1.getText().toString().trim();
            String a2 = et2.getText().toString().trim();

            if (a1.isEmpty() || a2.isEmpty()) {

                Toast.makeText(this,
                        "Input tidak boleh kosong",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            double angka1, angka2;

            try {

                angka1 = Double.parseDouble(a1);
                angka2 = Double.parseDouble(a2);

            } catch (NumberFormatException e) {

                Toast.makeText(this,
                        "Input harus angka",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            if (spinner.getSelectedItemPosition() == 0) {

                Toast.makeText(this,
                        "Pilih kategori",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            int selectedId = rg.getCheckedRadioButtonId();

            if (selectedId == -1) {

                Toast.makeText(this,
                        "Pilih operasi",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            double hasil = 0;

            String operasi = "";

            if (selectedId == R.id.rbTambah) {

                hasil = angka1 + angka2;
                operasi = "Penjumlahan";

            } else if (selectedId == R.id.rbKurang) {

                hasil = angka1 - angka2;
                operasi = "Pengurangan";

            } else if (selectedId == R.id.rbKali) {

                hasil = angka1 * angka2;
                operasi = "Perkalian";

            } else if (selectedId == R.id.rbBagi) {

                if (angka2 == 0) {

                    Toast.makeText(this,
                            "Tidak bisa dibagi 0",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                hasil = angka1 / angka2;
                operasi = "Pembagian";
            }

            String kategoriDipilih =
                    spinner.getSelectedItem().toString();

            Intent intent =
                    new Intent(MainActivity.this,
                            ResultActivity.class);

            intent.putExtra("hasil", hasil);
            intent.putExtra("operasi", operasi);
            intent.putExtra("kategori", kategoriDipilih);

            startActivity(intent);
        });
    }
}