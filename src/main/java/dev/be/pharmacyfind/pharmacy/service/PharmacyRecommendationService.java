package dev.be.pharmacyfind.pharmacy.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import dev.be.pharmacyfind.api.dto.DocumentDto;
import dev.be.pharmacyfind.api.dto.KakaoApiResponseDto;
import dev.be.pharmacyfind.api.service.KakaoAddressSearchService;
import dev.be.pharmacyfind.direction.entity.Direction;
import dev.be.pharmacyfind.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

	private final KakaoAddressSearchService kakaoAddressSearchService;
	private final DirectionService directionService;

	public void recommendPharmacyList(String address) {
		KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

		if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
			log.error("[PharmacyRecommendationService recommendPharmacyList fail] Input address: {}", address);
			return;
		}

		DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

		// 공공기관 약국 데이터 및 거리계산 알고리즘 이용
		List<Direction> directionList = directionService.buildDirectionList(documentDto);

		// kakao 카테고리를 이용한 장소 검색 api 이용
		//List<Direction> directionList = directionService.buildDirectionListByCategoryApi(documentDto);

		directionService.saveAll(directionList);
	}
}
