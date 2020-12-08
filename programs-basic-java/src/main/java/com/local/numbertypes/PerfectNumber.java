package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import static java.lang.Math.pow;
import static java.util.stream.Collectors.toMap;

@Slf4j
public class PerfectNumber implements INumberTypeOperation {

	// A number whose factors sum except itself, is equal to the same number
	// 1,6,28,496
	// Factors of 28 are 1, 2, 4, 7, 14
	// The sum of factors of 28 = 1+2+4+7+14 = 28

	@Override
	public boolean execute(long input, long order) {
		int sumOfFactors = 0;
		for (int i = 1; i <= Math.sqrt(input); i++) {
			if (input % i == 0) {
				sumOfFactors += i;
				if ((i != 1) && ((input / i) != Math.sqrt(input))) {
					sumOfFactors += (input / i);
				}
			}
		}

		var status = (sumOfFactors == input);
		log(status, input);
		return status;
	}

	private void log(boolean status, long input) {
		if (status) {
			log.debug("An Perfect Number {}", input);
		} else {
			log.debug("Not an Perfect Number {}", input);
		}
	}

	/**
	 * Take any number N and it is to be covert into product of prime numbers
	 * (Prime factorization) i.e
	 * 
	 * N = A^p x B^q x C^r here A, B , C are prime numbers and p,q,and r were
	 * respective powers of that prime numbers.
	 * 
	 * Total numbers of factors for N= (p + 1)(q +1)(r +1)
	 * 
	 * Sum of all factors of N = (p + 1)(q +1)(r +1)
	 * 
	 * Product of all factors of N = N ^(Total no. of factors/2)
	 * 
	 * @param input
	 * @return
	 */
	protected List<Integer> primeFactors(int input) {
		int number = input;
		List<Integer> factors = new ArrayList<>();
		for (int i = 2; i <= number / i; i++) {
			while (number % i == 0) {
				factors.add(i);
				number /= i;
			}
		}
		if (number > 1) {
			factors.add(number);
		}
		Map<Integer, Integer> countMap = factors.stream()
				.collect(toMap(Function.identity(), v -> 1, Integer::sum));
		var sumOfAllFactors = 1l;
		var numberOfFactors = 1l;

		for (Entry<Integer, Integer> entry : countMap.entrySet()) {
			sumOfAllFactors = sumOfAllFactors * calculateSum(entry.getKey(), entry.getValue());
			numberOfFactors = numberOfFactors * (entry.getKey() + 1);
		}
		var productAllFactors = (long) pow(input, numberOfFactors / 2f);
		log.info("Sum of all factors {}", sumOfAllFactors);
		log.info("No of factors {}", numberOfFactors);
		log.info("Product of all factors {}", productAllFactors);
		return factors;
	}

	long calculateSum(Integer primeNumber, Integer powerFactor) {
		return (long) (pow(primeNumber, powerFactor + 1f) - 1) / (primeNumber - 1);
	}
}