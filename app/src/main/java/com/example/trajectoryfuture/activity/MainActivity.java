package com.example.trajectoryfuture.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.trajectoryfuture.R;
import com.example.trajectoryfuture.models.AdapterItem;
import com.example.trajectoryfuture.models.ItemModel;
import com.example.trajectoryfuture.models.ServiceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterItem.MyOnItemClickListener {

    public static final String EXTRA_URL_ICON = "image_url";
    public static final String EXTRA_NAME = "name_service";
    public static final String EXTRA_DESCRIPTION = "des_service";
    public static final String EXTRA_URL_LINK = "link_url";
    private RecyclerView recyclerView;
    private AdapterItem adapterItem;
    private ArrayList<ItemModel> itemModels;
    private ArrayList<ServiceModel> serviceModels;
    private RequestQueue requestQueue;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerJson);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemModels = new ArrayList<>();
        serviceModels = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        parseJson();
    }

    private void parseJson(){
        String url = "https://mobile-olympiad-trajectory.hb.bizmrg.com/semi-final-data.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject item = jsonArray.getJSONObject(i);
                        String name = item.getString("name");
                        String description = item.getString("description");
                        String iconUrl = item.getString("icon_url");
                        String serviceUrl = item.getString("service_url");
                        itemModels.add(new ItemModel(iconUrl, name));
                        serviceModels.add(new ServiceModel(iconUrl, name, description, serviceUrl));
                    }

                    adapterItem = new AdapterItem(MainActivity.this, itemModels);
                    recyclerView.setAdapter(adapterItem);
                    adapterItem.setItemClickListener(MainActivity.this);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Не удалось найти нужные данные",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Не удалось получить данные с сайта",
                        Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ServiceActivity.class);
        ServiceModel serviceModel = serviceModels.get(position);
        intent.putExtra(EXTRA_NAME, serviceModel.getTitleService());
        intent.putExtra(EXTRA_URL_ICON, serviceModel.getIconService());
        intent.putExtra(EXTRA_DESCRIPTION, serviceModel.getDescriptionService());
        intent.putExtra(EXTRA_URL_LINK, serviceModel.getLinkService());
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}