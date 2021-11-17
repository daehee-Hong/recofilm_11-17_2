package com.example.recofilm.openApi;

import com.example.recofilm.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApiNowPlaying {
    private String key = "857cbf6d4b1cf43fd59a9a85db5d0ccb";
    private String language = "&language=ko-KR";
    private String page = "&page=1";
    private String region = "&region=KR";

    public List<MovieDto> movieNowPlaying() {
        List<MovieDto> dtoList = new ArrayList<MovieDto>();

        try {
            String getLatestMovie = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + key + language + page + region;
            URL obj = new URL(getLatestMovie);

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
            String posterSize = "w300";

            for (int i = 0; i < jArray.length(); i++){
                // json data & dto
                JSONObject obj1 = jArray.getJSONObject(i);
                MovieDto dto = new MovieDto();

                // json data 저장
                int id = obj1.getInt("id");
                String title = obj1.getString("title");
                String overview = obj1.getString("overview");
                String poster_path = obj1.getString("poster_path");

                Double vote_average = obj1.getDouble("vote_average");
                Long vote_count = obj1.getLong("vote_count");
                String release_date = obj1.getString("release_date");
                Double popularity = obj1.getDouble("popularity");

                // 포스터 경로설정
                String poster = posterURL + posterSize + poster_path;

                // dto에 저장
                dto.setId(id);
                dto.setTitle(title);
                dto.setOverview(overview);
                dto.setPoster_path(poster);
                dto.setVote_average(vote_average);
                dto.setVote_count(vote_count);
                dto.setRelease_date(release_date);
                dto.setPopularity(popularity);

                // dtoList에 dto 저장
                dtoList.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dtoList;
    }
}
