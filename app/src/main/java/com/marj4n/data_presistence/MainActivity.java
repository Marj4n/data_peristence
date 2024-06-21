package com.marj4n.data_presistence;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button writeBtn, readBtn, updateBtn, deleteBtn;
    TextView textFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        writeBtn = findViewById(R.id.write_btn);
        readBtn = findViewById(R.id.read_btn);
        updateBtn = findViewById(R.id.update_btn);
        deleteBtn = findViewById(R.id.delete_btn);
        textFile = findViewById(R.id.text_file);

        writeBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.write_btn) {
            writeFile();
        } else if (id == R.id.read_btn) {
            readFile();
        } else if (id == R.id.update_btn) {
            updateFile();
        } else if (id == R.id.delete_btn) {
            deleteFile();
        }
    }

    @SuppressLint("SetTextI18n")
    public void writeFile() {
        String fileContent = "Hello World! ";
        File file = new File(getFilesDir(), "my_file.txt");

        try (FileOutputStream outputStream = new FileOutputStream(file, true)) {
            outputStream.write(fileContent.getBytes());
            textFile.setText("File berhasil dibuat");
        } catch (Exception e) {
            e.printStackTrace();
            textFile.setText("Gagal membuat file");
        }
    }

    @SuppressLint("SetTextI18n")
    public void readFile() {
        File file = new File(getFilesDir(), "my_file.txt");

        if (file.exists()) {
            StringBuilder text = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line).append('\n');
                }
                textFile.setText(text.toString());
            } catch (Exception e) {
                e.printStackTrace();
                textFile.setText("Gagal membaca file");
            }
        } else {
            textFile.setText("File tidak ditemukan");
        }
    }

    @SuppressLint("SetTextI18n")
    public void updateFile() {
        String newContent = "Hello World! Updated! ";
        File file = new File(getFilesDir(), "my_file.txt");

        if (file.exists()) {
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(newContent.getBytes());
                textFile.setText("File berhasil diubah");
            } catch (Exception e) {
                e.printStackTrace();
                textFile.setText("Gagal mengubah file");
            }
        } else {
            textFile.setText("File tidak ditemukan");
        }
    }

    @SuppressLint("SetTextI18n")
    public void deleteFile() {
        File file = new File(getFilesDir(), "my_file.txt");

        if (file.exists()) {
            if (file.delete()) {
                textFile.setText("File berhasil dihapus");
            } else {
                textFile.setText("Gagal menghapus file");
            }
        } else {
            textFile.setText("File tidak ditemukan");
        }
    }
}