package dev.be.pharmacyfind.api.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.be.pharmacyfind.api.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoCategorySearchService {

	private final KakaoUriBuilderService kakaoUriBuilderService;
	private final RestTemplate restTemplate;

	private static final String PHARMACY_CATEGORY = "PM9"; //약국 카테고리

	@Value("${kakao.rest.api.key}")
	private String kakaoRestApikey;

	public KakaoApiResponseDto requestPharmacyCategorySearch(double latitude, double longitude, double radius) {

		URI uri = kakaoUriBuilderService.buildUriByCategorySearch(latitude, longitude, radius, PHARMACY_CATEGORY);

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApikey);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		return restTemplate.exchange(uri, HttpMethod.GET, entity, KakaoApiResponseDto.class).getBody();
	}
}
