package shopping_admin.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class shopping_cate_dto {
	private int idx; // 카테고리 고유 번호
    private String category_code; // 카테고리 코드
    private String lg_menu_code; // 대메뉴 코드
    private String cate_name; // 대메뉴명
    private String use_yn; // 사용 유/무
    private Timestamp created_at; // 생성 시간
    private Timestamp updated_at; // 수정 시간
}
