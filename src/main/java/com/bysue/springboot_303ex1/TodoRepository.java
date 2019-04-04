package com.bysue.springboot_303ex1;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long>{
}
