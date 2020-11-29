package pl.coderslab.BWF.webClient.matches;import lombok.extern.slf4j.Slf4j;import org.springframework.http.*;import org.springframework.stereotype.Component;import org.springframework.web.client.RestTemplate;import pl.coderslab.BWF.entity.SoccerMatchDto;import pl.coderslab.BWF.webClient.matches.dto.OpenMatchMatchDto;import pl.coderslab.BWF.webClient.matches.dto.OpenMatchesDto;import java.util.ArrayList;import java.util.Collections;import java.util.List;@Component@Slf4jpublic class MatchesClient {    private static final String URL_CL_MATCHES = "http://api.football-data.org/v2/competitions/CL/matches";    private static final String HEADER = "X-Auth-Token";    private static final String API_TOKEN = "a882976391e54e198bdb9ace56e68e95";    public List<SoccerMatchDto> getMatches() {        RestTemplate restTemplate = new RestTemplate();        HttpHeaders headers = new HttpHeaders();        headers.setContentType(MediaType.APPLICATION_JSON);        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));        headers.set(HEADER, API_TOKEN);        HttpEntity request = new HttpEntity(headers);        ResponseEntity<OpenMatchesDto> response = restTemplate.exchange(URL_CL_MATCHES, HttpMethod.GET, request, OpenMatchesDto.class);        OpenMatchesDto openMatchesDto = response.getBody();        List<SoccerMatchDto> soccerMatchDtoList = new ArrayList<>();        if (openMatchesDto != null) {            List<OpenMatchMatchDto> openMatchMatchDtoList = openMatchesDto.getMatches();            for (OpenMatchMatchDto o : openMatchMatchDtoList) {                SoccerMatchDto soccerMatchDto = SoccerMatchDto.builder()                        .id(o.getId())                        .utcDate(o.getUtcDate())                        .stage(o.getStage())                        .group(o.getGroup())                        .homeTeamId(o.getHomeTeam().getId())                        .homeTeam(o.getHomeTeam().getName())                        .homeTeamScore(o.getScore().getFullTime().getHomeTeam())                        .awayTeamId(o.getAwayTeam().getId())                        .awayTeam(o.getAwayTeam().getName())                        .awayTeamScore(o.getScore().getFullTime().getAwayTeam())                        .score(o.getScore().getFullTime().getHomeTeam() + " : " + o.getScore().getFullTime().getAwayTeam())                        .build();                soccerMatchDtoList.add(soccerMatchDto);                log.info(soccerMatchDto.toString());            }        }        return soccerMatchDtoList;    }}