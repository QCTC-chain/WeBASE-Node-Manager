/**
 * Copyright 2014-2021  the original author or authors.
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
package com.webank.webase.node.mgr.front;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.qctc.common.log.annotation.Log;
import com.qctc.common.log.enums.BusinessType;
import com.webank.webase.node.mgr.base.code.ConstantCode;
import com.webank.webase.node.mgr.base.controller.BaseController;
import com.webank.webase.node.mgr.base.entity.BasePageResponse;
import com.webank.webase.node.mgr.base.entity.BaseResponse;
import com.webank.webase.node.mgr.base.exception.NodeMgrException;
import com.webank.webase.node.mgr.config.properties.ConstantProperties;
import com.webank.webase.node.mgr.tools.JsonTools;
import com.webank.webase.node.mgr.front.entity.FrontInfo;
import com.webank.webase.node.mgr.front.entity.FrontNodeConfig;
import com.webank.webase.node.mgr.front.entity.FrontParam;
import com.webank.webase.node.mgr.front.entity.TbFront;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * front controller
 */
@Tag(name="节点前置")
@Log4j2
@RestController
@RequestMapping("front")
@SaCheckPermission("bcos:chain:front")
public class FrontController extends BaseController {

    @Autowired
    private FrontService frontService;

    /**
     * refresh frontn
     */
    @Log(title = "BCOS2/节点管理", businessType = BusinessType.UPDATE)
    @GetMapping("/refresh")
    public BaseResponse refreshFront() {
    	Instant startTime = Instant.now();
    	log.info("start refreshFront startTime:{}", startTime.toEpochMilli());
    	BaseResponse baseResponse = new BaseResponse(ConstantCode.SUCCESS);
    	frontService.refreshFront();
    	log.info("end refreshFront useTime:{}", Duration.between(startTime, Instant.now()).toMillis());
    	return baseResponse;
    }
    
    /**
     * add new front
     */
    @Log(title = "BCOS2/节点管理", businessType = BusinessType.INSERT)
    @PostMapping("/new")
    public BaseResponse newFront(@RequestBody @Valid FrontInfo frontInfo, BindingResult result) {
        checkBindResult(result);
        Instant startTime = Instant.now();
        log.info("start newFront startTime:{} frontInfo:{}",
            startTime.toEpochMilli(), JsonTools.toJSONString(frontInfo));
        BaseResponse baseResponse = new BaseResponse(ConstantCode.SUCCESS);
        TbFront tbFront = frontService.newFront(frontInfo);
        baseResponse.setData(tbFront);
        log.info("end newFront useTime:{} result:{}",
            Duration.between(startTime, Instant.now()).toMillis(), JsonTools.toJSONString(baseResponse));
        return baseResponse;
    }


    /**
     * query front info list.
     */
    @GetMapping(value = "/find")
    public BasePageResponse queryFrontList(
        @RequestParam(value = "frontId", required = false) Integer frontId,
        @RequestParam(value = "groupId", required = false) Integer groupId,
        @RequestParam(value = "frontStatus", required = false) Integer frontStatus)
        throws NodeMgrException {
        BasePageResponse pageResponse = new BasePageResponse(ConstantCode.SUCCESS);
        Instant startTime = Instant.now();
        log.info("start queryFrontList startTime:{} frontId:{} groupId:{},frontStatus:{}",
            startTime.toEpochMilli(), frontId, groupId, frontStatus);

        //param
        FrontParam param = new FrontParam();
        param.setFrontId(frontId);
        param.setGroupId(groupId);
        param.setFrontStatus(frontStatus);

        //query front info
        int count = frontService.getFrontCount(param);
        pageResponse.setTotalCount(count);
        if (count > 0) {
            List<TbFront> list = frontService.getFrontList(param);
            pageResponse.setData(list);
        }

        log.info("end queryFrontList useTime:{} result:{}",
            Duration.between(startTime, Instant.now()).toMillis(), JsonTools.toJSONString(pageResponse));
        return pageResponse;
    }

    /**
     * delete by frontId
     */
    @Log(title = "BCOS2/节点管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/{frontId}")
    public BaseResponse removeFront(@PathVariable("frontId") Integer frontId) {
        Instant startTime = Instant.now();
        log.info("start removeFront startTime:{} frontId:{}",
            startTime.toEpochMilli(), frontId);
        BaseResponse baseResponse = new BaseResponse(ConstantCode.SUCCESS);

        //remove
        frontService.removeFront(frontId);

        log.info("end removeFront useTime:{} result:{}",
            Duration.between(startTime, Instant.now()).toMillis(), JsonTools.toJSONString(baseResponse));
        return baseResponse;
    }

    /**
     * query front info list.
     */
//    @Log(title = "BCOS2/节点管理", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/refresh/status")
    public BaseResponse refreshFrontStatus() throws NodeMgrException {
        Instant startTime = Instant.now();
        log.info("start refreshFrontStatus startTime:{} ", startTime.toEpochMilli());

        frontService.refreshFrontStatus();

        log.info("end queryFrontList useTime:{}", Duration.between(startTime, Instant.now()).toMillis());
        return new BaseResponse(ConstantCode.SUCCESS);
    }

    /**
     * get front's node config
     */
    @GetMapping(value = "/nodeConfig")
    public BaseResponse getFrontNodeConfig(@RequestParam("frontId") int frontId) {
        Instant startTime = Instant.now();
        log.info("start getFrontNodeConfig startTime:{} ", startTime.toEpochMilli());
        FrontNodeConfig nodeConfig = frontService.getFrontNodeConfig(frontId);

        log.info("end getFrontNodeConfig useTime:{}", Duration.between(startTime, Instant.now()).toMillis());
        return new BaseResponse(ConstantCode.SUCCESS, nodeConfig);
    }

}
