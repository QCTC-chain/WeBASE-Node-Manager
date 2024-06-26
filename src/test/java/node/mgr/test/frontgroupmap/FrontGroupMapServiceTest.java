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
package node.mgr.test.frontgroupmap;

import com.webank.webase.node.mgr.tools.JsonTools;
import com.webank.webase.node.mgr.frontgroupmap.FrontGroupMapService;
import com.webank.webase.node.mgr.frontgroupmap.entity.FrontGroup;
import com.webank.webase.node.mgr.frontgroupmap.entity.MapListParam;
import java.util.List;
import node.mgr.test.base.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FrontGroupMapServiceTest extends TestBase {

    @Autowired
    private FrontGroupMapService frontGroupMapService;

    @Test
    public void getListTest() {
        MapListParam param = new MapListParam();
        param.setGroupId("2");
        List<FrontGroup> list = frontGroupMapService.getList(param);
        assert (list != null);
        System.out.println(JsonTools.toJSONString(list));
    }

    @Test
    public void listByGroupIdTest() {
        List<FrontGroup> list = frontGroupMapService.listByGroupId("2");
        assert (list != null);
        System.out.println(JsonTools.toJSONString(list));
    }

    @Test
    public void getCountTest() {
        int groupIdCountLocal = frontGroupMapService.getCount(new MapListParam());
        assert (groupIdCountLocal >= 0);
        System.out.println(groupIdCountLocal);
    }
}
