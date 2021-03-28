package pl.coderslab.BWF.model;

import javax.validation.constraints.NotNull;
import java.util.List;


public class TypeMatchForm {
    @NotNull
    private Long betGroupId = 0L;
    private List<TypeMatch> typeMatchList;

    public List<TypeMatch> getTypeMatchList() {
        return typeMatchList;
    }
    public void setTypeMatchList(List<TypeMatch>typeMatchList) {
        this.typeMatchList=typeMatchList;
    }

    public Long getBetGroupId() {
        return betGroupId;
    }

    public void setBetGroupId(Long betGroupId) {
        this.betGroupId = betGroupId;
    }

    @Override
    public String toString() {
        return "TypeMatchForm{" +
                "betGroupId=" + betGroupId +
                ", typeMatchList=" + typeMatchList +
                '}';
    }
}
