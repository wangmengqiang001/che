/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.multiuser.api.permission.server;

import org.eclipse.che.api.core.ForbiddenException;
import org.eclipse.che.api.core.jsonrpc.commons.JsonRpcException;
import org.eclipse.che.api.core.jsonrpc.commons.JsonRpcMethodInvokerFilter;

/** @author Sergii Leshchenko */
public abstract class PermissionsJsonRpcFilter implements JsonRpcMethodInvokerFilter {

  @Override
  public void accept(String method, Object... params) throws JsonRpcException {
    try {
      doAccept(method, params);
    } catch (ForbiddenException e) {
      throw new JsonRpcException(403, e.getMessage());
    }
  }

  protected abstract void doAccept(String method, Object... params) throws ForbiddenException;
}
