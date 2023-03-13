package com.example.coco_spring.Service.AfterSaleServices;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;


import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class ASSService implements ICRUDService<AfterSaleServices, Long> , IASSService {
    AfterSaleServicesRepository afterSaleServicesRepository;
    StoreRepository storeRepository;
    CustomerFeedbackRepository feedbackRepository;

    @Override
    public List<AfterSaleServices> findAll() {

        return afterSaleServicesRepository.findAll();
    }

    @Override
    public AfterSaleServices retrieveItem(Long idItem) {

        return afterSaleServicesRepository.findById(idItem).get();
    }

    @Override
    public AfterSaleServices add(AfterSaleServices ass1) {

        return afterSaleServicesRepository.save(ass1);
    }

    @Override
    public void delete(Long serviceId) {

        afterSaleServicesRepository.deleteById(serviceId);
    }

    @Override
    public AfterSaleServices update(AfterSaleServices afterSaleServices) {
        return afterSaleServicesRepository.save(afterSaleServices);
    }

    public void assignerSAVaStores() {

    }

    public List<String> getMostCommonWords(int limit) {
        List<CustomerFeedback> feedbacks = feedbackRepository.findAll();
        Map<String, Integer> wordCounts = new HashMap<>();

        for (CustomerFeedback feedback : feedbacks) {
            String comment = feedback.getComment();
            if (comment != null) {
                String[] words = comment.toLowerCase().split(" ");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCounts.merge(word, 1, Integer::sum);
                    }
                }
            }
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordCounts.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<String> result = new ArrayList<>();
        for (int i = 0; i < limit && i < sortedEntries.size(); i++) {
            result.add(sortedEntries.get(i).getKey());
        }

        return result;
    }

    public String getBestAfterSaleService() {
        List<AfterSaleServices> afterSaleServices = afterSaleServicesRepository.findAll();
        Map<AfterSaleServices, List<Integer>> scoresByAfterSaleService = new HashMap<>();
        for (AfterSaleServices afterSaleService : afterSaleServices) {
            List<CustomerFeedback> feedbacks = afterSaleService.getCustomerFeedbacks();
            List<Integer> scores = new ArrayList<>();
            for (CustomerFeedback feedback : feedbacks) {
                scores.add(feedback.getScore());
            }
            scoresByAfterSaleService.put(afterSaleService, scores);
        }
        double bestScore = -1;
        AfterSaleServices bestAfterSaleService = null;
        for (Map.Entry<AfterSaleServices, List<Integer>> entry : scoresByAfterSaleService.entrySet()) {
            AfterSaleServices afterSaleService = entry.getKey();
            List<Integer> scores = entry.getValue();
            double meanScore = scores.stream().mapToInt(Integer::intValue).average().orElse(0);
            if (meanScore > bestScore) {
                bestScore = meanScore;
                bestAfterSaleService = afterSaleService;
            }
        }
        return bestAfterSaleService.getStore().getStoreName();
    }
}








