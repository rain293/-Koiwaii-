package kk.soft.co.jp.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kk.soft.co.jp.sys.model.MessageInfoMstModel;

@Repository
@Mapper
public interface MessageInfoMstMapper {
	MessageInfoMstModel findbyid(int id);

	MessageInfoMstModel findbyname(int name);

	List<MessageInfoMstModel> selectAll();

	MessageInfoMstModel checkEmail(MessageInfoMstModel messageInfoMstModel);

	int insert(MessageInfoMstModel messageInfoMstModel);

	MessageInfoMstModel checkSignin(MessageInfoMstModel messageInfoMstModel);

}
