package demo.provider;

import com.google.gson.Gson;
import demo.dto.AccessTokenDTO;
import demo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/7/13 11:53
 * Class description：
 */

@Component
public class GithubProvider {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private Gson gson = new Gson();

    //获取token
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, gson.toJson(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String backInfo = response.body().string();
            String token = backInfo.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }//

    //根据token获取用户信息
    public GithubUser getUser(String accessToken) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            GithubUser githubUser = gson.fromJson(response.body().string(), GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }//
}
