package com.example.assignmentdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignmentdemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        List<String> list = new ArrayList<String>();
//        list.add("Toy Story");
//        list.add("Up");
//        list.add("Shrek");
//
//        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
//        binding.movieSpinner.setAdapter(spinnerAdapter);
//
//        binding.addButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                String newMovie=binding.editText.getText().toString(); //捕获用户的输入
//                spinnerAdapter.add(newMovie);       //加入到arraylist中
//                spinnerAdapter.notifyDataSetChanged(); //内容改变了就刷新，不需要通过重启app来刷新数据
//                binding.movieSpinner.setSelection(spinnerAdapter.getPosition(newMovie));
//                                    //强制更改选项内容，setSelection只能接受int值
//            }
//        });
//
//        binding.clearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.editText.setText("");
//            }
//        });

        //https://api.openweathermap.org/data/2.5/weather?lat=31.230391&lon=121.473701&appid=3bdb45244c9b2d4e6d4fbb5a5f176963
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApiInterface weatherApiInterface = retrofit.create(WeatherApiInterface.class);

        Call<Root> call = weatherApiInterface.getWeather();

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();
                double temp = root.getMain().getTemp()-273.15;
                binding.tempTextView.setText(String.valueOf((int)temp));
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                System.out.println(t.getMessage());
            }

        });
    }
}
