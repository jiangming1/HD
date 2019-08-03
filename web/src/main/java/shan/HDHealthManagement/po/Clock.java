package shan.HDHealthManagement.po;

import java.util.Date;

/**
 * 打卡记录
 * @author 18732
 *
 */
public class Clock {
	private Long id;
	private Long weixinId;
	private Integer motion;
	private Integer housework;
	private Date date;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWeixinId() {
		return weixinId;
	}
	public void setWeixinId(Long weixinId) {
		this.weixinId = weixinId;
	}
	public Integer getMotion() {
		return motion;
	}
	public void setMotion(Integer motion) {
		this.motion = motion;
	}
	public Integer getHousework() {
		return housework;
	}
	public void setHousework(Integer housework) {
		this.housework = housework;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
