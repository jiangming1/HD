package shan.HDHealthManagement.po;

/**
 * 角色权限实体类
 * @author 18732
 *
 */
public class RoleJurisdiction {
	private Long id;
	private Long roleId;
	private Long jurisdictionId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getJurisdictionId() {
		return jurisdictionId;
	}
	public void setJurisdictionId(Long jurisdictionId) {
		this.jurisdictionId = jurisdictionId;
	}

}
