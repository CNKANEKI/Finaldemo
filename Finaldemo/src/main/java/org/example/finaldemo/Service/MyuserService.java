package org.example.finaldemo.Service;

import org.example.finaldemo.Entity.Myuser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MyuserService {
    // 获取所有
    List<Myuser> getAllMyuser();
    // 新增或更新
    void saveMyuser(Myuser myuser);
    // 获取指定ID的
    Myuser getMyuserById(long id);
    // 删除指定ID的
    void deleteMyuserById(long id);
    // 查找
    List<Myuser> findByName(String name);
    // 更新
    void updateMyuser(Myuser myuser);
    // 分页
    Page<Myuser> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}