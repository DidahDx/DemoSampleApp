package com.example.user.firstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int number=0; boolean topping=false; boolean ice=false;
 int icePrice=1; int pineApple=2;
     String message="";
    public static final String TAG="Main Activity";


   /**Context context;
    private ViewPager viewPager;


    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdView adView=findViewById(R.id.adView);
       // AdView adView1=findViewById(R.id.adView2);

        MobileAds.initialize(this,getString(R.string.app_id_test));
        AdRequest adRequest=new AdRequest.Builder().addTestDevice(getString(R.string.tecno_y6_test_id)).build();
           // adView1.loadAd(adRequest);
            adView.loadAd(adRequest);

            TextView second=findViewById(R.id.secondBtn);
            second.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(MainActivity.this,number.class);
                    startActivity(i);
                }
            });

        TextView bottom=findViewById(R.id.bottom_bar);
        bottom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,BotomBar.class);
                startActivity(i);
            }
        });

    }


    public void setIce(View view) {
        CheckBox box=findViewById(R.id.ice);

        if (box.isChecked()){
            ice=true;
        }else{
            ice=false;
        }

    }

    public void setTopping(View view){
        CheckBox box=findViewById(R.id.topping);
        if (box.isChecked()){
            topping=true;
        }else {
            topping=false;
        }
    }

    public void increment(View view){
        number++;
        TextView num=  findViewById(R.id.dx);
        num.setText(number+" ");
        price(number);
    }

    public int price(int number){
        int Cprice=5;
        if (topping){
           Cprice=Cprice+pineApple;
        }
        if (ice){
           Cprice=Cprice+icePrice;
        }

        return number * Cprice;
    }

    public void decrement(View view) {
        number--;
        TextView num=  findViewById(R.id.dx);
        if(number>=0){
        num.setText(number+" ");
        price(number);
        }else{
            number=0;
        }
    }


    public void submitOrder(View view) {

        AdView adView=findViewById(R.id.adView);


        MobileAds.initialize(this,getString(R.string.app_id_test));
        AdRequest adRequest=new AdRequest.Builder().addTestDevice(getString(R.string.tecno_y6_test_id)).build();

        adView.loadAd(adRequest);

        TextView price=findViewById(R.id.lef);

        price.setText("$" + this.price(number) );
        TextView order=findViewById(R.id.order);
        SearchView text=findViewById(R.id.name_field);
            text.setQueryHint("search");


        CharSequence name=text.getQuery();

        if (topping || ice){
          message=getResources().getString(R.string.name, name)+"\n"+ getString(R.string.coffee_order,number) +
                  "\n"+ getString(R.string.total_price) +
                  getString(R.string.price,NumberFormat.getCurrencyInstance().format(price(number)))
                  +"\n"+ getString(R.string.contain_topping)+"\n"+getString(R.string.thanks);
        }else{
            message=getString(R.string.name, name)+"\n"+ getString(R.string.coffee_order,number) +
                    "\n"+ getString(R.string.total_price)
                   +"\t"+ getString(R.string.price,NumberFormat.getCurrencyInstance().format(price(number)))+
                    "\n"+ getString(R.string.thanks);
        }
        order.setText(message);
       Toast toast=Toast.makeText(this,getString(R.string.thanks), Toast.LENGTH_SHORT);
       toast.show();

       String address = null,subject="Order";
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,address);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent );
        }

    }





}