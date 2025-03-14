package com.company.topnews.usernameHistory.service;



import com.company.topnews.exceptionHandler.AppBadException;
import com.company.topnews.usernameHistory.entiy.EmailHistoryEntity;
import com.company.topnews.usernameHistory.repository.EmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailHistoryService {

    @Autowired
    private EmailHistoryRepository emailHistoryRepository;


    public void addEmailHistory(String email, String message, LocalDateTime createdDate) {
        List<EmailHistoryEntity> byEmail = emailHistoryRepository.findByEmail(email);

        if (byEmail.size() >= 4) {
            LocalDateTime date0 = byEmail.get(byEmail.size() - 1).getCreatedData();
            LocalDateTime date3 = byEmail.get(byEmail.size() - 4).getCreatedData();
            Duration duration = Duration.between(date0, date3);

            if (duration.abs().getSeconds() <= 60) {
                throw new AppBadException("The number of requests increased");
            }
        }
        EmailHistoryEntity entity = new EmailHistoryEntity();
        entity.setEmail(email);
        entity.setMessage(message);
        entity.setCreatedData(createdDate);
        emailHistoryRepository.save(entity);
    }

    public List<EmailHistoryEntity> getByEmail(String email) {
        List<EmailHistoryEntity> byEmail = emailHistoryRepository.findByEmail(email);
        if (byEmail == null) {
            throw new AppBadException("Email not found");
        }
        return byEmail;
    }
    public List<EmailHistoryEntity> getAllGiven(LocalDate date) {
        LocalDateTime now = date.atStartOfDay();
        List<EmailHistoryEntity> allByCreatedDate = emailHistoryRepository.findAllByCreatedDate(now);
        if (allByCreatedDate.isEmpty()) {
            throw new AppBadException("No email history found");
        }
        return allByCreatedDate;

    }
}
