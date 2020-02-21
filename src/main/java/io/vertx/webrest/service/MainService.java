package io.vertx.webrest.service;

import io.vertx.ext.sql.ResultSet;
import io.vertx.webrest.model.DbConnection;
import javax.inject.Inject;

public class MainService {

    private final DbConnection dbConnection;

    @Inject
    public MainService(DbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public Object getArtist(){
        return this.dbConnection.db.rxQuery(" Select * from artists");
    }
}
