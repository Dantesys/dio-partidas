package com.dantesys.dio_aula.data;

import com.dantesys.dio_aula.domain.Match;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchesAPI {
    @GET("partidas.json")
    Call<List<Match>> getMatches();
}
