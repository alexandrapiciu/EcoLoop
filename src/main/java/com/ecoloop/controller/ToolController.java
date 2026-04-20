package com.ecoloop.controller;

import com.ecoloop.model.Category;
import com.ecoloop.model.Tool;
import com.ecoloop.service.ToolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

// Handles browsing tools, viewing a single tool, and listing a new tool.
@Controller
@RequestMapping("/tools")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    // Browse + search page. Query params are optional.
    @GetMapping
    public String browse(@RequestParam(required = false) String query,
                         @RequestParam(required = false) String category,
                         Model model) {
        model.addAttribute("tools", toolService.search(query, category));
        model.addAttribute("query", query == null ? "" : query);
        model.addAttribute("selectedCategory", category == null ? "" : category);
        model.addAttribute("categories", Category.values());
        return "tools";
    }

    // Form to list a new tool. Must be mapped before "/{id}" to avoid conflict.
    @GetMapping("/new")
    public String newToolForm(Model model) {
        model.addAttribute("tool", new Tool());
        model.addAttribute("categories", Category.values());
        return "new-tool";
    }

    // Handles submission of the new-tool form. Just adds to the in-memory list.
    @PostMapping("/new")
    public String createTool(@ModelAttribute Tool tool) {
        toolService.add(tool);
        return "redirect:/tools";
    }

    // Tool details page with a basic booking area.
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        Tool tool = toolService.findById(id).orElse(null);
        if (tool == null) {
            return "redirect:/tools";
        }
        LocalDate today = LocalDate.now();
        model.addAttribute("tool", tool);
        model.addAttribute("today", today.toString());
        model.addAttribute("suggestedEnd", today.plusDays(3).toString());
        // Mock list of available dates just for display purposes.
        model.addAttribute("mockAvailableDates", java.util.List.of(
                today.plusDays(1).toString(),
                today.plusDays(2).toString(),
                today.plusDays(3).toString(),
                today.plusDays(4).toString(),
                today.plusDays(5).toString()
        ));
        return "tool-details";
    }
}
