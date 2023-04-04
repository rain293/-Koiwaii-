package kk.soft.co.jp.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kk.soft.co.jp.sys.mapper.MessageInfoMstMapper;
import kk.soft.co.jp.sys.model.MessageInfoMstModel;
import kk.soft.co.jp.sys.service.MessageInfoMstService;

@Service
public class MessageInfoMstModelImpl implements MessageInfoMstService {

	@Resource
	private MessageInfoMstMapper messageInfoMstmapper;

	@Override
	public Object selectAll() {
		return messageInfoMstmapper.selectAll();
	}

	@Override
	public Object insert(MessageInfoMstModel messageInfoMstModel) {
		return messageInfoMstmapper.insert(messageInfoMstModel);
	}

	@Override
	public MessageInfoMstModel checkEmail(MessageInfoMstModel messageInfoMstModel) {
		return messageInfoMstmapper.checkEmail(messageInfoMstModel);
	}

	@Override
	public MessageInfoMstModel checkSignin(MessageInfoMstModel messageInfoMstModel) {
		return messageInfoMstmapper.checkSignin(messageInfoMstModel);

	}

}
