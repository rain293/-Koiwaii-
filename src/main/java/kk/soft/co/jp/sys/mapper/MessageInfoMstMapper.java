package kk.soft.co.jp.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kk.soft.co.jp.sys.model.MessageInfoMstModel;


@Repository
@Mapper
public interface MessageInfoMstMapper {
	List<MessageInfoMstMapper> selectAll();
	MessageInfoMstMapper checkAccount(MessageInfoMstModel messageInfoMst);
	int insert(MessageInfoMstModel messageInfoMst);
	MessageInfoMstMapper checkLogin(MessageInfoMstModel messageInfoMst);
    
}
