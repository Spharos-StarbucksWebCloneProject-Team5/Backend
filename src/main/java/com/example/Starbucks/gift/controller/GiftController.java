package com.example.Starbucks.gift.controller;

import com.example.Starbucks.gift.service.IGiftService;
import com.example.Starbucks.gift.vo.RequestCreateGift;
import com.example.Starbucks.gift.vo.RequestUpdateGift;
import com.example.Starbucks.gift.vo.ResponseGift;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/gift")
@RequiredArgsConstructor
public class GiftController {

    private final IGiftService iGiftService;

    @GetMapping("/{id}")
    public @ResponseBody ResponseGift getGift(@PathVariable Long id) {
        return iGiftService.getGift(id);
    }

    @PostMapping("")
    public @ResponseBody ResponseGift addGift(@RequestBody RequestCreateGift requestCreateGift) {
        return iGiftService.addGift(requestCreateGift);
    }

    @PutMapping("/{id}")
    public void updateGift(@PathVariable Long id, @RequestBody RequestUpdateGift requestGift) {
        iGiftService.updateGift(id, requestGift);
    }

    @DeleteMapping("{id}")
    public void deleteGift(@PathVariable Long id) {
        iGiftService.deleteGift(id);
    }
}
