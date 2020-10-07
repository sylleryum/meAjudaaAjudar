package com.sylleryum.meajudaaajudar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiUserService implements UserDetailsService {

    @Autowired
    ApiUserRepository apiUserRepository;

//    public Optional<User> findByToken(String token) {
//        Optional<ApiUser> customer= customerRepository.findByToken(token);
//        if(customer.isPresent()){
//            ApiUser customer1 = customer.get();
//            User user= new User(customer1.getUserName(), customer1.getPassword(), true, true, true, true,
//                    AuthorityUtils.createAuthorityList("USER"));
//            return Optional.of(user);
//        }
//        return  Optional.empty();
//    }


    public Optional<ApiUserEntity> findByUsername(String username) {
        return apiUserRepository.findByUsername(username);
    }

    public ApiUserEntity findById(Long id) {
        Optional<ApiUserEntity> apiUser= apiUserRepository.findById(id);
        return apiUser.orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return apiUserRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(String.format("Username %s not found", username)));
    }
}
