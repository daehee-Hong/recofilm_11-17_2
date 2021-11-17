package com.example.recofilm.openApi;

import com.example.recofilm.dto.MovieDetailDto;
import com.example.recofilm.dto.MovieDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiMovieDetail {
    private String key = "857cbf6d4b1cf43fd59a9a85db5d0ccb";
    private String language = "&language=ko-KR";

    public MovieDetailDto movieDetail(int movieid) {
        MovieDetailDto dto = new MovieDetailDto();
        String genre = "";

        try {
            String getLatestMovie = "https://api.themoviedb.org/3/movie/" + movieid + "?api_key=" + key + language;
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
            JSONArray genres = jObject.getJSONArray("genres");

            // 포스터 사이즈
            String posterURL = "http://image.tmdb.org/t/p/";
            String posterSize = "w300";

            for (int i = 0; i < genres.length(); i++) {
                // 장르 저장
                JSONObject obj1 = genres.getJSONObject(i);
                genre = obj1.getString("name");
            }

                int id = jObject.getInt("id");
                String title = jObject.getString("title");
                String overview = jObject.getString("overview");
                String poster_path = jObject.getString("poster_path");
                Double vote_average = jObject.getDouble("vote_average");
                Long vote_count = jObject.getLong("vote_count");
                String release_date = jObject.getString("release_date");
                Double popularity = jObject.getDouble("popularity");
                int runtime = jObject.getInt("runtime");

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
                dto.setGenre(genre);
                dto.setRuntime(runtime);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}
