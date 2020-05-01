package hu.unideb.inf.Service;

import hu.unideb.inf.Entity.Role;
import hu.unideb.inf.Entity.User;
import hu.unideb.inf.Repository.RoleRepository;
import hu.unideb.inf.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepo;
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setRepos(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepo.findUserByUserName(userName);
    }

    public String registerUser(User userToRegister) {
        User userNameCheck = userRepo.findUserByUserName(userToRegister.getUserName());
        User emailCheck = userRepo.findUserByEmail(userToRegister.getEmail());

        if ((userNameCheck != null) || (emailCheck != null))
            return "alreadyExists";

        Role userRole = roleRepo.findByRole("USER");
        if (userRole != null) {
            userToRegister.getRoles().add(userRole);
        } else {
            userToRegister.addRole("USER");
        }
        userToRegister.setPassword(passwordEncoder.encode(userToRegister.getPassword()));
        userRepo.save(userToRegister);
        return "ok";
    }
}
