package dev.be.pharmacyfind.pharmacy.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.ObjectUtils;

import dev.be.pharmacyfind.pharmacy.entity.Pharmacy;
import dev.be.pharmacyfind.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRepositoryService {
	private final PharmacyRepository pharmacyRepository;

	// self invocation test
	@Transactional
	public void bar(List<Pharmacy> pharmacyList) {
		log.info("bar CurrentTransactionName: "+ TransactionSynchronizationManager.getCurrentTransactionName());
		foo(pharmacyList);
	}

	// self invocation test
	@Transactional
	public void foo(List<Pharmacy> pharmacyList) {
		log.info("foo CurrentTransactionName: "+ TransactionSynchronizationManager.getCurrentTransactionName());
		pharmacyList.forEach(pharmacy -> {
			pharmacyRepository.save(pharmacy);
			throw new RuntimeException("error"); // 예외 발생
		});
	}

	@Transactional(readOnly = true)
	public void startReadOnlyMethod(Long id) {
		pharmacyRepository.findById(id).ifPresent(pharmacy -> {
			pharmacy.changePharmacyAddress("서울 특별시 광진구");
		});
	}

	@Transactional
	public void updateAddress(Long id, String address) {
		Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

		if (ObjectUtils.isEmpty(entity)) {
			log.error("[PharmacyRepositoryService updateAddress] not found id: {}", id);
			return;
		}

		entity.changePharmacyAddress(address);
	}

	// for test
	public void updateAddressWithoutTransaction(Long id, String address) {
		Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

		if(Objects.isNull(entity)) {
			log.error("[PharmacyRepositoryService updateAddress] not found id: {}", id);
			return;
		}

		entity.changePharmacyAddress(address);
	}

	@Transactional(readOnly = true)
	public List<Pharmacy> findAll() {
		return pharmacyRepository.findAll();
	}
}
