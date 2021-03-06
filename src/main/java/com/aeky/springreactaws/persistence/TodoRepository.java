package com.aeky.springreactaws.persistence;

import com.aeky.springreactaws.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> { // <테이블에 매핑될 엔티티 클래스, 엔티티 기본 키 타입>
    List<TodoEntity> findByUserId(String userId);

//    @Query("select * from Todo t where t.userId = ?1")
//    List<TodoEntity> findByUserId(String userId);
}
