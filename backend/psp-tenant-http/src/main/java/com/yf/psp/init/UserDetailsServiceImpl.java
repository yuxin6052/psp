package com.yf.psp.init;

import com.yf.psp.db.postgres.csm.CsmTenant;
import com.yf.psp.db.postgres.csm.dao.CsmTenantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.yf.psp.db.postgres.csm.CsmUser;
import com.yf.psp.db.postgres.csm.dao.CsmUserDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CsmUserDao csmUserDao;
    @Autowired
    private CsmTenantDao csmTenantDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        CsmUser user = csmUserDao.getByUsername(s);
        if(null == user){
            throw new UsernameNotFoundException("user not exists");
        }
        CsmTenant ct = csmTenantDao.getByTenantId(user.getTenantId());
        User u = new User(user);
        u.setTenantName(ct.getTenantName());
        return u;
    }

}