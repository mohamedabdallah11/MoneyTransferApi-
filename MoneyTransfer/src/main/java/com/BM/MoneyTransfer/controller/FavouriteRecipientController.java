package com.BM.MoneyTransfer.controller;

import com.BM.MoneyTransfer.dto.FavouriteRecipientDto;
import com.BM.MoneyTransfer.dto.ViewUserDTO;
import com.BM.MoneyTransfer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FavouriteRecipientController {

    private UserService userService;

    @Autowired
    public FavouriteRecipientController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/favorites")
    public void addFavouriteRecipient(@RequestBody FavouriteRecipientDto favouriteRecipientDto) {
        userService.addFavorite(favouriteRecipientDto.getRecipientEmail());
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<ViewUserDTO>> getFavouriteRecipients() {
        return ResponseEntity.ok().body(userService.findFavorite());
    }

    @DeleteMapping("/favorites/{userEmail}")
    public void removeFavouriteRecipient(@PathVariable String userEmail) {
        userService.removeFavorite(userEmail);
    }
}
