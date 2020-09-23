package aixac.doit.justdoit.tables;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MapsId;
import java.io.Serializable;

@Embeddable
@Getter
class TB_CAG001M_PK implements Serializable {

    //적하목록 관리번호
    @MapsId
    @Column(name = "MRN")
    private String MRN;

    //Master BL 일련번호
    @MapsId
    @Column(length = 4)
    private String MSN;

    //House BL 일련번호
    @MapsId
    @Column(length = 4)
    private String HSN;

    //Master BL
    @MapsId
    @Column(length = 20)
    private String MBL;

    //House BL
    @MapsId
    @Column(length = 20)
    private String HBL;
}
