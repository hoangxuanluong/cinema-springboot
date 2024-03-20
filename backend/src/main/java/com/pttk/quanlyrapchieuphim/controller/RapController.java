package com.pttk.quanlyrapchieuphim.controller;

import com.pttk.quanlyrapchieuphim.entity.Rap;
import com.pttk.quanlyrapchieuphim.service.RapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RapController {

    @Autowired
    RapService rapService;

    @RequestMapping(method = RequestMethod.POST, value = "/raps")
    public Rap saveRap(@RequestBody Rap rap) {
        return rapService.saveRap(rap);
    }

    @GetMapping("/raps")
    public List<Rap> getListRap() {
        return rapService.getListRap();
    }

    @GetMapping("/raps/{id}")
    public Rap getRapById(@PathVariable("id") Long rapId) {
        return rapService.getRapById(rapId);
    }

    @DeleteMapping("/raps/{id}")
    public String deleteRapById(@PathVariable("id") Long rapId) {
        rapService.deleteRapById(rapId);
        return "delete rap successfully";
    }

    @PutMapping("/raps/{id}")
    public Rap updateRap(@PathVariable("id") Long rapId,
                         @RequestBody Rap rap) {
        return rapService.updateRap(rapId, rap);
    }

}
