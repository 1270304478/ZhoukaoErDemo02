package login.bwie.com.zhoukaoerdemo02;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import login.bwie.com.zhoukaoerdemo02.Adapter.RecyclerViewAdpter;
import login.bwie.com.zhoukaoerdemo02.Bean.JsonBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Handler handler=new Handler();
    private List<JsonBean.NewslistBean> list=new ArrayList<JsonBean.NewslistBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化控件
        recyclerView = (RecyclerView) findViewById(R.id.recycerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        Request request = new Request.Builder().url("https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String s = response.body().string();

                    Gson gson = new Gson();

                    JsonBean jsonBean = gson.fromJson(s, JsonBean.class);


                    list = jsonBean.getNewslist();


                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            RecyclerViewAdpter adpter = new RecyclerViewAdpter(MainActivity.this,list);

                            recyclerView.setAdapter(adpter);



                        }
                    });

                }
            }
        });
    }
}
