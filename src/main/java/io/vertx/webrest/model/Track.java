package io.vertx.webrest.model;

public class Track {
    public Integer id;
    public String name;
    public Integer track;
    public Integer duration;
    public String url;
    public Integer album_id;
    public String created_at;
    public String updated_ap;

    public Track (Integer id,String name, Integer track,Integer duration,String url,Integer album_id, String created_at, String updated_ap){
        this.id = id;
        this.name=name;
        this.track=track;
        this.duration=duration;
        this.url=url;
        this.album_id=album_id;
        this.created_at=created_at;
        this.updated_ap=updated_ap;
    }
}
