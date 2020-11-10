package mybatis.dao;

import mybatis.entity.Role;

import java.util.List;

public interface RoleDao {

    void insertRole(Role role);

    void deleteRole(Role role);

    void updateRole(Role role);

    Role getRole(Long id);

    List<Role> findRoles(String roleName);

}
