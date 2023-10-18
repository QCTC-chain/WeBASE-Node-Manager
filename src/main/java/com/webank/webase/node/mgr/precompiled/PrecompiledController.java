/**
 * Copyright 2014-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webank.webase.node.mgr.precompiled;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.qctc.common.log.annotation.Log;
import com.qctc.common.log.enums.BusinessType;
import com.webank.webase.node.mgr.base.code.ConstantCode;
import com.webank.webase.node.mgr.base.entity.BaseResponse;
import com.webank.webase.node.mgr.precompiled.entity.AddressStatusHandle;
import com.webank.webase.node.mgr.precompiled.entity.ContractStatusHandle;
import java.time.Duration;
import java.time.Instant;

import java.util.Map;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webank.webase.node.mgr.base.controller.BaseController;
import com.webank.webase.node.mgr.base.exception.NodeMgrException;
import com.webank.webase.node.mgr.config.properties.ConstantProperties;
import com.webank.webase.node.mgr.tools.JsonTools;
import com.webank.webase.node.mgr.precompiled.entity.ConsensusHandle;
import com.webank.webase.node.mgr.precompiled.entity.CrudHandle;

import lombok.extern.log4j.Log4j2;

/**
 * Precompiled common controller
 * including management of CNS, node consensus status, CRUD
 */
@Tag(name="预编译通用接口(包含cns、node status、crud)")
@Log4j2
@RestController
@RequestMapping("precompiled")
public class PrecompiledController extends BaseController {
    @Autowired
    PrecompiledService precompiledService;

    /**
     * get cns list
     * 透传front的BaseResponse
     */
    @SaCheckPermission("bcos:contract:cnsManagement")
    @GetMapping("cns/list")
    public Object listCns(
            @RequestParam(defaultValue = "1") int groupId,
            @RequestParam String contractNameAndVersion,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int pageNumber) {

        Instant startTime = Instant.now();
        log.info("start listCns startTime:{}", startTime.toEpochMilli());
        Object result = precompiledService.listCnsService(groupId, contractNameAndVersion, pageSize, pageNumber);

        log.info("end listCns useTime:{} result:{}",
                Duration.between(startTime, Instant.now()).toMillis(), JsonTools.toJSONString(result));
        return result;
    }

    /**
     * get node list with consensus status.
     */
    @SaCheckPermission("bcos:chain:front")
    @GetMapping("consensus/list")
    public Object getNodeList(
            @RequestParam(defaultValue = "1") int groupId,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int pageNumber) {

        Instant startTime = Instant.now();
        log.info("start getNodeList startTime:{}", startTime.toEpochMilli());

        Object result = precompiledService.getNodeListService(groupId, pageSize, pageNumber);
        log.info("end getNodeList useTime:{} result:{}",
                Duration.between(startTime, Instant.now()).toMillis(), JsonTools.toJSONString(result));
        return result;
    }

    @Log(title = "BCOS2/节点管理", businessType = BusinessType.UPDATE)
    @SaCheckPermission("bcos:chain:nodeManage")
    @PostMapping(value = "consensus")
    public Object nodeManage(@RequestBody @Valid ConsensusHandle consensusHandle,
                                  BindingResult result) throws NodeMgrException {
        checkBindResult(result);
        Instant startTime = Instant.now();
        log.info("start nodeManage startTime:{} consensusHandle:{}", startTime.toEpochMilli(),
                JsonTools.toJSONString(consensusHandle));

        Object res = precompiledService.nodeManageService(consensusHandle);

        log.info("end nodeManage useTime:{} result:{}",
                Duration.between(startTime, Instant.now()).toMillis(), JsonTools.toJSONString(res));

        return res;
    }

    /**
     * crud control.
     */
    @Log(title = "BCOS2/合约管理/CRUD", businessType = BusinessType.UPDATE)
    @SaCheckPermission("bcos:contract:crud")
    @PostMapping(value = "crud")
    public Object crud(@RequestBody @Valid CrudHandle crudHandle,
                                   BindingResult result) throws NodeMgrException {
        checkBindResult(result);
        Instant startTime = Instant.now();
        log.info("start crud startTime:{} crudHandle:{}", startTime.toEpochMilli(),
                JsonTools.toJSONString(crudHandle));

        Object res = precompiledService.crudService(crudHandle);

        log.info("end crud useTime:{} result:{}",
                Duration.between(startTime, Instant.now()).toMillis(), JsonTools.toJSONString(res));

        return res;
    }

    /**
     * contract status control.
     */
    @Log(title = "BCOS2/合约管理", businessType = BusinessType.UPDATE)
    @SaCheckPermission("bcos:contract:contractStatusManage")
    @PostMapping(value = "contract/status")
    public Object contractStatusManage(@RequestBody @Valid ContractStatusHandle contractStatusHandle,
        BindingResult result) throws NodeMgrException {
        checkBindResult(result);
        Instant startTime = Instant.now();
        log.info("start crud startTime:{} contractStatusHandle:{}", startTime.toEpochMilli(),
            JsonTools.toJSONString(contractStatusHandle));

        Object res = precompiledService.contractStatusManage(contractStatusHandle);

        log.info("end crud useTime:{} result:{}",
            Duration.between(startTime, Instant.now()).toMillis(), JsonTools.toJSONString(res));

        return res;
    }

    @SaCheckPermission("bcos:contract:List")
    @PostMapping(value = "contract/status/list")
    public BaseResponse listContractStatus(@RequestBody @Valid AddressStatusHandle addressStatusHandle,
        BindingResult result) throws NodeMgrException {
        checkBindResult(result);
        Instant startTime = Instant.now();
        log.info("start crud startTime:{} addressStatusHandle:{}", startTime.toEpochMilli(),
            JsonTools.toJSONString(addressStatusHandle));

        // return map of <contractAddress, response.data>
        Map<String, Object> res = precompiledService.queryContractStatus(addressStatusHandle);

        log.info("end crud useTime:{} result:{}",
            Duration.between(startTime, Instant.now()).toMillis(), JsonTools.toJSONString(res));

        return new BaseResponse(ConstantCode.SUCCESS, res);
    }
}
