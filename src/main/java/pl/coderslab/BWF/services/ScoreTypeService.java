package pl.coderslab.BWF.services;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Service;import pl.coderslab.BWF.entity.ScoreType;import pl.coderslab.BWF.entity.SoccerMatch;import pl.coderslab.BWF.repository.ScoreTypeRepository;@Service@Slf4j@RequiredArgsConstructorpublic class ScoreTypeService {    private final ScoreTypeRepository scoreTypeRepository;    private void countBetPoint(ScoreType scoreType, SoccerMatch soccerMatch) {        if (scoreType.getTypedHomeTeamResult() == soccerMatch.getHomeTeamScore() && scoreType.getTypedAwayTeamResult() == soccerMatch.getAwayTeamScore()) {            scoreType.setPoints(scoreType.getPoints()+3);        }        if(scoreType.getTypedHomeTeamResult()>scoreType.getTypedAwayTeamResult() && soccerMatch.getHomeTeamScore()>soccerMatch.getAwayTeamScore()){            scoreType.setPoints(scoreType.getPoints()+1);        }        if(scoreType.getTypedHomeTeamResult()<scoreType.getTypedAwayTeamResult() && soccerMatch.getHomeTeamScore()<soccerMatch.getAwayTeamScore()){            scoreType.setPoints(scoreType.getPoints()+1);        }        if(scoreType.getTypedHomeTeamResult()==scoreType.getTypedAwayTeamResult() && soccerMatch.getHomeTeamScore()==soccerMatch.getAwayTeamScore() && scoreType.getTypedHomeTeamResult()!=soccerMatch.getHomeTeamScore()){            scoreType.setPoints(scoreType.getPoints()+1);        }    }}