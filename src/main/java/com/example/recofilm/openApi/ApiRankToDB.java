package com.example.recofilm.openApi;

import com.example.recofilm.dto.MovieDto;
import com.example.recofilm.dto.MovieToEntityDto;
import com.example.recofilm.entity.Movie;
import com.example.recofilm.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApiRankToDB {
    private String key = "857cbf6d4b1cf43fd59a9a85db5d0ccb";
    private String language = "&language=ko-KR";
    private String page = "&page="; //나중에 page값 직접받아서 페이지 조절해야됌
    private String region = "&region=KR";
    @Autowired
    private MovieRepository repository;

    public void movieRankToDB(int pageValue) {

            try {
                String getPopularMovie = "https://api.themoviedb.org/3/movie/popular?api_key=" + key + language + page + pageValue + region;
                URL obj = new URL(getPopularMovie);

                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                String b = response.toString();
                JSONObject jObject = new JSONObject(b);
                JSONArray jArray = jObject.getJSONArray("results");

                // 포스터 사이즈
                String posterURL = "http://image.tmdb.org/t/p/";
                String posterSize = "w200";

                for (int i = 0; i < jArray.length(); i++) {
                    // json data & dto
                    JSONObject obj1 = jArray.getJSONObject(i);
                    MovieToEntityDto dto = new MovieToEntityDto();

                    // json data 저장
                    int movieID = obj1.getInt("id");
                    String title = obj1.getString("title");
                    String poster_path = obj1.getString("poster_path");

                    // 포스터 경로설정
                    String poster = posterURL + posterSize + poster_path;

                    // dto에 저장
                    dto.setMovieID(movieID);
                    dto.setTitle(title);
                    dto.setPoster_path(poster);

                    // entity변환
                    Movie movie = dto.toEntity();

                    //db에 저장
                    repository.save(movie);
                    System.out.println("db에 저장완료");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}