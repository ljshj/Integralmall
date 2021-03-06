package com.doublev2v.integralmall.activity.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doublev2v.foundation.core.dto.AbstractPagingDtoService;
import com.doublev2v.foundation.core.model.PagedList;
import com.doublev2v.foundation.dics.CategoryItem;
import com.doublev2v.foundation.dics.CategoryItemService;
import com.doublev2v.integralmall.activity.converter.ActivityBranchShopConverter;
import com.doublev2v.integralmall.activity.entity.ActivityBranchShop;
import com.doublev2v.integralmall.shop.branch.BranchShop;
import com.doublev2v.integralmall.shop.branch.BranchShopService;
@Service
public class ActivityBranchShopService extends AbstractPagingDtoService<BranchShop,ActivityBranchShop,String>{
	@Autowired
	private ActivityBranchShopConverter voConverter;
	@Autowired
	private BranchShopService service;
	@Autowired
	private CategoryItemService categoryItemService;
	
	public PagedList<ActivityBranchShop> findPage(Integer pageNo,Integer pageSize,String tagId){
		Pageable page=new PageRequest(pageNo-1, pageSize);
		CategoryItem tag=null;
		if(StringUtils.isNotBlank(tagId)){
			tag=categoryItemService.findOne(tagId);
		}
		Page<BranchShop> list=service.findPage(page, null,tag);
		List<ActivityBranchShop> listDetail=
				new ArrayList<ActivityBranchShop>(voConverter.convertSimples(list.getContent()));
		Page<ActivityBranchShop> result=new PageImpl<ActivityBranchShop>(listDetail,page,list.getTotalElements());
		return new PagedList<ActivityBranchShop>(result);
	}
}
