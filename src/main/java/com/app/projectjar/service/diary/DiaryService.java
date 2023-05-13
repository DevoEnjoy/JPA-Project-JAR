package com.app.projectjar.service.diary;

import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.file.diary.DiaryFile;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.member.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

public interface DiaryService {
    
//    목록
    public Slice<DiaryDTO> getOpenDiaryList(String sort, Pageable pageable);
//    상세보기
    public DiaryDTO getDiary(Long diaryId);

    default DiaryDTO toDiaryDTO(Diary diary) {
        return DiaryDTO.builder()
                .boardContent(diary.getBoardContent())
                .boardTitle(diary.getBoardTitle())
                .diaryStatus(diary.getDiaryStatus())
                .fileDTOS(FileToDTO(diary.getDiaryFiles()))
                .id(diary.getId())
                .likeCount(diary.getDiaryLikeCount())
                .memberDTO(toMemberDTO(diary.getMember()))
                .replyCount(diary.getDiaryReplyCount())
                .build();
    }

    default MemberDTO toMemberDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .memberStatus(member.getMemberStatus())
                .build();
    }

    default List<FileDTO> FileToDTO(List<DiaryFile> diaryFiles) {
        List<FileDTO> diaryFileList = new ArrayList<>();
        diaryFiles.forEach(
                diaryFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(diaryFile.getId())
                            .fileOriginalName(diaryFile.getFileOriginalName())
                            .fileUuid(diaryFile.getFileUuid())
                            .filePath(diaryFile.getFilePath())
                            .fileType(diaryFile.getFileType())
                            .build();
                    diaryFileList.add(fileDTO);
                }
        );
        return diaryFileList;
    }

}
