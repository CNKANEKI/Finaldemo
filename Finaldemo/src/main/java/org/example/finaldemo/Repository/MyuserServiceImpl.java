package org.example.finaldemo.Repository;

import org.example.finaldemo.Entity.Myuser;
import org.example.finaldemo.Service.MyuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyuserServiceImpl implements MyuserService {
    @Autowired
    private MyuserRepository myuserRepository;

    @Override
    public List<Myuser> getAllMyuser(){
        return myuserRepository.findAll();
    }
    @Override
    public void saveMyuser(Myuser myuser){
        this.myuserRepository.save(myuser);
    }
    @Override
    public Myuser getMyuserById(long id){
        // 调用数据访问层查找指定ID的 ，返回Optional==（选择)对象
        Optional<Myuser> optional = myuserRepository.findById(id);
        Myuser myuser = null;
        // 如果存在指定id的
        if (optional.isPresent()) {
            // 从Optional对象中获取
            myuser = optional.get();
        } else {
            // 否则抛出运行时异常
            throw new RuntimeException("找不到 ID :: " + id);
        }
        return myuser;
    }

    // 删除指定id的
    @Override
    public void deleteMyuserById(long id){
        this.myuserRepository.deleteById(id);
    }
    @Override
    public Page<Myuser> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        //设置排序参数，升序ASC/降序DESC？
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        //根据页号/每页记录数/排序依据返回某指定页面数据。
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);


        //使用jpa提供的findAll函数，传入pageable参数。
        return this.myuserRepository.findAll(pageable);
    }
    @Override
    public List<Myuser> findByName(String name) {
        List<Myuser> myuserNames = myuserRepository.findAllByUsername(name);


        return myuserNames;
    }
    @Override
    public void updateMyuser(Myuser user) {
        myuserRepository.save(user);
    }
}
