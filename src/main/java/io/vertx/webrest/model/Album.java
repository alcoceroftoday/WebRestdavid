package io.vertx.webrest.model;

public class Album {
    public Integer id;
    public String name;
    public String artwork;
    public Integer id_artist;
    public String created_at;
    public String updated_ap;

    public Album (Integer id,String name, String artwork,Integer id_artist, String created_at, String updated_ap){
        this.id = id;
        this.name=name;
        this.artwork=artwork;
        this.id_artist=id_artist;
        this.created_at=created_at;
        this.updated_ap=updated_ap;
    }
}
