package aixac.doit.justdoit.tables;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class TB_CAG003M {

    @Id
    @Column(name = "PRLST_IDFY_NO")
    private Long PRLST_IDFY_NO;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "RPRS_PRLST_IDFY_NO")
    private TB_CAG002M TB_CAG002M;

    //대표품목 대분류 명
    @Column(length = 10, nullable = false)
    private String rprsPrlstLrclMn;

    //대표품목 대분류 한글명
    @Column(length = 20, nullable = false)
    private String rprsPrlstLrclKoreMn;

    //대표품목 대분류 코드
    @Column(length = 4, nullable = false)
    private String rprsPrlstLrclCd;

    //대표품목 중분류 명
    @Column(length = 10, nullable = false)
    private String rprsPrlstMdclMn;

    //대표품목 중분류 한글명
    @Column(length = 20, nullable = false)
    private String rprsPrlstMdclKoreMn;

    //대표품목 중분류 코드
    @Column(length = 4, nullable = false)
    private String rprsPrlstMdclCd;

    //표시색상
    @Column(length = 10, nullable = false)
    private String scrnClr;

    //품목 명
    @Column(length = 200, nullable = false)
    private String prlstMn;

    /***** Date *****/
    //대표품목 정의 일자
    @Column(nullable = false)
    private Date rprsPrlstDefnDt;

    //대표품목 적용 시작 일자
    @Column(nullable = false)
    private Date rprsPrlstAplyStrtDt;

    //대표품목 적용 종료 일자
    @Column(nullable = false)
    private Date rprsPrlstAplyEndDt;


}