/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kuujo.copycat.cluster;

import java.util.Observable;
import java.util.Observer;

/**
 * Cluster member.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public abstract class Member<M extends MemberConfig> extends Observable implements Observer {
  private final String id;
  private final M config;

  protected Member(M config) {
    this.id = config.getId();
    this.config = config;
    config.addObserver(this);
  }

  @Override
  public void update(Observable o, Object arg) {
    notifyObservers();
  }

  /**
   * Returns the unique member ID.
   *
   * @return The unique member ID.
   */
  public final String id() {
    return id;
  }

  /**
   * Returns the member configuration.
   *
   * @return The member configuration.
   */
  public final M config() {
    return config;
  }

  @Override
  public String toString() {
    return String.format("Member[config=%s]", config);
  }

}
