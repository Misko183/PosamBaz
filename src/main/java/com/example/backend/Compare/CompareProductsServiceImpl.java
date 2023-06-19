package com.example.backend.Compare;

import com.example.backend.proSecurity.user.CurrentUser;
import com.example.backend.proSecurity.user.UserEntity;
import com.example.backend.proSecurity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompareProductsServiceImpl implements CompareProductsService{

    @Autowired
    private CompareProductsRepository compareProductsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addProductToCompare(CurrentUser currentUser, CompareProducts compareProducts) {

        UserEntity userEntity = userRepository.findByUsernameIgnoreCase(currentUser.getUsername());
        CompareProducts currentCompareProducts = compareProductsRepository.findByUserEntity(userEntity);

       if (currentCompareProducts != null) {
           if(compareProducts.getProduct1().getId() != currentCompareProducts.getProduct1().getId()){
           currentCompareProducts.setProduct2(currentCompareProducts.getProduct1());
           currentCompareProducts.setProduct1(compareProducts.getProduct1());
           compareProductsRepository.save(currentCompareProducts);
       }}
         else {
              compareProducts.setUserEntity(userEntity);
              compareProductsRepository.save(compareProducts);
         }

    }

    @Override
    public CompareProducts getCompareProducts(CurrentUser currentUser) {
        UserEntity userEntity = userRepository.findByUsernameIgnoreCase(currentUser.getUsername());
        return compareProductsRepository.findByUserEntity(userEntity);
    }
}
