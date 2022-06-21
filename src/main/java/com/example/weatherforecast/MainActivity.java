package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weatherforecast.retrofit.ApiClient;
import com.example.weatherforecast.retrofit.ApiService;
import com.example.weatherforecast.retrofit.CallbackResponse;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.time.Instant.ofEpochSecond;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefresh;
    ScrollView scrollView;
    AutoCompleteTextView etCityName;
    TextView tvTemp, tvFeelsLike,tvCountry;
    ImageView backImage;
    DecimalFormat df = new DecimalFormat("#.##");

    String units = "metric";
    String appId = "dee129f55dd45b231f3b87d51133741b";

    Call<CallbackResponse> responseCall;
   // ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCityName = findViewById(R.id.et_city_name);
        tvTemp= findViewById(R.id.tv_temp);
        tvFeelsLike = findViewById(R.id.tv_feels);
        //tvCountry = findViewById(R.id.tv_country);
        backImage = findViewById(R.id.back_image);

        Glide.with(MainActivity.this)
                .asGif()
                .load(R.drawable.waterfall1)
                .into(backImage);
        String[] CITIES = getResources().getStringArray(R.array.CITIES);

        String cityName = "surat";
        sendData(cityName);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,CITIES);
        etCityName.setAdapter(adapter);

        etCityName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ip = (String) parent.getItemAtPosition(position);
                sendData(ip);
            }
        });


       /* etCityName.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event != null &&
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {
                        // the user is done typing.

                        return true; // consume.
                    }
                }
                return false; // pass on to other listeners.
            }
        });*/
    }

    private void sendData(String cityName) {

        responseCall = ApiClient.getApiClient().create(ApiService.class).sendData(cityName,units,appId);
       // api = retrofit.create(ApiService.class);

       // responseCall = api.sendData(cityName,units,appId);

        responseCall.enqueue(new Callback<CallbackResponse>() {
            @Override
            public void onResponse(Call<CallbackResponse> call, Response<CallbackResponse> response) {

                if (response.isSuccessful()){

                    CallbackResponse res = response.body();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.format(new Date(res.getDt()));

                    long epoch = res.getSys().getSunrise();
                    String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date (epoch*1000));

                    tvTemp.setText(String.valueOf(res.getMain().getTemp()));
                    tvFeelsLike.setText("Feels like "+String.valueOf(res.getMain().getFeels_like()));
                   // tvCountry.setText(res.getSys().getCountry());

                    //Epoch in seconds, remove '*1000' for milliseconds.

//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                    sdf.format(new Date(myTimeAsLong));
                    Toast.makeText(MainActivity.this,res.getName(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CallbackResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}