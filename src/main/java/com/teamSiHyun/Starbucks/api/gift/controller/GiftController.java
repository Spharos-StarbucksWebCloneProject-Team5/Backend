package com.teamSiHyun.Starbucks.api.gift.controller;

import com.teamSiHyun.Starbucks.api.gift.service.IGiftService;
import com.teamSiHyun.Starbucks.api.gift.vo.RequestCreateGift;
import com.teamSiHyun.Starbucks.api.gift.vo.ResponseGift;
import com.teamSiHyun.Starbucks.api.gift.vo.RequestUpdateGift;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/gift")
@RequiredArgsConstructor
public class GiftController {

    private final IGiftService iGiftService;

    @Operation(summary = "선물 데이터 요청", description = "ID에 해당하는 선물데이터 가져오기", tags = {"선물"})
    @GetMapping("/{id}")
    public @ResponseBody ResponseGift getGift(@PathVariable Long id) {
        return iGiftService.getGift(id);
    }

    @Operation(summary = "선물하기 요청", description = "요청한 데이터로 선물하기", tags = {"선물"})
    @PostMapping("")
    public @ResponseBody ResponseGift addGift(@RequestBody RequestCreateGift requestCreateGift) {
        return iGiftService.addGift(requestCreateGift);
    }

    @Operation(summary = "선물 데이터 수정", description = "요청한 선물 데이터 수정", tags = {"선물"})
    @PutMapping("/{id}")
    public void updateGift(@PathVariable Long id, @RequestBody RequestUpdateGift requestGift) {
        iGiftService.updateGift(id, requestGift);
    }

    @Operation(summary = "선물 데이터 삭제", description = "선물 데이터 삭제", tags = {"선물"})
    @DeleteMapping("{id}")
    public void deleteGift(@PathVariable Long id) {
        iGiftService.deleteGift(id);
    }
}
