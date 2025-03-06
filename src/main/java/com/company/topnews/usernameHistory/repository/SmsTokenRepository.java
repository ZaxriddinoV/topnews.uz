package com.company.topnews.usernameHistory.repository;

import com.company.topnews.usernameHistory.entiy.SmsTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsTokenRepository extends JpaRepository<SmsTokenEntity,Integer> {
}
