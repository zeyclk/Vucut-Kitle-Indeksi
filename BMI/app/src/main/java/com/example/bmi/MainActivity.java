package com.example.bmi;

import android.icu.number.NumberFormatter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    Button btn_hesapla;
    TextView txt_cins,txt_bilgi,txt_sonuc;
    EditText edt_kilo,edt_boy;
    RadioButton rd_kadin,rd_erkek;
    RadioGroup rg_cins;
    Float boy; Float sonuc;
    //Integer sonuc=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_hesapla=findViewById(R.id.button_hsp);
        txt_cins=findViewById(R.id.textView4);
        edt_kilo=findViewById(R.id.edit_kilo);
        edt_boy=findViewById(R.id.edit_boy);
        rd_kadin=findViewById(R.id.radio_kadin);
        rd_erkek=findViewById(R.id.radio_erkek);
        rg_cins=findViewById(R.id.rg1);
        txt_bilgi=findViewById(R.id.textView5);//cinsiyete göre değiştirilecek yazı
        txt_bilgi.setText("");
        txt_sonuc=findViewById(R.id.textView3);

        NumberFormat formatter= NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(1);


        btn_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edt_boy.getText().toString().isEmpty() && edt_kilo.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Boy ve kilo bilgilerinizi giriniz.",Toast.LENGTH_SHORT).show();

                else
                {
                    boy = Float.parseFloat(edt_boy.getText().toString()) / 100; //cm cinsinden girilen boyu ondalıklı sayı yapar
                    sonuc = (Float.parseFloat(edt_kilo.getText().toString()) / (boy * boy));
                    Toast.makeText(getApplicationContext(), "Vücut Kitle İndeksiniz:" + " " + formatter.format(sonuc), Toast.LENGTH_LONG).show();

                    String s=formatter.format(sonuc);
                    if(Float.parseFloat(s)<=18.5)
                        txt_sonuc.setText("VKI Kategoriniz: Zayıf");
                    else if(Float.parseFloat(s)>18.5 && Float.parseFloat(s)<=24.9)
                        txt_sonuc.setText("VKI Kategoriniz: Normal Kilolu");
                    else if(Float.parseFloat(s)>25 && Float.parseFloat(s)<=29.9)
                        txt_sonuc.setText("VKI Kategoriniz: Fazla Kilolu");
                    else if(Float.parseFloat(s)>30 && Float.parseFloat(s)<=34.9)
                        txt_sonuc.setText("VKI Kategoriniz: 1. Derece Obezite");
                    else if(Float.parseFloat(s)>35 && Float.parseFloat(s)<=40)
                        txt_sonuc.setText("VKI Kategoriniz: 2. Derece Obezite");
                    else if(Float.parseFloat(s)>40)
                        txt_sonuc.setText("VKI Kategoriniz: 3. Derece Obezite(Morbid)");
                    else if(Float.parseFloat(s)>50)
                        txt_sonuc.setText("VKI Kategoriniz: Süper Obezite");
                    else if(Float.parseFloat(s)>60)
                        txt_sonuc.setText("VKI Kategoriniz: Süper Süper Obezite");

                    //cinsiyete göre bilgi verilmesi
                    if (rd_kadin.isChecked())
                        txt_bilgi.setText("Kadınlar için ideal vücut kitle indeksi 19-24 aralığıdır. 50+ kadın yetişkinler için ise bu aralık 21-26'dır.");

                    else if (rd_erkek.isChecked())
                        txt_bilgi.setText("Erkekler için ideal vücut kitle indeksi 20-25 aralığıdır. 50+ erkek yetişkinler için ise bu aralık 22-27'dir.");
                }
            }
        });
    }
}