import com.example.pro_fessor.sampledata.CVDto

object CVData {
    fun getCVDataList(): List<CVDto> {
        return listOf(
            CVDto(
                memberId = 1,
                edu = "경기고등학교 졸업\n서울대학교 졸업\n- 기계공학과 주전공\n- 산업디자인과 복수전공\n- 4.20/4.5 GPA",
                experience = "삼성전자, 인턴\n- 제품 설계 보조\n- CAD 소프트웨어 사용",
                qualification = "석사"
            , studentID = "20192342"),
            CVDto(
                memberId = 2,
                edu = "서울과학기술고등학교 졸업\nKAIST 졸업\n- 전기전자공학과 주전공\n- 인공지능과 복수전공\n- 3.85/4.5 GPA",
                experience = "KAIST 연구실, 연구 보조\n- 데이터 수집 및 분석\n- 실험 도구 준비",
                qualification = "인턴"
            , studentID = "20240123"),
            CVDto(
                memberId = 3,
                edu = "부산외국어고등학교 졸업\n연세대학교 졸업\n- 컴퓨터공학과 주전공\n- 경제학과 복수전공\n- 4.30/4.5 GPA",
                experience = "학생 프로젝트, 팀 리더\n- 웹 애플리케이션 개발\n- 팀원 관리 및 일정 조율",
                qualification = "인턴"
            , studentID = "20230143"),
            CVDto(
                memberId = 4,
                edu = "대전고등학교 졸업\nPOSTECH 졸업\n- 화학공학과 주전공\n- 경영학과 복수전공\n- 3.90/4.5 GPA",
                experience = "POSTECH 연구소, 실험 보조\n- 실험 자료 기록\n- 실험 장비 세팅",
                qualification = "석사"
            , studentID = "20200605"),
            CVDto(
                memberId = 5,
                edu = "수원고등학교 졸업\n서울대학교 졸업\n- 물리학과 주전공\n- 수학과 복수전공\n- 4.00/4.5 GPA",
                experience = "네이버, 인턴\n- 데이터 분석 보조\n- 데이터 정리 및 리포트 작성",
                qualification = "인턴"
            , studentID = "20220231"),
            CVDto(
                memberId = 6,
                edu = "청주고등학교 졸업\n고려대학교 졸업\n- 생명과학과 주전공\n- 의학과 복수전공\n- 4.10/4.5 GPA",
                experience = "학생 프로젝트, 연구원\n- 실험 결과 분석\n- 연구 자료 정리",
                qualification = "인턴"
            , studentID = "20210348"),
            CVDto(
                memberId = 7,
                edu = "인천고등학교 졸업\n경북대학교 졸업\n- 기계공학과 주전공\n- 전기공학과 복수전공\n- 3.75/4.5 GPA",
                experience = "LG전자, 인턴\n- 고객 서비스 개선 프로젝트 참여\n- 설문조사 및 분석",
                qualification = "석사"
            , studentID = "20200404"),
            CVDto(
                memberId = 8,
                edu = "대구외국어고등학교 졸업\n한양대학교 졸업\n- 토목공학과 주전공\n- 환경공학과 복수전공\n- 4.25/4.5 GPA",
                experience = "학생 프로젝트, 팀원\n- 환경 공학 관련 연구\n- 자료 분석 및 발표",
                qualification = "인턴"
            , studentID = "20220222"),
            CVDto(
                memberId = 9,
                edu = "서울과학고등학교 졸업\nKAIST 졸업\n- 소프트웨어공학과 주전공\n- 인공지능과 복수전공\n- 4.50/4.5 GPA",
                experience = "KAIST 연구실, 연구 보조\n- 머신러닝 데이터셋 준비\n- 코드 테스트 및 디버깅",
                qualification = "인턴"
            , studentID = "20220223"),
            CVDto(
                memberId = 10,
                edu = "경기외국어고등학교 졸업\n성균관대학교 졸업\n- 전자공학과 주전공\n- 산업공학과 복수전공\n- 4.00/4.5 GPA",
                experience = "학생 프로젝트, 팀 리더\n- 전자 회로 설계\n- 팀원 조율 및 실험 관리",
                qualification = "석사"
            , studentID = "20180234"),
            CVDto(
                memberId = 11,
                edu = "대전외국어고등학교 졸업\n이화여자대학교 졸업\n- 영어영문학과 주전공\n- 미디어학과 복수전공\n- 3.95/4.5 GPA",
                experience = "학생 단체, 마케팅 팀\n- 소셜 미디어 관리\n- 캠페인 결과 분석\n- 생산 공정 분석 보조\n- 전자 회로 설계\n- 머신러닝 데이터셋 준비\n" +
                        "- 코드 테스트 및 디버깅",
                qualification = "박사"
            , studentID = "20140834"),
            CVDto(
                memberId = 12,
                edu = "광주과학고등학교 졸업\n경기대학교 졸업\n- 화학공학과 주전공\n- 환경학과 복수전공\n- 3.80/4.5 GPA",
                experience = "현대자동차, 인턴\n- 생산 공정 분석 보조\n- 실험 데이터 정리",
                qualification = "인턴"
            , studentID = "20240133"),
            CVDto(
                memberId = 13,
                edu = "서울국제고등학교 졸업\n중앙대학교 졸업\n- 경영학과 주전공\n- 경제학과 복수전공\n- 4.10/4.5 GPA",
                experience = "학생회, 경제학 팀\n- 캠퍼스 경제 분석\n- 데이터 수집 및 리포트 작성",
                qualification = "석사"
            , studentID = "20170894"),
            CVDto(
                memberId = 14,
                edu = "광주고등학교 졸업\n부산대학교 졸업\n- 환경공학과 주전공\n- 생물공학과 복수전공\n- 3.90/4.5 GPA",
                experience = "부산대학교 연구소, 연구 보조\n- 환경 데이터 분석\n- 실험 장비 유지보수",
                qualification = "박사"
            , studentID = "20153094"),
            CVDto(
                memberId = 15,
                edu = "대전과학고등학교 졸업\n서울대학교 졸업\n- 수학과 주전공\n- 물리학과 복수전공\n- 4.35/4.5 GPA",
                experience = "서울대학교 연구소, 조교\n- 수학 논문 분석\n- 연구 자료 관리",
                qualification = "인턴"
            , studentID = "20240934"),
            CVDto(
                memberId = 16,
                edu = "인천과학고등학교 졸업\n연세대학교 졸업\n- 화학과 주전공\n- 경영학과 복수전공\n- 4.00/4.5 GPA",
                experience = "연세대학교 연구실, 실험 보조\n- 화학 실험 데이터 수집\n- 실험 기록 관리",
                qualification = "석사"
            , studentID = "20183334"),
            CVDto(
                memberId = 17,
                edu = "대구고등학교 졸업\n경북대학교 졸업\n- 전자공학과 주전공\n- 산업공학과 복수전공\n- 4.20/4.5 GPA",
                experience = "삼성전자, 인턴\n- 제조 공정 최적화 연구 보조\n- 데이터 수집 및 보고",
                qualification = "박사"
            , studentID = "20150893"),
            CVDto(
                memberId = 18,
                edu = "광명고등학교 졸업\n중앙대학교 졸업\n- 건축공학과 주전공\n- 도시공학과 복수전공\n- 3.75/4.5 GPA",
                experience = "중앙대학교 연구소, 연구 보조\n- 건축 데이터 분석\n- 프로젝트 자료 준비",
                qualification = "인턴"
            , studentID = "20230224"),
            CVDto(
                memberId = 19,
                edu = "청주외국어고등학교 졸업\n고려대학교 졸업\n- 경제학과 주전공\n- 통계학과 복수전공\n- 4.15/4.5 GPA",
                experience = "고려대학교 학생회, 팀원\n- 경제 데이터 분석\n- 발표 자료 준비",
                qualification = "석사"
            , studentID = "20170437"),
            CVDto(
                memberId = 20,
                edu = "서울국제고등학교 졸업\n이화여자대학교 졸업\n- 국제관계학과 주전공\n- 영어영문학과 복수전공\n- 3.85/4.5 GPA",
                experience = "학생 단체, 국제회의 준비\n- 회의 자료 번역 및 관리\n- 국제 손님 응대",
                qualification = "박사"
            , studentID = "20090834"),
            CVDto(
                memberId = 21,
                edu = "부산과학고등학교 졸업\nKAIST 졸업\n- 컴퓨터공학과 주전공\n- 로봇공학과 복수전공\n- 4.50/4.5 GPA",
                experience = "KAIST 연구실, 프로그래머\n- 로봇 소프트웨어 개발\n- 코드 리뷰 및 테스트",
                qualification = "인턴"
            , studentID = "20172939"),
            CVDto(
                memberId = 22,
                edu = "대구외국어고등학교 졸업\n한양대학교 졸업\n- 전기공학과 주전공\n- 기계공학과 복수전공\n- 4.10/4.5 GPA",
                experience = "한양대학교 연구소, 연구원\n- 전기 장비 설계\n- 데이터 수집 및 분석",
                qualification = "석사"
            , studentID = "20163444"),
            CVDto(
                memberId = 23,
                edu = "광주과학고등학교 졸업\nPOSTECH 졸업\n- 생명공학과 주전공\n- 환경공학과 복수전공\n- 4.30/4.5 GPA",
                experience = "POSTECH 연구소, 실험 조교\n- 생명공학 실험 준비\n- 데이터 정리",
                qualification = "박사"
            , studentID = "20182083"),
            CVDto(
                memberId = 24,
                edu = "부산외국어고등학교 졸업\n경기대학교 졸업\n- 사회학과 주전공\n- 통계학과 복수전공\n- 4.05/4.5 GPA",
                experience = "학생회, 사회학 팀\n- 사회 데이터 분석\n- 발표 자료 준비",
                qualification = "인턴"
            , studentID = "20230434"),
            CVDto(
                memberId = 25,
                edu = "서울과학고등학교 졸업\n성균관대학교 졸업\n- 물리학과 주전공\n- 수학과 복수전공\n- 4.25/4.5 GPA",
                experience = "성균관대학교 연구소, 실험 보조\n- 물리학 데이터 분석\n- 실험 기록 관리",
                qualification = "석사"
            , studentID = "20162882"),
            CVDto(
                memberId = 26,
                edu = "경기외국어고등학교 졸업\n고려대학교 졸업\n- 경영학과 주전공\n- 경제학과 복수전공\n- 3.90/4.5 GPA",
                experience = "고려대학교 학생회, 팀장\n- 경제 데이터 분석\n- 발표 자료 준비",
                qualification = "박사"
            , studentID = "20153434"),
            CVDto(
                memberId = 27,
                edu = "대전과학고등학교 졸업\n연세대학교 졸업\n- 의학과 주전공\n- 생물학과 복수전공\n- 4.40/4.5 GPA",
                experience = "연세대학교 연구소, 조교\n- 실험 자료 관리\n- 연구 데이터 분석",
                qualification = "인턴"
            , studentID = "20240673"),
            CVDto(
                memberId = 28,
                edu = "광주고등학교 졸업\n부산대학교 졸업\n- 컴퓨터공학과 주전공\n- 정보보안과 복수전공\n- 4.15/4.5 GPA",
                experience = "부산대학교 연구소, 연구원\n- 네트워크 보안 연구\n- 데이터 수집",
                qualification = "석사"
            , studentID = "20152222"),
            CVDto(
                memberId = 29,
                edu = "대구과학고등학교 졸업\n서울대학교 졸업\n- 전산학과 주전공\n- 정보보호학과 복수전공\n- 4.35/4.5 GPA",
                experience = "서울대학교 연구실, 연구 보조\n- 암호 알고리즘 개발 지원\n- 데이터 분석 및 보고서 작성",
                qualification = "박사"
            , studentID = "20132843"),
            CVDto(
                memberId = 30,
                edu = "서울과학고등학교 졸업\nPOSTECH 졸업\n- 신소재공학과 주전공\n- 물리학과 복수전공\n- 4.20/4.5 GPA",
                experience = "POSTECH 연구소, 연구 보조\n- 신소재 개발 실험\n- 실험 데이터 분석 및 정리",
                qualification = "석사"
            , studentID = "20132342")
        )
    }
}
