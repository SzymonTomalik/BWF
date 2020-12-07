package pl.coderslab.BWF.services;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Service;import pl.coderslab.BWF.entity.SoccerMatch;import pl.coderslab.BWF.repository.SoccerMatchRepository;import java.util.List;@Service@Slf4j@RequiredArgsConstructorpublic class MatchService {    private final SoccerMatchRepository soccerMatchRepository;    public List<SoccerMatch> getMatches() {        return soccerMatchRepository.findAll();    }    public void updateAllMatchDataBase(List<SoccerMatch> soccerMatchList) {        for (SoccerMatch soccerMatch : soccerMatchList) {            long id = soccerMatch.getId();            if (soccerMatchRepository.existsById(id)) {                SoccerMatch repositoryMatch = soccerMatchRepository.findById(id).get();                if (!soccerMatch.equals(repositoryMatch)) {                    soccerMatchRepository.save(soccerMatch);                    log.info("Mecz o id " + soccerMatch.getId() + " został zaktualizowany");                }            } else {                soccerMatchRepository.save(soccerMatch);                log.info("Mecz o id " + soccerMatch.getId() + " został dodany do bazy danych");            }        }        log.info("Baza meczy została zaktualizowana");    }}