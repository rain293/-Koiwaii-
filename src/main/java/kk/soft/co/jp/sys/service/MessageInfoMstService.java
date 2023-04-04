package kk.soft.co.jp.sys.service;

import kk.soft.co.jp.sys.model.MessageInfoMstModel;

public interface MessageInfoMstService {
	
	public Object selectAll();
	public MessageInfoMstModel checkEmail(MessageInfoMstModel messageInfoMstModel);
    public Object insert(MessageInfoMstModel messageInfoMstModel);
    public  MessageInfoMstModel checkSignin(MessageInfoMstModel messageInfoMstModel);
}
