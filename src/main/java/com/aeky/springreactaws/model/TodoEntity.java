package com.aeky.springreactaws.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data   // 클래스 멤버 변수의 getter, setter 메서드를 구현
public class TodoEntity {
    private String id;      // 오브젝트 아이디
    private String userId;  // 오브젝트 생성한 사용자의 아이디
    private String title;   // 투두 타이틀
    private boolean done;   // 투두 완료 시 true
}
