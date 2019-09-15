package io.github.xisyn.restApp.controller;

import io.github.xisyn.restApp.controller.dto.JournalEntityDTO;
import io.github.xisyn.restApp.controller.dto.JournalItemDTO;
import io.github.xisyn.restApp.controller.dto.JournalRequestDTO;
import io.github.xisyn.restApp.controller.dto.JournalResultDTO;
import io.github.xisyn.restApp.service.JournalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/journal")
public class JournalRestController {

    private final JournalService journalService;

    public JournalRestController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("{id}")
    public JournalEntityDTO getJournalEntity(@PathVariable String id){
        return new JournalEntityDTO(journalService.getJournal(id));
    }

    @PutMapping("{id}/rows")
    public JournalResultDTO getRows(@PathVariable String id, @RequestBody JournalRequestDTO req) {
        List<? extends JournalItemDTO> collect = journalService.getJournalRows(id, req);
        return new JournalResultDTO(collect.size(), collect);
    }
}
