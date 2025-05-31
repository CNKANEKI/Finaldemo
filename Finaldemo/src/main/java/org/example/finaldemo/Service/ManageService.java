package org.example.finaldemo.Service;
import org.example.finaldemo.Entity.Manage;
import java.util.List;

import org.springframework.data.domain.Page;

public interface ManageService {

    // 新增或更新一样商品
    void saveStu(Manage stu);

    // 获取指定ID的商品
    Manage getStuById(long id);

    // 删除指定ID的商品
    void deleteStuById(long id);

    // 查找某样商品
    List<Manage> findByName(String name);

    // 分页
    Page<Manage> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
