package com.developer.admin.query.controller;

import com.developer.admin.query.dto.RecruitApplyDetailReadDTO;
import com.developer.admin.query.dto.RecruitApplyListReadDTO;
import com.developer.admin.query.dto.ReportDetailReadDTO;
import com.developer.admin.query.dto.ReportListReadDTO;
import com.developer.admin.query.service.AdminQueryService;
import com.developer.user.query.dto.ResponseUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminQueryController {

    private final AdminQueryService adminQueryService;

    // ========= 사용자 =========
    // 사용자 상태별 User 객체 찾기
    @GetMapping("/user/status")
    public ResponseEntity<List<ResponseUserDTO>> findUserStatus(@RequestParam(name = "userStatus") String userStatus){

        log.info("사용자 상태별 조회 {}", userStatus);

        List<ResponseUserDTO> allByUserStatus = adminQueryService.findAllByUserStatus(userStatus);

        return ResponseEntity.ok(allByUserStatus);
    }

    // 신고 10회 이상 User 확인하기
    @GetMapping("/user/warning")
    public ResponseEntity<List<ResponseUserDTO>> findUserWarning(HttpServletRequest httpServletRequest){

        List<ResponseUserDTO> allByUserWarning = adminQueryService.findAllByUserWarning();

        log.info("사용자 경고 누적 10회 초과 조회 {}", allByUserWarning);

        return ResponseEntity.ok(allByUserWarning);
    }

    // 채용공고 등록 신청 내역 목록 조회
    @GetMapping("/recruit/apply-list")
    public ResponseEntity<List<RecruitApplyListReadDTO>> readApplyRecruitList(@RequestParam(defaultValue = "1") Integer page) {
        List<RecruitApplyListReadDTO> recruitApplyList = adminQueryService.readRecruitApplyList(page);
        return ResponseEntity.ok(recruitApplyList);
    }

    // 채용공고 등록 신청 상세내역 조회
    @GetMapping("/recruit/apply-detail/{recruitCode}")
    public ResponseEntity<RecruitApplyDetailReadDTO> readApplyDetail(@PathVariable Long recruitCode) {
        RecruitApplyDetailReadDTO recruitApplyDetailReadDTO = adminQueryService.readRecruitApplyDetailById(recruitCode);

        return ResponseEntity.ok(recruitApplyDetailReadDTO);
    }

    // 신고 목록 조회하기
    @GetMapping("/report/list")
    public ResponseEntity<List<ReportListReadDTO>> readReportList(@RequestParam(defaultValue = "1") Integer page) {
        List<ReportListReadDTO> reportListReadDTO = adminQueryService.readReportList(page);
        return ResponseEntity.ok(reportListReadDTO);
    }

    // 신고 상세 내용 조회하기
    @GetMapping("/report/detail/{repoCode}")
    public ResponseEntity<ReportDetailReadDTO> readReportDetail(@PathVariable Long repoCode) {
        ReportDetailReadDTO reportDetailReadDTO  = adminQueryService.readReportDetailById(repoCode);
        return ResponseEntity.ok(reportDetailReadDTO);
    }
}
