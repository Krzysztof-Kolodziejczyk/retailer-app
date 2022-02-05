package com.retailer.controller;

import com.retailer.exceptions.InvalidCustomerException;
import com.retailer.service.RewardService;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.DateTimeException;
import java.time.Month;

@RestController
@RequestMapping("reward")
@Validated
public class RewardController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/{firstName}/{lastName}/monthlyPoints")
    public int getTotalPoints(@PathVariable String firstName,
                              @PathVariable String lastName,
                              @RequestParam int year,
                              @RequestParam @Min(1) @Max(12) int month) throws InvalidCustomerException {
        logger.info("getting Reward for " + Month.of(month) + " " + year + " for " + firstName + " " + lastName);
        return rewardService.calculateMonthlyReward(firstName, lastName, year, month);
    }

    @GetMapping("/{firstName}/{lastName}/totalPoints")
    public int getTotalPoints(@PathVariable String firstName,
                              @PathVariable String lastName) throws InvalidCustomerException {
        logger.info("getting total Reward for " + firstName + " " + lastName);
        return rewardService.calculateTotalReward(firstName, lastName);
    }
}
