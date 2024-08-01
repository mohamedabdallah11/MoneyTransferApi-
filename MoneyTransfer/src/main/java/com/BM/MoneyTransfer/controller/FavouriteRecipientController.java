//package com.BM.MoneyTransfer.controller;
//
//import com.BM.MoneyTransfer.dto.FavouriteRecipientDto;
//import com.BM.MoneyTransfer.entity.FavouriteRecipient;
//import com.BM.MoneyTransfer.service.FavouriteRecipientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class FavouriteRecipientController {
//
//    @Autowired
//    private FavouriteRecipientService favouriteRecipientService;
//
//    @PostMapping("/favorites")
//    public ResponseEntity<FavouriteRecipient> addFavouriteRecipient(@RequestBody FavouriteRecipientDto favouriteRecipientDto) {
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/favorites")
//    public ResponseEntity<List<FavouriteRecipient>> getFavouriteRecipients(@RequestParam String userEmail) {
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/favorites/{userEmail2}")
//    public ResponseEntity<Void> removeFavouriteRecipient(@RequestParam String userEmail1, @PathVariable String userEmail2) {
//        return ResponseEntity.noContent().build();
//    }
//}
