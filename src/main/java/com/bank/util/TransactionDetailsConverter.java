package com.bank.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.bank.dto.TransactionDetailsDto;
import com.bank.entity.TransactionDetails;

@Component
public class TransactionDetailsConverter {

	public TransactionDetailsDto transactionDetailsToTransactionDetailsDtoConverter(TransactionDetails txnDetails) {
		TransactionDetailsDto txnDetailsDto = new TransactionDetailsDto();

		if (txnDetails != null) {
			BeanUtils.copyProperties(txnDetails, txnDetailsDto);
		}

		return txnDetailsDto;

	}

	public TransactionDetails transactionDetailsDtoToTransactionDetailsConverter(TransactionDetailsDto txnDetailsDto) {
		TransactionDetails txnDetails = new TransactionDetails();

		if (txnDetailsDto != null) {
			BeanUtils.copyProperties(txnDetailsDto, txnDetails);
		}

		return txnDetails;

	}
}
