package com.webank.webase.node.mgr.external.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class TbExternalContract implements Serializable {

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.group_id
     *
     * @mbg.generated
     */
    private String groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.contract_address
     *
     * @mbg.generated
     */
    private String contractAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.deploy_address
     *
     * @mbg.generated
     */
    private String deployAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.deploy_tx_hash
     *
     * @mbg.generated
     */
    private String deployTxHash;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.deploy_time
     *
     * @mbg.generated
     */
    private Date deployTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.contract_status
     *
     * @mbg.generated
     */
    private Integer contractStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.contract_type
     *
     * @mbg.generated
     */
    private Byte contractType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.contract_name
     *
     * @mbg.generated
     */
    private String contractName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.contract_version
     *
     * @mbg.generated
     */
    private String contractVersion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.contract_bin
     *
     * @mbg.generated
     */
    private String contractBin;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.contract_abi
     *
     * @mbg.generated
     */
    private String contractAbi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.bytecode_bin
     *
     * @mbg.generated
     */
    private String bytecodeBin;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_external_contract.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_external_contract
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}
