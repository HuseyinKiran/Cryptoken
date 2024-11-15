package com.huseyinkiran.cryptoretrofitjava.View;

import static com.huseyinkiran.cryptoretrofitjava.Common.ApiConstants.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huseyinkiran.cryptoretrofitjava.Adapter.CryptoAdapter;
import com.huseyinkiran.cryptoretrofitjava.Common.Resource;
import com.huseyinkiran.cryptoretrofitjava.Model.CryptoModel;
import com.huseyinkiran.cryptoretrofitjava.R;
import com.huseyinkiran.cryptoretrofitjava.Service.CryptoAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<CryptoModel> cryptoModelList;
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private CryptoAdapter cryptoAdapter;
    private FloatingActionButton btnScrollToTop, btnScrollToBottom;
    private ProgressBar progressBar;
    private TextView title;
    private ImageButton backButton;
    private SearchView searchView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFindViewByID();
        searchView.clearFocus();

        backButton.setVisibility(View.GONE);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        title.setLayoutParams(layoutParams);
        title.setText("CRYPTOKEN");

        clickedScrollButton();

        searchCrypto();

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        getDataFromAPI();

    }

    private void setupFindViewByID() {
        btnScrollToTop = findViewById(R.id.btnScrollToTop);
        btnScrollToBottom = findViewById(R.id.btnScrollToBottom);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        title = findViewById(R.id.title);
        backButton = findViewById(R.id.back);
        searchView = findViewById(R.id.searchView);
    }

    private void clickedScrollButton() {
        btnScrollToBottom.setOnClickListener(v -> {
            if (cryptoAdapter != null && cryptoAdapter.getItemCount() > 0) {
                recyclerView.scrollToPosition(cryptoAdapter.getItemCount() - 1);
            }
        });

        btnScrollToTop.setOnClickListener(v -> recyclerView.scrollToPosition(0));
    }

    private void searchCrypto() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String newText) {
        List<CryptoModel> filteredList = new ArrayList<>();
        for (CryptoModel cryptoModel : cryptoModelList) {
            if (cryptoModel.getName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(cryptoModel);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            cryptoAdapter.setFilteredList(filteredList);
        }
    }

    private void getDataFromAPI() {

        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);
        Call<List<CryptoModel>> call = cryptoAPI.getData();

        emitResource(new Resource.Loading<>(null));

        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CryptoModel>> call, @NonNull Response<List<CryptoModel>> response) {

                if (response.isSuccessful()) {

                    List<CryptoModel> responseList = response.body();
                    emitResource(new Resource.Success<>(responseList));

                    cryptoModelList = responseList;
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    cryptoAdapter = new CryptoAdapter(cryptoModelList);
                    recyclerView.setAdapter(cryptoAdapter);

                } else {
                    emitResource(new Resource.Error<>("An unexpected error occurred", null));
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<CryptoModel>> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                if (t instanceof IOException) {
                    emitResource(new Resource.Error<>("Please check your internet connection", null));
                } else {
                    emitResource(new Resource.Error<>("An unexpected error occurred", null));
                }
            }
        });

    }

    private void emitResource(Resource<List<CryptoModel>> resource) {
        if (resource instanceof Resource.Loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else if (resource instanceof Resource.Success) {
            progressBar.setVisibility(View.GONE);
        } else if (resource instanceof Resource.Error) {
            progressBar.setVisibility(View.GONE);
            String errorMessage = resource.getMessage() != null ? resource.getMessage() : "An unknown error occurred";
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

}

