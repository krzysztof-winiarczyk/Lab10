package pollub.ism.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pollub.ism.lab10.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding okno = null;

    private ZadanieAsynchroniczne2 zadanie2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        okno = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(okno.getRoot());

        //wstępna konfiguracja paska postępu
        okno.pasekPostepu.setProgress(0);
        okno.pasekPostepu.setMax(100);
        //wstępna konfiguracja przycisków 3 i 4
        okno.przycisk3.setEnabled(true);
        okno.przycisk4.setEnabled(false);

        okno.przycisk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                przycisk1(v);
            }
        });
        okno.przycisk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                przycisk2(v);
            }
        });
        okno.przycisk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                przycisk3(v);
            }
        });
        okno.przycisk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                przycisk4(v);
            }
        });
    }

    public void przycisk1(View view) {
        new ZadanieAsynchroniczne1(okno.poleTekstowe1).execute(); //utworzenie zadania
        okno.poleTekstowe1.setText(R.string.working); //ustawienie tekstu ze @strings/working
    }

    public void przycisk2(View view) {
        new ZadanieAsynchroniczne1(okno.poleTekstowe2).execute(); //utworzenie zadania
        okno.poleTekstowe2.setText(R.string.working); //ustawienie tekstu ze @strings/working
    }

    public void przycisk3(View view) {
        okno.przycisk3.setEnabled(false);
        try{

            int powtorzenia = Integer.valueOf(okno.poleEdycyjne1.getText().toString());
            int pauza = Integer.valueOf(okno.poleEdycyjne2.getText().toString());
            System.out.println("powt: " + powtorzenia);
            zadanie2 = new ZadanieAsynchroniczne2(okno.pasekPostepu, okno.przycisk3, okno.przycisk4);
            zadanie2.execute(powtorzenia, pauza);
            okno.przycisk4.setEnabled(true);
        }catch (NumberFormatException e){
            okno.przycisk3.setEnabled(true);
            Toast.makeText(this, "Wprowadź wartości w pola", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            okno.przycisk3.setEnabled(true);
            Toast.makeText(this, "Nastąpił błąd", Toast.LENGTH_LONG).show();
        }
    }

    public void przycisk4(View view) {
        zadanie2.cancel(true);
    }


}