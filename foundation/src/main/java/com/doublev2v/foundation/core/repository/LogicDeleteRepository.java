package com.doublev2v.foundation.core.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
/**
 * 需要逻辑删除的repository继承此接口
 * @author Administrator
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface LogicDeleteRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T,ID>{
	
	@Query("select t from #{#entityName} t where t.deleted = false")
	Iterable<T> findAll();

	@Query("select t from #{#entityName} t where t.deleted = false")
	Page<T> findAll(Pageable pageable);
	
	@Query("select t from #{#entityName} t where t.deleted = false")
	Iterable<T> findAll(Sort sort);
	
	@Query("select count(*) from #{#entityName} t where t.deleted = false")
	long count();

	@Modifying
	@Query("update #{#entityName} set deleted = true where id=?")
	void logicDelete(ID id);
	
	@Modifying
	@Query("update #{#entityName} set deleted = true where id=?#{#t.id}")
	void logicDelete(@Param("t") T t);
	
	@Modifying
	@Query("update #{#entityName} set deleted = true")
	void logicDeleteAll();
	
}
