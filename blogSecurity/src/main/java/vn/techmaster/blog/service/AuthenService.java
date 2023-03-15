package vn.techmaster.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import vn.techmaster.blog.controller.request.RegisterRequest;
import vn.techmaster.blog.model.Role;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.RoleRepository;
import vn.techmaster.blog.repository.UserRepository;
import vn.techmaster.blog.security.MyUserDetailService;

@Service
public class AuthenService implements IAuthenService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MyUserDetailService myUserDetailService;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void generateUsersRoles() {
    Role roleAdmin = new Role("ADMIN");
    Role roleAuthor = new Role("AUTHOR");
    Role roleEditor = new Role("EDITOR");

    roleRepository.save(roleAdmin);
    roleRepository.save(roleAuthor);
    roleRepository.save(roleEditor);
    roleRepository.flush();

    User admin = new User();
    admin.setFullname("Admin");
    admin.setEmail("admin@gmail.com");

    admin.setPassword(passwordEncoder.encode("abc"));

    userRepository.save(admin);

    admin.addRole(roleAdmin);
    admin.addRole(roleAuthor);
    userRepository.flush();
  }

  @Override
  public List<Role> getAllRoles(){
    return roleRepository.findAll();
  }

  @Override
  public Role getRole(String name){
    Optional<Role> optionalRole=roleRepository.findByName(name);
    if(optionalRole.isPresent()){
      return optionalRole.get();
    }
    return null;
  }

  @Override
  public void updateRole(Set<Role> roles, long id){
    Optional<User> userO = userRepository.findById(id);
    if (!userO.isPresent()) {
      throw new BadRequestException("Email đã tồn tại trong hệ thống. Vui lòng sử dụng email khác.");
    }
    User user=userO.get();
    userRepository.flush();
  }

  @Override
  public List<User> getAll(){
    return userRepository.findAll();
  }

  @Override
  public User findUserById(long id){
    return userRepository.findById(id).get();
  }

  @Override
  public User register(RegisterRequest request){
    Optional<User> userO = userRepository.findByEmail(request.getEmail());
    if (userO.isPresent()) {
      throw new BadRequestException("Email đã tồn tại trong hệ thống. Vui lòng sử dụng email khác.");
    }
    User user=new User();
    user.setFullname(request.getFullname());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    userRepository.save(user);

    userRepository.flush();
    return user;
  }
}
