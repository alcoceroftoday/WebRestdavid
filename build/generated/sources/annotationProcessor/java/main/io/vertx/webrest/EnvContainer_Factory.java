package io.vertx.webrest;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class EnvContainer_Factory implements Factory<EnvContainer> {
  private static final EnvContainer_Factory INSTANCE = new EnvContainer_Factory();

  @Override
  public EnvContainer get() {
    return new EnvContainer();
  }

  public static EnvContainer_Factory create() {
    return INSTANCE;
  }

  public static EnvContainer newInstance() {
    return new EnvContainer();
  }
}
