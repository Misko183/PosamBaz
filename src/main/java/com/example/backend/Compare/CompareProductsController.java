package com.example.backend.Compare;

import com.example.backend.proSecurity.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompareProductsController {

    @Autowired
    private CompareProductsServiceImpl compareProductsService;

    @PostMapping("/addProductToCompare")
    public void addProductToCompare(@RequestBody CompareProducts compareProducts, @AuthenticationPrincipal CurrentUser currentUser) {
        compareProductsService.addProductToCompare(currentUser, compareProducts);
    }

    @GetMapping("/getCompareProducts")
    public CompareProducts getCompareProducts(@AuthenticationPrincipal CurrentUser currentUser) {
        return compareProductsService.getCompareProducts(currentUser);
    }

}
