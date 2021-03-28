package pl.coderslab.BWF.webClient.matches;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.BWF.model.SoccerMatchDto;
import pl.coderslab.BWF.webClient.matches.dto.OpenMatchMatchDto;
import pl.coderslab.BWF.webClient.matches.dto.OpenMatchesDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@EnableCaching
public class MatchesClient {

    @Cacheable(cacheNames = "getMatches")
    public List<SoccerMatchDto> getMatches() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<OpenMatchesDto> response = restTemplate.exchange(ApiTokens.URL_CL_MATCHES, HttpMethod.GET, request, OpenMatchesDto.class);

        OpenMatchesDto openMatchesDto = response.getBody();

        List<SoccerMatchDto> soccerMatchDtoList = new ArrayList<>();

        if (openMatchesDto != null) {
            List<OpenMatchMatchDto> openMatchMatchDtoList = openMatchesDto.getMatches();
            for (OpenMatchMatchDto o : openMatchMatchDtoList) {
                SoccerMatchDto soccerMatchDto = SoccerMatchDto.builder()
                        .id(o.getId())
                        .utcDate(o.getUtcDate())
                        .stage(o.getStage())
                        .group(o.getGroup())
                        .homeTeamId(o.getHomeTeam().getId())
                        .homeTeam(o.getHomeTeam().getName())
                        .homeTeamScore(o.getScore().getFullTime().getHomeTeam())
                        .awayTeamId(o.getAwayTeam().getId())
                        .awayTeam(o.getAwayTeam().getName())
                        .awayTeamScore(o.getScore().getFullTime().getAwayTeam())
                        .score(o.getScore().getFullTime().getHomeTeam() + " : " + o.getScore().getFullTime().getAwayTeam())
                        .build();

                soccerMatchDtoList.add(soccerMatchDto);
            }

        }
        return soccerMatchDtoList;
    }

    @org.jetbrains.annotations.NotNull
    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(ApiTokens.HEADER, ApiTokens.API_TOKEN);
        return headers;
    }
}
