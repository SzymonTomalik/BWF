package pl.coderslab.BWF.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.BWF.converter.DateConverter;
import pl.coderslab.BWF.entity.ScoreType;
import pl.coderslab.BWF.entity.SoccerMatch;
import pl.coderslab.BWF.repository.ScoreTypeRepository;
import pl.coderslab.BWF.repository.SoccerMatchRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScoreTypeService {
    private final ScoreTypeRepository scoreTypeRepository;
    private final SoccerMatchRepository soccerMatchRepository;
    private final MatchService matchService;


    public void addScoreTypeToBase(ScoreType scoreType){
        scoreTypeRepository.save(scoreType);
        log.info(scoreType.getId()+" ScoreType was added to base");
    }


    public void countAndUpdateScoreTypePoints(ScoreType scoreType) {
        SoccerMatch soccerMatch = matchService.getMatch(scoreType.getSoccerMatchId());
        if (scoreType.getTypedHomeTeamResult() == soccerMatch.getHomeTeamScore() && scoreType.getTypedAwayTeamResult() == soccerMatch.getAwayTeamScore()) {
            scoreType.setPoints(3);
            addScoreTypeToBase(scoreType);
            log.info("scoreType "+scoreType.getId()+" points was updated to 3");
        } else if(soccerMatch.getHomeTeamScore()>soccerMatch.getAwayTeamScore()&&scoreType.getTypedHomeTeamResult()>scoreType.getTypedAwayTeamResult()&&soccerMatch.getHomeTeamScore()!=scoreType.getTypedHomeTeamResult()&&soccerMatch.getAwayTeamScore()!=scoreType.getTypedAwayTeamResult()){
            scoreType.setPoints(1);
            addScoreTypeToBase(scoreType);
            log.info("scoreType "+scoreType.getId()+" points was updated to 1");
        } else if(soccerMatch.getHomeTeamScore()>soccerMatch.getAwayTeamScore()&&scoreType.getTypedHomeTeamResult()>scoreType.getTypedAwayTeamResult()&&soccerMatch.getHomeTeamScore()==scoreType.getTypedHomeTeamResult()&&soccerMatch.getAwayTeamScore()!=scoreType.getTypedAwayTeamResult()){
            scoreType.setPoints(1);
            addScoreTypeToBase(scoreType);
            log.info("scoreType "+scoreType.getId()+" points was updated to 1");
        } else if(soccerMatch.getHomeTeamScore()>soccerMatch.getAwayTeamScore()&&scoreType.getTypedHomeTeamResult()>scoreType.getTypedAwayTeamResult()&&soccerMatch.getHomeTeamScore()!=scoreType.getTypedHomeTeamResult()&&soccerMatch.getAwayTeamScore()==scoreType.getTypedAwayTeamResult()){
            scoreType.setPoints(1);
            addScoreTypeToBase(scoreType);
            log.info("scoreType "+scoreType.getId()+" points was updated to 1");
        } else if(soccerMatch.getHomeTeamScore()<soccerMatch.getAwayTeamScore()&&scoreType.getTypedHomeTeamResult()<scoreType.getTypedAwayTeamResult()&&soccerMatch.getHomeTeamScore()!=scoreType.getTypedHomeTeamResult()&&soccerMatch.getAwayTeamScore()!=scoreType.getTypedAwayTeamResult()){
            scoreType.setPoints(1);
            addScoreTypeToBase(scoreType);
            log.info("scoreType "+scoreType.getId()+" points was updated to 1");
        } else if(soccerMatch.getHomeTeamScore()<soccerMatch.getAwayTeamScore()&&scoreType.getTypedHomeTeamResult()<scoreType.getTypedAwayTeamResult()&&soccerMatch.getHomeTeamScore()==scoreType.getTypedHomeTeamResult()&&soccerMatch.getAwayTeamScore()!=scoreType.getTypedAwayTeamResult()){
            scoreType.setPoints(1);
            addScoreTypeToBase(scoreType);
            log.info("scoreType "+scoreType.getId()+" points was updated to 1");
        } else if(soccerMatch.getHomeTeamScore()<soccerMatch.getAwayTeamScore()&&scoreType.getTypedHomeTeamResult()<scoreType.getTypedAwayTeamResult()&&soccerMatch.getHomeTeamScore()!=scoreType.getTypedHomeTeamResult()&&soccerMatch.getAwayTeamScore()==scoreType.getTypedAwayTeamResult()){
            scoreType.setPoints(1);
            addScoreTypeToBase(scoreType);
            log.info("scoreType "+scoreType.getId()+" points was updated to 1");
        } else if(soccerMatch.getHomeTeamScore()==soccerMatch.getAwayTeamScore()&&scoreType.getTypedHomeTeamResult()==scoreType.getTypedAwayTeamResult()&&soccerMatch.getHomeTeamScore()!=scoreType.getTypedHomeTeamResult()&&soccerMatch.getAwayTeamScore()!=scoreType.getTypedAwayTeamResult()){
            scoreType.setPoints(1);
            addScoreTypeToBase(scoreType);
            log.info("scoreType "+scoreType.getId()+" points was updated to 1");
        } else if(soccerMatch.getHomeTeamScore()==scoreType.getTypedHomeTeamResult()&&soccerMatch.getAwayTeamScore()!=scoreType.getTypedAwayTeamResult()){
            scoreType.setPoints(0);
            addScoreTypeToBase(scoreType);
            log.info("scoreType "+scoreType.getId()+" points was updated to 0");
        } else if(soccerMatch.getHomeTeamScore()!=scoreType.getTypedHomeTeamResult()&&soccerMatch.getAwayTeamScore()==scoreType.getTypedAwayTeamResult()){
            scoreType.setPoints(0);
            addScoreTypeToBase(scoreType);
            log.info("scoreType "+scoreType.getId()+" points was updated to 0");
        }else {
            scoreType.setPoints(0);
            addScoreTypeToBase(scoreType);
            log.info("scoreType "+scoreType.getId()+" points was updated to 0");
        }
    }

    public boolean isTypeChangeable(ScoreType scoreType) {
        SoccerMatch soccerMatch= soccerMatchRepository.getOne(scoreType.getSoccerMatchId());
        return matchService.canBeBet(soccerMatch) && scoreType.getChangedDate().equals(scoreType.getCreatedDate());
    }

    public boolean isAlreadyTyped(long userId, long betGroupId,ScoreType scoreType){
        return scoreTypeRepository.findAllByUserIdAndUserBetGroup(userId, betGroupId).contains(scoreType);
    }
    public ScoreType getScoreType(Long userAccountId, Long soccerMatchId) {
        return scoreTypeRepository.findByUserAccountIdAndSoccerMatchId(userAccountId, soccerMatchId);
    }
    public ScoreType getScoreType(Long scoreTypeId) {
        return scoreTypeRepository.findById(scoreTypeId).get();
    }

    public void editScoreType(ScoreType newType){
        ScoreType scoreTypeToEdit = getScoreType(newType.getUserAccount().getId(), newType.getSoccerMatch().getId());
        scoreTypeToEdit.setTypedHomeTeamResult(newType.getTypedHomeTeamResult());
        scoreTypeToEdit.setTypedAwayTeamResult(newType.getTypedAwayTeamResult());
        scoreTypeToEdit.setChangedDate(DateConverter.getDateNow());
        addScoreTypeToBase(scoreTypeToEdit);
        log.info("Type " +scoreTypeToEdit.toString() + " was edited in the base");
    }
    public List<ScoreType> findAllGroupTypes(Long betGroupId) {
        return scoreTypeRepository.findAllByBetGroupId(betGroupId);
    }
    public void updateAllGroupPointScoreTypes(Long betGroupId) {
        List<ScoreType> groupTypes= findAllGroupTypes(betGroupId);
        for (ScoreType scoreType :groupTypes) {
            countAndUpdateScoreTypePoints(scoreType);
        }
    }

    public List<ScoreType> findAllUserTypesInGroup(Long userId,Long betGroupId) {
        return scoreTypeRepository.findAllByUserIdAndBetGroupId(userId,betGroupId);
    }


}
