package pl.coderslab.BWF.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.BWF.entity.BetGroup;
import pl.coderslab.BWF.repository.BetGroupRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BetGroupService {
    private final BetGroupRepository betGroupRepository;

    public List<BetGroup> showBetGroups() {
        return betGroupRepository.findAll();
    }

    public void addBetGroup(BetGroup group) {
        betGroupRepository.save(group);
    }

    public void updateBetGroup(BetGroup group) {
        betGroupRepository.save(group);
    }

    public BetGroup getBetGroup(Long id) {
        return betGroupRepository.findById(id).get();
    }

    public void deleteBetGroup(Long id) {
        betGroupRepository.deleteById(id);
    }

    public List<BetGroup> findUserGroups(Long userId){
        return betGroupRepository.findBetGroupsByUserGroupAccountList(userId);
    }
}
