package com.leohapps.springsecurity.demo.mapper;


import com.leohapps.springsecurity.demo.pojo.JcSysRolePermission;
import com.leohapps.springsecurity.demo.pojo.example.JcSysRolePermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JcSysRolePermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     */
    long countByExample(JcSysRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     */
    int deleteByExample(JcSysRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     */
    int insert(JcSysRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     */
    int insertSelective(JcSysRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     */
    List<JcSysRolePermission> selectByExample(JcSysRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     */
    JcSysRolePermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") JcSysRolePermission record, @Param("example") JcSysRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") JcSysRolePermission record, @Param("example") JcSysRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(JcSysRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(JcSysRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<JcSysRolePermission> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_role_permission
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<JcSysRolePermission> list, @Param("selective") JcSysRolePermission.Column... selective);
}