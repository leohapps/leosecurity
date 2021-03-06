package com.leohapps.springsecurity.demo.mapper;


import com.leohapps.springsecurity.demo.pojo.JcSysRole;
import com.leohapps.springsecurity.demo.pojo.JcSysUser;
import com.leohapps.springsecurity.demo.pojo.example.JcSysUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JcSysUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    long countByExample(JcSysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    int deleteByExample(JcSysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    int insert(JcSysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    int insertSelective(JcSysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    List<JcSysUser> selectByExample(JcSysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    JcSysUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") JcSysUser record, @Param("example") JcSysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") JcSysUser record, @Param("example") JcSysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(JcSysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(JcSysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<JcSysUser> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<JcSysUser> list, @Param("selective") JcSysUser.Column... selective);
    
    /**
     *  description: 
     *  @Param: [id]
     *  @return: java.util.List<com.sohu.jc.op.pojo.entity.JcSysRole>
        
     *  @author: lx
     *  @date: 2019-10-29 11:31
     */
    List<JcSysRole> getRolesByUserId(Long id);


    JcSysUser loadUserByUsername(String username);
}