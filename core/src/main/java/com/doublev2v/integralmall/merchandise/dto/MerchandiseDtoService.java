package com.doublev2v.integralmall.merchandise.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.doublev2v.foundation.core.dto.service.AbstractDtoPagingService;
import com.doublev2v.foundation.core.model.PagedList;
import com.doublev2v.integralmall.merchandise.Merchandise;
import com.doublev2v.integralmall.merchandise.MerchandiseService;
@Service
public class MerchandiseDtoService extends AbstractDtoPagingService<Merchandise,MerchandiseDto,String>{
	@Autowired
	private MerchandiseDtoConverter dtoConverter;
	@Autowired
	private MerchandiseService service;

	public PagedList<MerchandiseDto> getList(Integer pageNo,Integer pageSize,String isActual,
			String search,String orderBy,Direction seq){
		Page<Merchandise> list = service.getList(pageNo, pageSize, isActual, search, orderBy, seq);
		Page<MerchandiseDto> result=list.map(dtoConverter);
		return new PagedList<MerchandiseDto>(result);
	}
}