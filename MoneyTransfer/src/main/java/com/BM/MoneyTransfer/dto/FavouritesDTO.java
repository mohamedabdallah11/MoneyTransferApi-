package com.BM.MoneyTransfer.dto;

import com.BM.MoneyTransfer.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouritesDTO {
    private List<ViewUserDTO> favourites;
}