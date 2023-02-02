package me.kapsin.jpa.my;
//
//// MyRepository.java
//
//import lombok.Setter;
//
//import java.util.HashMap;
//
//@Setter
//public class MyRepository {
//
//    private HashMap<Long, String> dataTable; // DB 테이블을 의미
//
//    public String find(Long id) {
//        return dataTable.getOrDefault(id, "");
//    }
//
//    public Long save(String data) {
//        var newId = Long.valueOf(dataTable.size());
//        this.dataTable.put(newId, data);
//        return newId;
//    }
//}


import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface MyRepository<User, ID extends Serializable> extends Repository<User, ID> {

  User save(User entity);

  Optional<User> findByUsername(String username);

}