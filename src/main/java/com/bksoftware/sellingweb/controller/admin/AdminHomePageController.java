package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.homepage.FooterMenu;
import com.bksoftware.sellingweb.entities.homepage.FooterMenuDetails;
import com.bksoftware.sellingweb.entities.homepage.HeaderMenu;
import com.bksoftware.sellingweb.entities.homepage.HomeImage;
import com.bksoftware.sellingweb.service_impl.homepage.FooterMenuService_Impl;
import com.bksoftware.sellingweb.service_impl.homepage.HeaderMenuService_Impl;
import com.bksoftware.sellingweb.service_impl.homepage.HomeImageService_Impl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping("/api/v1/admin")
public class AdminHomePageController {

    private final HeaderMenuService_Impl headerMenuService;
    private final FooterMenuService_Impl footerMenuService;
    private final HomeImageService_Impl homeImageService;

    public AdminHomePageController(HeaderMenuService_Impl headerMenuService, FooterMenuService_Impl footerMenuService, HomeImageService_Impl homeImageService) {
        this.headerMenuService = headerMenuService;
        this.footerMenuService = footerMenuService;
        this.homeImageService = homeImageService;
    }

    //------------------------------HEADER------------------------------------------------
    //add
    @PostMapping("/header-menu")
    public ResponseEntity<String> addHeaderMenu(@RequestBody HeaderMenu headerMenu) {
        headerMenu.setStatus(true);
        if (headerMenuService.saveHeaderMenu(headerMenu)) {
            return new ResponseEntity<>("save success", HttpStatus.OK);
        } else return new ResponseEntity<>("save error", HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping("/header-menu")
    public ResponseEntity<String> updateHeaderMenu(@RequestBody HeaderMenu headerMenu) {
        if (headerMenuService.saveHeaderMenu(headerMenu)) {
            return new ResponseEntity<>("update success", HttpStatus.OK);
        } else return new ResponseEntity<>("update error", HttpStatus.BAD_REQUEST);
    }

    //delete
    @PutMapping("/delete-header-menu")
    public ResponseEntity<String> deleteHeaderMenu(@RequestBody HeaderMenu headerMenu) {
        if (headerMenuService.deleteHeaderMenu(headerMenu)) {
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete error", HttpStatus.BAD_REQUEST);
    }

    //-----------------------------FOOTER------------------------------------------------
    //add
    @PostMapping("/footer-menu")
    public ResponseEntity<String> addFooterMenu(@RequestBody FooterMenu footerMenu) {
        footerMenu.setStatus(true);
        if (footerMenuService.saveFooterMenu(footerMenu)) {
            return new ResponseEntity<>("save success", HttpStatus.OK);
        } else return new ResponseEntity<>("save error", HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping("/footer-menu")
    public ResponseEntity<String> updateFooterMenu(@RequestBody FooterMenu footerMenu) {
        if (footerMenuService.saveFooterMenu(footerMenu)) {
            return new ResponseEntity<>("update success", HttpStatus.OK);
        } else return new ResponseEntity<>("update error", HttpStatus.BAD_REQUEST);
    }

    //delete
    @PutMapping("/delete-footer-menu")
    public ResponseEntity<String> deleteFooterMenu(@RequestBody FooterMenu footerMenu) {
        if (footerMenuService.deleteFooterMenu(footerMenu)) {
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete error", HttpStatus.BAD_REQUEST);
    }

    //-------------------------FOOTER DETAILS-------------------------------------------
    //add
    @PostMapping(value = "/footer-menu-details", params = "footer-menu-id")
    public ResponseEntity<String> addFooterMenuDetails(@RequestBody FooterMenuDetails footerMenuDetails,
                                                       @RequestParam(name = "footer-menu-id") int footerMenuId) {
        footerMenuDetails.setStatus(true);
        footerMenuDetails.setFooterMenu(footerMenuService.findById(footerMenuId));
        if (footerMenuService.saveFooterMenuDetails(footerMenuDetails)){
            return new ResponseEntity<>("save success", HttpStatus.OK);
        } else return new ResponseEntity<>("save error", HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping(value = "/footer-menu-details")
    public ResponseEntity<String> updateFooterMenuDetails(@RequestBody FooterMenuDetails footerMenuDetails) {
        if (footerMenuService.saveFooterMenuDetails(footerMenuDetails)) {
            return new ResponseEntity<>("update success", HttpStatus.OK);
        } else return new ResponseEntity<>("update error", HttpStatus.BAD_REQUEST);
    }

    //delete
    @PutMapping(value = "/delete-footer-menu-details")
    public ResponseEntity<String> deleteFooterMenuDetails(@RequestBody FooterMenuDetails footerMenuDetails) {
        if (footerMenuService.deleteFooterMenuDetails(footerMenuDetails)) {
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete error", HttpStatus.BAD_REQUEST);
    }

    //----------------------------HOME IMAGE--------------------------------------------
    //add
    @PostMapping(value = "/home-image")
    public ResponseEntity<String> addHomeImage(@RequestBody HomeImage homeImage) {
        homeImage.setStatus(true);
        if (homeImageService.saveHomeImage(homeImage)) {
            return new ResponseEntity<>("save success", HttpStatus.OK);
        } else return new ResponseEntity<>("save error", HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping(value = "/home-image")
    public ResponseEntity<String> updateHomeImage(@RequestBody HomeImage homeImage) {
        if (homeImageService.saveHomeImage(homeImage)) {
            return new ResponseEntity<>("update success", HttpStatus.OK);
        } else return new ResponseEntity<>("update error", HttpStatus.BAD_REQUEST);
    }

    //add
    @PutMapping(value = "/delete-home-image")
    public ResponseEntity<String> deleteHomeImage(@RequestBody HomeImage homeImage) {
        if (homeImageService.deleteHomeImage(homeImage)) {
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete error", HttpStatus.BAD_REQUEST);
    }
}

