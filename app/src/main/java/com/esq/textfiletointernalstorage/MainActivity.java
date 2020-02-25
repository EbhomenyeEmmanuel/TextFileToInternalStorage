package com.esq.textfiletointernalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";
    EditText mEdiText;
    File file;
    Scanner input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdiText = findViewById(R.id.edit_text);
        file = new File(FILE_NAME);
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save (View v){
            String text = mEdiText.getText().toString();
        FileOutputStream fileOutputStream = null;;
        try{
                fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
               fileOutputStream.write(text.getBytes());
               mEdiText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null){
                try{
                    fileOutputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
    public void load(View v){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String text;

            while((text = bufferedReader.readLine()) != null){
                stringBuilder.append(text);
            }
            mEdiText.setText(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream != null){
                try{
                    fileInputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
