package com.gamzeuysal.runnable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Main";

    TextView textView;
    Button btnileri;
    Button btnGeri;
    Button btnSifirla;

    //Bir işlemi birden fazla kez belirli periyotlarda yapmamızı sağlar
    Runnable runnable;
    //Runnable 'ı yönettiğimiz arayüz
    Handler handler;

    int baslangicSayisi;
    long i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btnileri = findViewById(R.id.buttonIleri);
        btnGeri = findViewById(R.id.buttonGeri);
        btnSifirla = findViewById(R.id.buttonSıfırla);

        //buttonun text sürekli belirli aralıklarala degistireyim

        //Yani while ile belli aralıklarla button text i guncellenmiyor
        //Bunun için runnble gerekli


        //ileri say butonu tıklandıgında 10'dan itibaren arttıracaktır





        handler = new Handler();

        btnileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baslangicSayisi = 0;
                btnileri.setEnabled(false);
                btnGeri.setEnabled(false);


                //runnable bana çalıştırabileceğim bir iş parçası ver demektir.
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG,"handler runnable -->"+System.currentTimeMillis());
                        //textView guncelle
                        textView.setText("Süre  : "+baslangicSayisi);
                        baslangicSayisi ++;

                        Log.d(TAG,"handler postDelayed 1  -->"+System.currentTimeMillis());
                        //1 saniyede(1000) bir bu işlemin gerçekleşmesini burada sağladık
                        handler.postDelayed(runnable,1000);
                    }
                };
              //Her çalışan runnable handler sayesinde post edecegiz
                Log.d(TAG,"handler postDelayed 2  -->"+System.currentTimeMillis());
                 handler.post(runnable);

            }
        });

        btnGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baslangicSayisi = 50;

                btnileri.setEnabled(false);
                btnGeri.setEnabled(false);


                runnable = new Runnable() {
                    @Override
                    public void run() {
                        //textView guncelle
                        textView.setText("Süre  "+baslangicSayisi);
                        baslangicSayisi--;

                        //1 saniyede(1000) bir bu işlemin gerçekleşmesini burada sağladık

                        handler.postDelayed(runnable,1000);
                    }
                };
                Log.d(TAG,"handler postDelayed  -->"+System.currentTimeMillis());
                handler.post(runnable);

                Log.d(TAG,"handler runnable -->"+System.currentTimeMillis());
               runnable.run(); //runnable hemen çalıştır anlamındadır.
            }
        });

        btnSifirla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSifirla.setEnabled(false);
                btnileri.setEnabled(false);
                btnGeri.setEnabled(false);

                handler.removeCallbacks(runnable);
                textView.setText("0");
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
/*

        new Thread(new Runnable() {
            @Override
            public void run() {
                //Thread arka plan kodları
                while(i<10)
                {
                    Log.d(TAG,"Thread  1 sn uyutulacak");

                    try {
                        Thread.sleep(   1000);
                    }catch (Exception e)
                    {
                        Log.d(TAG,"Thread sleep exception is : "+e.getMessage());
                    }

                    Log.d(TAG,"Thread  1 sn uyutuldu");

                    // btnileri.setText("i:"+i);
                    i++;
                    //thread ui veri gönderme
                    //1--> runOnUiThread
                    /*
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("Süre : "+i);
                        }
                    });
                     */
                    // Create a handler that associated with Looper of the main thread
                    //thread ui veri gönderme
                    //2.1--> handler.post
                    /*
                    handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //code will be executed on the main thread
                            textView.setText("Süre : "+i);
                        }
                    });

                     */
                    //thread ui veri gönderme
                    //2.2--> handler.post farklı yazım sekli
/*
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("Süre : "+i);
                        }
                    });

                }
            }
        }).start();

*/

        //Thread içindeyken ui mesaj gönderip onu ui elemanında göstermek istiyorsak  bu messajı ilk once ui message queue koymamaız gerekir.

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(i<50)
                {
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    i++;
                }
                Handler mainHandler = new Handler(Looper.getMainLooper());
                //send a task to the message queue


            }
        }).start();
*/

        }
    }

