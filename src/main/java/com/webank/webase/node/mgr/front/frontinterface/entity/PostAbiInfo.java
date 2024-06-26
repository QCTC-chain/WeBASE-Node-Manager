/**
 * Copyright 2014-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.webank.webase.node.mgr.front.frontinterface.entity;

import java.util.List;
import lombok.Data;
import org.fisco.bcos.sdk.v3.codec.wrapper.ABIDefinition;


/**
 * abiMeta interface parameter to request front's sendAbi api
 */
@Data
public class PostAbiInfo {

    private String groupId;
    private String contractName;
    private String address;
    private List<ABIDefinition> abiInfo;
    private String contractBin;
}
