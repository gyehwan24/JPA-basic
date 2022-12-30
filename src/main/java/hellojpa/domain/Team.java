package hellojpa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    //mappedBy가 있으면 연관관계의 주인이 아니라는 뜻, 주인을 지정하는 것, 조회만 가능
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    //연관관계 편의 메소드. 연관관계 양쪽에 값을 넣어주는 메소드
    public void addMember(Member member) {
        members.add(member);
        member.setTeam(this);
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
