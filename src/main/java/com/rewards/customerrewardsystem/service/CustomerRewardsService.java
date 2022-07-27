package com.rewards.customerrewardsystem.service;

import com.rewards.customerrewardsystem.entyties.CustomerTransaction;
import com.rewards.customerrewardsystem.exceptions.NoCustomerFoundException;
import com.rewards.customerrewardsystem.exceptions.NoRewardsFoundException;
import com.rewards.customerrewardsystem.lamdautils.CusRewardsUtil;
import com.rewards.customerrewardsystem.repository.CustomerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerRewardsService {
    @Autowired
    CustomerTransactionRepository customerTransactionRepository;

    /**
     * @param id
     * @return Map<String, Double>
     * @throws NoCustomerFoundException
     * @throws NoRewardsFoundException
     */
    public Map<String, Double> getCustomerRewardsById(Long id) throws NoCustomerFoundException, NoRewardsFoundException {
        /**
         * Get the List of Customer Transactions from CUSTOMER_TRANSACTION table
         */
        List<CustomerTransaction> allByCustomerId = customerTransactionRepository.findAllCustomerTxnByLast3months(id);
        if (!allByCustomerId.isEmpty()) {

            /**
             * Filter and Group the Customer Transactions By Month
             */
            Map<String, List<CustomerTransaction>> collect = CusRewardsUtil.getTxnGroupByMonth(allByCustomerId.stream());
            /**
             * Calculate the rewards By month
             * CusRewardsUtil contains Lambda Expression
             */
            Map<String, Double> totalRewardsByMonth = collect.entrySet().stream()
                    .collect(Collectors
                            .toMap(Map.Entry::getKey, v -> v.getValue()
                                    .stream()
                                    .filter(CusRewardsUtil.txnAmountGt50$AndLtEq100$.or(CusRewardsUtil.txnAmountGt100$))
                                    .map(CusRewardsUtil.calculateRewards)
                                    .reduce(CusRewardsUtil.minDoubleValue, CusRewardsUtil.getRewardsForGt50$AndLtEq100$)));

            if (totalRewardsByMonth.isEmpty()) {
                throw new NoRewardsFoundException();
            } else {
                totalRewardsByMonth.put("TOTAL", CusRewardsUtil.calculateTotalRewards(totalRewardsByMonth.values().stream()));
                return totalRewardsByMonth;
            }
        } else {
            throw new NoCustomerFoundException();

        }


    }
}
