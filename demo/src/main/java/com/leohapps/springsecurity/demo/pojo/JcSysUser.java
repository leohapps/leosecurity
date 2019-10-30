package com.leohapps.springsecurity.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class JcSysUser implements UserDetails {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sys_user.ID
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sys_user.USER_NAME
     *
     * @mbg.generated
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sys_user.EMPLOY_ID
     *
     * @mbg.generated
     */
    private String employId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sys_user.PASSWORD
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sys_user.NICK_NAME
     *
     * @mbg.generated
     */
    private String nickName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sys_user.HEAD_IMAGE_URL
     *
     * @mbg.generated
     */
    private String headImageUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sys_user.PHONE
     *
     * @mbg.generated
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sys_user.EMAIL
     *
     * @mbg.generated
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sys_user.STATUS
     *
     * @mbg.generated
     */
    private Boolean status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sys_user.CREATE_TIME
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sys_user.UPDATE_TIME
     *
     * @mbg.generated
     */
    private Date updateTime;

    private List<JcSysRole> roles;


    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;


    public JcSysUser() {
    }

    public JcSysUser(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (grantedAuthorities.size() == 0) {
            if (!CollectionUtils.isEmpty(roles)) {
                for (JcSysRole role : roles) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
                }
            }
        }

        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<JcSysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<JcSysRole> roles) {
        this.roles = roles;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sys_user.ID
     *
     * @return the value of jc_sys_user.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sys_user.ID
     *
     * @param id the value for jc_sys_user.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sys_user.USER_NAME
     *
     * @return the value of jc_sys_user.USER_NAME
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sys_user.USER_NAME
     *
     * @param userName the value for jc_sys_user.USER_NAME
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sys_user.EMPLOY_ID
     *
     * @return the value of jc_sys_user.EMPLOY_ID
     *
     * @mbg.generated
     */
    public String getEmployId() {
        return employId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sys_user.EMPLOY_ID
     *
     * @param employId the value for jc_sys_user.EMPLOY_ID
     *
     * @mbg.generated
     */
    public void setEmployId(String employId) {
        this.employId = employId == null ? null : employId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sys_user.PASSWORD
     *
     * @return the value of jc_sys_user.PASSWORD
     *
     * @mbg.generated
     */
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sys_user.PASSWORD
     *
     * @param password the value for jc_sys_user.PASSWORD
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sys_user.NICK_NAME
     *
     * @return the value of jc_sys_user.NICK_NAME
     *
     * @mbg.generated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sys_user.NICK_NAME
     *
     * @param nickName the value for jc_sys_user.NICK_NAME
     *
     * @mbg.generated
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sys_user.HEAD_IMAGE_URL
     *
     * @return the value of jc_sys_user.HEAD_IMAGE_URL
     *
     * @mbg.generated
     */
    public String getHeadImageUrl() {
        return headImageUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sys_user.HEAD_IMAGE_URL
     *
     * @param headImageUrl the value for jc_sys_user.HEAD_IMAGE_URL
     *
     * @mbg.generated
     */
    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl == null ? null : headImageUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sys_user.PHONE
     *
     * @return the value of jc_sys_user.PHONE
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sys_user.PHONE
     *
     * @param phone the value for jc_sys_user.PHONE
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sys_user.EMAIL
     *
     * @return the value of jc_sys_user.EMAIL
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sys_user.EMAIL
     *
     * @param email the value for jc_sys_user.EMAIL
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sys_user.STATUS
     *
     * @return the value of jc_sys_user.STATUS
     *
     * @mbg.generated
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sys_user.STATUS
     *
     * @param status the value for jc_sys_user.STATUS
     *
     * @mbg.generated
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sys_user.CREATE_TIME
     *
     * @return the value of jc_sys_user.CREATE_TIME
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sys_user.CREATE_TIME
     *
     * @param createTime the value for jc_sys_user.CREATE_TIME
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sys_user.UPDATE_TIME
     *
     * @return the value of jc_sys_user.UPDATE_TIME
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sys_user.UPDATE_TIME
     *
     * @param updateTime the value for jc_sys_user.UPDATE_TIME
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", employId=").append(employId);
        sb.append(", password=").append(password);
        sb.append(", nickName=").append(nickName);
        sb.append(", headImageUrl=").append(headImageUrl);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table jc_sys_user
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("ID", "id", "INTEGER", false),
        userName("USER_NAME", "userName", "VARCHAR", false),
        employId("EMPLOY_ID", "employId", "VARCHAR", false),
        password("PASSWORD", "password", "VARCHAR", true),
        nickName("NICK_NAME", "nickName", "VARCHAR", false),
        headImageUrl("HEAD_IMAGE_URL", "headImageUrl", "VARCHAR", false),
        phone("PHONE", "phone", "VARCHAR", false),
        email("EMAIL", "email", "VARCHAR", false),
        status("STATUS", "status", "BIT", true),
        createTime("CREATE_TIME", "createTime", "TIMESTAMP", false),
        updateTime("UPDATE_TIME", "updateTime", "TIMESTAMP", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table jc_sys_user
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }
    }
}