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
package node.mgr.test.contract;


import com.webank.webase.node.mgr.tools.JsonTools;
import com.webank.webase.node.mgr.contract.ContractService;
import com.webank.webase.node.mgr.contract.entity.ContractParam;
import com.webank.webase.node.mgr.contract.entity.DeployInputParam;
import com.webank.webase.node.mgr.contract.entity.TbContract;
import com.webank.webase.node.mgr.contract.entity.TransactionInputParam;
import java.util.Arrays;
import java.util.List;
import node.mgr.test.base.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ContractServiceTest extends TestBase {
    @Autowired
    private ContractService contractService;
    private String groupId = "1";
    private Integer userId = 700001;


    @Test
    public void deployTest(){
        //param
        DeployInputParam deployInputParam = new DeployInputParam();
        deployInputParam.setGroupId(groupId);
        deployInputParam.setUser("");
        deployInputParam.setContractName("Ok");
        deployInputParam.setContractSource("cHJhZ21hIHNvbGlkaXR5IF4wLjQuMjsNCmNvbnRyYWN0IE9rew0KICAgIA0KICAgIHN0cnVjdCBBY2NvdW50ew0KICAgICAgICBhZGRyZXNzIGFjY291bnQ7DQogICAgICAgIHVpbnQgYmFsYW5jZTsNCiAgICB9DQogICAgDQogICAgc3RydWN0ICBUcmFuc2xvZyB7DQogICAgICAgIHN0cmluZyB0aW1lOw0KICAgICAgICBhZGRyZXNzIGZyb207DQogICAgICAgIGFkZHJlc3MgdG87DQogICAgICAgIHVpbnQgYW1vdW50Ow0KICAgIH0NCiAgICANCiAgICBBY2NvdW50IGZyb207DQogICAgQWNjb3VudCB0bzsNCiAgICANCiAgICBUcmFuc2xvZ1tdIGxvZzsNCg0KICAgIGZ1bmN0aW9uIE9rKCl7DQogICAgICAgIGZyb20uYWNjb3VudD0weDE7DQogICAgICAgIGZyb20uYmFsYW5jZT0xMDAwMDAwMDAwMDsNCiAgICAgICAgdG8uYWNjb3VudD0weDI7DQogICAgICAgIHRvLmJhbGFuY2U9MDsNCg0KICAgIH0NCiAgICBmdW5jdGlvbiBnZXQoKWNvbnN0YW50IHJldHVybnModWludCl7DQogICAgICAgIHJldHVybiB0by5iYWxhbmNlOw0KICAgIH0NCiAgICBmdW5jdGlvbiB0cmFucyh1aW50IG51bSl7DQogICAgCWZyb20uYmFsYW5jZT1mcm9tLmJhbGFuY2UtbnVtOw0KICAgIAl0by5iYWxhbmNlKz1udW07DQogICAgDQogICAgCWxvZy5wdXNoKFRyYW5zbG9nKCIyMDE3MDQxMyIsZnJvbS5hY2NvdW50LHRvLmFjY291bnQsbnVtKSk7DQogICAgfQ0KDQoNCg0KfQ==");
        deployInputParam.setBytecodeBin("6060604052341561000c57fe5b5b6001600060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506402540be4006000600101819055506002600260000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060006002600101819055505b5b610443806100c26000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806366c99139146100465780636d4ce63c14610066575bfe5b341561004e57fe5b610064600480803590602001909190505061008c565b005b341561006e57fe5b610076610264565b6040518082815260200191505060405180910390f35b806000600101540360006001018190555080600260010160008282540192505081905550600480548060010182816100c49190610272565b916000526020600020906004020160005b608060405190810160405280604060405190810160405280600881526020017f32303137303431330000000000000000000000000000000000000000000000008152508152602001600060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600260000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200185815250909190915060008201518160000190805190602001906101c49291906102a4565b5060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301555050505b50565b600060026001015490505b90565b81548183558181151161029f5760040281600402836000526020600020918201910161029e9190610324565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e557805160ff1916838001178555610313565b82800160010185558215610313579182015b828111156103125782518255916020019190600101906102f7565b5b50905061032091906103aa565b5090565b6103a791905b808211156103a357600060008201600061034491906103cf565b6001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560038201600090555060040161032a565b5090565b90565b6103cc91905b808211156103c85760008160009055506001016103b0565b5090565b90565b50805460018160011615610100020316600290046000825580601f106103f55750610414565b601f01602090049060005260206000209081019061041391906103aa565b5b505600a165627a7a72305820d453cb481a312519166e409e7248d76d8c2672458c08b9500945a4004a1b69020029");
        deployInputParam.setContractAbi("[{\"constant\":false,\"inputs\":[{\"name\":\"num\",\"type\":\"uint256\"}],\"name\":\"trans\",\"outputs\":[],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"type\":\"constructor\"}]");
        deployInputParam.setContractBin("60606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806366c99139146100465780636d4ce63c14610066575bfe5b341561004e57fe5b610064600480803590602001909190505061008c565b005b341561006e57fe5b610076610264565b6040518082815260200191505060405180910390f35b806000600101540360006001018190555080600260010160008282540192505081905550600480548060010182816100c49190610272565b916000526020600020906004020160005b608060405190810160405280604060405190810160405280600881526020017f32303137303431330000000000000000000000000000000000000000000000008152508152602001600060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600260000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200185815250909190915060008201518160000190805190602001906101c49291906102a4565b5060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301555050505b50565b600060026001015490505b90565b81548183558181151161029f5760040281600402836000526020600020918201910161029e9190610324565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e557805160ff1916838001178555610313565b82800160010185558215610313579182015b828111156103125782518255916020019190600101906102f7565b5b50905061032091906103aa565b5090565b6103a791905b808211156103a357600060008201600061034491906103cf565b6001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560038201600090555060040161032a565b5090565b90565b6103cc91905b808211156103c85760008160009055506001016103b0565b5090565b90565b50805460018160011615610100020316600290046000825580601f106103f55750610414565b601f01602090049060005260206000209081019061041391906103aa565b5b505600a165627a7a72305820d453cb481a312519166e409e7248d76d8c2672458c08b9500945a4004a1b69020029");

        //deploy
        TbContract tbContract = contractService.deployContract(deployInputParam);
        assert (tbContract.getContractId()!=null);
        System.out.println("=========================================deploy result:"+ JsonTools.toJSONString(tbContract));
    }

    @Test
    public void sendTransactionTest(){
        //abi
        String abiStr = "[{\"constant\":false,\"inputs\":[{\"name\":\"num\",\"type\":\"uint256\"}],\"name\":\"trans\",\"outputs\":[],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"type\":\"constructor\"}]";
        List<Object> abiList = JsonTools.toJavaObjectList(abiStr, Object.class);

        //param
        TransactionInputParam param = new TransactionInputParam();
        param.setGroupId(groupId);
        param.setUser("");
        param.setContractName("Ok");
        param.setContractAddress("0x821bd01b8aa7f14b53bc62da33c9882bbe045459");
        param.setFuncName("trans");
        param.setFuncParam(Arrays.asList("3"));

        //trans
        Object transRsp = contractService.sendTransaction(param);
        assert (transRsp!=null);
    }

    @Test
    public void testGetContractList() {
        List<TbContract> resList = contractService.queryContractList(new ContractParam());
        System.out.println("list:");
        System.out.println(resList);
    }
}
