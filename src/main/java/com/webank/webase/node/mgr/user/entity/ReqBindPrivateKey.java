/**
 * Copyright 2014-2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.webank.webase.node.mgr.user.entity;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReqBindPrivateKey {
    @NotNull
    private String groupId;
    /**
     * encoded in base64
     */
    private String privateKey;
    /**
     * used to bind private key
     */
    @NotNull
    private Integer userId;

    /**
     * used for pem import
     */
    private String pemContent;

    /**
     * init constructor
     * @param groupId
     * @param userId
     * @param privateKeyEncoded base64
     */
    public ReqBindPrivateKey(String groupId, int userId, String privateKeyEncoded) {
        this.groupId = groupId;
        this.userId = userId;
        this.privateKey = privateKeyEncoded;
    }

}
