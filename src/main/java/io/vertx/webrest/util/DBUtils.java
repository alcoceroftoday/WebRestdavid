package io.vertx.webrest.util;

import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class DBUtils {
  public static final String host="localhost";
  public static final String db="prueba";
  public static final String user="david";
  public static final String pass="david";

  private static Map<String, JsonObject> artists = new HashMap<>();
  private static Map<String, JsonObject> albums = new HashMap<>();
  private static Map<String, JsonObject> tracks = new HashMap<>();
  public void addArtists(JsonObject product) {
    artists.put(product.getString("id"), product);
  }
  public void addAlbums(JsonObject product) {
    albums.put(product.getString("id"), product);
  }
  public void addtracks(JsonObject product) {
    tracks.put(product.getString("id"), product);
  }

  public Boolean putArtits(String id, String name, String artwork, String created_at,String updated_ap){
    addArtists(
      new JsonObject()
        .put("id", id)
        .put("name",name)
        .put("artwork", artwork)
        .put("created_at", created_at)
        .put("updated_ap", updated_ap));
    return true;
  }
  public Boolean putAlbums(String id,String name,String artwork,String artist_id, String created_at,String updated_ap){
    addAlbums(
      new JsonObject()
        .put("id", id)
        .put("name",name)
        .put("artwork", artwork)
        .put("artist_id", artist_id)
        .put("created_at", created_at)
        .put("updated_ap", updated_ap));
    return true;
  }
  public Boolean putTracks(String id,String name,String track,String duration,String url,String album_id, String created_at,String updated_ap){
    addtracks(
      new JsonObject()
        .put("id", id)
        .put("name",name)
        .put("track", track)
        .put("duration", duration)
        .put("url", url)
        .put("album_id", album_id)
        .put("created_at", created_at)
        .put("updated_ap", updated_ap));
    return true;
  }
  public Map<String, JsonObject> getArtists(){
    return this.artists;
  }
  public Map<String, JsonObject> getAlbums(){
    return this.albums;
  }
  public Map<String, JsonObject> getTracks(){
    return this.tracks;
  }
}
