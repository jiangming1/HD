package shan.HDHealthManagement.po;

import java.util.Date;

/**
 * 积分获取记录
 * @author 18732
 *
 */
public class IntegralRecord {
	private Long id;
	private Long weixinId;
	private Integer integral;
	private String memo;
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
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
