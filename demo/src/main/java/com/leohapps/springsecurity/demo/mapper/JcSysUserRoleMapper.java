package com.leohapps.springsecurity.demo.mapper;


import com.leohapps.springsecurity.demo.pojo.JcSysUserRole;
import com.leohapps.springsecurity.demo.pojo.example.JcSysUserRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JcSysUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     */
    long countByExample(JcSysUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     */
    int deleteByExample(JcSysUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     */
    int insert(JcSysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     */
    int insertSelective(JcSysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     */
    List<JcSysUserRole> selectByExample(JcSysUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     */
    JcSysUserRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") JcSysUserRole record, @Param("example") JcSysUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") JcSysUserRole record, @Param("example") JcSysUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(JcSysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(JcSysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<JcSysUserRole> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user_role
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<JcSysUserRole> list, @Param("selective") JcSysUserRole.Column... selective);
}