package com.example.demo.services;

import com.example.demo.models.Tool;
import com.example.demo.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolService {

    private final ToolRepository toolRepository;

    @Autowired
    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public Tool saveTool(Tool tool) {
        return toolRepository.save(tool);
    }

    public void deleteTool(Long id) {
        toolRepository.deleteById(id);
    }

    public Optional<Tool> findToolById(Long id) {
        return toolRepository.findById(id);
    }

    public List<Tool> findAllTools() {
        return toolRepository.findAll();
    }

}
