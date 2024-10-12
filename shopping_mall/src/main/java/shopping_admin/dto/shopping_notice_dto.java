package shopping_admin.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class shopping_notice_dto {
    private int idx;  // 공지사항의 고유 ID
    private int notify;
    private String noticeTitle;
    private String noticeWriter;
    private String noticeText;
    private Timestamp createdDate;  // 공지 작성일자
    private int viewCount;     // 조회수
}
