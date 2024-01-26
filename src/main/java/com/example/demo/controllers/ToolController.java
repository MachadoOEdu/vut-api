package com.example.demo.controllers;

import com.example.demo.models.Tool;
import com.example.demo.services.ToolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    private final ToolService toolService;

    @Autowired
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @PostMapping
    public ResponseEntity<Tool> createTool(@RequestBody @Valid Tool tool) {
        if (tool.getId() != 0 && toolService.findToolById(tool.getId()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Tool savedTool = toolService.saveTool(tool);
        return new ResponseEntity<>(savedTool, HttpStatus.CREATED);
    }


    @GetMapping
    public List<Tool> getAllTools() {
        return toolService.findAllTools();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tool> getToolById(@PathVariable Long id) {
        return toolService.findToolById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tool> updateTool(@PathVariable Long id, @RequestBody Tool tool) {
        if (toolService.findToolById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Tool updatedTool = toolService.saveTool(tool);
        return ResponseEntity.ok(updatedTool);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tool> modifyTool(@PathVariable Long id, @RequestBody Tool toolUpdates) {
        return toolService.findToolById(id)
                .map(existingTool -> {
                    existingTool.setTitle(toolUpdates.getTitle());
                    if (toolUpdates.getLink() != null) {
                        existingTool.setLink(toolUpdates.getLink());
                    }
                    existingTool.setDescription(toolUpdates.getDescription());
                    if (toolUpdates.getTags() != null) {
                        existingTool.setTags(toolUpdates.getTags());
                    }
                    Tool updatedTool = toolService.saveTool(existingTool);
                    return new ResponseEntity<>(updatedTool, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTool(@PathVariable Long id) {
        if (toolService.findToolById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        toolService.deleteTool(id);
        return ResponseEntity.noContent().build();
    }
}
