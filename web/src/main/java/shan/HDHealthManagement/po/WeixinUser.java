package shan.HDHealthManagement.po;

public class WeixinUser {
	private Long id;
	private String nickName;
	private String name;
	private String code;
	private String fileUrl;
	private Boolean examine;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Boolean getExamine() {
		return examine;
	}
	public void setExamine(Boolean examine) {
		this.examine = examine;
	}
	
}
