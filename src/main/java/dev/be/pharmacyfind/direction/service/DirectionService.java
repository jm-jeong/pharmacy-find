package dev.be.pharmacyfind.direction.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.be.pharmacyfind.api.dto.DocumentDto;
import dev.be.pharmacyfind.direction.entity.Direction;
import dev.be.pharmacyfind.pharmacy.dto.PharmacyDto;
import dev.be.pharmacyfind.pharmacy.service.PharmacySearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DirectionService {

	private final PharmacySearchService pharmacySearchService;

	public List<Direction> buildDirectionList(DocumentDto documentDto) {

		// 약국 데이터 조회
		List<PharmacyDto> pharmacyDtos = pharmacySearchService.searchPharmacyDtoList();

		// 거리계산 알고리즘을 이용하여, 고객과 약국 사이의 거리를 계산하고 sort

		// 다음 강의에서 진행
		return Collections.emptyList();
	}

	// Haversine formula
	private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		lat1 = Math.toRadians(lat1);
		lon1 = Math.toRadians(lon1);
		lat2 = Math.toRadians(lat2);
		lon2 = Math.toRadians(lon2);

		double earthRadius = 6371; //Kilometers
		return earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
	}
}
