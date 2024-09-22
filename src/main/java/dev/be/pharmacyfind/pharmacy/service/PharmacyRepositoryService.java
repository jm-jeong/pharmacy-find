package dev.be.pharmacyfind.pharmacy.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

}
