package aixac.doit.justdoit.tables;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class TB_CAG004M {

    @Id @GeneratedValue
    private Long id;

    @JoinColumn(name = "AI_IDFY_RSLT_NO")
    @OneToOne
    private TB_CAG005M FK;

    //적하목록관리번호
    @Column(length = 11, nullable = false)
    private String MRN;
    //Master BL 일련번호
    @Column(length = 4, nullable = false)
    private String MSN;
    //House BL 일련번호
    @Column(length = 4, nullable = false)
    private String HSN;
    //Master BL
    @Column(length = 20, nullable = false)
    private String MBL;
    //BL
    @Column(length = 20, nullable = false)
    private String BL;
    //가로 이미지 경로
    @Column(length = 100, nullable = false)
    private String WDTH_IMGE_PATH;
    //세로 이미지 경로
    @Column(length = 100, nullable = false)
    private String VRTC_IMGE_PATH;
    //가로 이미지 정제 JSON
    @Column(length = 200, nullable = false)
    private String WDTH_IMGE_RFIN_JSON;
    //세로 이미지 정제 JSON
    @Column(length = 200, nullable = false)
    private String VRTC_IMGE_RFIN_JSON;
    //가로 이미지 정제 건수
    @Column(nullable = false)
    private long WDTH_IMGE_RFIN_CNT;
    //세로 이미지 정제 건수
    @Column(nullable = false)
    private long VRTC_IMGE_RFIN_CNT;
    //라벨 등록 시간
    @Column(nullable = false)
    private Date LBL_RGSR_HR;
    //라벨 수정 시간
    @Column(nullable = false)
    private Date LBL_ALTT_HR;
    //라벨 사용자
    @Column(length = 20, nullable = false)
    private String LBL_USER;
    //라벨 여부
    @Column(length = 2, nullable = false)
    private String LBL_YN;
    //AI 엔진 학습 반영 여부
    @Column(length = 2, nullable = false)
    private String AI_ENGN_LERN_RFLC_YN;
    //AI 엔진 학습 반영 일자
    @Column(nullable = false)
    private Date AI_ENGN_LERN_RFLC_HR;


}
