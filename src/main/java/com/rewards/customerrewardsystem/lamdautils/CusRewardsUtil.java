package com.rewards.customerrewardsystem.lamdautils;

import com.rewards.customerrewardsystem.entyties.CustomerTransaction;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface CusRewardsUtil {
    Double minDoubleValue = Double.valueOf(0);
    Double minAmtToCalRewards = Double.valueOf(50);
    Double max_rewards = Double.valueOf(100);
    Predicate<CustomerTransaction> txnAmountGt50$AndLtEq100$ = c -> c.getTxnAmount() > minAmtToCalRewards & c.getTxnAmount() <= max_rewards;
    Predicate<CustomerTransaction> txnAmountGt100$ = c -> c.getTxnAmount() > max_rewards;
    Function<CustomerTransaction, Double> rewardsForAmountGt50$AndLtEq100$ = c -> c.getTxnAmount() - minAmtToCalRewards;
    Function<CustomerTransaction, Double> rewardsForAmountGt100$ = c -> 2 * (c.getTxnAmount() - max_rewards)+minAmtToCalRewards;
    BinaryOperator<Double> getRewardsForGt50$AndLtEq100$ = (a, b) -> a + b;

    public static Map<String, List<CustomerTransaction>> getTxnGroupByMonth(Stream<CustomerTransaction> s) {
        return s.filter(txnAmountGt100$.or(txnAmountGt50$AndLtEq100$)).collect(Collectors.groupingBy(txn -> Month.of(txn.getTxnDate().getMonth() + 1).name(), Collectors.toList()));
    }

    Function<CustomerTransaction, Double> calculateRewards = c -> {
        if (txnAmountGt50$AndLtEq100$.test(c)) {
            return c.getTxnAmount() - 50d;
        } else {
            return rewardsForAmountGt100$.apply(c);
        }
    };

    public static Double calculateTotalRewards(Stream<Double> s) {
        return s.mapToDouble(Double::doubleValue).sum();
    }
}
