package hellojpa.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    @Id  //pk값
    @GeneratedValue
    private Long id;

    @Column(name="USER_NAME")  //DB에 저장할 칼럼명을 변수명과 다르게 지정해줄 수 있다
    private String name;

    private String city;

    private String street;

    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
