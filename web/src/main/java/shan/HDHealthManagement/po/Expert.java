package shan.HDHealthManagement.po;

/**
 * 专家实体类
 * @author 18732
 *
 */
public class Expert {
	private Long id;
	private String hospital;
	private Integer recommend;
	private Integer letter;
	private Integer gift;
	private String department;
	private String specialize;
	private String introduction;
	private Long region;
	private String fileUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getLetter() {
		return letter;
	}
	public void setLetter(Integer letter) {
		this.letter = letter;
	}
	public Integer getGift() {
		return gift;
	}
	public void setGift(Integer gift) {
		this.gift = gift;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSpecialize() {
		return specialize;
	}
	public void setSpecialize(String specialize) {
		this.specialize = specialize;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Long getRegion() {
		return region;
	}
	public void setRegion(Long region) {
		this.region = region;
	}

	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
}
