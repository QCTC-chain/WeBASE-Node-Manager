/**
 * Copyright 2014-2021  the original author or authors.
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
package com.webank.webase.node.mgr.transdaily;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * trans_daily data interface.
 */
@Repository
public interface TbTransDailyMapper {

    /**
     * query Trading within seven days.
     */
    List<SeventDaysTrans> listSeventDayOfTransDaily(@Param("groupId") String groupId);

    /**
     * update tb_trans_daily.
     */
    Integer updateTransDaily(Map<String, Object> paramMap);

    /**
     * add new tb_trans_daily data.
     */
    Integer addTransDailyRow(TbTransDaily tbTransDaily);

    /**
     * query max block number by group id.
     */
    BigInteger queryMaxBlockByGroup(@Param("groupId") String groupId);

    /**
     * delete by groupId.
     */
    Integer deleteByGroupId( @Param("groupId") String groupId);
}
