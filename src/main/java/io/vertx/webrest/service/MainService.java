package io.vertx.webrest.service;

import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.webrest.model.Artist;
import io.vertx.webrest.model.DbConnection;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainService {

    private final DbConnection dbConnection;

    @Inject
    public MainService(DbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public Single<List<Artist>> getArtist(){
       return   this.dbConnection.db.rxQuery(" SELECT * FROM artists")
               .map(ResultSet::getRows)
               .map(
                       rows ->
                               rows.stream()
                                       .map(
                                               row -> {
                                                   Artist d = new Artist(
                                                           row.getInteger("id"),
                                                           row.getString("name"),
                                                           row.getString("artwork"),
                                                           row.getString("created_at"),
                                                           row.getString("updated_ap")
                                                   );
                                                   return d;
                                               })
                                       .collect(Collectors.toList()));


    }
}
