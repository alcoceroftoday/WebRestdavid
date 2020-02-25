package io.vertx.webrest.service;

import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.webrest.model.DbConnection;
import io.vertx.webrest.model.Artist;
import io.vertx.ext.sql.ResultSet;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArtistService {
    private final DbConnection dbConnection;


    public ArtistService(DbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

//    public Single<ResultSet> getArtist() {
    public Single<List<Artist>> getArtist() {
        return dbConnection
//        return dbConnection
                .db
                .rxQuery("select * from artist")
                .map(ResultSet::getRows)
                .map(
                        rows ->
                                rows.stream()
                                        .map(
                                                row -> {
                                                    Artist artist = new Artist(
//                                                            2,"david","asdasdad","asdsad","asdad"
                                                            row.getInteger("id"),
                                                            row.getString("name"),
                                                            row.getString("artwork"),
                                                            row.getString("created_at"),
                                                            row.getString("updated_ap")
                                                    );
                                                    return artist;
                                                })
                                        .collect(Collectors.toList()));
    }
}
