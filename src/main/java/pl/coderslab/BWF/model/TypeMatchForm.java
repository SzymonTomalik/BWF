package pl.coderslab.BWF.model;import lombok.Getter;import lombok.Setter;import javax.validation.constraints.NotNull;import java.util.List;@Getter@Setterpublic class TypeMatchForm {    @NotNull    private Long betGroupId = 0L;    private List<TypeMatch> typeMatchList;    public TypeMatchForm() {    }}