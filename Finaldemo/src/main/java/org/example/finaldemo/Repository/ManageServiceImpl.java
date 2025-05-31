package org.example.finaldemo.Repository;
import org.example.finaldemo.Entity.Manage;
import org.example.finaldemo.Service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {
    @Autowired
    private ManageRepository manageRepository;

    @Override
    public void saveStu(Manage stu){
        this.manageRepository.save(stu);
    }

    @Override
    public Manage getStuById(long id){
        // 调用数据访问层查找指定ID的商品，返回Optional==（选择)对象
        Optional<Manage> optional = manageRepository.findById(id);
        Manage stu = null;
        // 如果存在指定id的商品
        if (optional.isPresent()) {
            // 从Optional对象中获取商品对象
            stu = optional.get();
        } else {
            // 否则抛出运行时异常
            throw new RuntimeException("找不到学生ID :: " + id);
        }
        return stu;
    }

    // 删除指定id的商品
    @Override
    public void deleteStuById(long id){
        this.manageRepository.deleteById(id);
    }

    @Override
    public Page<Manage> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        //设置排序参数，升序ASC/降序DESC
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        //根据页号/每页记录数/排序依据返回某指定页面数据。
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        //使用jpa提供的findAll函数，传入pageable参数。
        return this.manageRepository.findAll(pageable);
    }

    @Override
    public List<Manage> findByName(String name) {
        List<Manage> stuNames = manageRepository.findAllByName(name);

        return stuNames;
    }


}
