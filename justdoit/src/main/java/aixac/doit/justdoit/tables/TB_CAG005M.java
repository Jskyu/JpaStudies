package aixac.doit.justdoit.tables;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Entity
@Getter
public class TB_CAG005M {
    //AI 화물 식별 결과 번호
    @Id
    private Long AI_IDFY_RSLT_NO;

    //적하목록관리번호
    @Column(length = 11)
    private String MRN;
    //Master BL 일련번호
    @Column(length = 4)
    private String MSN;
    //House BL 일련번호
    @Column(length = 4)
    private String HSN;
    //Master BL
    @Column(length = 20)
    private String MBL;
    //BL
    @Column(length = 20)
    private String BL;
    //X-Ray 코드
    @Column(length = 6)
    private String XRAY_CD;
    //가로 이미지 명
    @Column(length = 100)
    private String WDTH_IMGE_MN;
    //세로 이미지 명
    @Column(length = 100)
    private String VRTC_IMGE_MN;
    //가로 이미지 AI 식별 수
    private long WDTH_IMGE_AI_IDFY_QTY;
    //세로 이미지 AI 식별 수
    private long VRTC_IMGE_AI_IDFY_QTY;
    //허위 신고 여부
    @Column(length = 1)
    private String FLSH_DCLR_YN;
    //AI 엔진 버전
    @Column(length = 6)
    private String AI_ENGN_VER;
    //판독자 ID
    @Column(length = 10)
    private String ITPN_ID;
    //판독자 검사 지정 사유
    @Column(length = 2)
    private String ITPN_INSC_APNT_RSN;
    //판독자 검사 지정 등록 시간
    private Date ITPN_INSC_APNT_RGSR_HR;
    //개장 검사자 ID
    @Column(length = 10)
    private String OPBG_ISPR_ID;
    //개장 검사 결과
    @Column(length = 2)
    private String OPBG_INSC_RSLT;
    //개장 검사 결과 등록 시간
    private Date OPBG_INSC_RSLT_RGSR_HR;
    //AI 대표 품목 대분류 명
    @Column(length = 100)
    private String AI_RPRS_PRLST_LRCL_MN;
    //AI 대표 품목 대분류 한글 명
    @Column(length = 100)
    private String AI_RPRS_PRLST_LRCL_KORE_MN;
    //대표 품목 대분류 명
    @Column(length = 100)
    private String RPRS_PRLST_LRCL_MN;
    //대표 품목 대분류 한글 명
    @Column(length = 100)
    private String RPRS_PRLST_LRCL_KORE_MN;
    //품목 명
    @Column(length = 200)
    private String PRLST_MN;
    //AI 식별 일자
    private Date AI_IDFY_DT;
    //AI 식별 시간
    private Time AI_IDFY_HR;

}
