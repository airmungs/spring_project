package shopping_admin.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class shopping_cate_dto {
	private int idx; // 카테고리 고유 번호
    private String categoryCode; // 카테고리 코드
    private String lgMenuCode; // 대메뉴 코드
    private String cateName; // 대메뉴명
    private String useYn; // 사용 유/무
    private Timestamp createdAt; // 생성 시간
    private Timestamp updatedAt; // 수정 시간
}
