package io.vertx.webrest.service;

import dagger.internal.Factory;
import io.vertx.webrest.model.DbConnection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainService_Factory implements Factory<MainService> {
  private final Provider<DbConnection> dbConnectionProvider;

  public MainService_Factory(Provider<DbConnection> dbConnectionProvider) {
    this.dbConnectionProvider = dbConnectionProvider;
  }

  @Override
  public MainService get() {
    return new MainService(dbConnectionProvider.get());
  }

  public static MainService_Factory create(Provider<DbConnection> dbConnectionProvider) {
    return new MainService_Factory(dbConnectionProvider);
  }

  public static MainService newInstance(DbConnection dbConnection) {
    return new MainService(dbConnection);
  }
}
