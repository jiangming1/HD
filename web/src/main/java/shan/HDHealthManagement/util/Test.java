package shan.HDHealthManagement.util;

public enum Test {
	_0000("0000","����"),
	_1001("1001","����δ�г�������ԭ���ʧ��"),
	_1002("1002","�ظ��ı��ı�ʶ"),
	_1003("1003","ͨѶ����ͷ��ʽ����"),
	_1004("1004","ͨѶҵ�����ʽ����"),
	_1005("1005","����ǩ����֤ʧ��"),
	_1006("1006","��Կ��һ��"),
	_5001("5001","ԭ���ײ�����"),
	_9900("9900","�����Զ������"),
	_9999("9999","�򲻷���ҵ�����������ʧ��")
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
	
