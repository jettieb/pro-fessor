import com.example.pro_fessor.sampledata.CVDto

object CVData {
    fun getCVDataList(): List<CVDto> {
        return listOf(
            CVDto(
                memberId = 1,
                edu = "경기고등학교 졸업\n서울대학교 졸업\n- 기계공학과 주전공\n- 산업디자인과 복수전공\n- 4.20/4.5 GPA",
                experience = "삼성전자, 인턴\n- 제품 설계 보조\n- CAD 소프트웨어 사용",
                qualification = "석사"
            ),
            CVDto(
                memberId = 2,
                edu = "서울과학기술고등학교 졸업\nKAIST 졸업\n- 전기전자공학과 주전공\n- 인공지능과 복수전공\n- 3.85/4.5 GPA",
                experience = "KAIST 연구실, 연구 보조\n- 데이터 수집 및 분석\n- 실험 도구 준비",
                qualification = "박사"
            ),
            CVDto(
                memberId = 3,
                edu = "부산외국어고등학교 졸업\n연세대학교 졸업\n- 컴퓨터공학과 주전공\n- 경제학과 복수전공\n- 4.30/4.5 GPA",
                experience = "학생 프로젝트, 팀 리더\n- 웹 애플리케이션 개발\n- 팀원 관리 및 일정 조율",
                qualification = "인턴"
            ),
            CVDto(
                memberId = 4,
                edu = "대전고등학교 졸업\nPOSTECH 졸업\n- 화학공학과 주전공\n- 경영학과 복수전공\n- 3.90/4.5 GPA",
                experience = "POSTECH 연구소, 실험 보조\n- 실험 자료 기록\n- 실험 장비 세팅",
                qualification = "석사"
            ),
            CVDto(
                memberId = 5,
                edu = "수원고등학교 졸업\n서울대학교 졸업\n- 물리학과 주전공\n- 수학과 복수전공\n- 4.00/4.5 GPA",
                experience = "네이버, 인턴\n- 데이터 분석 보조\n- 데이터 정리 및 리포트 작성",
                qualification = "박사"
            ),
            CVDto(
                memberId = 6,
                edu = "청주고등학교 졸업\n고려대학교 졸업\n- 생명과학과 주전공\n- 의학과 복수전공\n- 4.10/4.5 GPA",
                experience = "학생 프로젝트, 연구원\n- 실험 결과 분석\n- 연구 자료 정리",
                qualification = "인턴"
            ),
            CVDto(
                memberId = 7,
                edu = "인천고등학교 졸업\n경북대학교 졸업\n- 기계공학과 주전공\n- 전기공학과 복수전공\n- 3.75/4.5 GPA",
                experience = "LG전자, 인턴\n- 고객 서비스 개선 프로젝트 참여\n- 설문조사 및 분석",
                qualification = "석사"
            ),
            CVDto(
                memberId = 8,
                edu = "대구외국어고등학교 졸업\n한양대학교 졸업\n- 토목공학과 주전공\n- 환경공학과 복수전공\n- 4.25/4.5 GPA",
                experience = "학생 프로젝트, 팀원\n- 환경 공학 관련 연구\n- 자료 분석 및 발표",
                qualification = "박사"
            ),
            CVDto(
                memberId = 9,
                edu = "서울과학고등학교 졸업\nKAIST 졸업\n- 소프트웨어공학과 주전공\n- 인공지능과 복수전공\n- 4.50/4.5 GPA",
                experience = "KAIST 연구실, 연구 보조\n- 머신러닝 데이터셋 준비\n- 코드 테스트 및 디버깅",
                qualification = "인턴"
            ),
            CVDto(
                memberId = 10,
                edu = "경기외국어고등학교 졸업\n성균관대학교 졸업\n- 전자공학과 주전공\n- 산업공학과 복수전공\n- 4.00/4.5 GPA",
                experience = "학생 프로젝트, 팀 리더\n- 전자 회로 설계\n- 팀원 조율 및 실험 관리",
                qualification = "석사"
            ),
            CVDto(
                memberId = 11,
                edu = "대전외국어고등학교 졸업\n이화여자대학교 졸업\n- 영어영문학과 주전공\n- 미디어학과 복수전공\n- 3.95/4.5 GPA",
                experience = "학생 단체, 마케팅 팀\n- 소셜 미디어 관리\n- 캠페인 결과 분석",
                qualification = "박사"
            ),
            CVDto(
                memberId = 12,
                edu = "광주과학고등학교 졸업\n경기대학교 졸업\n- 화학공학과 주전공\n- 환경학과 복수전공\n- 3.80/4.5 GPA",
                experience = "현대자동차, 인턴\n- 생산 공정 분석 보조\n- 실험 데이터 정리",
                qualification = "인턴"
            ),
            CVDto(
                memberId = 13,
                edu = "서울국제고등학교 졸업\n중앙대학교 졸업\n- 경영학과 주전공\n- 경제학과 복수전공\n- 4.10/4.5 GPA",
                experience = "학생회, 경제학 팀\n- 캠퍼스 경제 분석\n- 데이터 수집 및 리포트 작성",
                qualification = "석사"
            )
        )
    }
}