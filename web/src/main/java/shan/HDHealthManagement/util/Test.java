package shan.HDHealthManagement.util;

public enum Test {
	_0000("0000","正常"),
	_1001("1001","后续未列出的其他原因的失败"),
	_1002("1002","重复的报文标识"),
	_1003("1003","通讯报文头格式错误"),
	_1004("1004","通讯业务体格式错误"),
	_1005("1005","报文签名验证失败"),
	_1006("1006","密钥不一致"),
	_5001("5001","原交易不存在"),
	_9900("9900","备用自定义编码"),
	_9999("9999","因不符合业务规则引发的失败")
	;

	private Test (String code,String lable){
		this.code = code;
		this.lable = lable;
	}
	private String code;
	private String lable;
	public String getLabel() {
		return lable;
	}

	public String getCode() {
		return code;
	}
}
	
