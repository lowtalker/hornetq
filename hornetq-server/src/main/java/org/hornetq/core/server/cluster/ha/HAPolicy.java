/*
 * Copyright 2005-2014 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.hornetq.core.server.cluster.ha;

import java.util.Map;

import org.hornetq.core.server.impl.Activation;
import org.hornetq.core.server.impl.HornetQServerImpl;

/**
 * Every live server will have an HAPolicy that configures the type of server that it should be either live, backup or
 * colocated (both). It also configures how, if colocated, it should react to sending and receiving requests for backups.
 */
public interface HAPolicy<T extends Activation>
{
   /*
   * created the Activation associated with this policy.
   * */
   T createActivation(HornetQServerImpl server, boolean wasLive, Map<String, Object> activationParams, HornetQServerImpl.ShutdownOnCriticalErrorListener shutdownOnCriticalIO) throws Exception;

   boolean isSharedStore();

   boolean isBackup();

   boolean canScaleDown();

   /*
   * todo These 3 methods could probably be moved as they are specific to the activation however they are needed for certain packets.
   * */

   String getBackupGroupName();

   String getScaleDownGroupName();

   String getScaleDownClustername();

}
