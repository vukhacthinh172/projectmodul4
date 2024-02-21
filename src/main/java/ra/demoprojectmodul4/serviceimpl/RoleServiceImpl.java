package ra.demoprojectmodul4.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.demoprojectmodul4.model.Role;
import ra.demoprojectmodul4.reponsitory.RoleReponsitory;
import ra.demoprojectmodul4.service.IRoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private  final RoleReponsitory roleReponsitory ;
    @Override
    public void save(Role role) {
         roleReponsitory.save(role);
    }

    @Override
    public Role findRolesByName(String name) {
        return roleReponsitory.findRolesByName(name);
    }
}
