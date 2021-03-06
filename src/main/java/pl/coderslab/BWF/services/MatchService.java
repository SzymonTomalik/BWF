package pl.coderslab.BWF.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import pl.coderslab.BWF.converter.DateConverter;
import pl.coderslab.BWF.entity.SoccerMatch;
import pl.coderslab.BWF.repository.SoccerMatchRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchService {
    private final SoccerMatchRepository soccerMatchRepository;
    private final DateConverter dateConverter;


    public SoccerMatch getMatch(long id){
        return soccerMatchRepository.findById(id).get();
    }
    public List<SoccerMatch> getMatches() {
        return soccerMatchRepository.findAll();
    }

    public void updateAllMatchDataBase(List<SoccerMatch> soccerMatchList) {
        for (SoccerMatch soccerMatch : soccerMatchList) {
            long id = soccerMatch.getId();
            if (soccerMatchRepository.existsById(id)) {
                SoccerMatch repositoryMatch = soccerMatchRepository.findById(id).get();
                if (!soccerMatch.equals(repositoryMatch)) {
                    soccerMatchRepository.save(soccerMatch);
                    log.info("Mecz o id " + soccerMatch.getId() + " został zaktualizowany");
                }
            } else {
                soccerMatchRepository.save(soccerMatch);
                log.info("Mecz o id " + soccerMatch.getId() + " został dodany do bazy danych");
            }
        }
        log.info("Baza meczy została zaktualizowana");
    }
    public String getBetMatchDate(SoccerMatch soccerMatch){
        DateTimeFormatter fmt= DateTimeFormat.forPattern("yyyy-MM-dd, HH:mm");
        DateTime dateTime = fmt.parseDateTime(soccerMatch.getMatchDate());
        DateTime betMatchDate=dateTime.minusHours(2);
        return betMatchDate.toString(fmt);
    }

    public List<SoccerMatch> getActualMatchesToBet() {
        List<SoccerMatch> soccerMatchList=getMatches();
        List<SoccerMatch> matchToBetList=new ArrayList<>();
        for (SoccerMatch match:soccerMatchList){
            if(canBeBet(match)) {
                matchToBetList.add(match);
            }
        }
        return matchToBetList;
    }

    public boolean canBeBet(SoccerMatch soccerMatch) {
        return dateConverter.isEarlier(DateConverter.getDateNow(), getBetMatchDate(soccerMatch));
    }



}
