package io.vertx.webrest.service;

import io.reactivex.Single;
import io.vertx.webrest.model.DbConnection;
import io.vertx.webrest.model.Artist;
import io.vertx.ext.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistService {
    private final DbConnection dbConnection;

    public ArtistService(DbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public Single<List<Artist>> getArtist() {
        return dbConnection
                .db
                .rxQuery("select * from artist")
                .map(ResultSet::getRows)
                .map(
                        rows ->
                                rows.stream()
                                        .map(
                                                row -> {
                                                    Artist artist = new Artist(
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
