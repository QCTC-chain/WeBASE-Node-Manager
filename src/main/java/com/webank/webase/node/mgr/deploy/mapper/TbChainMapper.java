package com.webank.webase.node.mgr.deploy.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.webank.webase.node.mgr.deploy.entity.TbChain;

public interface TbChainMapper {

    @Select({
            "select ", TbChainSqlProvider.ALL_COLUMN_FIELDS, " from tb_chain ",
            "where chain_name = #{chainName,jdbcType=VARCHAR}"
    })
    TbChain getByChainName(@Param("chainName") String chainName);

    @Update({
            " update tb_chain set chain_status=#{newStatus},modify_time=#{modifyTime}  where id = #{chainId} and chain_status !=#{newStatus}"
    })
    int updateChainStatus(@Param("chainId") int chainId, @Param("modifyTime") Date modifyTime, @Param("newStatus") byte newStatus );


    @Select({
            "select count(id) from tb_chain ",
    })
    int countChain();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chain
     *
     * @mbg.generated
     */
    @Delete({
        "delete from tb_chain",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chain
     *
     * @mbg.generated
     */
    @InsertProvider(type=TbChainSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(TbChain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chain
     *
     * @mbg.generated
     */
    @Select({
        "select",
        TbChainSqlProvider.ALL_COLUMN_FIELDS,
        "from tb_chain",
        "where id = #{id,jdbcType=INTEGER}"
    })
    TbChain selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chain
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TbChainSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TbChain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chain
     *
     * @mbg.generated
     */
    @Options(useGeneratedKeys = true,keyProperty="id",keyColumn = "id")
    @Insert({
    "<script>",
        "insert into tb_chain (chain_name, ",
        "chain_desc, version, ",
        "encrypt_type, chain_status, root_dir,run_type, ",
        "create_time, modify_time,webase_sign_addr)",
        "values<foreach collection=\"list\" item=\"detail\" index=\"index\" separator=\",\">(#{detail.chainName,jdbcType=VARCHAR}, ",
        "#{detail.chainDesc,jdbcType=VARCHAR}, #{detail.version,jdbcType=VARCHAR}, ",
        "#{detail.encryptType,jdbcType=TINYINT}, #{detail.chainStatus,jdbcType=TINYINT},#{detail.rootDir,jdbcType=VARCHAR}, " +
                "#{detail.runType,jdbcType=TINYINT},#{detail.webaseSignAddr,jdbcType=VARCHAR}",
        "#{detail.createTime,jdbcType=TIMESTAMP}, #{detail.modifyTime,jdbcType=TIMESTAMP})</foreach></script>"
    })
    int batchInsert(java.util.List<TbChain> list);

    @Select({
            "select",
            TbChainSqlProvider.ALL_COLUMN_FIELDS,
            "from tb_chain"
    })
    List<TbChain> getChainList();

    @Select({
            "select count(id) from tb_chain where chain_status=3",
    })
    int getRunningChainCount();
}