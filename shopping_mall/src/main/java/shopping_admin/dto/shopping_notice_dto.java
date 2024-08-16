package shopping_admin.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class shopping_notice_dto {
	private int idx;
	private String notify;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeFile;
	private String noticeText;
	private Timestamp createDate;
}
