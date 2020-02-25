package io.vertx.webrest.service;

import io.reactivex.Single;
import io.vertx.webrest.model.Album;
import io.vertx.webrest.model.DbConnection;
import io.vertx.webrest.model.Artist;
import io.vertx.ext.sql.ResultSet;
import io.vertx.webrest.model.Track;

import java.util.List;
import java.util.stream.Collectors;

public class MusicService {
    private final DbConnection dbConnection;

    public MusicService(DbConnection dbConnection){
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
    public Single<List<Album>> getArtist(String artist) {
        return dbConnection
                .db
                .rxQuery("select * from albums WHERE artist_id ="+artist)
                .map(ResultSet::getRows)
                .map(
                        rows ->
                                rows.stream()
                                        .map(
                                                row -> {
                                                    Album albums = new Album(
                                                            row.getInteger("id"),
                                                            row.getString("name"),
                                                            row.getString("artwork"),
                                                            row.getInteger("artist_id"),
                                                            row.getString("created_at"),
                                                            row.getString("updated_ap")
                                                    );
                                                    return albums;
                                                })
                                        .collect(Collectors.toList()));
    }
    public Single<List<Album>> getAlbum() {
        return dbConnection
                .db
                .rxQuery("select * from albums")
                .map(ResultSet::getRows)
                .map(
                        rows ->
                                rows.stream()
                                        .map(
                                                row -> {
                                                    Album albums = new Album(
                                                            row.getInteger("id"),
                                                            row.getString("name"),
                                                            row.getString("artwork"),
                                                            row.getInteger("artist_id"),
                                                            row.getString("created_at"),
                                                            row.getString("updated_ap")
                                                    );
                                                    return albums;
                                                })
                                        .collect(Collectors.toList()));
    }

    public Single<List<Track>> getAlbum(String album) {
        return dbConnection
                .db
                .rxQuery("select * from tracks WHERE album_id ="+album)
                .map(ResultSet::getRows)
                .map(
                        rows ->
                                rows.stream()
                                        .map(
                                                row -> {
                                                    Track tracks = new Track(
                                                            row.getInteger("id"),
                                                            row.getString("name"),
                                                            row.getInteger("track"),
                                                            row.getInteger("duration"),
                                                            row.getString("url"),
                                                            row.getInteger("album_id"),
                                                            row.getString("created_at"),
                                                            row.getString("updated_ap")
                                                    );
                                                    return tracks;
                                                })
                                        .collect(Collectors.toList()));
    }

    public Single<List<Track>> getTrack() {
        return dbConnection
                .db
                .rxQuery("select * from tracks")
                .map(ResultSet::getRows)
                .map(
                        rows ->
                                rows.stream()
                                        .map(
                                                row -> {
                                                    Track tracks = new Track(
                                                            row.getInteger("id"),
                                                            row.getString("name"),
                                                            row.getInteger("track"),
                                                            row.getInteger("duration"),
                                                            row.getString("url"),
                                                            row.getInteger("album_id"),
                                                            row.getString("created_at"),
                                                            row.getString("updated_ap")
                                                    );
                                                    return tracks;
                                                })
                                        .collect(Collectors.toList()));
    }
}
