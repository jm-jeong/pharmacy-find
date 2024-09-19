package dev.be.pharmacyfind.api.service;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import dev.be.pharmacyfind.api.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoAddressSearchService {

	private final RestTemplate restTemplate;
	private final KakaoUriBuilderService kakaoUriBuilderService;

	@Value("${KAKAO_REST_API_KEY}")
	private String kakaoRestApiKey;

	public KakaoApiResponseDto requestAddressSearch(String address) {
		if (ObjectUtils.isEmpty(address)) return null;

		URI uri = kakaoUriBuilderService.buildUriByAddressSearch(address);

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
		HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

		//kakao api request
		return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody();
	}
}
