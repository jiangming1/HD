package shan.HDHealthManagement.po;

public class WeixinUser {
	private Long id;
	private String openid;
	private String sessionKey;
	private String unionid;
	private String nickname;
	private String name;
	private String code;
	private String fileUrl;
	private Integer examine;
	private Integer integral;
	private Integer week;
	private Integer mouth;
	private Integer year;
	private Integer clock;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getExamine() {
		return examine;
	}
	public void setExamine(Integer examine) {
		this.examine = examine;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public Integer getMouth() {
		return mouth;
	}
	public void setMouth(Integer mouth) {
		this.mouth = mouth;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getClock() {
		return clock;
	}
	public void setClock(Integer clock) {
		this.clock = clock;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}
