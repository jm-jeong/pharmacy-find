package dev.be.pharmacyfind.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoApiResponseDto {

	@JsonProperty("meta")
	private MetaDto metaDto;

	@JsonProperty("documents")
	private List<DocumentDto> documentList;
}
