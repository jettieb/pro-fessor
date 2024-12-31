@file:Suppress("DEPRECATION")

package com.example.pro_fessor.sampledata

import java.time.LocalDate

object MissionData {
    private val missionDataList = mutableListOf(
        MissionDto("모닝 과일 먹기", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 7), false, 1, 20),
        MissionDto("논문 초안 작성하기", LocalDate.of(2024, 1, 2), LocalDate.of(2024, 1, 10), false, 2, 50),
        MissionDto("매일 영어 논문 읽기", LocalDate.of(2024, 1, 3), LocalDate.of(2024, 1, 17), true, 3, 42),
        MissionDto("코딩 연습 하루 2시간", LocalDate.of(2024, 1, 4), LocalDate.of(2024, 1, 14), true, 4, 72),
        MissionDto("실험 데이터 정리", LocalDate.of(2024, 1, 5), LocalDate.of(2024, 1, 8), true, 5, 15),
        MissionDto("운동 30분 하기", LocalDate.of(2024, 1, 6), LocalDate.of(2024, 1, 13), false, 6, 75),
        MissionDto("세미나 발표 준비", LocalDate.of(2024, 1, 7), LocalDate.of(2024, 1, 15), false, 7, 0),
        MissionDto("주간 학습 보고서 작성", LocalDate.of(2024, 1, 8), LocalDate.of(2024, 1, 12), true, 8, 100),
        MissionDto("코드 리뷰 참여", LocalDate.of(2024, 1, 9), LocalDate.of(2024, 1, 16), false, 9, 100),
        MissionDto("주말 프로젝트 진행", LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 20), true, 10, 42),
        MissionDto("신규 연구 아이디어 작성", LocalDate.of(2024, 1, 11), LocalDate.of(2024, 1, 18), false, 1, 74),
        MissionDto("분석 결과 발표 자료 만들기", LocalDate.of(2024, 1, 12), LocalDate.of(2024, 1, 19), false, 2, 92),
        MissionDto("모의 실험 진행", LocalDate.of(2024, 1, 13), LocalDate.of(2024, 1, 21), false, 3, 100),
        MissionDto("스터디 그룹 참여", LocalDate.of(2024, 1, 14), LocalDate.of(2024, 1, 22), true, 4, 100),
        MissionDto("소논문 리뷰 작성", LocalDate.of(2024, 1, 15), LocalDate.of(2024, 1, 23), false, 5, 42),
        MissionDto("도서 요약 발표 준비", LocalDate.of(2024, 1, 16), LocalDate.of(2024, 1, 24), true, 6, 50),
        MissionDto("학기말 프로젝트 계획 세우기", LocalDate.of(2024, 1, 17), LocalDate.of(2024, 1, 25), true, 7, 50),
        MissionDto("매일 코드 디버깅 연습", LocalDate.of(2024, 1, 18), LocalDate.of(2024, 1, 26), false, 8, 25),
        MissionDto("연구실 청소하기", LocalDate.of(2024, 1, 19), LocalDate.of(2024, 1, 27), false, 9, 50),
        MissionDto("매일 연구 로그 작성", LocalDate.of(2024, 1, 20), LocalDate.of(2024, 1, 30), true, 10, 75)
    )

    fun getMissionDataList(): List<MissionDto> {
        return missionDataList
    }

    fun addMissionItem(newMissionItem: MissionDto) {
        missionDataList.add(newMissionItem)
    }
}
