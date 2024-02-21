package ra.demoprojectmodul4.service;

import ra.demoprojectmodul4.model.Role;

import java.util.List;

public interface IRoleService {
    void  save(Role role);
 Role findRolesByName(String name);
}
