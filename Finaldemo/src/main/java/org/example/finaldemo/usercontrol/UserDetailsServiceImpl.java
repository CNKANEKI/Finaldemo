package org.example.finaldemo.usercontrol;
import org.example.finaldemo.Entity.Myuser;
import org.example.finaldemo.Repository.MyuserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    MyuserRepository service; // 用户信息CRUD服务类
    public UserDetailsServiceImpl(MyuserRepository service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Myuser usr = service.findByUsername(username); // 根据username查询用户
        if (usr != null) {
            // return new User(usr.getUsername(),usr.getPassword(), AuthorityUtils.NO_AUTHORITIES);
            List<GrantedAuthority> grantedAuthorities = new ArrayList();

            String roles=usr.getRole();
            String[] roles_list=roles.split(",");  //数据库中角色都是用逗号,分隔的。
            for(String one:roles_list)
            {
                System.out.println("UserDetailsServiceImpl:one:"+one);
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(one);
                grantedAuthorities.add(grantedAuthority);
            }
            return new User(usr.getUsername(),usr.getPassword(),grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("当前用户不存在！");
        }
    }
}