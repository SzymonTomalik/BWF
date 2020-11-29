package pl.coderslab.BWF.controller;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.bind.annotation.RestController;import pl.coderslab.BWF.entity.SoccerMatchDto;import pl.coderslab.BWF.services.MatchService;import java.util.List;@RestControllerpublic class MatchController {    private final String token="a882976391e54e198bdb9ace56e68e95";    private final MatchService matchService;    public MatchController(MatchService matchService) {        this.matchService = matchService;    }    @GetMapping("/match")    @ResponseBody    public List<SoccerMatchDto> getMatches() {        return matchService.getMatches();    }}