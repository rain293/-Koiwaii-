package kk.soft.co.jp.sys.service;

import kk.soft.co.jp.sys.model.MessageInfoMstModel;

public interface MessageInfoMstService {

	public Object selectAll();
	public MessageInfoMstModel checkAccount(MessageInfoMstModel messageInfoMst);

	
    public Object insert(MessageInfoMstModel messageInfoMst);
    public  MessageInfoMstModel checkLogin(MessageInfoMstModel messageInfoMst);
}
