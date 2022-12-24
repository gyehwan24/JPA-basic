package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    @Id  //pk값
    private Long id;

    @Column(name="name")  //DB에 저장할 칼럼명을 변수명과 다르게 지정해줄 수 있다
    private String username;

    private int age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username=name;
    }

}
