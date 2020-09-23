package aixac.doit.justdoit.tables;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
public class TB_CAG001M {

    /**
     * PK
     **/
    @EmbeddedId
    TB_CAG001M_PK tb_cag001M_id;

    //터미널 코드
    @Column(length = 8)
    private String TRMN_CD;

    //반입 년도
    @Column(length = 4)
    private String BRNG_YY;

    //반입 생성 번호
    @Column(length = 10)
    private String BRNG_CRTN_NO;

    //분할 차수
    @Column(length = 10)
    private String DVDE_DGCNT;

    //반입 일자
    @Column(length = 8)
    private String BRNG_DT;

    //반입 시간
    private Date BRNG_HR;

    //반입 유형 코드
    @Column(length = 4)
    private String BRNG_PCD;

    //XRAY검사 여부
    @Column(length = 1)
    private String XRAY_INSC_YN;

    //데이터 생성 위치
    @Column(length = 100)
    private String DATA_CRTN_LOCT;

    //반입 상태
    @Column(length = 1)
    private String BRNG_STTS;

    //반출 상태
    @Column(length = 1)
    private String RLSE_STTS;

    //근거 번호
    @Column(length = 20)
    private String BSS_NO;

    //화물 속성 구분 코드
    @Column(length = 4)
    private String CARG_ATRB_TPCD;

    //CS검사 지정 사유
    @Column(length = 1)
    private String CS_INSC_APNT_RSN;

    //XRAY검사 지정 사유
    @Column(length = 1)
    private String XRAY_INSC_APNT_RSN;

    //회부 코드
    @Column(length = 4)
    private String FWD_CD;

    //회부 명
    @Column(length = 100)
    private String FWD_MN;

    //자동분류 가능 여부
    @Column(length = 1)
    private String AUTO_CLSF_PSBL_YN;

    //검사 확인 완료 여부
    @Column(length = 1)
    private String INSC_CFRM_CMPL_YN;

    //개발원 코드
    @Column(length = 4)
    private String DI_CD;

    //반입 번호 년도
    @Column(length = 4)
    private String BRNG_NO_YY;

    //반입 번호 일련번호
    @Column(length = 10)
    private String BRNG_NO_SRNO;

    //입항 세관명
    @Column(length = 20)
    private String ETPR_CSTM_MN;

    //업체코드
    @Column(length = 4)
    private String ENTS_CD;

    //업체 명
    @Column(length = 20)
    private String ENTS_MN;

    //품목코드 HS2
    @Column(length = 4)
    private String PRLST_CD_HS2;

    //품목 코드
    @Column(length = 4)
    private String PRLST_CD;

    //품목 명
    @Column(length = 20)
    private String PRLST_MN;

    //선박 명
    @Column(length = 20)
    private String SHIP_MN;

    //보세운송기간 FROM
    @Column(length = 8)
    private String BNTP_PRID_FROM;

    //보세운송 기간 TO
    @Column(length = 8)
    private String BNTP_PRIT_TO;

    //보세운송 면허 번호
    @Column(length = 20)
    private String BNTP_LCNE_NO;

    //보세운송 회사 명
    @Column(length = 20)
    private String BNTP_CMP_MN;

    //포장 종류
    @Column(length = 3)
    private String PCK_KND;

    //반입사고 구분코드
    @Column(length = 4)
    private String BRNG_ACDN_TPCD;

    //반입 수량
    private long BRNG_QTY;

    //반입 중량
    private long BRNG_WGHT;

    //재고 수량
    private long INVN_QTY;

    //재고 중량
    private long INVN_WGHT;

    //승인 수량
    private long APRE_QTY;

    //승인 중량
    private long APRE_WGHT;

    //반출 수량
    private long RLSE_QTY;

    //반출 중량
    private long RLSE_WGHT;

    //장치 위치
    @Column(length = 10)
    private String DVCE_LOCT;

    //장치확인 일자
    @Column(length = 8)
    private String DVCE_CFRM_DT;

    //장치 확인자(명)
    @Column(length = 10)
    private String DVCE_CFPN;

    //체화 여부
    @Column(length = 1)
    private String OVGD_YN;

    //체화 번호
    @Column(length = 10)
    private String OVGD_NO;

    //체화 일자
    @Column(length = 8)
    private String OVGD_DT;

    //특수 화물 구분코드
    @Column(length = 4)
    private String SPCN_CARG_TPCD;

    //조업 일자
    @Column(length = 8)
    private String FSOP_DT;

    //입항 일자
    @Column(length = 8)
    private String ETPR_DT;

    //감가상각
    private long DPRC;

    //관세
    private long FRIF;

    //적재항
    @Column(length = 20)
    private String LDPR;

    //발송국
    @Column(length = 2)
    private String DPCY;

    //송하인 명
    @Column(length = 20)
    private String SHPR_MN;

    //송하인 주소
    @Column(length = 100)
    private String SHPR_ADDR;

    //수하인 명
    @Column(length = 20)
    private String CNSI_MN;

    //수하인 주소
    @Column(length = 100)
    private String CNSI_ADDR;

    //송하인 전화번호
    @Column(length = 20)
    private String SHPR_TELNO;

    //수하인 전화번호
    @Column(length = 20)
    private String CNSI_TELNO;

    //수하인 우편번호
    @Column(length = 10)
    private String CNSI_PSNO;

    //전자상거래 사이트 URL
    @Column(length = 100)
    private String ELCM_SITE_URL;

    //물품 가격
    private long CMDT_PRC;

    //물품 가격 단위
    @Column(length = 2)
    private String CMDT_PRC_UT;

    //거래 유형코드
    @Column(length = 4)
    private String DLNG_PCD;
    @Column(length = 4)
    private String USG_TPCD;  //용도 구분코드
    @Column(length = 11)
    private String CNTR_NO; //컨테이너 번호
    @Column(length = 20)
    private String HMDL_NO; //택배 번호
    @Column(length = 20)
    private String HMDL_CFS_NO;  //택배 집하장 번호
    @Column(length = 2)
    private String PTCL_MATR;  //특이 사항
    @Column(length = 8)
    private String BRNG_DATA_CRTN_DT; //반입 데이터 생성 일자
    @Column(length = 8)
    private String BRNG_DATA_LAST_ATLL_DT;  //반입 데이터 최종 수정 일자
    @Column(length = 100)
    private String RMRK;  //비고


}