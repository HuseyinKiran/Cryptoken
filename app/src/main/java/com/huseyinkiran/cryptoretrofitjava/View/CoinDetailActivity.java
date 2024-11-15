package com.huseyinkiran.cryptoretrofitjava.View;

import static com.huseyinkiran.cryptoretrofitjava.Common.ApiConstants.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.huseyinkiran.cryptoretrofitjava.Adapter.LinksAdapter;
import com.huseyinkiran.cryptoretrofitjava.Adapter.TagAdapter;
import com.huseyinkiran.cryptoretrofitjava.Adapter.TeamMemberAdapter;
import com.huseyinkiran.cryptoretrofitjava.Common.Resource;
import com.huseyinkiran.cryptoretrofitjava.Model.CoinDetailModel;
import com.huseyinkiran.cryptoretrofitjava.Model.Link;
import com.huseyinkiran.cryptoretrofitjava.Model.Links;
import com.huseyinkiran.cryptoretrofitjava.Model.Tag;
import com.huseyinkiran.cryptoretrofitjava.Model.TeamMember;
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

public class CoinDetailActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TeamMemberAdapter teamMemberAdapter;
    private RecyclerView recyclerViewTags, linksRv, membersRv;
    private TagAdapter tagAdapter;
    private CryptoAPI cryptoAPI;
    private ProgressBar progressBar;
    private TextView txtName, txtRank, txtIsActive, txtDesc, labelTags, labelLinks, labelMembers;
    private ImageView imgLogo;
    private LinksAdapter linksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);

        setupFindViewByID();

        backButton.setOnClickListener(v -> finish());

        FlexboxLayoutManager tagLayoutManager = new FlexboxLayoutManager(this);
        tagLayoutManager.setFlexDirection(FlexDirection.ROW);
        tagLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerViewTags.setLayoutManager(tagLayoutManager);

        FlexboxLayoutManager linkLayoutManager = new FlexboxLayoutManager(this);
        linkLayoutManager.setFlexDirection(FlexDirection.ROW);
        linkLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        linksRv.setLayoutManager(linkLayoutManager);

        List<TeamMember> teamMemberList = new ArrayList<>();
        teamMemberAdapter = new TeamMemberAdapter(teamMemberList);
        membersRv.setLayoutManager(new LinearLayoutManager(this));
        membersRv.setAdapter(teamMemberAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cryptoAPI = retrofit.create(CryptoAPI.class);

        String coinId = getIntent().getStringExtra("coinId");

        getCoinDetail(coinId);

    }

    private void setupFindViewByID() {
        backButton = findViewById(R.id.back);
        recyclerViewTags = findViewById(R.id.recyclerViewTags);
        membersRv = findViewById(R.id.teamMemberRv);
        txtName = findViewById(R.id.txtName);
        txtRank = findViewById(R.id.txtRank);
        txtIsActive = findViewById(R.id.txtIsActive);
        txtDesc = findViewById(R.id.txtDesc);
        progressBar = findViewById(R.id.progressBar);
        imgLogo = findViewById(R.id.imgCrypto);
        linksRv = findViewById(R.id.linksRv);
        labelLinks = findViewById(R.id.labelLinks);
        labelTags = findViewById(R.id.labelTags);
        labelMembers = findViewById(R.id.labelMembers);
    }

    private void getCoinDetail(String coinId) {

        Call<CoinDetailModel> call = cryptoAPI.getDetailData(coinId);

        emitResource(new Resource.Loading<>(null));

        call.enqueue(new Callback<CoinDetailModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<CoinDetailModel> call, @NonNull Response<CoinDetailModel> response) {
                if (response.isSuccessful() && response.body() != null) {

                    CoinDetailModel coinDetail = response.body();
                    emitResource(new Resource.Success<>(coinDetail));

                    txtName.setText(coinDetail.getName().trim() + " (" + coinDetail.getSymbol().trim() + ")");
                    txtRank.setText(coinDetail.getRank() + ".");
                    txtDesc.setText(coinDetail.getDescription());

                    String logoUrl = coinDetail.getLogo();
                    Glide.with(CoinDetailActivity.this)
                            .load(logoUrl)
                            .into(imgLogo);

                    if (coinDetail.isActive()) {
                        txtIsActive.setText("Active");
                        txtIsActive.setTextColor(Color.GREEN);
                    } else {
                        txtIsActive.setText("Inactive");
                        txtIsActive.setTextColor(Color.RED);
                    }

                    Links links = coinDetail.getLinks();

                    if (links != null) {

                        List<Link> linkList = new ArrayList<>();

                        List<String> explorerLinks = links.getExplorer();
                        List<String> youtubeLinks = links.getYoutube();
                        List<String> facebookLinks = links.getFacebook();
                        List<String> redditLinks = links.getReddit();
                        List<String> websiteLinks = links.getWebsite();
                        List<String> sourceCodeLinks = links.getSourceCode();

                        if (explorerLinks != null && !explorerLinks.isEmpty()) {
                            for (String url : links.getExplorer()) {
                                linkList.add(new Link("Explorer", url));
                            }
                        }

                        if (links.getFacebook() != null && !facebookLinks.isEmpty()) {
                            for (String url : links.getFacebook()) {
                                linkList.add(new Link("Facebook", url));
                            }
                        }

                        if (links.getReddit() != null && !redditLinks.isEmpty()) {
                            for (String url : links.getReddit()) {
                                linkList.add(new Link("Reddit", url));
                            }
                        }

                        if (links.getSourceCode() != null && !sourceCodeLinks.isEmpty()) {
                            for (String url : links.getSourceCode()) {
                                linkList.add(new Link("GitHub", url));
                            }
                        }

                        if (links.getWebsite() != null && !websiteLinks.isEmpty()) {
                            for (String url : links.getWebsite()) {
                                linkList.add(new Link("Website", url));
                            }
                        }

                        if (links.getYoutube() != null && !youtubeLinks.isEmpty()) {
                            for (String url : links.getYoutube()) {
                                linkList.add(new Link("YouTube", url));
                            }
                        }

                        linksAdapter = new LinksAdapter(linkList, CoinDetailActivity.this);
                        linksRv.setAdapter(linksAdapter);

                    } else {
                        labelLinks.setVisibility(View.GONE);
                    }

                    List<Tag> tags = coinDetail.getTags();

                    if (tags != null && !tags.isEmpty()) {
                        tagAdapter = new TagAdapter(tags);
                        recyclerViewTags.setAdapter(tagAdapter);
                    } else {
                        labelTags.setVisibility(View.GONE);
                    }

                    List<TeamMember> teamMembers = coinDetail.getTeam();

                    if (teamMembers != null && !teamMembers.isEmpty()) {
                        teamMemberAdapter = new TeamMemberAdapter(teamMembers);
                        membersRv.setAdapter(teamMemberAdapter);
                    } else {
                        labelMembers.setVisibility(View.GONE);
                    }

                } else {
                    emitResource(new Resource.Error<>("An unexpected error occurred", null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<CoinDetailModel> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    emitResource(new Resource.Error<>("Please check your internet connection", null));
                } else {
                    emitResource(new Resource.Error<>("An unexpected error occurred", null));
                }
            }
        });
    }


    private void emitResource(Resource<CoinDetailModel> resource) {
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

