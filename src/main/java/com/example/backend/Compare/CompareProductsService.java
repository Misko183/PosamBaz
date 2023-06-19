package com.example.backend.Compare;

import com.example.backend.proSecurity.user.CurrentUser;
import com.example.backend.proSecurity.user.UserEntity;

import java.util.List;

public interface CompareProductsService {

    void addProductToCompare(CurrentUser currentUser, CompareProducts compareProducts);

    CompareProducts getCompareProducts(CurrentUser currentUser);
}
