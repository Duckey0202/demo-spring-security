package com.mt.demo.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * BaseRepository
 *
 * @author MT.LUO
 * 2018/1/24 11:29
 * @Description:
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>, Serializable {
    List<T> findByDeleted(boolean deleted);

    //@Modifying
    @Query("select t from #{#entityName} t  where t.id = ?1")
    List<T> testSelect(ID id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update #{#entityName} t set t.gmtUpdate=CURRENT_TIMESTAMP, t.deleted=1 where t.id = ?1")
    int setDeletedTrue(ID id);

}
