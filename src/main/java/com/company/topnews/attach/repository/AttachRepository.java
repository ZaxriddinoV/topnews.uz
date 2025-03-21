package com.company.topnews.attach.repository;


import com.company.topnews.attach.entity.AttachEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachRepository extends JpaRepository<AttachEntity,String> , PagingAndSortingRepository<AttachEntity,String> {


    @Query("FROM AttachEntity a ")
    Page<AttachEntity> allAttach(Pageable pageable);

}
