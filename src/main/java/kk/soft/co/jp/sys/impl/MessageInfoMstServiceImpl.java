package kk.soft.co.jp.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kk.soft.co.jp.sys.mapper.MessageInfoMstMapper;
import kk.soft.co.jp.sys.model.MessageInfoMstModel;
import kk.soft.co.jp.sys.service.MessageInfoMstService;

@Service
public class MessageInfoMstServiceImpl implements MessageInfoMstService {

    @Resource
    private MessageInfoMstMapper mapper;
    @Override
	public Object selectAll() {
		return mapper.selectAll();
	}
	
	@Override
	public Object insert(MessageInfoMstModel messageInfoMst) {
		return mapper.insert(messageInfoMst);
	}
	@Override
	public MessageInfoMstModel checkAccount(MessageInfoMstModel messageInfoMst) {
		return (MessageInfoMstModel) mapper.checkAccount(messageInfoMst);
	}

	@Override
	public MessageInfoMstModel checkLogin(MessageInfoMstModel messageInfoMst) {
		 return (MessageInfoMstModel) mapper.checkLogin(messageInfoMst);
    /**
     * 新規作成
     *
     * @param messageInfoMst
     */
  
   
    }
}
