package io.vertx.webrest.model;

public class Artist {
    public Integer id;
    public String name;
    public String artwork;
    public String created_at;
    public String updated_ap;

    public Artist (Integer id,String name, String artwork, String created_at, String updated_ap){
        this.id = id;
        this.name=name;
        this.artwork=artwork;
        this.created_at=created_at;
        this.updated_ap=updated_ap;
    }
}