package com.doublev2v.integralmall.find.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doublev2v.foundation.core.dto.AbstractDtoPagingService;
import com.doublev2v.foundation.core.model.PagedList;
import com.doublev2v.integralmall.find.converter.IntegralOrderVoConverter;
import com.doublev2v.integralmall.find.entity.IntegralOrderVo;
import com.doublev2v.integralmall.order.IntegralOrder;
import com.doublev2v.integralmall.order.IntegralOrderService;
import com.doublev2v.integralmall.userinfo.UserInfo;
@Service
public class IntegralOrderVoService extends AbstractDtoPagingService<IntegralOrder,IntegralOrderVo,String>{
	@Autowired
	private IntegralOrderVoConverter voConverter;
	@Autowired
	private IntegralOrderService service;
	
	public PagedList<IntegralOrderVo> getList(Integer pageNo,Integer pageSize,UserInfo user){
		Pageable page=new PageRequest(pageNo-1, pageSize);
		Page<IntegralOrder> list = service.findPage(page, user, null, null, null, null, null);
		List<IntegralOrderVo> listDetail=new ArrayList<IntegralOrderVo>();
		listDetail.addAll(voConverter.convertSimples(list.getContent()));//转换dto
		Page<IntegralOrderVo> result=new PageImpl<IntegralOrderVo>(listDetail,page,list.getTotalElements());
		return new PagedList<IntegralOrderVo>(result);
	}
}